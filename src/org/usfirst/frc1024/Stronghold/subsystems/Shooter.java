// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1024.Stronghold.subsystems;
import org.usfirst.frc1024.Stronghold.Robot;
import org.usfirst.frc1024.Stronghold.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


/**
 *
 */
public class Shooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Solenoid one = RobotMap.launcherOne;
    private final Solenoid two = RobotMap.launcherTwo;
    /*private final Solenoid three = RobotMap.launcherThree;
    private final Solenoid four = RobotMap.launcherFour;
    */

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void up(){
    	one.set(true);
    	two.set(true);
    	/*three.set(true);
    	four.set(true);
    	*/
    }
    
    public void down(){
    	one.set(false);
    	two.set(false);
    	/*three.set(false);
    	four.set(false);
    	*/
    }
    public void autoShoot(){
    	boolean isDone = false;
    	double resolution = 69;
    	Robot.drivetrain.driveMotors(0.2, -0.2);
    	while(isDone == false && Robot.oi.logi.getButtonStart() == false){
    		try {
    			while(isDone == false && Robot.oi.logi.getButtonStart() == false){
    				NetworkTable table = NetworkTable.getTable("GRIP/myContoursReport");
    				double[] centerx = table.getNumberArray("centerX", new double[0]);
    				double centerxDouble = centerx[0];
    				if(centerxDouble < (resolution/2 + 5)){
    					Robot.drivetrain.driveMotors(0.2, -0.2);
    				}
    				if(centerxDouble > (resolution/2 - 5)){
    					Robot.drivetrain.driveMotors(-0.2, 0.2);
    				}
    				if(centerxDouble >= (resolution/2 -5) && centerxDouble <= (resolution/2 + 5)){
    					Robot.drivetrain.driveMotors(0, 0);
    					Timer.delay(5);
    					Robot.shooter.up();
    					isDone = true;
    				}
    			}
    		}
    		catch (ArrayIndexOutOfBoundsException NullPointerException){
    			Robot.drivetrain.driveMotors(0.2,  -0.2);
    		}
    	}
    }
    
    public void shoot(){
    	up();
    	Timer.delay(0.5);
    	down();
    }
}
    			
    			
    			
    			
    			
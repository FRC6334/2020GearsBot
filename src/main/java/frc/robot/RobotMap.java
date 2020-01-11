/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {
    
    //Motor numbers
    public static final int leftFrontMotor = 3;
    public static final int leftBackMotor = 4;
    public static final int rightFrontMotor = 1;
    public static final int rightBackMotor = 2;

    //Drive Train Power
    //0 = no power
    //0.5 = half power
    //1 = full power
    public static final double driveTrainPower = 0.5;

    //Default LimeLight Value
    public static final double defaultLimeLight = -999;

    //target height calculaitons
    public static final double heightOfTarget = 115.625;   //(h2) The height of the target 
    public static final double heightOfLimeLight = 36.125;   //(h1) The height of your camera above the floor
    public static final double angleOfLimeLight = 45; // in degrees
}

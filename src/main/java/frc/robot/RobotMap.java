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
    public static final double driveTrainPower = 0.8;

    //Motor rotations per inch for DriveInInches
    public static final double roations_per_turn = 10.58;
    public static final double rotations_per_inch = 0.5629;
    public static final double din_power = 0.5;

    //Default LimeLight Value
    public static final double defaultLimeLight = -999;

    //target height calculaitons
    public static final double heightOfTarget = 115.625;   //(h2) The height of the target 
    public static final double heightOfLimeLight = 36.125;   //(h1) The height of your camera above the floor
    public static final double angleOfLimeLight = 45; // in degrees
    public static final double shoot_distance = 60; //how far to shoot from
    public static final double x_speed = 0.8; //drive to target right/left speed
    public static final double y_speed = 0.8; //drive to trget forward/back up speed
    public static final double x_flex = 2; //amount of acceptable error in degrees when lining up on the X axis

    //color wheel colors
    public static final int red = 0;
    public static final int green = 1;
    public static final int blue = 2;
    public static final int yellow = 3;
    public static final int unknown = -1;

    //limelight ledmode
    public static final int ll_current = 0;
    public static final int ll_off = 1;
    public static final int ll_blink = 2;
    public static final int ll_on = 3;

      //limelight cam mode
      public static final int ll_vision = 0;
      public static final int ll_driver = 1;
}


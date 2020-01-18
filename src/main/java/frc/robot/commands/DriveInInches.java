/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;

public class DriveInInches extends InstantCommand {
  private DriveTrain drive_train;
  private double inches_or_angle = 0;
  private String direction = "F";

  /**
   * Creates a new resetEncoderDistance.
   * F = forward
   * B = backwards
   * R = right turn
   * L = left turn
   * 
   * If forward or backwards, inches = number of inches
   * If right or left, inches = degrees of turn
   */
  public DriveInInches(DriveTrain dt, double _inches_or_angle, String _direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive_train = dt;
    inches_or_angle = _inches_or_angle;
    direction = _direction;
  }

  @Override
  public void initialize() {
    System.out.println("begin ("+direction+"), inches="+inches_or_angle);
    drive_train.resetEncoders();
    Timer t = new Timer();
    t.start();
    while (t.get() < 0.08);
    t.stop();

    //go forward
    if (direction.equals("F")) {
      driveForward(); 
    }
    //go backwards
    else if (direction.equals("B")){
      driveBackward();
    }
    //turn right
    else if (direction.equals("R")){
      turnRight();
    }
    //turn left
    else if (direction.equals("L")){
      turnLeft();
    }

    System.out.println("end ("+direction+"), inches="+inches_or_angle);
  }

  private void driveForward() {
    System.out.println("FWD encoder R:"+drive_train.getRightEncoderDistance()+",L:"+drive_train.getLeftEncoderDistance());
    double travled =0;
    double target  = inches_or_angle*RobotMap.rotations_per_inch;
    while(travled<=target){
      drive_train.drive(-RobotMap.din_power, 0);
      travled = drive_train.getDistance();
    }
    System.out.println("F:"+travled+" of "+target);
    drive_train.resetEncoders();
  }

  private void driveBackward() {
    System.out.println("REV encoder R:"+drive_train.getRightEncoderDistance()+",L:"+drive_train.getLeftEncoderDistance());
    double travled =0;
    double target  = inches_or_angle*RobotMap.rotations_per_inch;
    while(travled<=target){
        drive_train.drive(RobotMap.din_power, 0);
        travled = Math.abs(drive_train.getDistance());
    }
    System.out.println("B:"+travled+" of "+target);
    drive_train.resetEncoders();
  }

  private void turnRight() {
    //10.58 = 90 degree turn
    //0.11755556 rotations per degree 
    double rotate = RobotMap.roations_per_angle * inches_or_angle;
    System.out.println("turn right:"+rotate);
    while(drive_train.getRightEncoderDistance()<rotate)
      drive_train.drive(0,RobotMap.din_power); 
    System.out.println("R:"+drive_train.getRightEncoderDistance()+", L:"+drive_train.getLeftEncoderDistance());
  }

  private void turnLeft() {
    //10.58 = 90 degree turn
    //0.11755556 rotations per degree 
    double rotate = RobotMap.roations_per_angle * inches_or_angle;
    while(Math.abs(drive_train.getRightEncoderDistance())<rotate)
      drive_train.drive(0,-RobotMap.din_power); 
  }

}
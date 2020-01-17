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

public class DriveInInches extends InstantCommand {
  DriveTrain drive_train;
  double inches = 0;
  String direction = "F";
  /**
   * Creates a new resetEncoderDistance.
   * F = forward
   * B = backwards
   * R = right turn
   * L = left turn
   * G = group commmand
   */
  public DriveInInches(DriveTrain dt, double _inches, String _direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive_train = dt;
    this.inches = _inches;
    this.direction = _direction;
  }

  public DriveInInches(DriveTrain dt, double _inches) {
    this(dt, _inches, "F");
  }

  public DriveInInches(DriveTrain dt, String _direction) {
    this(dt, 0, _direction);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.drive_train.reset();
    
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
    else if (direction.equals("G")) {
      inches = 24;
      driveForward();
      turnRight();
      inches = 24;
      driveForward();
    }
  }

  private void driveForward() {
    this.drive_train.reset();
    double distance =0;
    double target  = inches*RobotMap.rotations_per_inch;
    while(distance<=target){
      drive_train.drive(-0.4, 0);
      distance = drive_train.getDistance();
    }
  }

  private void driveBackward() {
    this.drive_train.reset();
    double distance =0;
    double target  = inches*RobotMap.rotations_per_inch;
      while(distance<=target){
        drive_train.drive(0.4, 0);
        distance = Math.abs(drive_train.getDistance());
      }
  }

  private void turnRight() {
    this.drive_train.reset();
    while(drive_train.getRightEncoderDistance()<10.6104)
      drive_train.drive(0,0.4);
  }

  private void turnLeft() {
    this.drive_train.reset();
    while(Math.abs(drive_train.getRightEncoderDistance())<10.6104)
      drive_train.drive(0,-0.4);
  }
}
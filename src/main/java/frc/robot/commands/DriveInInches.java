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
  private DriveTrain drive_train;
  private double inches = 0;
  private String direction = "F";

  /**
   * Creates a new resetEncoderDistance.
   * F = forward
   * B = backwards
   * R = right turn
   * L = left turn
   */
  public DriveInInches(DriveTrain dt, double _inches, String _direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive_train = dt;
    inches = _inches;
    direction = _direction;
  }

  public DriveInInches(DriveTrain dt, double _inches) {
    this(dt, _inches, "F");
  }

  public DriveInInches(DriveTrain dt, String _direction) {
    this(dt, 0, _direction);
  }


  @Override
  public void initialize() {
    //go forward
    if (direction.equals("F")) {
      driveForward(inches); 
    }
    //go backwards
    else if (direction.equals("B")){
      driveBackward(inches);
    }
    //turn right
    else if (direction.equals("R")){
      turnRight();
    }
    //turn left
    else if (direction.equals("L")){
      turnLeft();
    }
}

  private void driveForward(double _inches) {
    System.out.println("begin drive forward, inches="+_inches);
    drive_train.reset();
    double travled =0;
    double target  = _inches*RobotMap.rotations_per_inch;
    while(travled<=target){
      drive_train.drive(-RobotMap.din_power, 0);
      travled = drive_train.getDistance();
    }
    System.out.println("end drive forward, inches="+_inches);
  }

  private void driveBackward(double _inches) {
    System.out.println("begin drive backward, inches="+_inches);
    drive_train.reset();
    double travled =0;
    double target  = _inches*RobotMap.rotations_per_inch;
    while(travled<=target){
        drive_train.drive(RobotMap.din_power, 0);
        travled = Math.abs(drive_train.getDistance());
    }
    System.out.println("end drive backward, inches="+_inches);
  }

  private void turnRight() {
    System.out.println("begin turn right");
    drive_train.reset();
    while(drive_train.getRightEncoderDistance()<10.6104)
      drive_train.drive(0,RobotMap.din_power);
    System.out.println("end turn right");
  }

  private void turnLeft() {
    System.out.println("begin turn left");
    drive_train.reset();
    while(Math.abs(drive_train.getRightEncoderDistance())<10.6104)
      drive_train.drive(0,-RobotMap.din_power);
    System.out.println("end turn left");
  }
}
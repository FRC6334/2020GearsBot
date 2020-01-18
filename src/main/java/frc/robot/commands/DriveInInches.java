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
    System.out.println("begin ("+direction+"), inches="+inches);
    drive_train.resetEncoders();
    Timer t = new Timer();
    t.start();
    /*while (t.get() < 2) {
        if (t.get() == 1 || t.get() == 1.9) drive_train.resetEncoders();
        if (drive_train.getRightEncoderDistance() == 0 && drive_train.getLeftEncoderDistance() == 0)
          break;
    }*/
    while (t.get() < 0.5) ;
    t.stop();

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

    System.out.println("end ("+direction+"), inches="+inches);
  }

  private void driveForward(double _inches) {
    System.out.println("FWD encoder R:"+drive_train.getRightEncoderDistance()+",L:"+drive_train.getLeftEncoderDistance());
    double travled =0;
    double target  = _inches*RobotMap.rotations_per_inch;
    while(travled<=target){
      drive_train.drive(-RobotMap.din_power, 0);
      travled = drive_train.getDistance();
    }
    System.out.println("F:"+travled+" of "+target);
    drive_train.resetEncoders();
  }

  private void driveBackward(double _inches) {
    System.out.println("REV encoder R:"+drive_train.getRightEncoderDistance()+",L:"+drive_train.getLeftEncoderDistance());
    double travled =0;
    double target  = _inches*RobotMap.rotations_per_inch;
    while(travled<=target){
        drive_train.drive(RobotMap.din_power, 0);
        travled = Math.abs(drive_train.getDistance());
    }
    System.out.println("B:"+travled+" of "+target);
    drive_train.resetEncoders();
  }

  private void turnRight() {
    while(drive_train.getRightEncoderDistance()<RobotMap.roations_per_turn)
      drive_train.drive(0,RobotMap.din_power);
  }

  private void turnLeft() {
    while(Math.abs(drive_train.getRightEncoderDistance())<RobotMap.roations_per_turn)
      drive_train.drive(0,-RobotMap.din_power);
  }
}
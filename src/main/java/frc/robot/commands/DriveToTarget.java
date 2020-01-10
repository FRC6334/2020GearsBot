/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLightVision;
import frc.robot.subsystems.DriveTrain;

public class DriveToTarget extends CommandBase {
  LimeLightVision lime_light;
  DriveTrain drive_train;
  private double x_speed = 0.8;
  private double y_speed = 0.5;

  /**
   * Creates a new DriveToTarget.
   */
  public DriveToTarget(LimeLightVision m_lime, DriveTrain m_drive) {
    // Use addRequirements() here to declare subsystem dependencies    
    lime_light = m_lime;
    drive_train = m_drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double ta = lime_light.getTA();
      double tx = lime_light.getTX();
      
      //move up to target and correct X by moving robot to left
      if (ta < 11 && tx < 1) {
        drive_train.drive(-x_speed, -y_speed);
      } 
      //move up to target and correct X by moving robot to right
      else if (ta < 11 && tx > 1) {
        drive_train.drive(-x_speed, y_speed);
      } 
      //move up to target and stay on course with X axis
      else if (ta < 11 && tx == 0) {
        drive_train.drive(-x_speed, 0);
      } 
      //too close to target move back
      else if (ta > 13) {
        drive_train.drive(x_speed, 0);
      } 

      System.out.println("TA="+ta);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //drive_train.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //drive_train.drive(0, 0);
    return true;
  }
}

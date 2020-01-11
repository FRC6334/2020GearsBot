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
  private double x_speed = 1;
  private double y_speed = 0.5;
  private double x_flex = 4;

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
      double tv = lime_light.getTV();
      double ts = lime_light.getTS();

      //spin around and look for a target
      while (tv == 0) {
        drive_train.drive(0, 1.2);
        tv = lime_light.getTV();
      }
      
      if (tx <= -x_flex) {
        drive_train.drive(0, -y_speed);
        alignReport(7, ta, tx, ts);
      }
      else if (tx >= x_flex) {
        drive_train.drive(0, y_speed);
        alignReport(8, ta, tx, ts);
      }
      //move up to target and correct X by moving robot to left
      else if (ta < 11 && tx < x_flex && tv==1) {
        drive_train.drive(-x_speed, -y_speed);
        alignReport(1, ta, tx, ts);
      } 
      //move up to target and correct X by moving robot to right
      else if (ta < 11 && tx > x_flex && tv==1) {
        drive_train.drive(-x_speed, y_speed);
        alignReport(2, ta, tx, ts);
      } 
      //move up to target and stay on course with X axis
      else if (ta < 11 && tx == 0 && tv==1) {
        drive_train.drive(-x_speed, 0);
        alignReport(3, ta, tx,ts );
      } 
      //too close to target move back
      else if (ta > 13 && tv==1) {
        drive_train.drive(x_speed, 0);
        alignReport(4, ta, tx, ts);
      } 
  }

  private void alignReport(int id, double ta, double tx, double ts) {
    System.out.println(id+": TA="+ta+", TX="+tx+","+ts);
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

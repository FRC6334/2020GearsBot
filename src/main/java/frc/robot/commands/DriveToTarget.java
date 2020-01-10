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
  boolean m_finished;
  
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
    m_finished = false;    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double target = lime_light.getTA();

    while (target < 23) {
      drive_train.drive(-0.75, 0);
      target = lime_light.getTA();
      System.out.println(">>TA="+target);
    }
    drive_train.drive(0, 0);

    m_finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_finished;
  }
}

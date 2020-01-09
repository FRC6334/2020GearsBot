/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLightVision;

public class LimeLightCommands extends CommandBase {
  LimeLightVision lime_light;
  boolean m_finished;

  /**
   * Creates a new LimeLightCommands.
   */
  public LimeLightCommands(LimeLightVision m_lime) {
    // Use addRequirements() here to declare subsystem dependencies.
    lime_light = m_lime;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lime_light.outputLimeLightValues();
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

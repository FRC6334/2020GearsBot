/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimeLightVision;

public class GetLimeLightValues extends InstantCommand {
  LimeLightVision lime_light;

  /**
   * Creates a new GetLimeLightValues.
   */
  public GetLimeLightValues(LimeLightVision m_lime) {
    // Use addRequirements() here to declare subsystem dependencies.
    lime_light = m_lime;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lime_light.outputLimeLightValues();
  }
}

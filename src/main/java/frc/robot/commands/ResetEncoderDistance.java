/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ResetEncoderDistance extends InstantCommand {
  DriveTrain drive_train;

  /**
   * Creates a new resetEncoderDistance.
   */
  public ResetEncoderDistance(DriveTrain dt) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive_train = dt;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.drive_train.resetEncoders();
  }
}

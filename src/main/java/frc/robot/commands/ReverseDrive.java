/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotMap;
// import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.LimeLightVision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ReverseDrive extends InstantCommand {
  public enum Direction { FORWARD, REVERSE }
  private Direction dir;

  public ReverseDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (RobotMap.direction == 1) dir = Direction.REVERSE;
    else dir = Direction.FORWARD;

    if (dir == Direction.FORWARD) RobotMap.direction = 1;
    else RobotMap.direction = -1;
  }
}

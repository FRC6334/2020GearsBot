/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveInInches;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveInInchesGroup extends SequentialCommandGroup {
  /**
   * Creates a new DriveInInchesGroup.
   */
  public DriveInInchesGroup(DriveTrain dt) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //super();
    /*addCommands(new DriveInInches(dt, 24, "F"));
    addCommands(new DriveInInches(dt, 24, "B"));
    addCommands(new DriveInInches(dt, 45, "R"));
    addCommands(new DriveInInches(dt, 45, "R"));
    addCommands(new DriveInInches(dt, 45, "L"));
    addCommands(new DriveInInches(dt, 45, "L"));
    addCommands(new DriveInInches(dt, 12, "F"));
    addCommands(new DriveInInches(dt, 12, "B"));
    addCommands(new DriveInInches(dt, 180, "R"));
    addCommands(new DriveInInches(dt, 180, "R"));
    addCommands(new DriveInInches(dt, 180, "R"));
    addCommands(new DriveInInches(dt, 180, "R"));
    addCommands(new DriveInInches(dt, 360, "L"));
    addCommands(new DriveInInches(dt, 6, "F"));
    addCommands(new DriveInInches(dt, 6, "B"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));
    addCommands(new DriveInInches(dt, 36, "R"));*/
    addCommands(new DriveInInches(dt, 360, "R"));
    addCommands(new DriveInInches(dt, 360, "L"));
  }
}

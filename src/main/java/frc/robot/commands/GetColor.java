/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ColorSensor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class GetColor extends InstantCommand {
  private ColorSensor m_color_sensor;
  
  public GetColor(ColorSensor _s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_color_sensor = _s;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("getting color...");
    m_color_sensor.getColor();
  }
}

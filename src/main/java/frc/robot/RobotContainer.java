/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveInInches;
import frc.robot.commands.GetColorInformation;
import frc.robot.commands.ResetEncoderDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.USBCamera;
import frc.robot.subsystems.LimeLightVision;
import frc.robot.subsystems.ColorSensor;
import frc.robot.commands.GetLimeLightValues;
import frc.robot.commands.ToggleLimeLightLED;
import frc.robot.commands.ToggleLimeLightVision;
import frc.robot.commands.DriveToTarget;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final USBCamera m_camera = new USBCamera();
  private final LimeLightVision m_limelight = new LimeLightVision();
  private final ColorSensor m_color_sensor = new ColorSensor();
  private final Joystick m_joystick0 = new Joystick(0);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Put Some buttons on the SmartDashboard
    // Assign default commands
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_joystick0, m_drivetrain));

    // Show what command your subsystem is running on the SmartDashboard
    SmartDashboard.putData(m_drivetrain);
    SmartDashboard.putData(m_camera);

    // Call log method on all subsystems
    m_drivetrain.log();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton m_button02 = new JoystickButton(m_joystick0, 2);
    m_button02.whenPressed(new DriveInInches(m_drivetrain, 24, "G"));

    final JoystickButton m_button03 = new JoystickButton(m_joystick0, 3);
    m_button03.whileHeld(new DriveToTarget(m_limelight, m_drivetrain));

    final JoystickButton m_button06 = new JoystickButton(m_joystick0, 6);
    m_button06.whenPressed(new ToggleLimeLightVision(m_limelight));

    final JoystickButton m_button07 = new JoystickButton(m_joystick0, 7);
    m_button07.whenPressed(new ToggleLimeLightLED(m_limelight));

    final JoystickButton m_button09 = new JoystickButton(m_joystick0, 9);
    m_button09.whenPressed(new ResetEncoderDistance(m_drivetrain));

    final JoystickButton m_button10 = new JoystickButton(m_joystick0, 10);
    m_button10.whenPressed(new GetColorInformation(m_color_sensor));

    final JoystickButton m_button11 = new JoystickButton(m_joystick0, 11);
    m_button11.whenPressed(new GetLimeLightValues(m_limelight));
  }
}

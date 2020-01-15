/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
  /**
   * The DriveTrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  private final CANSparkMax leftFrontMotor = new CANSparkMax(RobotMap.leftFrontMotor, MotorType.kBrushless);
  private final CANSparkMax leftBackMotor = new CANSparkMax(RobotMap.leftBackMotor, MotorType.kBrushless);
  private final CANSparkMax rightFrontMotor = new CANSparkMax(RobotMap.rightFrontMotor, MotorType.kBrushless);
  private final CANSparkMax rightBackMotor = new CANSparkMax(RobotMap.rightBackMotor, MotorType.kBrushless);

  private final CANEncoder right_encoder = rightFrontMotor.getEncoder();
  private final CANEncoder left_encoder = leftFrontMotor.getEncoder();

  private final SpeedController m_leftMotor =
      new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
  private final SpeedController m_rightMotor =
      new SpeedControllerGroup(rightFrontMotor, rightBackMotor);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  /**
   * Create a new drive train subsystem.
   */
  public DriveTrain() {
    super();

    // Let's name the sensors on the LiveWindow
    addChild("Drive", m_drive);
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  public void log() {
    SmartDashboard.putNumber("Left Position", left_encoder.getPosition());
    SmartDashboard.putNumber("Right Distance", right_encoder.getPosition());
    SmartDashboard.putNumber("Left Speed", left_encoder.getVelocity());
    SmartDashboard.putNumber("Right Speed", right_encoder.getVelocity());
  }

  /**
   * Arcade style driving for the DriveTrain.
   *
   */
  public void drive(double y, double x) {
    m_drive.arcadeDrive((y*RobotMap.driveTrainPower*-1), (x*RobotMap.driveTrainPower));
  }

  /* not used but example of calling tank drive */
  public void TankDrive(double left, double right) {
    m_drive.tankDrive(left, right);
  }

  /**
   * Reset the robots sensors to the zero states.
   */
  public void reset() {
   
  }

  /**
   * Get the average distance of the encoders since the last reset.
   *
   * @return The distance driven (average of left and right encoders).
   */
  public double getDistance() {
    return (left_encoder.getPosition()+right_encoder.getPosition())/2;
  }
}

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
//import edu.wpi.first.wpilibj.SPI;
//import com.kauailabs.navx.frc.AHRS;

public class DriveTrain extends SubsystemBase {
  /**
   * The DriveTrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  //private final AHRS navx;

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

    //initialize NavX
    //navx = new AHRS(SPI.Port.kMXP);
    //navx.reset();

    //reset encoders to 0
    this.resetEncoders();

    // Let's name the sensors on the LiveWindow
    //addChild("Drive", m_drive);
  }

  //NAVX methods
  /*
  public float getNAVXDisplacementX() { return navx.getDisplacementX(); }
  public float getNAVXDisplacementY() { return navx.getDisplacementY(); }
  public float getNAVXDisplacementZ() { return navx.getDisplacementZ(); }
  public void  resetNAVX() { navx.resetDisplacement(); }
  public float getNAVXRoll() { return navx.getRoll(); }
  public float getNAVXPitch() { return navx.getPitch(); }
  */

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  public void log() {
    SmartDashboard.putNumber("Left Position", left_encoder.getPosition());
    SmartDashboard.putNumber("Right Position", right_encoder.getPosition());
    SmartDashboard.putNumber("Left Speed", left_encoder.getVelocity());
    SmartDashboard.putNumber("Right Speed", right_encoder.getVelocity());
  }

  /**
   * Arcade style driving for the DriveTrain.
   *
   */
  public void drive(double y, double x) {
    m_drive.arcadeDrive((y*RobotMap.driveTrainPower*-1), (x*RobotMap.driveTrainPower));
    /*System.out.println("roll/pitch="+getNAVXRoll()+","+getNAVXPitch()+
    "- NAVX (X/Y/Z):" + 
    getNAVXDisplacementX()+","+getNAVXDisplacementY()+","+getNAVXDisplacementZ());*/
  }

  /* not used but example of calling tank drive */
  /*
  public void TankDrive(double left, double right) {
    m_drive.tankDrive(left, right);
  } */

  /**
   * Reset the robots sensors to the zero states.
   */
  public synchronized void resetEncoders() {
    //System.out.println("Start Reset: L:"+left_encoder.getPosition()+",R:"+right_encoder.getPosition());
    left_encoder.setPosition(0.0000);
    right_encoder.setPosition(0.0000); 
    //resetNAVX();
    log();
    //System.out.println("End Reset: L:"+left_encoder.getPosition()+",R:"+right_encoder.getPosition());
  }

  /**
   * Get the average distance of the encoders since the last reset.
   * When the robot drives forward, the right value will be negative and
   * the left value will be positive.
   * @return The distance driven (average of left and right encoders).
   */
  public double getDistance() {
        return (Math.abs(left_encoder.getPosition())+Math.abs(right_encoder.getPosition()))/2;
  }

  public double getLeftEncoderDistance() { return left_encoder.getPosition(); }
  public double getRightEncoderDistance() { return right_encoder.getPosition(); }
}

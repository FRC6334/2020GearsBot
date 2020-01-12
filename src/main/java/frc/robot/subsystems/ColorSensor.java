/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;

public class ColorSensor extends SubsystemBase {
  /**
   * Creates a new ColorSensor.
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  public ColorSensor() {
    
  }

  public void getColor() {
    Color detectedColor = m_colorSensor.getColor();
    double IR = m_colorSensor.getIR();


    System.out.println("RED:" + detectedColor.red);
    System.out.println("Green:" + detectedColor.green);
    System.out.println("Blue:" + detectedColor.blue);

    if(detectedColor.red > 0.3 && detectedColor.red < detectedColor.green && detectedColor.blue < 0.3) {
      System.out.println("The color is YELLOW");
    }
    
    else if (detectedColor.red > detectedColor.blue && detectedColor.red > detectedColor.green) {
      System.out.println("The color is RED");
    } else if(detectedColor.green > detectedColor.red && detectedColor.green > detectedColor.blue) {
      System.out.println("The color is GREEN");
    } else if(detectedColor.blue > detectedColor.red && detectedColor.blue > detectedColor.green) {
      System.out.println("The color is BLUE");
    }  else {
      System.out.println("Color unknown...");
    }


    System.out.println("IR:" + m_colorSensor.getIR());
    System.out.println("Proximity:" + m_colorSensor.getProximity());    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

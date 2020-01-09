/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightVision extends SubsystemBase {
  NetworkTable nTable;
  NetworkTableEntry tx, ty, ta, tv, ts;

  /**
   * Creates a new LimeLightVision.
   */
  public LimeLightVision() {
    nTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public void outputLimeLightValues() {
      tx = nTable.getEntry("tx");
      ty = nTable.getEntry("ty");
      ta = nTable.getEntry("ta");
      tv = nTable.getEntry("tv");
      ts = nTable.getEntry("ts");
      System.out.println(">>>>>>>>>>>>>>tx="+tx.getDouble(-999)+", ty="+ty.getDouble(-999)+",ta="+ta.getDouble(-999)+",tv="+tv.getDouble(-999)+",ts="+ts.getDouble(-999));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

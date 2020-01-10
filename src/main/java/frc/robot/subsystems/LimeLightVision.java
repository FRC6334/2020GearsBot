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
import frc.robot.RobotMap;

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
      System.out.println(">>>>>>>>>>>>>>tx="+tx.getDouble(RobotMap.defaultLimeLight)+
        ", ty="+ty.getDouble(RobotMap.defaultLimeLight)+
        ",ta="+ta.getDouble(RobotMap.defaultLimeLight)+
        ",tv="+tv.getDouble(RobotMap.defaultLimeLight)+
        ",ts="+ts.getDouble(RobotMap.defaultLimeLight));


      //Calculate distance
      System.out.println("Distance to Target: "+this.getDistanceToTarget());
  }

  //not working
  public double getDistanceToTarget() {
    if (this.getTV() != 1.0) return RobotMap.defaultLimeLight;
    
    //d = (h2-h1) / tan(a1+a2)
    double heightOfTarget = RobotMap.heightOfTarget; //h2 
    double heightOfLimeLight = RobotMap.heightOfLimeLight; // h1
    double a2 = this.getTY(); // The limelight (or your vision system) can tell you the y angle to the target (a2).
    double a1 = RobotMap.angleOfLimeLight;   //lime light mounting angle is known (a1).

    System.out.println("a1="+a1+",a2="+a2);

    double hdiff =  heightOfTarget - heightOfLimeLight;
    double adiff =  Math.tan(a1+a2);

    return hdiff / adiff;
  }

  //X offset to center of target
  public double getTX() { return nTable.getEntry("tx").getDouble(RobotMap.defaultLimeLight); }

  //The limelight (or your vision system) can tell you the y angle to the target (a2).
  public double getTY() { return nTable.getEntry("ty").getDouble(RobotMap.defaultLimeLight); }

  //target area
  public double getTA() { return nTable.getEntry("ta").getDouble(RobotMap.defaultLimeLight); }

  //is a valid target - 1.0 means valid target
  public double getTV() { return nTable.getEntry("tv").getDouble(RobotMap.defaultLimeLight); }

  //target scew
  public double getTS() { return nTable.getEntry("ts").getDouble(RobotMap.defaultLimeLight); }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

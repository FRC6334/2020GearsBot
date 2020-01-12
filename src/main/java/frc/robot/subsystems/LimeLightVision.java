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

/*
API Info - http://docs.limelightvision.io/en/latest/networktables_api.html
tv	Whether the limelight has any valid targets (0 or 1)
tx	Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
ty	Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
ta	Target Area (0% of image to 100% of image)
ts	Skew or rotation (-90 degrees to 0 degrees)
tl	The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
tshort	Sidelength of shortest side of the fitted bounding box (pixels)
tlong	Sidelength of longest side of the fitted bounding box (pixels)
thor	Horizontal sidelength of the rough bounding box (0 - 320 pixels)
tvert	Vertical sidelength of the rough bounding box (0 - 320 pixels)
getpipe	True active pipeline index of the camera (0 .. 9)
camtran	Results of a 3D position solution, 6 numbers: Translation (x,y,y) Rotation(pitch,yaw,roll)
*/

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

      System.out.println("distance to target (inches): "+this.getDistanceToTarget());
  }

  //working within 1-2" of actual distance, a wider angle makes it less accurate
  public double getDistanceToTarget() {
    if (this.getTV() != 1.0) return RobotMap.defaultLimeLight;
    
    //d = (h2-h1) / tan(a1+a2)
    double heightOfTarget = RobotMap.heightOfTarget; //h2 
    double heightOfLimeLight = RobotMap.heightOfLimeLight; // h1
    double a2 = this.getTY(); // The limelight (or your vision system) can tell you the y angle to the target (a2).
    double a1 = RobotMap.angleOfLimeLight;   //lime light mounting angle is known (a1)

    //calculate distance
    return (heightOfTarget - heightOfLimeLight) / Math.tan(Math.toRadians(a1+a2));
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLightVision;
import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotMap;

public class DriveToTarget extends CommandBase {
  LimeLightVision lime_light;
  DriveTrain drive_train;

  /**
   * Creates a new DriveToTarget.
   */
  public DriveToTarget(LimeLightVision m_lime, DriveTrain m_drive) {
    // Use addRequirements() here to declare subsystem dependencies    
    lime_light = m_lime;
    drive_train = m_drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      //check if we can see a target
      double tv = lime_light.getTV();  

      //spin around and look for a target
      while (tv == 0) {
        drive_train.drive(0, 0.4);
        tv = lime_light.getTV();
      }

      //once we find a target get the distance and X offset
      double dist = lime_light.getDistanceToTarget();  
      double tx = lime_light.getTX();

      //calculate speed as a function of distance to shooting distance
      double speed_adj = -Math.exp(-((dist-RobotMap.shoot_distance)-46)/10)+100;
      speed_adj *= RobotMap.y_speed / 100;
      //if (speed_adj < 0.5) speed_adj = 0.3;
      //if (speed_adj > 1.0) speed_adj = 1.0;

      //calculate center adjustment as a function of motor power to X offset
      //the larger the X offset the more motor power to turn and get back to x = 0
      double center_adj = Math.pow(tx/5, 2);
      center_adj *= RobotMap.x_speed / 10;
      //if (center_adj < 0.5) center_adj = 0.3;
      //if (center_adj > 1.0) center_adj = 1.0;

      //move to the target at the proper forward speed and X center adjustment speed
      if (dist > RobotMap.shoot_distance && tv==1) {
        if (tx < 0) {
           drive_train.drive(-speed_adj, -center_adj);
           alignReport(10, tv, tx, -speed_adj, -center_adj, dist);

        }
        else {
           drive_train.drive(-speed_adj, center_adj);
           alignReport(10, tv, tx, -speed_adj, center_adj, dist);
        }
      }
      //too close to target move back
     /* else if (tv==1 && (dist <= RobotMap.shoot_distance-2.5 || Math.abs(tx) > RobotMap.x_flex+2)) {
        drive_train.drive(RobotMap.y_speed, 0);
        alignReport(20, tv, RobotMap.y_speed, 0, dist);
      } */
  }

  private void alignReport(int id, double tv, double tx, double speed, double turn, double dist) {
    System.out.println(id+": TV="+tv+", TX="+tx+", Yspeed="+speed+", Xspeed="+turn+", Dist="+dist);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}


/*OLD MOVE TO TARGET CODE - DELETE ONCE NEW CODE WORKS
      too far right of target, just spin left
      if (tx <= -RobotMap.x_flex) {
        drive_train.drive(0, -RobotMap.x_speed);
        alignReport(7, tv, tx, dist);
      }
      //too far left of target, just spin right
      else if (tx >= RobotMap.x_flex) {
        drive_train.drive(0, RobotMap.x_speed);
        alignReport(8, tv, tx, dist);
      }
      //move up to target and correct X by moving robot to left
      else if (dist > RobotMap.shoot_distance+2 && tx<0 && tv==1) {
        drive_train.drive(-speed_adj, -RobotMap.x_speed);
        alignReport(1, tv, tx, dist);
      } 
      //move up to target and correct X by moving robot to right
      else if (dist > RobotMap.shoot_distance+2 && tx>0 && tv==1) {
        drive_train.drive(-speed_adj, RobotMap.x_speed);
        alignReport(2, tv, tx, dist);
      } 
      //move up to target and stay on course with X axis
      else if (dist > RobotMap.shoot_distance+2 && tx == 0 && tv==1) {
        drive_train.drive(-speed_adj, 0);
        alignReport(3, tv, tx, dist);
      } */
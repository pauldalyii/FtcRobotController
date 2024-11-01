package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
  public DcMotorEx frontLeft;
  public DcMotorEx frontRight;
  public DcMotorEx rearLeft;
  public DcMotorEx rearRight;

  private double driveVelocity = 2750;

  public Robot(HardwareMap hardwareMap) {
    this.frontLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_LEFT");
    this.frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.frontRight = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_RIGHT");
    //this.frontRight.setDirection(DcMotorEx.Direction.REVERSE);
    this.rearLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_LEFT");
    this.rearLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.rearRight = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_RIGHT");
  }

  public void drive(double drive, double strafe, double rotate) {
    double frontLeftPower = drive + strafe + rotate;
    double frontRightPower = drive - strafe - rotate;
    double rearLeftPower = drive - strafe + rotate;
    double rearRightPower = drive + strafe - rotate;

    frontLeft.setVelocity(frontLeftPower * this.driveVelocity);
    frontRight.setVelocity(frontRightPower * this.driveVelocity);
    rearLeft.setVelocity(rearLeftPower * this.driveVelocity);
    rearRight.setVelocity(rearRightPower * this.driveVelocity);
  }
}
package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
  public DcMotorEx frontLeft;
  public DcMotorEx frontRight;
  public DcMotorEx rearLeft;
  public DcMotorEx rearRight;

  private double driveVelocity = 720;

  public Robot(HardwareMap hardwareMap) {
    this.frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
    this.frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
    this.rearLeft = hardwareMap.get(DcMotorEx.class, "RearLeft");
    this.rearRight = hardwareMap.get(DcMotorEx.class, "RearRight");
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
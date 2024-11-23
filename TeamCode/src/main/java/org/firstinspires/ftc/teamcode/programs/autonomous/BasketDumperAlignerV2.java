package org.firstinspires.ftc.teamcode.programs.autonomous;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Basket Dumper Aligner V2")
public class BasketDumperAlignerV2 extends OpMode {
  public DcMotorEx frontLeft = null;
  public DcMotorEx frontRight = null;
  public DcMotorEx rearLeft = null;
  public DcMotorEx rearRight = null;

  private DistanceSensor leftDistanceSensor;
  private DistanceSensor rightDistanceSensor;

  @Override
  public void init() {
    frontLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_LEFT");
    frontRight = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_RIGHT");
    frontRight.setDirection(DcMotorEx.Direction.REVERSE);
    rearLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_LEFT");
    rearRight = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_RIGHT");

    this.leftDistanceSensor = hardwareMap.get(DistanceSensor.class, "LEFT_DISTANCE_SENSOR");
    this.rightDistanceSensor = hardwareMap.get(DistanceSensor.class, "RIGHT_DISTANCE_SENSOR");
  }

  @Override
  public void loop() {
    telemetry.addData("Left Distance Sensor", this.leftDistanceSensor.getDistance(DistanceUnit.INCH));
    telemetry.addData("Right Distance Sensor", this.rightDistanceSensor.getDistance(DistanceUnit.INCH));
    telemetry.addData("Front Left Motor", frontLeft.getVelocity());
    telemetry.addData("Front Right Motor", frontRight.getVelocity());
    telemetry.addData("Rear Left Motor", rearLeft.getVelocity());
    telemetry.addData("Rear Right Motor", rearRight.getVelocity());

    double leftDistance = this.leftDistanceSensor.getDistance(DistanceUnit.INCH);
    double rightDistance = this.rightDistanceSensor.getDistance(DistanceUnit.INCH);

    double targetDistance = 12.0;
    double errorMargin = 1.0;

    if (Math.abs(leftDistance - targetDistance) > errorMargin
        || Math.abs(rightDistance - targetDistance) > errorMargin) {
      double x = 0;
      double y = 0;
      double rotate = 0;

      if ((leftDistance + rightDistance) / 2 > targetDistance + errorMargin) {
        y = 0.1;
      } else if ((leftDistance + rightDistance) / 2 < targetDistance - errorMargin) {
        y = -0.1;
      }

      if (leftDistance > rightDistance - errorMargin) {
        x = 0.1;
      } else if (leftDistance < rightDistance + errorMargin) {
        x = -0.1;
      }

      drive(x, y, rotate);
    } else {
      drive(0, 0, 0);
    }
  }

  private double driveVelocity = 2000;//1250;//2750;

  public void drive(double x, double y, double rotate) {
    double frontLeftPower = y + x + rotate;
    double frontRightPower = y - x - rotate;
    double rearLeftPower = y - x + rotate;
    double rearRightPower = y + x - rotate;

    frontLeft.setVelocity(frontLeftPower * this.driveVelocity);
    frontRight.setVelocity(frontRightPower * this.driveVelocity);
    rearLeft.setVelocity(rearLeftPower * this.driveVelocity);
    rearRight.setVelocity(rearRightPower * this.driveVelocity);
  }
}
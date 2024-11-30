package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.Camera;
import org.firstinspires.ftc.teamcode.hardware.RaiseHeight;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Camera.AprilTagPosition;
import org.firstinspires.ftc.teamcode.hardware.Camera.AprilTag;

@TeleOp(name = "Into The Deep Pro")
public class IntoTheDeepPro extends OpMode {
  private Robot robot;
  private Camera camera;

  @Override
  public void init() {
    this.robot = new Robot(hardwareMap);
    this.camera = new Camera(hardwareMap);
    try {
      this.camera.resume();
    } catch (Camera.CameraNotAttachedException _e) {
      telemetry.speak("Camera not attached");
    }
  }

  @Override
  public void start() {
  }

  @Override
  public void loop() {
    driverLoop();
    operatorLoop();
    telemetries();
  }

  boolean rightBumperLast = true;

  void driverLoop() {
    double x = gamepad1.left_stick_x / 3;
    x *= 2;
    x += gamepad1.right_trigger * (gamepad1.left_stick_x / 3);
    x -= gamepad1.left_trigger * (gamepad1.left_stick_x / 3);
    double y = -gamepad1.left_stick_y / 3;
    y *= 2;
    y += gamepad1.right_trigger * (-gamepad1.left_stick_y / 3);
    y -= gamepad1.left_trigger * (-gamepad1.left_stick_y / 3);
    double z = gamepad1.right_stick_x / 3;
    z *= 2;
    z += gamepad1.right_trigger * (gamepad1.right_stick_x / 3);
    z -= gamepad1.left_trigger * (gamepad1.right_stick_x / 3);

    if (gamepad1.left_bumper) {
      x *= -1;
      y *= -1;

      double leftDistance = this.robot.leftDistance.getDistance(DistanceUnit.INCH);
      double rightDistance = this.robot.rightDistance.getDistance(DistanceUnit.INCH);

      double targetDistance = 16.0;
      double errorMargin = 1.0;

      if (Math.abs(leftDistance - targetDistance) > errorMargin
          || Math.abs(rightDistance - targetDistance) > errorMargin) {
        if ((leftDistance + rightDistance) / 2 > targetDistance + errorMargin) {
          y -= 0.1;
        } else if ((leftDistance + rightDistance) / 2 < targetDistance - errorMargin) {
          y += 0.1;
        }

        if (leftDistance > rightDistance + errorMargin) {
          x += 0.1;
        } else if (leftDistance < rightDistance - errorMargin) {
          x -= 0.1;
        }
      }
    }
    this.robot.drive(x, y, z);
  }

  void operatorLoop() {
    this.robot.lift.setVelocity(-gamepad2.left_stick_y, 0);

    double currentPos = this.robot.intake.intakeElbow.getPosition();
    this.robot.intake.setElbow(currentPos + gamepad2.right_stick_y * -0.01);

    this.robot.intake.setArmVelocity(-gamepad2.right_stick_y);
  }

  void telemetries() {
    telemetry.addData("Front Left Motor Velocity", this.robot.frontLeft.getVelocity());
    telemetry.addData("Front Right Motor Velocity", this.robot.frontRight.getVelocity());
    telemetry.addData("Back Left Motor Velocity", this.robot.rearLeft.getVelocity());
    telemetry.addData("Back Right Motor Velocity", this.robot.rearRight.getVelocity());
    telemetry.addLine();

    telemetry.addData("Left Distance Sensor", this.robot.leftDistance.getDistance(DistanceUnit.INCH));
    telemetry.addData("Right Distance Sensor", this.robot.rightDistance.getDistance(DistanceUnit.INCH));
    this.robot.odometry.update();
    telemetry.addData("Odometry X", this.robot.odometry.getPosX());
    telemetry.addData("Odometry Y", this.robot.odometry.getPosY());

    telemetry.addLine();

    telemetry.addData("Extending Arm Position", this.robot.extendingArm.getCurrentPosition());
    telemetry.addData("Riser Left Position", this.robot.lift.riserLeft.getCurrentPosition());
    telemetry.addData("Riser Right Position", this.robot.lift.riserRight.getCurrentPosition());
    telemetry.addData("Riser Left Current", this.robot.lift.riserLeft.getCurrent(CurrentUnit.AMPS));
    telemetry.addData("Riser Right Current", this.robot.lift.riserRight.getCurrent(CurrentUnit.AMPS));

    telemetry.addData("Intake Elbow Position", this.robot.intakeElbow.getPosition());
    telemetry.addData("Intake Wheel Power", this.robot.intakeWheel.getPower());
    telemetry.addData("Lift Bucket Position", this.robot.liftBucket.getPosition());

    telemetry.addLine();
    this.camera.telemetryAprilTag(telemetry);
  }
}

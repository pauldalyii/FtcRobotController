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
    this.robot.intake.resetServos();
  }

  @Override
  public void loop() {
    this.robot.odometry.update();
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

    if (gamepad1.a) {
      x *= -1;
      y *= -1;
      double targetDistance = 17.0;
      double errorMargin = 1.0;

      double leftDistance = this.robot.leftDistance.getDistance(DistanceUnit.INCH);
      boolean leftFaulted = false;
      if (leftDistance > 100) {
        leftDistance = targetDistance;
        leftFaulted = true;
      }
      double rightDistance = this.robot.rightDistance.getDistance(DistanceUnit.INCH);
      boolean rightFaulted = false;
      if (rightDistance > 100) {
        rightDistance = targetDistance;
        rightFaulted = true;
      }

      gamepad1.rumble(leftFaulted ? 1 : 0, rightFaulted ? 1 : 0, Gamepad.RUMBLE_DURATION_CONTINUOUS);

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
      this.robot.drive(x, y, z);
    } else {
      gamepad1.rumble(0, 0, Gamepad.RUMBLE_DURATION_CONTINUOUS);
      this.robot.drive(x, y, z, this.robot.odometry.getHeading());
    }
    if (gamepad1.back) {
      this.robot.drive(0, 0, 0);
      this.robot.odometry.resetPosAndIMU();
    }
  }

  void operatorLoop() {
    this.robot.intake.setArmVelocity(-gamepad2.right_stick_y);

    if (gamepad2.dpad_up) {
      this.robot.lift.setVelocity(0.75, 0.75);
    } else if (gamepad2.dpad_down) {
      this.robot.lift.setVelocity(-0.75, -0.75);
    } else {
      if (gamepad2.left_stick_button) {
        this.robot.lift.setVelocity(0, -gamepad2.left_stick_y);
      } else {
        this.robot.lift.setVelocity(-gamepad2.left_stick_y, 0);
      }
    }

    this.robot.intake.setWheelPower((gamepad2.right_trigger * 0.5) - gamepad2.left_trigger);

    if (gamepad2.a) {
      this.robot.intake.setElbow(0.05);
      this.robot.intake.setWrist(0.7);
    } else if (gamepad2.b) {
      this.robot.intake.setElbow(0.75);
      this.robot.intake.setWrist(1);
    } else if (gamepad2.y) {//TODO: Refine the values from 0.5
      this.robot.intake.setElbow(0.5);
      this.robot.intake.setWrist(0.5);
    }

    double currentPos = this.robot.intake.getElbow();
    this.robot.intake.setElbow(currentPos + gamepad2.right_stick_x * -0.01);

    currentPos = this.robot.intake.getWrist();
    this.robot.intake.setWrist(currentPos + gamepad2.left_stick_x * -0.01);

    if (gamepad2.left_bumper) {
      this.robot.intake.setBucket(1);
    } else {
      this.robot.intake.setBucket(0);
    }
  }

  void telemetries() {
    telemetry.addData("Front Left Motor Velocity", this.robot.frontLeft.getVelocity());
    telemetry.addData("Front Right Motor Velocity", this.robot.frontRight.getVelocity());
    telemetry.addData("Back Left Motor Velocity", this.robot.rearLeft.getVelocity());
    telemetry.addData("Back Right Motor Velocity", this.robot.rearRight.getVelocity());
    telemetry.addLine();

    //telemetry.addData("Left Distance Sensor", this.robot.leftDistance.getDistance(DistanceUnit.INCH));
    //telemetry.addData("Right Distance Sensor", this.robot.rightDistance.getDistance(DistanceUnit.INCH));
    //this.robot.odometry.update();
    //telemetry.addData("Odometry X", this.robot.odometry.getPosX());
    //telemetry.addData("Odometry Y", this.robot.odometry.getPosY());
    //telemetry.addData("Odometry Heading", this.robot.odometry.getHeading());

    telemetry.addLine();

    telemetry.addData("Extending Arm Position", this.robot.intake.extendingArm.getCurrentPosition());
    telemetry.addData("Riser Left Position", this.robot.lift.riserLeft.getCurrentPosition());
    telemetry.addData("Riser Right Position", this.robot.lift.riserRight.getCurrentPosition());
    telemetry.addData("Riser Left Power", this.robot.lift.riserLeft.getPower());
    telemetry.addData("Riser Right Power", this.robot.lift.riserRight.getPower());

    telemetry.addData("Intake Elbow Position", this.robot.intake.getElbow());
    telemetry.addData("Intake Wrist Position", this.robot.intake.getWrist());
    telemetry.addData("Intake Wheel Power", this.robot.intake.intakeWheel.getPower());
    telemetry.addData("Lift Bucket Position", this.robot.intake.getBucket());

    telemetry.addLine();
    this.camera.telemetryAprilTag(telemetry);
  }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.hardware.Camera;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Camera.AprilTagPosition;
import org.firstinspires.ftc.teamcode.hardware.Camera.AprilTag;

@TeleOp(name = "Into The Deep+")
public class IntoTheDeepPlus extends OpMode {
  private Robot robot;
  private Camera camera;

  @Override
  public void init() {
    this.robot = new Robot(hardwareMap);
    this.camera = new Camera(hardwareMap);
  }

  @Override
  public void start() {
    this.robot.intake.constrict();
  }

  @Override
  public void loop() {
    driverLoop();
    operatorLoop();
    telemetries();
  }

  void driverLoop() {
    double x = gamepad1.left_stick_x / 3;
    x *= 2;
    x -= gamepad1.right_bumper ? gamepad1.left_stick_x / 3 : 0;
    x += gamepad1.left_bumper ? gamepad1.left_stick_x / 3 : 0;
    double y = -gamepad1.left_stick_y / 3;
    y *= 2;
    y -= gamepad1.right_bumper ? -gamepad1.left_stick_y / 3 : 0;
    y += gamepad1.left_bumper ? -gamepad1.left_stick_y / 3 : 0;
    double z = gamepad1.right_stick_x / 3;
    z *= 2;
    z -= gamepad1.right_bumper ? gamepad1.right_stick_x / 3 : 0;
    z += gamepad1.left_bumper ? gamepad1.right_stick_x / 3 : 0;

    if (gamepad1.b) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.raise();
      }
      if (this.align(9, 2.2, 50)) {
        this.robot.liftBucket.setPosition(1);
      } else {
        this.robot.liftBucket.setPosition(0.3);
      }
    } else if (gamepad1.a) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.halfRaise();
      }
      if (this.align(9, 2.2, 50)) {
        this.robot.liftBucket.setPosition(1);
      } else {
        this.robot.liftBucket.setPosition(0.3);
      }
    } else {
      this.robot.drive(x, y, z);
      this.lastRange = 0;
      this.lastX = 0;
      this.lastYaw = 0;
    }
    // robot.Drive System
    this.robot.drive(x, y, z);

  }

  private double sensitivity = 0.05;
  private double speedLimit = 0.25;

  public double parseSpeed(double speed) {
    return Range.clip(speed * sensitivity, -speedLimit, speedLimit);
  }

  public double successThreshold = 0.5;

  public double lastRange = 0;
  public double lastX = 0;
  public double lastYaw = 0;

  public boolean align(double range, double x, double yaw) {
    try {
      this.camera.getAprilTags();
      //Get the list of april tags, identify which one has a position of BASKETS, then line up by driving so that the range: 10, x: -2, and yaw: 45
      List<AprilTag> tags = this.camera.getAprilTags();
      boolean tagfound = false;
      for (AprilTag tag : tags) {
        if (tag.position == AprilTagPosition.BASKETS) {
          tagfound = true;
          double rangeSpeed = tag.ftcPose.range - range;
          double xSpeed = tag.ftcPose.x - x;
          double yawSpeed = tag.ftcPose.yaw - yaw;

          lastRange = rangeSpeed;
          lastX = xSpeed;
          lastYaw = yawSpeed;
          this.robot.drive(-this.parseSpeed(rangeSpeed) + gamepad1.left_stick_x,
              this.parseSpeed(xSpeed) - gamepad1.left_stick_y,
              (-this.parseSpeed(yawSpeed) * 0.5) + gamepad1.right_stick_x);
          return rangeSpeed < this.successThreshold && xSpeed < this.successThreshold
              && yawSpeed < this.successThreshold;
        }
      }
      if (!tagfound) {
        this.robot.drive(-this.parseSpeed(lastRange) * 0.5, this.parseSpeed(lastX) * 0.5,
            -this.parseSpeed(lastRange) * 0.25);
        gamepad1.rumble(1);
      } else {
        gamepad1.rumble(0);
      }
    } catch (Camera.CameraNotStreamingException e) {
      telemetry.speak("Camera not streaming");
      this.robot.drive(0, 0, 0);
    } catch (Camera.CameraNotAttachedException e) {
      telemetry.speak("Camera not attached");
      requestOpModeStop();
    }
    return false;
  }

  void operatorLoop() {
    if (gamepad2.dpad_up) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.raise();
      }
    } else if (gamepad2.dpad_left || gamepad2.dpad_right) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.halfRaise();
      }
    } else if (gamepad2.dpad_down) {
      if (gamepad2.y || this.robot.intake.clearLift()) {
        this.robot.lift.lower();
      }
    } else if (!gamepad1.b && !gamepad1.a) {
      this.robot.lift.stop();
    }

    /*if (gamepad2.a) {
      this.robot.liftBucket.setPosition(1);
    } else if (!this.gamepad2.left_bumper) {
      this.robot.liftBucket.setPosition(0.3);
    }*/

    if (gamepad2.right_bumper && gamepad2.right_trigger > 0.5) {
      this.robot.intake.expand();
      this.robot.intakeWheel.setPower(1);
    } else if (gamepad2.right_trigger > 0.5) {
      this.robot.intake.hover();
      this.robot.intakeWheel.setPower(0);
    } else if (gamepad2.right_bumper) {
      this.robot.intakeWheel.setPower(1);
    } else if (gamepad2.left_bumper) {
      this.robot.intakeWheel.setPower(-1);
      this.robot.liftBucket.setPosition(0.25);
    } else {
      this.robot.intakeWheel.setPower(0);
    }

    if (gamepad2.left_trigger > 0.5) {
      this.robot.intake.constrict();
    } else if (gamepad2.y) {
      this.robot.intake.tip();
    }

  }

  void telemetries() {
    telemetry.addData("Front Left Motor Velocity", this.robot.frontLeft.getVelocity());
    telemetry.addData("Front Right Motor Velocity", this.robot.frontRight.getVelocity());
    telemetry.addData("Back Left Motor Velocity", this.robot.rearLeft.getVelocity());
    telemetry.addData("Back Right Motor Velocity", this.robot.rearRight.getVelocity());

    telemetry.addData("Extending Arm Position", this.robot.extendingArm.getCurrentPosition());
    telemetry.addData("Riser Left Position", this.robot.lift.riserLeft.getCurrentPosition());
    telemetry.addData("Riser Right Position", this.robot.lift.riserRight.getCurrentPosition());
    telemetry.addData("Riser Left Current", this.robot.lift.riserLeft.getCurrent(CurrentUnit.AMPS));
    telemetry.addData("Riser Right Current", this.robot.lift.riserRight.getCurrent(CurrentUnit.AMPS));

    telemetry.addData("Intake Elbow Position", this.robot.intakeElbow.getPosition());
    telemetry.addData("Intake Wheel Power", this.robot.intakeWheel.getPower());
    telemetry.addData("Lift Bucket Position", this.robot.liftBucket.getPosition());
  }
}

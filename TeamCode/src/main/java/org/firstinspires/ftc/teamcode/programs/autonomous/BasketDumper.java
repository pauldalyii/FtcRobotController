package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.List;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Camera;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Camera.AprilTag;
import org.firstinspires.ftc.teamcode.hardware.Camera.AprilTagPosition;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "Basket Dumper")
public class BasketDumper extends OpMode {
  private Robot robot;
  private Camera camera;

  @Override
  public void init() {
    this.robot = new Robot(hardwareMap);
    this.camera = new Camera(hardwareMap);
    try {
      this.camera.resume();
    } catch (Camera.CameraNotAttachedException e) {
      telemetry.speak("Camera not attached");
      requestOpModeStop();
    }
  }

  @Override
  public void init_loop() {
    this.camera.telemetryAprilTag(telemetry);
  }

  private double sensitivity = 0.05;
  private double speedLimit = 0.15;

  public double parseSpeed(double speed) {
    return Range.clip(speed * sensitivity, -speedLimit, speedLimit);
  }

  /** Stages: 0: start; 1: aligned: 2: ready to dump; */
  int stage = 0;

  @Override
  public void loop() {
    this.camera.telemetryAprilTag(telemetry);
    switch (stage) {
      case 0:
        this.robot.lift.raise();
        if (this.align(12, -1.5, 45) && this.robot.lift.isRaised()) {
          stage = 1;
        }
        break;
      case 1:
        this.robot.lift.raise();
        if (this.align(9, 1.5, 50) && this.robot.lift.isRaised()) {
          stage = 2;
        }
        break;
      case 2:
        this.robot.lift.raise();
        this.robot.liftBucket.setPosition(1);
        this.robot.drive(0, 0, 0);
        break;
    }
  }

  public double successThreshold = 0.5;

  public boolean align(double range, double x, double yaw) {
    try {
      this.camera.getAprilTags();
      //Get the list of april tags, identify which one has a position of BASKETS, then line up by driving so that the range: 10, x: -2, and yaw: 45
      List<AprilTag> tags = this.camera.getAprilTags();
      for (AprilTag tag : tags) {
        if (tag.position == AprilTagPosition.BASKETS) {
          double rangeSpeed = tag.ftcPose.range - range;
          double xSpeed = tag.ftcPose.x - x;
          double yawSpeed = tag.ftcPose.yaw - yaw;
          this.robot.drive(-this.parseSpeed(rangeSpeed), this.parseSpeed(xSpeed), -this.parseSpeed(yawSpeed));
          return rangeSpeed < this.successThreshold && xSpeed < this.successThreshold
              && yawSpeed < this.successThreshold;
        }
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
}
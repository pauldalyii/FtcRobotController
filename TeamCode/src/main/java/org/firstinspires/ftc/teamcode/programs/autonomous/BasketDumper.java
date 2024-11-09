package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.util.ElapsedTime;
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
  private ElapsedTime runtime = new ElapsedTime();

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

  int stage = 0;
  double timer = 0;

  @Override
  public void loop() {
    telemetry.addData("Stage", stage);
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
        timer = this.runtime.milliseconds();
        break;
      case 2:
        this.robot.lift.raise();
        this.robot.liftBucket.setPosition(1);
        this.robot.drive(0, 0, 0);
        if (this.runtime.milliseconds() - timer > 1000) {
          stage = 3;
        }
        break;
      case 3:
        if (this.align(12, -1.5, 45)) {
          stage = 4;
        }
        break;
      case 4:
        this.robot.liftBucket.setPosition(0.4);
        this.robot.lift.lower();
        if (this.robot.lift.isLowered()) {
          stage = 5;
        }
        break;
      case 5:
        requestOpModeStop();
    }
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
          this.robot.drive(-this.parseSpeed(rangeSpeed), this.parseSpeed(xSpeed), -this.parseSpeed(yawSpeed) * 0.5);
          return rangeSpeed < this.successThreshold && xSpeed < this.successThreshold
              && yawSpeed < this.successThreshold;
        }
      }
      if (!tagfound) {
        telemetry.speak("No basket found");
        this.robot.drive(-this.parseSpeed(lastRange) * -0.5, this.parseSpeed(lastX) * -0.5,
            -this.parseSpeed(lastRange) * -0.25);
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
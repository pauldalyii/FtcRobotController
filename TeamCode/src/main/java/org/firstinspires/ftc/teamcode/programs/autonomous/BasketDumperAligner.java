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

@Autonomous(name = "Basket Dumper Aligner")
public class BasketDumperAligner extends OpMode {
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

  private double sensitivity = 0.05;
  private double speedLimit = 0.15;

  public double parseSpeed(double speed) {
    return Range.clip(speed * sensitivity, -speedLimit, speedLimit);
  }

  @Override
  public void loop() {
    if (gamepad1.a) {
      //Prep to dump
      try {
        this.camera.getAprilTags();
        //Get the list of april tags, identify which one has a position of BASKETS, then line up by driving so that the range: 10, x: -2, and yaw: 45
        List<AprilTag> tags = this.camera.getAprilTags();
        boolean tagfound = false;
        for (AprilTag tag : tags) {
          if (tag.position == AprilTagPosition.BASKETS) {
            double x = tag.ftcPose.x;
            double yaw = tag.ftcPose.yaw;
            double range = tag.ftcPose.range;
            this.robot.drive(-this.parseSpeed(range - 12), this.parseSpeed(x + 1.5), -this.parseSpeed(yaw - 45));
            tagfound = true;
          }
        }
        if (!tagfound) {
          telemetry.speak("No basket found");
          this.robot.drive(0, 0, 0);
        }
      } catch (Camera.CameraNotStreamingException e) {
        telemetry.speak("Camera not streaming");
        this.robot.drive(0, 0, 0);
      } catch (Camera.CameraNotAttachedException e) {
        telemetry.speak("Camera not attached");
        requestOpModeStop();
      }
    } else if (gamepad1.b) {
      try {
        this.camera.getAprilTags();
        List<AprilTag> tags = this.camera.getAprilTags();
        boolean tagfound = false;
        for (AprilTag tag : tags) {
          if (tag.position == AprilTagPosition.BASKETS) {
            double x = tag.ftcPose.x;
            double yaw = tag.ftcPose.yaw;
            double range = tag.ftcPose.range;
            this.robot.drive(-this.parseSpeed(range - 9), this.parseSpeed(x - 1.5), -this.parseSpeed(yaw - 50));
            tagfound = true;
          }
        }
        if (!tagfound) {
          telemetry.speak("No basket found");
          this.robot.drive(0, 0, 0);
        }
      } catch (Camera.CameraNotStreamingException e) {
        telemetry.speak("Camera not streaming");
        this.robot.drive(0, 0, 0);
      } catch (Camera.CameraNotAttachedException e) {
        telemetry.speak("Camera not attached");
        requestOpModeStop();
      }
    } else {
      this.robot.drive(0, 0, 0);
    }
    this.camera.telemetryAprilTag(telemetry);
  }
}
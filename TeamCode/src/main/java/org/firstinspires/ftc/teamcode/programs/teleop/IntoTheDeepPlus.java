package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.hardware.Camera;
import org.firstinspires.ftc.teamcode.hardware.RaiseHeight;
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
    try {
      this.camera.resume();
    } catch (Camera.CameraNotAttachedException _e) {
      telemetry.speak("Camera not attached");
    }
  }

  @Override
  public void start() {
    this.robot.intake.transfer();
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

    if (gamepad1.right_bumper && (rightBumperLast || !gamepad1.left_bumper)) {
      rightBumperLast = true;
      if (this.robot.intake.clearLift()) {
        double power = 0.75;
        this.robot.lift.raise(RaiseHeight.HighBasket, power);  //raise to the highbasket
      }
      this.align(9, 2.2, 50);
      if (gamepad1.left_bumper) {
        this.robot.liftBucket.setPosition(0.65);
      } else {
        this.robot.liftBucket.setPosition(0.3);
      }
    } else if (gamepad1.left_bumper) {
      rightBumperLast = false;
      if (this.robot.intake.clearLift()) {
        double power = 0.75;
        this.robot.lift.raise(RaiseHeight.LowBasket, power);
      }
      this.align(9, 2.2, 50);
      if (gamepad1.right_bumper) {
        this.robot.liftBucket.setPosition(0.55);
      } else {
        this.robot.liftBucket.setPosition(0.3);
      }
    } else {
      gamepad1.stopRumble();
      this.robot.drive(x, y, z);
      this.lastRange = 0;
      this.lastX = 0;
      this.lastYaw = 0;
    }
  }

  private double sensitivity = 0.05;
  private double speedLimit = 0.15;

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
          this.robot.drive(-this.parseSpeed(rangeSpeed) - gamepad1.left_stick_x / 3,
              this.parseSpeed(xSpeed) + gamepad1.left_stick_y / 3,
              (-this.parseSpeed(yawSpeed) * 0.5) + gamepad1.right_stick_x / 3);
          return rangeSpeed < this.successThreshold && xSpeed < this.successThreshold
              && yawSpeed < this.successThreshold;
        }
      }
      if (!tagfound) {
        this.robot.drive((-this.parseSpeed(lastRange) * 0.5)
            - gamepad1.left_stick_x / 3,
            (this.parseSpeed(lastX) * 0.5)
                + gamepad1.left_stick_y / 3,
            (-this.parseSpeed(lastRange) * 0.25) + gamepad1.right_stick_x / 3);
        //gamepad1.rumble(1, 1, Gamepad.RUMBLE_DURATION_CONTINUOUS);
      } else {
        gamepad1.stopRumble();
      }
    } catch (Camera.CameraNotStreamingException e) {
      telemetry.speak("Camera not streaming");
      this.robot.drive(-gamepad1.left_stick_x / 3, gamepad1.left_stick_y / 3, gamepad1.right_stick_x / 3);
    } catch (Camera.CameraNotAttachedException e) {
      telemetry.speak("Camera not attached");
      requestOpModeStop();
    }
    return false;
  }

  void operatorLoop() {
    // if you press the up button on the dpad, the intake will move out of the way (if it isn't already)
    if (gamepad2.dpad_up && this.robot.intake.clearLift()) {
        double power = 0.75;
        // and the lift will raise to the high basket
        this.robot.lift.raise(RaiseHeight.HighBasket, power);
    } 
    // if you press the left or right button on the dpad, the intake will move out of the way (if it isn't already)
    else if ((gamepad2.dpad_left || gamepad2.dpad_right) && this.robot.intake.clearLift()) {
        double power = 0.75;
        // and the lift will raise to the low basket
        this.robot.lift.raise(RaiseHeight.LowBasket, power);
    }
    // if you press the down button on the dpad, the intake will move out of the way (if it isn't already)
    else if (gamepad2.dpad_down && (gamepad2.y || this.robot.intake.clearLift())) {
        // and the lift will lower all the way down
        this.robot.lift.lower();
    }
    // if player 1 isn't pressing both the left and the right bumpers
    else if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
      // the lift will stop?
      this.robot.lift.stop();
    }

    /*if (gamepad2.a) {
      this.robot.liftBucket.setPosition(1);
    } else if (!this.gamepad2.left_bumper) {
      this.robot.liftBucket.setPosition(0.3);
    }*/

    // if you press the right bumper or right trigger
    if (gamepad2.right_bumper && gamepad2.right_trigger > 0.5) {
        // the intake will collect samples
        this.robot.intake.collect();
    // if you press just the right trigger
    } else if (gamepad2.right_trigger > 0.5) {
        // the intake will hover into or out of the submersible chamber and the intake wheel will stop
        this.robot.intake.hover();
        this.robot.intakeWheel.setPower(0);
    // if you press just the right bumper
    } else if (gamepad2.right_bumper) {
        // the intake wheel will spin to collect samples
        this.robot.intakeWheel.setPower(1);
      // if you press just the left bumper
    } else if (gamepad2.left_bumper) {
        // adjust lift bucket to catch specimen
        this.robot.liftBucket.setPosition(0.25);
        // transfer sample into lift bucket
        this.robot.intakeWheel.setPower(-1);
    // if you do none of these things
    } else {
      // stop the intake wheel
      this.robot.intakeWheel.setPower(0);
    }

    // if the left trigger is pulled
    if (gamepad2.left_trigger > 0.5) {
      // bring the intake to the transfer position
      this.robot.intake.transfer();
    // if the y button is pressed
    } else if (gamepad2.y) {
      // tip the entire robot
      this.robot.intake.tip();
    }

    if (gamepad2.back) {
         // allign the bucket to collect a specimen
      this.robot.liftBucket.setPosition(0.1);
    }

    if (gamepad2.x) {
      double power = 0.75;
      // lift specimen from perimeter wall
      this.robot.lift.raise(RaiseHeight.LiftSpecimen, power);
    }

    if (gamepad2.a) {
      double power = 0.75;
      // lift specimen to high chamber
      this.robot.lift.raise(RaiseHeight.AboveHighChamber, power);
    }

    if (gamepad2.b) {
      double power = 0.75;
      // lower specimen to low chamber
      this.robot.lift.raise(RaiseHeight.HighChamber, power);
    }
  }

  @Override
  public void stop() {
    this.robot.stop();
  }


  void telemetries() {
    telemetry.addData("Build Number:", System.getProperty("buildNumber", "unknown"));

    telemetry.addData("Front Left Motor Velocity", this.robot.frontLeft.getVelocity());
    telemetry.addData("Front Right Motor Velocity", this.robot.frontRight.getVelocity());
    telemetry.addData("Back Left Motor Velocity", this.robot.rearLeft.getVelocity());
    telemetry.addData("Back Right Motor Velocity", this.robot.rearRight.getVelocity());

    ////telemetry.addData("Extending Arm Position", this.robot.extendingArm.getCurrentPosition());
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

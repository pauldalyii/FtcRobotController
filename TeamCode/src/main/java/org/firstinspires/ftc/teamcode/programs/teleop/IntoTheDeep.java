package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name = "Into The Deep")
public class IntoTheDeep extends OpMode {
  private Robot robot;

  @Override
  public void init() {
    this.robot = new Robot(hardwareMap);
  }

  @Override
  public void loop() {
    driverLoop();
    operatorLoop();
    telemetries();
  }

  void driverLoop() {
    double drive = -gamepad1.left_stick_y;
    double strafe = gamepad1.left_stick_x;
    double rotate = gamepad1.right_stick_x;

    this.robot.drive(drive, strafe, rotate);
  }

  void operatorLoop() {
    if (gamepad2.a) {
      this.robot.intake.constrict();
      if (gamepad2.left_bumper) {
        this.robot.intakeWheel.setPower(-1);
        this.robot.liftBucket.setPosition(0.3);
      } else {
        this.robot.intakeWheel.setPower(1);
      }
    } else {
      if (gamepad2.right_trigger > 0.5) {
        this.robot.intake.expand();
      } else if (gamepad2.left_trigger > 0.5) {
        this.robot.intake.hover();
      }
    }

    if (gamepad2.dpad_up) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.raise();
      }
    } else if (gamepad2.dpad_left || gamepad2.dpad_right) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.halfRaise();
      }
    } else if (gamepad2.dpad_down) {
      if (this.robot.intake.clearLift()) {
        this.robot.lift.lower();
      }
    } else {
      this.robot.lift.stop();
    }

    if (gamepad2.right_bumper) {
      this.robot.liftBucket.setPosition(1);
    } else if (!this.gamepad2.left_bumper) {
      this.robot.liftBucket.setPosition(0.4);
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

    telemetry.addData("Intake Elbow Position", this.robot.intakeElbow.getPosition());
    telemetry.addData("Intake Wheel Power", this.robot.intakeWheel.getPower());
    telemetry.addData("Lift Bucket Position", this.robot.liftBucket.getPosition());
  }
}

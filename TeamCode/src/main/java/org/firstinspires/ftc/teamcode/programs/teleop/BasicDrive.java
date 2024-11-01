package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name = "Basic Drive")
public class BasicDrive extends OpMode {
  private Robot robot;

  @Override
  public void init() {
    this.robot = new Robot(hardwareMap);
  }

  @Override
  public void loop() {
    double drive = -gamepad1.left_stick_y;
    double strafe = gamepad1.left_stick_x;
    double rotate = gamepad1.right_stick_x;

    this.robot.drive(drive, strafe, rotate);

    if (gamepad1.dpad_up) {
      this.robot.lift.raise();
    } else if (gamepad1.dpad_down) {
      this.robot.lift.lower();
    } else {
      this.robot.lift.stop();
    }

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

    if (gamepad1.a) {
      this.robot.intakeElbow.setPosition(0.5);
    } else if (gamepad1.b) {
      this.robot.intakeElbow.setPosition(0.0);
    }

    if (gamepad1.x) {
      this.robot.intakeWheel.setPower(1.0);
    } else if (gamepad1.y) {
      this.robot.intakeWheel.setPower(-1.0);
    } else {
      this.robot.intakeWheel.setPower(0.0);
    }

    if (gamepad1.left_bumper) {
      this.robot.liftBucket.setPosition(0.0);
    } else if (gamepad1.right_bumper) {
      this.robot.liftBucket.setPosition(1.0);
    }

  }
}
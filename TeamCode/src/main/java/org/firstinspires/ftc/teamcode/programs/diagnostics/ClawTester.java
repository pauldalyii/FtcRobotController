package org.firstinspires.ftc.teamcode.programs.diagnostics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Claw Tester")
public class ClawTester extends OpMode {
  private DcMotorEx frontLeft, frontRight, rearLeft, rearRight;
  private Servo elbow, wrist, claw;

  @Override
  public void init() {
    frontLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_LEFT");
    frontRight = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_RIGHT");
    rearLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_LEFT");
    rearRight = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_RIGHT");

    elbow = hardwareMap.get(Servo.class, "ELBOW");
    wrist = hardwareMap.get(Servo.class, "WRIST");
    claw = hardwareMap.get(Servo.class, "CLAW");

    // Set motor directions if needed
    frontRight.setDirection(DcMotorEx.Direction.REVERSE);
  }

  @Override
  public void start() {
    // Initialize servos to starting positions
    elbow.setPosition(0.5);
    wrist.setPosition(0.5);
    claw.setPosition(0.5);
  }

  @Override
  public void loop() {
    // --- Drivetrain ---
    double x = gamepad1.left_stick_x; // strafe
    double y = -gamepad1.left_stick_y; // forward
    double z = gamepad1.right_stick_x; // turn

    double fl = y + x + z;
    double fr = y - x - z;
    double rl = y - x + z;
    double rr = y + x - z;

    double max = Math.max(Math.max(Math.abs(fl), Math.abs(fr)), Math.max(Math.abs(rl), Math.abs(rr)));
    if (max > 1.0) {
      fl /= max;
      fr /= max;
      rl /= max;
      rr /= max;
    }

    double powerScale = 0.5; // slow for testing
    frontLeft.setPower(fl * powerScale);
    frontRight.setPower(fr * powerScale);
    rearLeft.setPower(rl * powerScale);
    rearRight.setPower(rr * powerScale);

    // --- Arm Servos ---
    // Elbow: right stick y (gamepad2)
    double elbowPos = elbow.getPosition();
    elbowPos += gamepad2.right_stick_y * 0.005;
    elbowPos = Math.max(0.3, Math.min(1, elbowPos)); // Adjusted range to 0.3 - 1
    elbow.setPosition(elbowPos);

    // Wrist: left stick y (gamepad2)
    double wristPos = wrist.getPosition();
    wristPos += gamepad2.left_stick_y * 0.005; // Inverted control
    wristPos = Math.max(0.25, Math.min(0.7, wristPos)); // Adjusted range to 0.25 - 0.7
    wrist.setPosition(wristPos);

    // --- Claw Servo ---
    if (gamepad2.right_bumper) {
      claw.setPosition(1.0); // open
    } else if (gamepad2.left_bumper) {
      claw.setPosition(0.0); // close
    }

    // Telemetry
    telemetry.addData("Elbow Pos", elbow.getPosition());
    telemetry.addData("Wrist Pos", wrist.getPosition());
    telemetry.addData("Claw Pos", claw.getPosition());
    telemetry.addData("Front Left Power", frontLeft.getPower());
    telemetry.addData("Front Right Power", frontRight.getPower());
    telemetry.addData("Rear Left Power", rearLeft.getPower());
    telemetry.addData("Rear Right Power", rearRight.getPower());
    telemetry.update();
  }
}

/*
Controls:
- Gamepad 1:
  - Left stick: Strafe (X), Forward/Backward (Y)
  - Right stick X: Turn

- Gamepad 2:
  - Right stick Y: Move elbow servo
  - Left stick Y: Move wrist servo
  - Right bumper: Open claw
  - Left bumper: Close claw
*/

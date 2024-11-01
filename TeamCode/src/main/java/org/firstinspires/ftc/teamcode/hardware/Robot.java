package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
  public DcMotorEx frontLeft;
  public DcMotorEx frontRight;
  public DcMotorEx rearLeft;
  public DcMotorEx rearRight;

  public DcMotorEx extendingArm;

  private DcMotorEx riserLeft;
  private DcMotorEx riserRight;
  public Lift lift;

  public Servo intakeElbow;
  public CRServo intakeWheel;
  public Servo liftBucket;

  public Intake intake;

  private double driveVelocity = 2750;

  public Robot(HardwareMap hardwareMap) {
    this.frontLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_LEFT");
    this.frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.frontRight = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_RIGHT");
    //this.frontRight.setDirection(DcMotorEx.Direction.REVERSE);
    this.rearLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_LEFT");
    this.rearLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.rearRight = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_RIGHT");

    this.intakeElbow = hardwareMap.get(Servo.class, "INTAKE_ELBOW");
    this.intakeElbow.scaleRange(0.0, 0.6);
    this.intakeWheel = hardwareMap.get(CRServo.class, "INTAKE_WHEEL");
    //this.intakeWheel.setDirection(CRServo.Direction.REVERSE);
    this.liftBucket = hardwareMap.get(Servo.class, "LIFT_BUCKET");
    this.intake = new Intake(this.intakeElbow, this.intakeWheel, this.extendingArm);

    this.extendingArm = hardwareMap.get(DcMotorEx.class, "EXTENDING_ARM");
    this.extendingArm.setDirection(DcMotorEx.Direction.REVERSE);
    this.riserLeft = hardwareMap.get(DcMotorEx.class, "RISER_LEFT");
    this.riserLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.riserRight = hardwareMap.get(DcMotorEx.class, "RISER_RIGHT");

    this.lift = new Lift(this.riserLeft, this.riserRight, this.intake);

  }

  public void drive(double drive, double strafe, double rotate) {
    double frontLeftPower = drive + strafe + rotate;
    double frontRightPower = drive - strafe - rotate;
    double rearLeftPower = drive - strafe + rotate;
    double rearRightPower = drive + strafe - rotate;

    frontLeft.setVelocity(frontLeftPower * this.driveVelocity);
    frontRight.setVelocity(frontRightPower * this.driveVelocity);
    rearLeft.setVelocity(rearLeftPower * this.driveVelocity);
    rearRight.setVelocity(rearRightPower * this.driveVelocity);
  }

  public class Lift {
    public DcMotorEx riserLeft;
    public DcMotorEx riserRight;
    private Intake intake;

    Lift(DcMotorEx riserLeft, DcMotorEx riserRight, Intake intake) {
      this.riserLeft = riserLeft;
      this.riserRight = riserRight;
      this.intake = intake;

      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

      this.riserLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
      this.riserRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void raise() {
      this.intake.constrict(true);

      this.riserLeft.setTargetPosition(3000);
      this.riserRight.setTargetPosition(3000);

      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

      this.riserLeft.setPower(0.5);
      this.riserRight.setPower(0.5);
    }

    public void lower() {
      this.intake.expand();
      this.riserLeft.setTargetPosition(0);
      this.riserRight.setTargetPosition(0);

      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

      this.riserLeft.setPower(0.5);
      this.riserRight.setPower(0.5);
    }

    public void stop() {
      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

      this.riserLeft.setPower(0);
      this.riserRight.setPower(0);
    }
  }

  public class Intake {
    public Servo intakeElbow;
    public CRServo intakeWheel;
    public DcMotorEx extendingArm;

    Intake(Servo intakeElbow, CRServo intakeWheel, DcMotorEx extendingArm) {
      this.intakeElbow = intakeElbow;
      this.intakeWheel = intakeWheel;
      this.extendingArm = extendingArm;
    }

    public void expand() {
      this.extendingArm.setTargetPosition(800);
      this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      this.extendingArm.setPower(0.25);
      this.intakeElbow.setPosition(0.2);
      this.intakeWheel.setPower(1);
    }

    public void constrict(boolean eject) {
      this.extendingArm.setTargetPosition(500);
      this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      this.extendingArm.setPower(0.25);
      this.intakeElbow.setPosition(1);
      if (eject) {
        this.intakeWheel.setPower(-1);
      } else {
        this.intakeWheel.setPower(1);
      }
    }

    public void tip() {
      this.intakeElbow.setPosition(0);
      this.extendingArm.setTargetPosition(0);
      this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      this.extendingArm.setPower(0.25);
      this.intakeWheel.setPower(0);
    }
  }
}
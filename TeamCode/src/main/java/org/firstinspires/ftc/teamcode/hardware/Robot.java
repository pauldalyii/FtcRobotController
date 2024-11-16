package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {
  public DcMotorEx frontLeft;
  public DcMotorEx frontRight;
  public DcMotorEx rearLeft;
  public DcMotorEx rearRight;

  ////public DcMotorEx extendingArm;

  private DcMotorEx riserLeft;
  private DcMotorEx riserRight;
  public Lift lift;

  public Servo intakeElbow;
  public CRServo intakeWheel;
  public Servo liftBucket;

  public Intake intake;

  private double driveVelocity = 2000;//1250;//2750;

  public Robot(HardwareMap hardwareMap) {
    this.frontLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_LEFT");
    this.frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.frontRight = hardwareMap.get(DcMotorEx.class, "DRIVE_FRONT_RIGHT");
    //this.frontRight.setDirection(DcMotorEx.Direction.REVERSE);
    this.rearLeft = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_LEFT");
    this.rearLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.rearRight = hardwareMap.get(DcMotorEx.class, "DRIVE_REAR_RIGHT");

    this.riserLeft = hardwareMap.get(DcMotorEx.class, "RISER_LEFT");
    this.riserLeft.setDirection(DcMotorEx.Direction.REVERSE);
    this.riserLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    this.riserRight = hardwareMap.get(DcMotorEx.class, "RISER_RIGHT");
    this.riserRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

    ////this.extendingArm = hardwareMap.get(DcMotorEx.class, "EXTENDING_ARM");
    ////this.extendingArm.setDirection(DcMotorEx.Direction.REVERSE);
    ////this.extendingArm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    this.intakeElbow = hardwareMap.get(Servo.class, "INTAKE_ELBOW");
    this.intakeElbow.scaleRange(0.0, 0.6);
    this.intakeWheel = hardwareMap.get(CRServo.class, "INTAKE_WHEEL");
    //this.intakeWheel.setDirection(CRServo.Direction.REVERSE);
    this.liftBucket = hardwareMap.get(Servo.class, "LIFT_BUCKET");
    this.liftBucket.setDirection(Servo.Direction.REVERSE);

    this.intake = new Intake(this.intakeElbow, this.intakeWheel/*////, this.extendingArm*/);
    this.lift = new Lift(this.riserLeft, this.riserRight);

  }

  public void drive(double x, double y, double rotate) {
    double frontLeftPower = y + x + rotate;
    double frontRightPower = y - x - rotate;
    double rearLeftPower = y - x + rotate;
    double rearRightPower = y + x - rotate;

    frontLeft.setVelocity(frontLeftPower * this.driveVelocity);
    frontRight.setVelocity(frontRightPower * this.driveVelocity);
    rearLeft.setVelocity(rearLeftPower * this.driveVelocity);
    rearRight.setVelocity(rearRightPower * this.driveVelocity);
  }

  public class Lift {
    public DcMotorEx riserLeft;
    public DcMotorEx riserRight;

    Lift(DcMotorEx riserLeft, DcMotorEx riserRight) {
      this.riserLeft = riserLeft;
      this.riserRight = riserRight;

      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

      this.riserLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
      this.riserRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void raise(RaiseHeight height, double power) {
      this.riserLeft.setTargetPosition(height.getValue());
      this.riserRight.setTargetPosition(height.getValue());

      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

      this.riserLeft.setPower(power);
      this.riserRight.setPower(power);
    }


    public void raise() {
      double power = 0.75;
      this.raise(RaiseHeight.HighBasket, power);
    }
    public void halfRaise() {
      double power = 0.5;
      this.raise(RaiseHeight.LowBasket, power);
    }

    int liftVariance = 10;
    public boolean isRaisedTo(RaiseHeight height) {
      return this.riserLeft.getCurrentPosition() >= height.getValue() - liftVariance;
    }

    public boolean isLoweredTo(RaiseHeight height) {
      
      return this.riserLeft.getCurrentPosition() <= height.getValue() + liftVariance;
    }

    public void setVelocity(float input) {
      if (input >= -0.05 && input <= 0.05)
        return;

      float ticksPerRev = 537.6f;
      int maxRPM = 312;

      float targetVelocity = input * ((maxRPM * ticksPerRev) / 60);

      this.riserLeft.setVelocity(targetVelocity);
      this.riserRight.setVelocity(targetVelocity);
    }

    public void lower() {
      this.raise(RaiseHeight.Minimum, 0.75);
      // this.riserLeft.setTargetPosition(0);
      // this.riserRight.setTargetPosition(0);

      // this.riserLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      // this.riserRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

      // this.riserLeft.setPower(0.75);
      // this.riserRight.setPower(0.75);
    }

    public boolean isLowered() {
      return this.isLoweredTo(RaiseHeight.Minimum);
      //return this.riserLeft.getCurrentPosition() <= 10;
    }

    public void stop() {
      this.riserLeft.setPower(0);
      this.riserRight.setPower(0);
    }
  }

  public class Intake {
    public Servo intakeElbow;
    public CRServo intakeWheel;
    private ElapsedTime runtime = new ElapsedTime();
    ////public DcMotorEx extendingArm;

    Intake(Servo intakeElbow, CRServo intakeWheel/*////, DcMotorEx extendingArm*/) {
      this.intakeElbow = intakeElbow;
      this.intakeWheel = intakeWheel;
      ////this.extendingArm = extendingArm;
    }

    public void hover() {
      this.intakeElbow.setPosition(0.275);
      this.intakeWheel.setPower(0);

      ////this.extendingArm.setTargetPosition(750);
      ////this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      ////this.extendingArm.setPower(0.25);
    }

    public void expand() {
      this.intakeElbow.setPosition(0.2);
      this.intakeWheel.setPower(1);

      ////this.extendingArm.setTargetPosition(750);
      ////this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      ////this.extendingArm.setPower(0.25);
    }

    public void constrict() {
      this.intakeElbow.setPosition(1);

      ////this.extendingArm.setTargetPosition(150);
      ////this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      ////this.extendingArm.setPower(0.25);
    }

    public void tip() {
      this.intakeElbow.setPosition(0);
      this.intakeWheel.setPower(0);

      ////this.extendingArm.setTargetPosition(0);
      ////this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      ////this.extendingArm.setPower(0.25);
    }

    double timer = 0;

    public boolean clearLift() {
      if (this.intakeElbow.getPosition() == 0.7) {
        if (this.runtime.milliseconds() - this.timer > 1000) {
          return true;
        }
      } else {
        this.timer = this.runtime.milliseconds();
      }
      /*if (this.extendingArm.getCurrentPosition() >= 450) {
        return true;
      }*/
      this.intakeElbow.setPosition(0.7);
      this.intakeWheel.setPower(0);

      ////this.extendingArm.setTargetPosition(500);
      ////this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      ////this.extendingArm.setPower(0.75);

      return false;
    }
  }
}
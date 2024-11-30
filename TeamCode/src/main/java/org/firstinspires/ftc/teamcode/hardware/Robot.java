package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.teamcode.hardware.drivers.GoBildaPinpointDriver;

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

  public DcMotorEx extendingArm;

  private DcMotorEx riserLeft;
  private DcMotorEx riserRight;
  public Lift lift;

  public Servo intakeElbow;
  public CRServo intakeWheel;
  public Servo liftBucket;

  public DistanceSensor leftDistance;
  public DistanceSensor rightDistance;

  public GoBildaPinpointDriver odometry;

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

    this.extendingArm = hardwareMap.get(DcMotorEx.class, "EXTENDING_ARM");
    this.extendingArm.setDirection(DcMotorEx.Direction.REVERSE);
    this.extendingArm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    this.intakeElbow = hardwareMap.get(Servo.class, "INTAKE_ELBOW");
    this.intakeElbow.scaleRange(0.0, 0.6);
    this.intakeWheel = hardwareMap.get(CRServo.class, "INTAKE_WHEEL");
    //this.intakeWheel.setDirection(CRServo.Direction.REVERSE);
    this.liftBucket = hardwareMap.get(Servo.class, "LIFT_BUCKET");
    this.liftBucket.setDirection(Servo.Direction.REVERSE);

    this.intake = new Intake(this.intakeElbow, this.intakeWheel, this.extendingArm);
    this.lift = new Lift(this.riserLeft, this.riserRight);

    this.leftDistance = hardwareMap.get(DistanceSensor.class, "LEFT_DISTANCE");
    this.rightDistance = hardwareMap.get(DistanceSensor.class, "RIGHT_DISTANCE");

    this.odometry = hardwareMap.get(GoBildaPinpointDriver.class, "GOBILDA_ODOMETRY");
    this.odometry.setOffsets(-84.0, -168.0);
    this.odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
    this.odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
        GoBildaPinpointDriver.EncoderDirection.FORWARD);
    this.odometry.resetPosAndIMU();
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

    public void setVelocity(double left, double right) {
      /*if (input >= -0.05 && input <= 0.05)
        return;*/
      this.riserLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
      this.riserRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

      float ticksPerRev = 537.6f;
      int maxRPM = 312;

      double leftTargetVelocity = left * ((maxRPM * ticksPerRev) / 60);
      double rightTargetVelocity = right * ((maxRPM * ticksPerRev) / 60);

      this.riserLeft.setVelocity(leftTargetVelocity);
      this.riserRight.setVelocity(rightTargetVelocity);
    }
  }

  public class Intake {
    public Servo intakeElbow;
    public CRServo intakeWheel;
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotorEx extendingArm;

    Intake(Servo intakeElbow, CRServo intakeWheel, DcMotorEx extendingArm) {
      this.intakeElbow = intakeElbow;
      this.intakeWheel = intakeWheel;
      this.extendingArm = extendingArm;
    }

    public void setElbow(double position) {
      this.intakeElbow.setPosition(position);
    }

    public void setArmVelocity(double power) {
      this.extendingArm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
      float ticksPerRev = 537.6f;
      int maxRPM = 312;

      double targetVelocity = power * ((maxRPM * ticksPerRev) / 60);

      this.extendingArm.setVelocity(targetVelocity);
    }
  }
}
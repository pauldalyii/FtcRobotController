package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Range;
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

  private DcMotorEx extendingArm;

  private DcMotorEx riserLeft;
  private DcMotorEx riserRight;
  public Lift lift;

  private Servo intakeElbow;
  private Servo intakeWrist;
  private CRServo intakeWheel;
  private Servo bucket;

  public DistanceSensor leftDistance;
  public DistanceSensor rightDistance;
  public DistanceSensor centerDistance;

  public GoBildaPinpointDriver odometry;

  public Intake intake;

  private double driveVelocity = 2750;//1250;//2000;

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
    this.intakeElbow = hardwareMap.get(Servo.class, "INTAKE_JOINT_1");
    this.intakeElbow.setDirection(Servo.Direction.REVERSE);
    this.intakeWrist = hardwareMap.get(Servo.class, "INTAKE_JOINT_2");
    this.intakeWrist.setDirection(Servo.Direction.REVERSE);
    this.intakeWheel = hardwareMap.get(CRServo.class, "INTAKE_WHEEL");
    this.intakeWheel.setDirection(CRServo.Direction.REVERSE);
    this.bucket = hardwareMap.get(Servo.class, "LIFT_BUCKET");
    this.bucket.setDirection(Servo.Direction.REVERSE);

    this.intake = new Intake(this.intakeElbow, this.intakeWrist, this.intakeWheel, this.extendingArm, this.bucket);
    this.lift = new Lift(this.riserLeft, this.riserRight);

    this.leftDistance = hardwareMap.get(DistanceSensor.class, "LEFT_DISTANCE");
    this.rightDistance = hardwareMap.get(DistanceSensor.class, "RIGHT_DISTANCE");
    this.centerDistance = hardwareMap.get(DistanceSensor.class, "CENTER_DISTANCE");

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

  public void drive(double x, double y, double rotate, double gyro) {
    double tempX = x * Math.cos(gyro) + y * Math.sin(gyro);
    double tempY = -x * Math.sin(gyro) + y * Math.cos(gyro);

    this.drive(tempX, tempY, rotate);
  }

  public class Lift {
    public DcMotorEx riserLeft;
    public DcMotorEx riserRight;

    Lift(DcMotorEx riserLeft, DcMotorEx riserRight) {
      this.riserLeft = riserLeft;
      this.riserRight = riserRight;

      this.riserLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
      this.riserRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    private final int MAX_HEIGHT = 4250; //? Was 4000 but it couldn't reach
    private int lastLeftPosition = 0;
    private int lastRightPosition = 0;

    public void setVelocity(double left, double right) {
      /*if (input >= -0.05 && input <= 0.05)
        return;*/
      if (left == 0) {
        this.riserLeft.setTargetPosition(lastLeftPosition);
        this.riserLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.riserLeft.setPower(lastLeftPosition < 300 ? 0 : 0.25);
      } else if (left > 0) {
        this.riserLeft.setTargetPosition(MAX_HEIGHT);
        this.riserLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.riserLeft.setPower(left);
        lastLeftPosition = this.riserLeft.getCurrentPosition();
      } else {
        float ticksPerRev = 537.6f;
        int maxRPM = 312;

        double leftTargetVelocity = left * ((maxRPM * ticksPerRev) / 60);

        this.riserLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        this.riserLeft.setVelocity(leftTargetVelocity);

        lastLeftPosition = this.riserLeft.getCurrentPosition();
      }

      if (right == 0) {
        this.riserRight.setTargetPosition(lastRightPosition);
        this.riserRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.riserRight.setPower(lastRightPosition < 300 ? 0 : 0.25);
      } else if (right > 0) {
        this.riserRight.setTargetPosition(MAX_HEIGHT);
        this.riserRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.riserRight.setPower(right);

        lastRightPosition = this.riserRight.getCurrentPosition();
      } else {
        float ticksPerRev = 537.6f;
        int maxRPM = 312;

        double rightTargetVelocity = right * ((maxRPM * ticksPerRev) / 60);

        this.riserRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        this.riserRight.setVelocity(rightTargetVelocity);

        lastRightPosition = this.riserRight.getCurrentPosition();
      }

    }
  }

  public class Intake {
    public Servo intakeElbow;
    public Servo intakeWrist;
    public CRServo intakeWheel;
    public Servo bucket;
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotorEx extendingArm;

    Intake(Servo intakeElbow, Servo intakeWrist, CRServo intakeWheel, DcMotorEx extendingArm, Servo bucket) {
      this.intakeElbow = intakeElbow;
      this.intakeWrist = intakeWrist;
      this.intakeWheel = intakeWheel;
      this.extendingArm = extendingArm;
      this.bucket = bucket;
    }

    private double scale(double input, double lower, double upper, boolean inverted) {
      if (inverted) {
        return Range.clip(Range.scale(input, lower, upper, 0, 1), 0, 1);
      }
      return Range.clip(Range.scale(input, 0, 1, lower, upper), 0, 1);
    }

    public void setElbow(double position) {
      this.intakeElbow.setPosition(this.scale(position, 0.325, 0.9, false));
    }

    public double getElbow() {
      return this.scale(this.intakeElbow.getPosition(), 0.325, 0.9, true);
    }

    public void setWrist(double position) {
      this.intakeWrist.setPosition(this.scale(position, 0.25, 1, false));
    }

    public double getWrist() {
      return this.scale(this.intakeWrist.getPosition(), 0.25, 1, true);
    }

    public void setBucket(double position) {
      this.bucket.setPosition(this.scale(position, 0.5, 0.85, false));
    }

    public double getBucket() {
      return this.scale(this.bucket.getPosition(), 0.55, 0.85, true);
    }

    private final int MAX_ARM_EXTENSION = 1500;

    public void setArmVelocity(double power) {
      if (power > 0.05) {
        this.extendingArm.setTargetPosition(MAX_ARM_EXTENSION);
        this.extendingArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        this.extendingArm.setPower(power);
      } else {
        this.extendingArm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        float ticksPerRev = 537.6f;
        int maxRPM = 312;

        double targetVelocity = power * ((maxRPM * ticksPerRev) / 60);

        this.extendingArm.setVelocity(targetVelocity);
      }
    }

    public void setWheelPower(double power) {
      this.intakeWheel.setPower(power);
    }

    public void resetServos() {
      this.setElbow(0.75);
      this.setWrist(1);
      this.setBucket(0);
    }
  }
}
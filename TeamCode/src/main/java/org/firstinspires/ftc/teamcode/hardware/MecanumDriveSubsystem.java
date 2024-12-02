package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveSubsystem extends Subsystem implements Loop {
    private RobotHardware robot;

    public DcMotor frontLeftMotor;
    private double frontLeftPower = 0;
    
    public DcMotor frontRightMotor;
    private double frontRightPower = 0;

    public DcMotor backLeftMotor;
    private double backLeftPower = 0;

    public DcMotor backRightMotor;
    private double backRightPower = 0;

    public MecanumDriveSubsystem(RobotHardware robot) {
        this.robot = robot;
    }

    public void init() {
        frontLeftMotor = robot.hardwareMap.get(DcMotor.class, "DRIVE_FRONT_LEFT");
        frontRightMotor = robot.hardwareMap.get(DcMotor.class, "DRIVE_FRONT_RIGHT");
        backLeftMotor = robot.hardwareMap.get(DcMotor.class, "DRIVE_REAR_LEFT");
        backRightMotor = robot.hardwareMap.get(DcMotor.class, "DRIVE_REAR_RIGHT");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double y = -robot.driverGamepad.left_stick_y; // Forward/Back
        double x = robot.driverGamepad.left_stick_x;  // Left/Right
        double rotate = robot.driverGamepad.right_stick_x; // Rotate

        frontLeftPower = y + x + rotate;
        frontRightPower = y - x - rotate;
        backLeftPower = y - x + rotate;
        backRightPower = y + x - rotate;

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
    }

    public void addTelemetry() {
        robot.telemetryObserver.addTelemetry("DRIVE_FRONT_LEFT Power", backLeftPower);
        robot.telemetryObserver.addTelemetry("DRIVE_FRONT_RIGHT Power", frontRightPower);
        robot.telemetryObserver.addTelemetry("DRIVE_REAR_LEFT Power", backLeftPower);
        robot.telemetryObserver.addTelemetry("DRIVE_REAR_RIGHT Power", backRightPower);
    }
}

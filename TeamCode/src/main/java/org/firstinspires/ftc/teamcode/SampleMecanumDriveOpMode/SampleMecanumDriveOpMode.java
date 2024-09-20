package org.firstinspires.ftc.teamcode.mecanumdrivesample;
import org.firstinspires.ftc.teamcode.BaseOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class SampleMecanumDriveOpMode extends BaseOpMode {
    protected DcMotor frontLeftMotor;
    protected DcMotor frontRightMotor;
    protected DcMotor backLeftMotor;
    protected DcMotor backRightMotor;

    private DriveCommand driveCommand;

    @Override
    protected void initializeHardware() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        
        MecanumDriveCommand mecanumDriveCommand = new MecanumDriveCommand(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
        driveCommand = new DriveCommandTelemetryDecorator(mecanumDriveCommand, this, frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    }

    @Override
    protected void executeLinearOp() {
    }

    @Override
    protected void executeTeleOp() {
        double y = -gamepad1.left_stick_y; // Forward/backward
        double x = gamepad1.left_stick_x * 1.1; // Strafing left/right
        double rx = gamepad1.right_stick_x; // Rotation

        driveCommand.execute(y, x, rx);
    }
}
package org.firstinspires.ftc.teamcode.mecanumdrivesample;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveCommandTelemetryDecorator implements DriveCommand {

    private final DriveCommand decoratedCommand;
    private final LinearOpMode opMode;
    private final DcMotor frontLeftMotor;
    private final DcMotor frontRightMotor;
    private final DcMotor backLeftMotor;
    private final DcMotor backRightMotor;

    public DriveCommandTelemetryDecorator(DriveCommand decoratedCommand, LinearOpMode opMode, DcMotor frontLeftMotor, DcMotor frontRightMotor, DcMotor backLeftMotor, DcMotor backRightMotor) {
        this.decoratedCommand = decoratedCommand;
        this.opMode = opMode;
        this.frontLeftMotor = frontLeftMotor;
        this.frontRightMotor = frontRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.backRightMotor = backRightMotor;
    }

    @Override
    public void execute(double y, double x, double rx) {
        decoratedCommand.execute(y, x, rx);

        opMode.telemetry.addData("Front Left Power", frontLeftMotor.getPower());
        opMode.telemetry.addData("Front Right Power", frontRightMotor.getPower());
        opMode.telemetry.addData("Back Left Power", backLeftMotor.getPower());
        opMode.telemetry.addData("Back Right Power", backRightMotor.getPower());
        opMode.telemetry.update();
    }
}
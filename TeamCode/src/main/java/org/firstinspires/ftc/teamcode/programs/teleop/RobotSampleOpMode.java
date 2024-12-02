package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Robot Sample", group = "TeleOp")
public class RobotSampleOpMode extends OpMode {
    private RobotHardware robot;

    @Override
    public void init() {
        robot = RobotHardware.getInstance();
        robot.init(hardwareMap, this); // Initialize game pads, telemetry and subsystems
    }

    @Override
    public void loop() {
        robot.odometrySubsystem.loop();
        robot.odometrySubsystem.addTelemetry();

        robot.mecanumDriveSubsystem.loop();
        robot.mecanumDriveSubsystem.addTelemetry();

        // robot.intakeSubsystem.loop();

        // robot.bucketSubsystem.loop();

        // robot.liftSubsystem.loop();

        robot.telemetryObserver.update();
    }
}
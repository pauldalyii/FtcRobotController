package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotHardware {
    private static RobotHardware instance = null;

    protected HardwareMap hardwareMap = null;

    protected OpMode opMode = null;

    public TelemetryObserver telemetryObserver = null;

    public Gamepad driverGamepad = null;
    public Gamepad technicianGamepad = null;

    public MecanumDriveSubsystem mecanumDriveSubsystem = null;
    public OdometrySubsystem odometrySubsystem = null;

    private RobotHardware() {
        // Private constructor to prevent instantiation
    }

    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }
        return instance;
    }

    public void init(HardwareMap hardwareMap, OpMode opMode) {
        this.hardwareMap = hardwareMap;

        this.opMode = opMode;

        this.telemetryObserver = new TelemetryObserver(opMode);

        this.driverGamepad = opMode.gamepad1;
        this.technicianGamepad = opMode.gamepad2;

        // Initialize subsystems
        this.mecanumDriveSubsystem = new MecanumDriveSubsystem(this);
        this.mecanumDriveSubsystem.init();

        this.odometrySubsystem = new OdometrySubsystem(this);
        this.odometrySubsystem.init();
    }
}

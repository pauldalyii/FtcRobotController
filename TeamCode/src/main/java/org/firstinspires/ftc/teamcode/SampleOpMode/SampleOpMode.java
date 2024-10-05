package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Sample OpMode", group = "Autonomous")
public class SampleOpMode extends OpMode {
    private TelemetryStrategy telemetryStrategy;

    @Override
    public void init() {
        String buildNumber = System.getProperty("buildNumber", "unknown");
        telemetryStrategy = new SimpleTelemetryStrategy(telemetry, buildNumber);
    }

    @Override
    public void loop() {
        telemetryStrategy.log("Hello, World!");
        telemetryStrategy.write();
    }

    @Override
    public void stop() {
        telemetryStrategy.write();
    }
}
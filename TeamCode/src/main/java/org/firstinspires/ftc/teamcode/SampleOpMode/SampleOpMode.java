package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Sample OpMode", group = "Autonomous")
public class SampleOpMode extends OpMode {
    SampleOpModeApp app = null;

    public SampleOpMode() {
        super();
        
        String buildNumber = System.getProperty("buildNumber", "unknown");
        TelemetryStrategy telemetryStrategy = new RobotCoreTelemetryStrategy(telemetry);

        app = new SampleOpModeApp(  buildNumber,
                                    telemetryStrategy);
    }

    @Override
    public void init() {
        app.init();
    }

    @Override
    public void loop() {
        app.loop();
    }

    @Override
    public void stop() {
        app.stop();
    }
}
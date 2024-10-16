package org.firstinspires.ftc.teamcode;

public class SampleOpModeApp {
    private TelemetryStrategy telemetryStrategy;
    private String buildNumber;
    

    public SampleOpModeApp( String buildNumber,
                            TelemetryStrategy telemetryStrategy) {
        super();

        this.telemetryStrategy = telemetryStrategy;
        this.buildNumber = buildNumber;
    }

    public void init() {
        this.telemetryStrategy.log("Build Number", buildNumber);
    }

    public void loop() {
        telemetryStrategy.log("Message", "Hello, World!");
        telemetryStrategy.write();
    }

    public void stop() {
        telemetryStrategy.write();
    }
}
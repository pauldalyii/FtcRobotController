package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop;

class SimpleTelemetryStrategy implements TelemetryStrategy {
    private Telemetry telemetry;

    public SimpleTelemetryStrategy(Telemetry telemetry, String buildNumber) {
        this.telemetry = telemetry;
        this.telemetry.addData("Build Number", buildNumber);
    }

    @Override
    public void log(String message) {
        telemetry.addData(message);
    }

    @Override
    public void write() {
        telemetry.update();
    }
}
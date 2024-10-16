package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

class RobotCoreTelemetryStrategy implements TelemetryStrategy {
    private Telemetry telemetry;

    public RobotCoreTelemetryStrategy(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void log(String title, String message) {
        telemetry.addData(title, message);
    }

    @Override
    public void write() {
        telemetry.update();
    }
}
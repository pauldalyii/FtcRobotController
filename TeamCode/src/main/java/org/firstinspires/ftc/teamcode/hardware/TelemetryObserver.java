package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TelemetryObserver {
    private OpMode opMode;

    public TelemetryObserver(OpMode opMode) {
        this.opMode = opMode;
    }

    public void addTelemetry(String caption, Object value) {
        opMode.telemetry.addData(caption, value);
    }

    public void update() {
        opMode.telemetry.update();
    }
}

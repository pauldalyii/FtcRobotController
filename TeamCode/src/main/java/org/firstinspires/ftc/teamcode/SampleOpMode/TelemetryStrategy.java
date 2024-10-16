package org.firstinspires.ftc.teamcode;

interface TelemetryStrategy {
    void log(String title, String message);
    void write();
}
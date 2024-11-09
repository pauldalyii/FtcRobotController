package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Camera;

@Autonomous(name = "AprilTag Diagnostics", group = "Diagnostics")
public class AprilTagDiagnostics extends OpMode {
  private Camera camera;

  @Override
  public void init() {
    this.camera = new Camera(hardwareMap);
    try {
      this.camera.resume();
    } catch (Camera.CameraNotAttachedException e) {
      telemetry.speak("Camera not attached");
      requestOpModeStop();
    }
  }

  @Override
  public void loop() {
    this.camera.telemetryAprilTag(telemetry);
  }
}

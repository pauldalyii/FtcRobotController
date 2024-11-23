package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.Camera;

@Autonomous(name = "Distance Sensor Diagnsotics", group = "Diagnostics")
public class DistanceSensorDiagnostics extends OpMode {
  private DistanceSensor leftDistanceSensor;
  private DistanceSensor rightDistanceSensor;

  @Override
  public void init() {
    this.leftDistanceSensor = hardwareMap.get(DistanceSensor.class, "LEFT_DISTANCE_SENSOR");
    this.rightDistanceSensor = hardwareMap.get(DistanceSensor.class, "RIGHT_DISTANCE_SENSOR");
  }

  @Override
  public void loop() {
    telemetry.addData("Left Distance Sensor", this.leftDistanceSensor.getDistance(DistanceUnit.INCH));
    telemetry.addData("Right Distance Sensor", this.rightDistanceSensor.getDistance(DistanceUnit.INCH));
  }
}

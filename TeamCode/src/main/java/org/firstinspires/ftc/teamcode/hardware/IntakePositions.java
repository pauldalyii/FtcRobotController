package org.firstinspires.ftc.teamcode.hardware;

public enum IntakePositions {
  Tip(0.0), // Tip the robot to hang
  Collect(0.2), // Pick up samples
  Hover(0.275), // Get into chamber
  ClearLift(0.7), // Move the intake away from the lift bucket
  Transfer(1.0); // Transfer samples into the lift bucket

  private final double value;

  IntakePositions(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
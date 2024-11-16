package org.firstinspires.ftc.teamcode.hardware;

public enum LiftBucketPositions {
  Catch(0.25), // Tip the bucket to catch samples
  Dump(0.65), // Dump the samples into ether the high or low bucket
  SpecimenPickup(0.1); // Align the bucket to pick up a specimen

  private final double value;

  LiftBucketPositions( double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
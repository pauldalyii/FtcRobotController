package org.firstinspires.ftc.teamcode.hardware;

public enum RaiseHeight {
  Minimum(0),
  HighBasket(4370),
  LowBasket(2500),
  HighChamber(1),
  LowChamber(1),
  HighHangingRung(1),
  LowHangingRung(1),
  Maximum(0);

  private final int value;

  RaiseHeight(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
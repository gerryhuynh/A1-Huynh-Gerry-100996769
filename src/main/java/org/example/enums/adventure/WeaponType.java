package org.example.enums.adventure;

public enum WeaponType implements AdventureType {
  D5(5, 6), 
  H10(10, 12), 
  S10(10, 16),
  B15(15, 8), 
  L20(20, 6), 
  E30(30, 2);

  private final int value;
  private final int defaultCount;

  WeaponType(int value, int defaultCount) {
    this.value = value;
    this.defaultCount = defaultCount;
  }

  public int getValue() {
    return value;
  }

  public int getDefaultCount() {
    return defaultCount;
  }
}

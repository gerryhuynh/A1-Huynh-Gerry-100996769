package org.example.enums.adventure;

public enum WeaponType implements AdventureType {
  D5(5), H10(10), S10(10), B15(15), L20(20), E30(30);

  private final int value;

  WeaponType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  } 
}

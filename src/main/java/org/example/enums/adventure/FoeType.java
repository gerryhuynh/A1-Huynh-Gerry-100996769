package org.example.enums.adventure;

public enum FoeType implements AdventureType {
  F5(5), F10(10), F15(15), F20(20), F25(25), F30(30), F35(35), F40(40), F50(50), F70(70);

  private final int value;

  FoeType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}

package game.enums.adventure;

public enum FoeType implements AdventureType {
  F5(5, 8),
  F10(10, 7),
  F15(15, 8),
  F20(20, 7),
  F25(25, 7),
  F30(30, 4),
  F35(35, 4),
  F40(40, 2),
  F50(50, 2),
  F70(70, 1);

  private final int value;
  private final int defaultCount;

  FoeType(int value, int defaultCount) {
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

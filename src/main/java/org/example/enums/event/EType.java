package org.example.enums.event;

public enum EType implements EventType {
  PLAGUE("Plague", 1, "Current player loses 2 shields"), 
  QUEENS_FAVOR("Queen's Favor", 2, "Current player draws 2 adventure cards"), 
  PROSPERITY("Prosperity", 2, "All players draw 2 adventure cards");

  private final String name;
  private final int defaultCount;
  private final String effectDesc;

  EType(String name, int defaultCount, String effectDesc) {
    this.name = name;
    this.defaultCount = defaultCount;
    this.effectDesc = effectDesc;
  }

  public int getDefaultCount() {
    return defaultCount;
  }

  public String getEffectDesc() {
    return effectDesc;
  }

  @Override
  public String toString() {
    return String.format("%s", name);
  }
}

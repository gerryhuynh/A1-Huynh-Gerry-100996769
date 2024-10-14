package org.example.enums.event;

public enum EType implements EventType {
  PLAGUE("Plague", 1), 
  QUEENS_FAVOR("Queen's Favor", 2), 
  PROSPERITY("Prosperity", 2);

  private final String name;
  private final int defaultCount;

  EType(String name, int defaultCount) {
    this.name = name;
    this.defaultCount = defaultCount;
  }

  public int getDefaultCount() {
    return defaultCount;
  }

  public String getEffectDesc() {
    return "";
  }

  @Override
  public String toString() {
    return String.format("%s", name);
  }
}

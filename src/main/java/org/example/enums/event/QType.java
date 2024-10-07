package org.example.enums.event;

public enum QType implements EventType {
  Q2(3), 
  Q3(4), 
  Q4(3), 
  Q5(2);

  private final int defaultCount;

  QType(int defaultCount) {
    this.defaultCount = defaultCount;
  }

  public int getDefaultCount() {
    return defaultCount;
  }
}

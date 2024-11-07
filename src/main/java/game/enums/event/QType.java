package game.enums.event;

public enum QType implements EventType {
  Q2(2, 3),
  Q3(3, 4),
  Q4(4, 3),
  Q5(5, 2);

  private final int defaultCount;
  private final int numStages;

  QType(int numStages,int defaultCount) {
    this.numStages = numStages;
    this.defaultCount = defaultCount;
  }

  public int getDefaultCount() {
    return defaultCount;
  }

  public int getNumStages() {
    return numStages;
  }

  public String getEffectDesc() {
    return String.format("Play a %s quest.", this);
  }
}

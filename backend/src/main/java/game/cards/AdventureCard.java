package game.cards;

import game.enums.adventure.AdventureType;

public class AdventureCard extends Card<AdventureType> {
  private final int value;

  public AdventureCard(AdventureType type) {
    super(type);

    this.value = type.getValue();
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.format("%s", getType());
  }
}

package org.example.cards;

import org.example.enums.adventure.AdventureType;

public class AdventureCard extends Card<AdventureType> {
  private final int value;

  public AdventureCard(AdventureType type) {
    super(type);
    
    this.value = type.getValue();
  }

  public int getValue() {
    return value;
  }
}

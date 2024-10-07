package org.example.cards;

public class AdventureCard extends Card {
  private final int value;

  public AdventureCard(String type, String subtype, int value) {
    super(type, subtype);
    
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}

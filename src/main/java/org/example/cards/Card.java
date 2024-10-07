package org.example.cards;

public abstract class Card {
  private final String type;
  private final String subtype;

  public Card(String type, String subtype) {
    this.type = type;
    this.subtype = subtype;
  }

  public String getType() {
    return type;
  }

  public String getSubtype() {
    return subtype;
  }
}

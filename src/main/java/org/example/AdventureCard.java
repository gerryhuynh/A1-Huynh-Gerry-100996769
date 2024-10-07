package org.example;

public class AdventureCard {
  private final String type;
  private final String subtype;
  private final int value;

  public AdventureCard(String type, String subtype, int value) {
    this.type = type;
    this.subtype = subtype;
    this.value = value;
  }

  public String getType() {
    return type;
  }

  public String getSubtype() {
    return subtype;
  }

  public int getValue() {
    return value;
  }
}

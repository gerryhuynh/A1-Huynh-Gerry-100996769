package org.example;

public class EventCard {
  private final String type;
  private final String subtype;
  private final Runnable effect;

  public EventCard(String type, String subtype, Runnable effect) {
    this.type = type;
    this.subtype = subtype;
    this.effect = effect;
  }

  public String getType() {
    return type;
  }

  public String getSubtype() {
    return subtype;
  }
}

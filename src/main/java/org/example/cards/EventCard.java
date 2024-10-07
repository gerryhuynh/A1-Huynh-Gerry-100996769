package org.example.cards;

public class EventCard extends Card {
  private final Runnable effect;

  public EventCard(String type, String subtype, Runnable effect) {
    super(type, subtype);
    
    this.effect = effect;
  }
}

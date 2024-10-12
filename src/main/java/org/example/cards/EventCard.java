package org.example.cards;

import org.example.enums.event.EventType;

public class EventCard extends Card<EventType> {
  private final Runnable effect;

  public EventCard(EventType type, Runnable effect) {
    super(type);
    
    this.effect = effect;
  }

  @Override
  public String toString() {
    return getType().toString();
  }
}

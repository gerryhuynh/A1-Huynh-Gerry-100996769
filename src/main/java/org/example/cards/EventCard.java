package org.example.cards;

import org.example.enums.event.EventType;
import org.example.Game;
import org.example.Player;
import org.example.enums.event.EType;

public class EventCard extends Card<EventType> {
  public EventCard(EventType type) {
    super(type);
  }

  public String play(Game game) {
    switch (getType()) {
      case EType.PLAGUE: return plagueEffect(game.getCurrentPlayer());
      default: return "";
    }
  }

  private String plagueEffect(Player player) {
    int originalShields = player.getShields();
    player.setShields(Math.max(player.getShields() - 2, 0));
    return String.format("%s's shields: %d -> %d", player.getName(), originalShields, player.getShields());
  }

  @Override
  public String toString() {
    return getType().toString();
  }
}

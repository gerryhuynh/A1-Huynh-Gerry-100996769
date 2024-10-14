package org.example.cards;

import org.example.enums.event.EventType;
import org.example.Display;
import org.example.Game;
import org.example.Player;
import org.example.decks.AdventureDeck;
import org.example.enums.event.EType;

public class EventCard extends Card<EventType> {
  public EventCard(EventType type) {
    super(type);
  }

  public String play(Game game) {
    switch (getType()) {
      case EType.PLAGUE: return plagueEffect(game.getCurrentPlayer());
      case EType.QUEENS_FAVOR: return queensFavorEffect(game.getCurrentPlayer(), game.getAdventureDeck(), game.getDisplay());
      default: return "";
    }
  }

  private String plagueEffect(Player player) {
    int originalShields = player.getShields();
    player.setShields(Math.max(player.getShields() - 2, 0));
    return String.format("%s's shields: %d -> %d", player.getName(), originalShields, player.getShields());
  }

  private String queensFavorEffect(Player player, AdventureDeck adventureDeck, Display display) {
    return drawTwoAdventureCards(player, adventureDeck, display);
  }

  private String drawTwoAdventureCards(Player player, AdventureDeck adventureDeck, Display display) {
    player.addToHand(adventureDeck.draw(2), display);
    return String.format("%s drew 2 adventure cards", player.getName());
  }

  @Override
  public String toString() {
    return getType().toString();
  }
}

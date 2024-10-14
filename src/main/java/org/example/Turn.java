package org.example;

import org.example.cards.EventCard;

public class Turn {
  private Player player;
  private EventCard eventCard;

  public Turn(Player player) {
    this.player = player;
    this.eventCard = null;
  }

  public String playEventCard(Game game) {
    return eventCard.play(game);
  }

  public void endTurn(Display display) {
    return;
  }

  public Player getPlayer() {
    return player;
  }

  public EventCard getEventCard() {
    return eventCard;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setEventCard(EventCard eventCard) {
    this.eventCard = eventCard;
  }
}

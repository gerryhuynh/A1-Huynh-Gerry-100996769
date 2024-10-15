package org.example.quest;

import org.example.Display;
import org.example.Player;
import org.example.decks.AdventureDeck;

public class Participant {
  private Player player;

  public Participant(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  public void drawCard(AdventureDeck adventureDeck, Display display) {
    return;
  }

  @Override
  public String toString() {
    return String.format("%s", player.getName());
  }
}

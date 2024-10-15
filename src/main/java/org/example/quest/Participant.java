package org.example.quest;

import org.example.Player;

public class Participant {
  private Player player;

  public Participant(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public String toString() {
    return String.format("%s", player.getName());
  }
}

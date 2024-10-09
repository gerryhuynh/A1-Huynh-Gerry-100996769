package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
  protected static final int MAX_PLAYERS = 4;

  private final List<Player> players;

  public Game() {
    this.players = new ArrayList<>();
  }

  public void setupPlayers() {
    for (int i = 1; i <= MAX_PLAYERS; i++) {
      players.add(new Player("P" + i));
    }
  }

  public int getNumPlayers() {
    return players.size();
  }

  public List<Player> getPlayers() {
    return players;
  }
}

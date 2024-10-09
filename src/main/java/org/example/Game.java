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
      players.add(new Player());
    }
  }

  public int getNumPlayers() {
    return 0;
  }

  public List<Player> getPlayers() {
    return players;
  }
}

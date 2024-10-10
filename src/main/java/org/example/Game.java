package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.decks.AdventureDeck;

public class Game {
  protected static final int MAX_PLAYERS = 4;

  private final List<Player> players;
  private final AdventureDeck adventureDeck;

  public Game() {
    this.players = new ArrayList<>();
    this.adventureDeck = new AdventureDeck();
  }

  public void setupPlayers() {
    for (int i = 1; i <= MAX_PLAYERS; i++) {
      players.add(new Player("P" + i));
    }
  }

  public void dealAdventureCards() {
    adventureDeck.shuffle();
    for (Player player : players) {
      for (int i = 0; i < Player.MAX_HAND_SIZE; i++) {
        player.getHand().add(adventureDeck.draw());
      }
    }
  }

  public AdventureDeck getAdventureDeck() {
    return adventureDeck;
  }

  public int getNumPlayers() {
    return players.size();
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Player getCurrentTurn() {
    return new Player("");
  }

  public void nextTurn() {
    return;
  }
}

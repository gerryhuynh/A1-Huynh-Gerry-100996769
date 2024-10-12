package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.EventCard;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;
import org.example.enums.event.EType;

public class Game {
  protected static final int MAX_PLAYERS = 4;

  private final List<Player> players;
  private final AdventureDeck adventureDeck;
  private Player currentTurn;

  public Game() {
    this.players = new ArrayList<>();
    this.adventureDeck = new AdventureDeck();
  }

  public void setupPlayers() {
    for (int i = 1; i <= MAX_PLAYERS; i++) {
      players.add(new Player("P" + i));
    }
    currentTurn = players.get(0);
  }

  public void dealAdventureCards() {
    adventureDeck.shuffle();
    for (Player player : players) {
      for (int i = 0; i < Player.MAX_HAND_SIZE; i++) {
        player.getHand().add(adventureDeck.draw());
      }
    }
  }

  public void startTurn() {
    return;
  }

  public AdventureDeck getAdventureDeck() {
    return adventureDeck;
  }

  public EventDeck getEventDeck() {
    return new EventDeck();
  }

  public EventCard getCurrentEventCard() {
    return new EventCard(EType.PLAGUE, () -> {});
  }

  public int getNumPlayers() {
    return players.size();
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Player getCurrentTurn() {
    return currentTurn;
  }

  public void nextTurn() {
    int currentIndex = players.indexOf(currentTurn);
    int nextIndex = (currentIndex + 1) % players.size();
    currentTurn = players.get(nextIndex);
  }
}

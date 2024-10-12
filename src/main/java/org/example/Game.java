package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.EventCard;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;

public class Game {
  protected static final int MAX_PLAYERS = 4;

  private final List<Player> players;
  private final AdventureDeck adventureDeck;
  private final EventDeck eventDeck;
  private Player currentTurn;
  private EventCard currentEventCard;

  public Game() {
    this.players = new ArrayList<>();
    this.adventureDeck = new AdventureDeck();
    this.eventDeck = new EventDeck();
    this.currentEventCard = null;
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
    currentEventCard = eventDeck.draw();
  }

  public AdventureDeck getAdventureDeck() {
    return adventureDeck;
  }

  public EventDeck getEventDeck() {
    return eventDeck;
  }

  public EventCard getCurrentEventCard() {
    return currentEventCard;
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

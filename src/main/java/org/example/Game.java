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
  private Turn currentTurn;

  public Game() {
    this.players = new ArrayList<>();
    this.adventureDeck = new AdventureDeck();
    this.eventDeck = new EventDeck();
    this.currentTurn = null;

    adventureDeck.shuffle();
    eventDeck.shuffle();
  }

  public void setupPlayers() {
    for (int i = 1; i <= MAX_PLAYERS; i++) {
      players.add(new Player("P" + i));
    }
    currentTurn = new Turn(players.get(0));
  }

  public void dealAdventureCards() {
    for (Player player : players) {
      for (int i = 0; i < Player.MAX_HAND_SIZE; i++) {
        player.getHand().add(adventureDeck.draw());
      }
    }
  }

  public void startTurn() {
    currentTurn.setEventCard(eventDeck.draw());
  }

  public void nextTurn() {
    int currentIndex = players.indexOf(currentTurn.getPlayer());
    int nextIndex = (currentIndex + 1) % players.size();
    currentTurn.setPlayer(players.get(nextIndex));
  }

  // Getters

  public AdventureDeck getAdventureDeck() {
    return adventureDeck;
  }

  public EventDeck getEventDeck() {
    return eventDeck;
  }

  public EventCard getCurrentEventCard() {
    return currentTurn.getEventCard();
  }

  public int getNumPlayers() {
    return players.size();
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Player getCurrentPlayer() {
    return currentTurn.getPlayer();
  }
}

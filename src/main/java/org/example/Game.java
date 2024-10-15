package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.example.cards.EventCard;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;

public class Game {
  protected static final int MAX_PLAYERS = 4;
  protected static final int SHIELDS_TO_WIN = 7;

  private final List<Player> players;
  private final AdventureDeck adventureDeck;
  private final EventDeck eventDeck;
  private Turn currentTurn;
  private Display display;
  private boolean gameOver;

  public Game() {
    this.players = new ArrayList<>();
    this.adventureDeck = new AdventureDeck();
    this.eventDeck = new EventDeck();
    this.currentTurn = null;
    this.display = new Display(new PrintWriter(System.out));
    this.gameOver = false;

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
    display.printCurrentPlayer(currentTurn.getPlayer());
    display.printHand(currentTurn.getPlayer().getHand());
    currentTurn.setEventCard(eventDeck.draw());
    display.printDrawnEventCard(currentTurn.getEventCard());
    display.printEventCardEffect(currentTurn.getEventCard());
  }

  public void playEventCard() {
    display.print(currentTurn.playEventCard(this));
  }

  public void nextTurn() {
    int currentIndex = players.indexOf(currentTurn.getPlayer());
    int nextIndex = (currentIndex + 1) % players.size();
    currentTurn.setPlayer(players.get(nextIndex));
  }

  public void endTurn() {
    currentTurn.endTurn(display);
    List<Player> winners = checkWinners();
    if (winners.size() > 0) {
      endGame(winners);
    }
    nextTurn();
  }

  public List<Player> checkWinners() {
    List<Player> winners = new ArrayList<>();
    for (Player player : players) {
      if (player.getShields() >= SHIELDS_TO_WIN) {
        winners.add(player);
      }
    }
    return winners;
  }

  public void endGame(List<Player> winners) {
    display.printGameOver(winners);
    gameOver = true;
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

  public Turn getCurrentTurn() {
    return currentTurn;
  }

  public Display getDisplay() {
    return display;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setDisplay(Display display) {
    this.display = display;
  }
}
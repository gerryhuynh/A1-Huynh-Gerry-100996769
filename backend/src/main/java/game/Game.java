package game;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import game.cards.AdventureCard;
import game.cards.EventCard;
import game.decks.AdventureDeck;
import game.decks.EventDeck;
import game.quest.Quest;

public class Game {
  public static final int MAX_PLAYERS = 4;
  public static final int SHIELDS_TO_WIN = 7;

  private final List<Player> players;
  private final AdventureDeck adventureDeck;
  private final EventDeck eventDeck;
  private Turn currentTurn;
  private Display display;
  private boolean gameOver;
  private Quest quest;
  private List<Player> winners;
  public Game() {
    this(
      new AdventureDeck(),
      new EventDeck(),
      new Display(new PrintWriter(System.out)),
      true
    );
  }

  public Game(AdventureDeck adventureDeck, EventDeck eventDeck, Display display) {
    this(
      adventureDeck,
      eventDeck,
      display,
      false
    );
  }

  private Game(AdventureDeck adventureDeck, EventDeck eventDeck, Display display, boolean shuffle) {
    this.players = new ArrayList<>();
    this.adventureDeck = adventureDeck;
    this.eventDeck = eventDeck;
    this.currentTurn = null;
    this.display = display;
    this.gameOver = false;
    this.winners = new ArrayList<>();

    if (shuffle) {
      this.adventureDeck.shuffle();
      this.eventDeck.shuffle();
    }
  }

  public void setupPlayers() {
    display.print("\nSetting up 4 players...");
    for (int i = 1; i <= MAX_PLAYERS; i++) {
      players.add(new Player("P" + i));
    }
    currentTurn = new Turn(players.get(0));
  }

  public void dealAdventureCards() {
    display.print("\nDealing " + Player.MAX_HAND_SIZE + " adventure cards to each player...");
    List<AdventureCard> cardsToDeal = new ArrayList<>();
    for (Player player : players) {
      for (int i = 0; i < Player.MAX_HAND_SIZE; i++) {
        cardsToDeal.add(adventureDeck.draw());
      }
      player.addToHand(cardsToDeal, display, true);
      cardsToDeal.clear();
    }
  }

  public void startTurn() {
    display.printCurrentPlayer(currentTurn.getPlayer());
    display.printHand(currentTurn.getPlayer().getHand());
    drawEventCard();
  }

  public void drawEventCard() {
    currentTurn.setEventCard(eventDeck.draw());
    display.printDrawnEventCard(currentTurn.getEventCard());
    display.printEventCardEffect(currentTurn.getEventCard());
  }

  public void playEventCard() {
    display.print(currentTurn.playEventCard(this));
  }

  public Quest createQuest(int numStages) {
    display.print("\nCreating quest with " + numStages + " stages...");
    quest = new Quest(numStages, getCurrentPlayer());
    return quest;
  }

  public void startQuest() {
    quest.findSponsor(players, currentTurn.getPlayer(), display);
    if (quest.getSponsor() != null) {
      quest.setup(display);
      quest.startAttack(display, players, adventureDeck);
    }
  }

  public void nextTurn() {
    int currentIndex = players.indexOf(currentTurn.getPlayer());
    int nextIndex = (currentIndex + 1) % players.size();
    currentTurn.setPlayer(players.get(nextIndex));
  }

  public void endTurn() {
    currentTurn.endTurn(display);
    checkWinners();
    if (winners.size() > 0) {
      endGame(winners);
    }
    nextTurn();
  }

  public void checkWinners() {
    winners.clear();
    for (Player player : players) {
      if (player.getShields() >= SHIELDS_TO_WIN) {
        winners.add(player);
      }
    }
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

  public Quest getQuest() {
    return quest;
  }

  public List<Player> getWinners() {
    return winners;
  }
}

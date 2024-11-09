import java.io.PrintWriter;

import game.Display;
import game.Game;
import game.decks.AdventureDeck;
import game.decks.EventDeck;

// import shared.A1Scenario;
import shared.TestAdventureDeck;
import shared.TestEventDeck;
// import shared.TwoWinnerGameTwoWinnerQuest;
import shared.OneWinnerGameWithEvents;

public class Main {
  public static void main(String[] args) {

    // COMPULSORY SCENARIO

    // Display display = new Display(new PrintWriter(System.out));
    // AdventureDeck adventureDeck = new TestAdventureDeck(A1Scenario.getAdventureCards());
    // EventDeck eventDeck = new TestEventDeck(A1Scenario.getEventCards());
    // Game game = new Game(adventureDeck, eventDeck, display);

    // game.setupPlayers();
    // game.dealAdventureCards();

    // game.getAdventureDeck().addToTopOfDeck(A1Scenario.getRiggedTopOfDeck());
    // game.getPlayers().get(0).overWriteHand(A1Scenario.getP1Hand(), display);
    // game.getPlayers().get(1).overWriteHand(A1Scenario.getP2Hand(), display);
    // game.getPlayers().get(2).overWriteHand(A1Scenario.getP3Hand(), display);
    // game.getPlayers().get(3).overWriteHand(A1Scenario.getP4Hand(), display);

    // game.startTurn();
    // game.playEventCard();


    // TWO WINNER GAME TWO WINNER QUEST

    // Display display = new Display(new PrintWriter(System.out));
    // AdventureDeck adventureDeck = new TestAdventureDeck(TwoWinnerGameTwoWinnerQuest.getAdventureCards());
    // EventDeck eventDeck = new TestEventDeck(TwoWinnerGameTwoWinnerQuest.getEventCards());
    // Game game = new Game(adventureDeck, eventDeck, display);

    // game.setupPlayers();
    // game.dealAdventureCards();

    // game.getAdventureDeck().addToTopOfDeck(TwoWinnerGameTwoWinnerQuest.getRiggedTopOfDeck());
    // game.getPlayers().get(0).overWriteHand(TwoWinnerGameTwoWinnerQuest.getP1Hand(), display);
    // game.getPlayers().get(1).overWriteHand(TwoWinnerGameTwoWinnerQuest.getP2Hand(), display);
    // game.getPlayers().get(2).overWriteHand(TwoWinnerGameTwoWinnerQuest.getP3Hand(), display);
    // game.getPlayers().get(3).overWriteHand(TwoWinnerGameTwoWinnerQuest.getP4Hand(), display);

    // game.startTurn();
    // game.playEventCard();
    // game.endTurn();

    // game.startTurn();
    // game.playEventCard();
    // game.endTurn();

    // ONE WINNER GAME WITH EVENTS

    Display display = new Display(new PrintWriter(System.out));
    AdventureDeck adventureDeck = new TestAdventureDeck(OneWinnerGameWithEvents.getAdventureCards());
    EventDeck eventDeck = new TestEventDeck(OneWinnerGameWithEvents.getEventCards());
    Game game = new Game(adventureDeck, eventDeck, display);

    game.setupPlayers();
    game.dealAdventureCards();

    game.getAdventureDeck().addToTopOfDeck(OneWinnerGameWithEvents.getRiggedTopOfDeck());
    game.getPlayers().get(0).overWriteHand(OneWinnerGameWithEvents.getP1Hand(), display);
    game.getPlayers().get(1).overWriteHand(OneWinnerGameWithEvents.getP2Hand(), display);
    game.getPlayers().get(2).overWriteHand(OneWinnerGameWithEvents.getP3Hand(), display);
    game.getPlayers().get(3).overWriteHand(OneWinnerGameWithEvents.getP4Hand(), display);

    game.startTurn();
    game.playEventCard();
    game.endTurn();

    game.startTurn();
    game.playEventCard();
    game.endTurn();

    game.startTurn();
    game.playEventCard();
    game.endTurn();

    game.startTurn();
    game.playEventCard();
    game.endTurn();

    game.startTurn();
    game.playEventCard();
    game.endTurn();

    // NORMAL GAME LOOP

    // Display display = new Display(new PrintWriter(System.out));
    // Game game = new Game();

    // game.setDisplay(display);
    // game.setupPlayers();
    // game.dealAdventureCards();

    // while (!game.isGameOver()) {
    //   game.startTurn();
    //   game.playEventCard();
    //   game.endTurn();
    // }
  }
}

package org.example;

import java.io.PrintWriter;
import org.example.cards.EventCard;
import org.example.enums.event.QType;

import common.A1Scenario;

public class Main {
  public static void main(String[] args) {

    // COMPULSORY SCENARIO

    Display display = new Display(new PrintWriter(System.out));
    Game game = new Game();

    game.setupPlayers();
    game.setDisplay(display);

    game.dealAdventureCards();
    game.getAdventureDeck().addToTopOfDeck(A1Scenario.getRiggedTopOfDeck());
    game.getPlayers().get(0).overWriteHand(A1Scenario.getP1Hand(), display);
    game.getPlayers().get(1).overWriteHand(A1Scenario.getP2Hand(), display);
    game.getPlayers().get(2).overWriteHand(A1Scenario.getP3Hand(), display);
    game.getPlayers().get(3).overWriteHand(A1Scenario.getP4Hand(), display);

    game.startTurn();
    game.getCurrentTurn().setEventCard(new EventCard(QType.Q4));

    game.playEventCard();


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

package org.example;

import java.io.PrintWriter;

import org.example.cards.EventCard;
import org.example.enums.event.QType;
import org.example.quest.Quest;

public class Main {
  public static void main(String[] args) {

    Game game = new Game();
    game.setupPlayers();
    game.createQuest(2);
    Quest quest = game.getQuest();
    quest.setSponsor(game.getPlayers().get(0));

    game.getDisplay().printAttackSetup(1, quest.getParticipants().get(1));



    // Display display = new Display(new PrintWriter(System.out));
    // Game game = new Game();
    // game.setDisplay(display);
    // game.setupPlayers();
    // game.dealAdventureCards();

    // while (!game.isGameOver()) {
    //   game.startTurn();
    //   game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
    //   game.playEventCard();
    //   // game.getPlayers().forEach(player -> player.setShields(Game.SHIELDS_TO_WIN));
    //   // game.getPlayers().get(1).setShields(8);
    //   // game.getPlayers().get(2).setShields(4);
    //   game.endTurn();
    // }
  }
}

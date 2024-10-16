package org.example;

import java.io.PrintWriter;
import java.util.List;

import org.example.cards.AdventureCard;
import org.example.cards.EventCard;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.enums.event.QType;
import org.example.quest.Participant;
import org.example.quest.Quest;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();
    game.setupPlayers();
    game.createQuest(2);
    Quest quest = game.getQuest();

    quest.getParticipants().add(new Participant(game.getPlayers().get(1)));
    quest.getParticipants().add(new Participant(game.getPlayers().get(2)));
    quest.rewardShields(game.getDisplay());
    // quest.getStages().get(0).addCard(new AdventureCard(FoeType.F10));
    // participant.addCardToAttack(new AdventureCard(WeaponType.D5), game.getDisplay());

    // quest.resolveAttacks(0, game.getDisplay());


    // game.getDisplay().promptNextParticipantAttack(1, quest.getParticipants().get(0));

    // Participant participant = quest.getParticipants().get(0);
    // participant.addCardToAttack(new AdventureCard(WeaponType.D5), game.getDisplay());
    // System.out.println(participant.getAttackCards());

    // List<AdventureCard> participantAttackCards = quest.getParticipants().get(0).getAttackCards();
    // participantAttackCards.add(new AdventureCard(WeaponType.D5));
    // AdventureCard cardToAdd = new AdventureCard(WeaponType.D5);
    // boolean validCard = quest.validateAttackCard(participantAttackCards, cardToAdd, game.getDisplay());
    // System.out.println(validCard);


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

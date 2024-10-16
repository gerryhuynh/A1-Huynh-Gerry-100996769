package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
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

    // COMPULSORY SCENARIO

    Display display = new Display(new PrintWriter(System.out));
    Game game = new Game();

    game.setupPlayers();
    game.setDisplay(display);

    game.dealAdventureCards();
    game.getAdventureDeck().addToTopOfDeck(riggedTopOfDeck());
    game.getPlayers().get(0).overWriteHand(p1Hand(), display);
    game.getPlayers().get(1).overWriteHand(p2Hand(), display);
    game.getPlayers().get(2).overWriteHand(p3Hand(), display);
    game.getPlayers().get(3).overWriteHand(p4Hand(), display);

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
    //   game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
    //   game.playEventCard();
    //   game.endTurn();
    // }
  }

  private static List<AdventureCard> riggedTopOfDeck() {
    List<AdventureCard> riggedDeck = new ArrayList<>();
    riggedDeck.add(new AdventureCard(FoeType.F30));
    riggedDeck.add(new AdventureCard(WeaponType.S10));
    riggedDeck.add(new AdventureCard(WeaponType.B15));
    riggedDeck.add(new AdventureCard(FoeType.F10));
    riggedDeck.add(new AdventureCard(WeaponType.L20));
    riggedDeck.add(new AdventureCard(WeaponType.L20));
    riggedDeck.add(new AdventureCard(WeaponType.B15));
    riggedDeck.add(new AdventureCard(WeaponType.S10));
    riggedDeck.add(new AdventureCard(FoeType.F30));
    riggedDeck.add(new AdventureCard(WeaponType.L20));

    return riggedDeck;
  }

  private static List<AdventureCard> p1Hand() {
    List<AdventureCard> P1_hand = new ArrayList<>();
    P1_hand.add(new AdventureCard(FoeType.F5));
    P1_hand.add(new AdventureCard(FoeType.F5));
    P1_hand.add(new AdventureCard(FoeType.F15));
    P1_hand.add(new AdventureCard(FoeType.F15));
    P1_hand.add(new AdventureCard(WeaponType.D5));
    P1_hand.add(new AdventureCard(WeaponType.S10));
    P1_hand.add(new AdventureCard(WeaponType.S10));
    P1_hand.add(new AdventureCard(WeaponType.H10));
    P1_hand.add(new AdventureCard(WeaponType.H10));
    P1_hand.add(new AdventureCard(WeaponType.B15));
    P1_hand.add(new AdventureCard(WeaponType.B15));
    P1_hand.add(new AdventureCard(WeaponType.L20));

    return P1_hand;
  }

  private static List<AdventureCard> p2Hand() {
    List<AdventureCard> P2_hand = new ArrayList<>();
    P2_hand.add(new AdventureCard(FoeType.F5));
    P2_hand.add(new AdventureCard(FoeType.F5));
    P2_hand.add(new AdventureCard(FoeType.F15));
    P2_hand.add(new AdventureCard(FoeType.F15));
    P2_hand.add(new AdventureCard(FoeType.F40));
    P2_hand.add(new AdventureCard(WeaponType.D5));
    P2_hand.add(new AdventureCard(WeaponType.S10));
    P2_hand.add(new AdventureCard(WeaponType.H10));
    P2_hand.add(new AdventureCard(WeaponType.H10));
    P2_hand.add(new AdventureCard(WeaponType.B15));
    P2_hand.add(new AdventureCard(WeaponType.B15));
    P2_hand.add(new AdventureCard(WeaponType.E30));

    return P2_hand;
  }

  private static List<AdventureCard> p3Hand() {
    List<AdventureCard> P3_hand = new ArrayList<>();
    P3_hand.add(new AdventureCard(FoeType.F5));
    P3_hand.add(new AdventureCard(FoeType.F5));
    P3_hand.add(new AdventureCard(FoeType.F5));
    P3_hand.add(new AdventureCard(FoeType.F15));
    P3_hand.add(new AdventureCard(WeaponType.D5));
    P3_hand.add(new AdventureCard(WeaponType.S10));
    P3_hand.add(new AdventureCard(WeaponType.S10));
    P3_hand.add(new AdventureCard(WeaponType.S10));
    P3_hand.add(new AdventureCard(WeaponType.H10));
    P3_hand.add(new AdventureCard(WeaponType.H10));
    P3_hand.add(new AdventureCard(WeaponType.B15));
    P3_hand.add(new AdventureCard(WeaponType.L20));

    return P3_hand;
  }

  private static List<AdventureCard> p4Hand() {
    List<AdventureCard> P4_hand = new ArrayList<>();
    P4_hand.add(new AdventureCard(FoeType.F5));
    P4_hand.add(new AdventureCard(FoeType.F15));
    P4_hand.add(new AdventureCard(FoeType.F15));
    P4_hand.add(new AdventureCard(FoeType.F40));
    P4_hand.add(new AdventureCard(WeaponType.D5));
    P4_hand.add(new AdventureCard(WeaponType.D5));
    P4_hand.add(new AdventureCard(WeaponType.S10));
    P4_hand.add(new AdventureCard(WeaponType.H10));
    P4_hand.add(new AdventureCard(WeaponType.H10));
    P4_hand.add(new AdventureCard(WeaponType.B15));
    P4_hand.add(new AdventureCard(WeaponType.L20));
    P4_hand.add(new AdventureCard(WeaponType.E30));

    return P4_hand;
  }
}

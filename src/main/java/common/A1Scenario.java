package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.cards.AdventureCard;
import org.example.cards.EventCard;
import org.example.enums.adventure.AdventureType;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.enums.event.QType;

public class A1Scenario {

  // Starting cards

  private static final AdventureType[] P1_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F15,
    FoeType.F15,
    WeaponType.D5,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.B15,
    WeaponType.B15,
    WeaponType.L20
  };

  private static final AdventureType[] P2_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F15,
    FoeType.F15,
    FoeType.F40,
    WeaponType.D5,
    WeaponType.S10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.B15,
    WeaponType.B15,
    WeaponType.E30
  };

  private static final AdventureType[] P3_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F5,
    FoeType.F15,
    WeaponType.D5,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.B15,
    WeaponType.L20
  };

  private static final AdventureType[] P4_HAND = {
    FoeType.F5,
    FoeType.F15,
    FoeType.F15,
    FoeType.F40,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.S10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.B15,
    WeaponType.L20,
    WeaponType.E30
  };

  private static final AdventureType[] RIGGED_TOP_OF_DECK = {
    FoeType.F30,
    WeaponType.S10,
    WeaponType.B15,
    FoeType.F10,
    WeaponType.L20,
    WeaponType.L20,
    WeaponType.B15,
    WeaponType.S10,
    FoeType.F30,
    WeaponType.L20
  };

  // Game setup

  public static List<AdventureCard> getP1Hand() {
    return Setup.createHand(P1_HAND);
  }

  public static List<AdventureCard> getP2Hand() {
    return Setup.createHand(P2_HAND);
  }

  public static List<AdventureCard> getP3Hand() {
    return Setup.createHand(P3_HAND);
  }

  public static List<AdventureCard> getP4Hand() {
    return Setup.createHand(P4_HAND);
  }

  public static List<AdventureCard> getRiggedTopOfDeck() {
    return Setup.createDeck(RIGGED_TOP_OF_DECK);
  }

  public static List<AdventureCard> getAdventureCards() {
    List<AdventureCard> cards = new ArrayList<>();
    cards.addAll(getP1Hand());
    cards.addAll(getP2Hand());
    cards.addAll(getP3Hand());
    cards.addAll(getP4Hand());
    cards.addAll(getRiggedTopOfDeck());
    return cards;
  }

  public static List<EventCard> getEventCards() {
    return Arrays.asList(
      new EventCard(QType.Q4)
    );
  }

  // Inputs

  public static String getQuestStagesSetupInput() {
    String p2Stage1Setup = "1\n7\nQUIT\n\n";
    String p2Stage2Setup = "2\n5\nQUIT\n\n";
    String p2Stage3Setup = "2\n3\n4\nQUIT\n\n";
    String p2Stage4Setup = "2\n3\nQUIT\n\n\n";

    return p2Stage1Setup + p2Stage2Setup + p2Stage3Setup + p2Stage4Setup;
  }

  public static String getAttackSetupInput(int playerNumber, int stageNumber) {
    switch (stageNumber) {
      case 1:
        return getStage1AttackSetupInputForPlayer(playerNumber);
      case 2:
        return getStage2AttackSetupInputForPlayer(playerNumber);
      case 3:
        return getStage3AttackSetupInputForPlayer(playerNumber);
      case 4:
        return getStage4AttackSetupInputForPlayer(playerNumber);
      default:
        throw new IllegalArgumentException("Unknown stage number: " + stageNumber);
    }
  }

  public static String getReplenishSponsorHandInput() {
    return "1\n1\n1\n";
  }

  // Stage attack setup helper methods

  private static String getStage1AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 1:
        return "5\n5\nQUIT\n\n";
      case 3:
        return "5\n4\nQUIT\n\n";
      case 4:
        return "4\n6\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getStage2AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 1:
        return "7\n6\nQUIT\n\n";
      case 3:
        return "9\n4\nQUIT\n\n";
      case 4:
        return "6\n6\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getStage3AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 3:
        return "9\n6\n4\nQUIT\n\n";
      case 4:
        return "7\n5\n6\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getStage4AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 3:
        return "7\n6\n6\nQUIT\n\n";
      case 4:
        return "4\n4\n4\n5\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  // End game hands

  public static List<AdventureType> getP1EndGameHand() {
    return Arrays.asList(
      FoeType.F5,
      FoeType.F10,
      FoeType.F15,
      FoeType.F15,
      FoeType.F30,
      WeaponType.H10,
      WeaponType.B15,
      WeaponType.B15,
      WeaponType.L20
    );
  }

  public static List<AdventureType> getP3EndGameHand() {
    return Arrays.asList(
      FoeType.F5,
      FoeType.F5,
      FoeType.F15,
      FoeType.F30,
      WeaponType.S10
    );
  }

  public static List<AdventureType> getP4EndGameHand() {
    return Arrays.asList(
      FoeType.F15,
      FoeType.F15,
      FoeType.F40,
      WeaponType.L20
    );
  }
}

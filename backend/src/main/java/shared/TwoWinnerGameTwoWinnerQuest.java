package shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.cards.AdventureCard;
import game.cards.EventCard;
import game.enums.adventure.AdventureType;
import game.enums.adventure.FoeType;
import game.enums.adventure.WeaponType;
import game.enums.event.QType;

public class TwoWinnerGameTwoWinnerQuest {

  // Starting cards

  private static final AdventureType[] P1_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F10,
    FoeType.F10,
    FoeType.F15,
    FoeType.F15,
    WeaponType.D5,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.B15,
    WeaponType.B15,
    WeaponType.L20
  };

  private static final AdventureType[] P2_HAND = {
    FoeType.F40,
    FoeType.F50,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.B15,
    WeaponType.B15,
    WeaponType.L20,
    WeaponType.L20,
    WeaponType.E30
  };

  private static final AdventureType[] P3_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F5,
    FoeType.F5,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.H10
  };

  private static final AdventureType[] P4_HAND = {
    FoeType.F50,
    FoeType.F70,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.B15,
    WeaponType.B15,
    WeaponType.L20,
    WeaponType.L20,
    WeaponType.E30
  };

  private static final AdventureType[] RIGGED_TOP_OF_DECK = {
    FoeType.F5,
    FoeType.F40,
    FoeType.F10,
    FoeType.F10,
    FoeType.F30,
    FoeType.F30,
    FoeType.F15,
    FoeType.F15,
    FoeType.F20,
    FoeType.F5,
    FoeType.F10,
    FoeType.F15,
    FoeType.F15,
    FoeType.F20,
    FoeType.F20,
    FoeType.F20,
    FoeType.F20,
    FoeType.F25,
    FoeType.F25,
    FoeType.F30,
    WeaponType.D5,
    WeaponType.D5,
    FoeType.F15,
    FoeType.F15,
    FoeType.F25,
    FoeType.F25,
    FoeType.F20,
    FoeType.F20,
    FoeType.F25,
    FoeType.F30,
    WeaponType.S10,
    WeaponType.B15,
    WeaponType.B15,
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
      new EventCard(QType.Q4),
      new EventCard(QType.Q3)
    );
  }

  // Inputs

  public static String getQ1StagesSetupInput() {
    String p1Stage1Setup = "1\nQUIT\n\n";
    String p1Stage2Setup = "1\n5\nQUIT\n\n";
    String p1Stage3Setup = "1\n4\nQUIT\n\n";
    String p1Stage4Setup = "1\n4\nQUIT\n\n\n";

    return p1Stage1Setup + p1Stage2Setup + p1Stage3Setup + p1Stage4Setup;
  }

  public static String getQ1AttackSetupInput(int playerNumber, int stageNumber) {
    switch (stageNumber) {
      case 1:
        return getQ1S1AttackSetupInputForPlayer(playerNumber);
      case 2:
        return getQ1S2AttackSetupInputForPlayer(playerNumber);
      case 3:
        return getQ1S3AttackSetupInputForPlayer(playerNumber);
      case 4:
        return getQ1S4AttackSetupInputForPlayer(playerNumber);
      default:
        throw new IllegalArgumentException("Unknown stage number: " + stageNumber);
    }
  }

  public static String getQ1ReplenishSponsorHandInput() {
    return "4\n".repeat(4);
  }

  public static String getQ2StagesSetupInput() {
    String p3Stage1Setup = "1\nQUIT\n\n";
    String p3Stage2Setup = "2\nQUIT\n\n";
    String p3Stage3Setup = "2\nQUIT\n\n";

    return p3Stage1Setup + p3Stage2Setup + p3Stage3Setup + "\n";
  }

  public static String getQ2AttackSetupInput(int playerNumber, int stageNumber) {
    switch (stageNumber) {
      case 1:
        return getQ2S1AttackSetupInputForPlayer(playerNumber);
      case 2:
        return getQ2S2AttackSetupInputForPlayer(playerNumber);
      case 3:
        return getQ2S3AttackSetupInputForPlayer(playerNumber);
      default:
        throw new IllegalArgumentException("Unknown stage number: " + stageNumber);
    }
  }

  public static String getQ2ReplenishSponsorHandInput() {
    return "4\n".repeat(2);
  }

  // Stage attack setup helper methods

  // Quest 1

  private static String getQ1S1AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "8\nQUIT\n\n";
      case 3:
        return "9\nQUIT\n\n";
      case 4:
        return "9\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getQ1S2AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "10\nQUIT\n\n";
      case 4:
        return "10\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getQ1S3AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "10\n8\nQUIT\n\n";
      case 4:
        return "7\n9\n5\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getQ1S4AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "10\n8\nQUIT\n\n";
      case 4:
        return "10\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  // Quest 2

  private static String getQ2S1AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "7\nQUIT\n\n";
      case 4:
        return "7\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getQ2S2AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "8\n6\nQUIT\n\n";
      case 4:
        return "6\n6\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }

  private static String getQ2S3AttackSetupInputForPlayer(int playerNumber) {
    switch (playerNumber) {
      case 2:
        return "9\nQUIT\n\n";
      case 4:
        return "8\nQUIT\n\n";
      default:
        throw new IllegalArgumentException("Unknown player number: " + playerNumber);
    }
  }
}

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


public class ZeroWinnerQuest {
  private static final AdventureType[] P1_HAND = {
    FoeType.F50,
    FoeType.F70,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.B15,
    WeaponType.B15,
    WeaponType.L20,
    WeaponType.L20
  };

  private static final AdventureType[] P2_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F10,
    FoeType.F15,
    FoeType.F15,
    FoeType.F20,
    FoeType.F20,
    FoeType.F25,
    FoeType.F30,
    FoeType.F30,
    FoeType.F40,
    WeaponType.E30
  };

  private static final AdventureType[] P3_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F10,
    FoeType.F15,
    FoeType.F15,
    FoeType.F20,
    FoeType.F20,
    FoeType.F25,
    FoeType.F25,
    FoeType.F30,
    FoeType.F40,
    WeaponType.L20
  };

  private static final AdventureType[] P4_HAND = {
    FoeType.F5,
    FoeType.F5,
    FoeType.F10,
    FoeType.F15,
    FoeType.F15,
    FoeType.F20,
    FoeType.F20,
    FoeType.F25,
    FoeType.F25,
    FoeType.F30,
    FoeType.F50,
    WeaponType.E30
  };

  private static final AdventureType[] RIGGED_TOP_OF_DECK = {
    FoeType.F5,
    FoeType.F15,
    FoeType.F10,
    FoeType.F5,
    FoeType.F10,
    FoeType.F15,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.D5,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.H10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
  };

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
      new EventCard(QType.Q2)
    );
  }
}

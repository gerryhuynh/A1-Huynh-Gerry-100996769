package rigs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.cards.AdventureCard;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.enums.adventure.AdventureType;
import org.example.cards.EventCard;
import org.example.enums.event.QType;

public class A1Scenario {
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

  public static List<AdventureCard> getP1Hand() {
    return createHand(P1_HAND);
  }

  public static List<AdventureCard> getP2Hand() {
    return createHand(P2_HAND);
  }

  public static List<AdventureCard> getP3Hand() {
    return createHand(P3_HAND);
  }

  public static List<AdventureCard> getP4Hand() {
    return createHand(P4_HAND);
  }

  public static List<AdventureCard> getRiggedTopOfDeck() {
    return createDeck(RIGGED_TOP_OF_DECK);
  }

  public static List<AdventureCard> getA1ScenarioAdventureCards() {
    List<AdventureCard> cards = new ArrayList<>();
    cards.addAll(getP1Hand());
    cards.addAll(getP2Hand());
    cards.addAll(getP3Hand());
    cards.addAll(getP4Hand());
    cards.addAll(getRiggedTopOfDeck());
    return cards;
  }

  public static List<EventCard> getA1ScenarioEventCards() {
    return Arrays.asList(
      new EventCard(QType.Q4)
    );
  }

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

  private static List<AdventureCard> createDeck(AdventureType... types) {
    List<AdventureCard> deck = new ArrayList<>();
    for (AdventureType type : types) {
      deck.add(new AdventureCard(type));
    }
    return deck;
  }

  private static List<AdventureCard> createHand(AdventureType[] types) {
    return createDeck(types);
  }
}

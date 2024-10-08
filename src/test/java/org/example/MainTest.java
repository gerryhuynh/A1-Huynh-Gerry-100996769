package org.example;

import org.example.cards.AdventureCard;
import org.example.cards.EventCard;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.enums.event.EType;
import org.example.enums.event.QType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MainTest {
  @Nested
  @DisplayName("RESP_1: Adventure Deck Setup")
  class RESP_1 {
    private final AdventureDeck adventureDeck = new AdventureDeck();

    @Test
    @DisplayName("RESP_1_test_1: has 100 cards")
    void RESP_1_test_1() {
      assertEquals(100, adventureDeck.size(), "Total cards equal 100");
    }

    @Test
    @DisplayName("RESP_1_test_2: has 100 cards with only Foe and Weapon cards")
    void RESP_1_test_2() {
      int totalFoeAndWeaponCards = adventureDeck.getNumFoeCards() + adventureDeck.getNumWeaponCards();
      assertEquals(100, totalFoeAndWeaponCards, "Foe + Weapon cards equals 100 cards");
    }

    @Test
    @DisplayName("RESP_1_test_3: has 50 Foe cards")
    void RESP_1_test_3() {
      assertEquals(50, adventureDeck.getNumFoeCards());
    }

    @Test
    @DisplayName("RESP_1_test_4: has 50 Weapon cards")
    void RESP_1_test_4() {
      assertEquals(50, adventureDeck.getNumWeaponCards());
    }


    @ParameterizedTest(name = "RESP_1_test_5_{index}: has correct number of {0} cards")
    @EnumSource(FoeType.class)
    void RESP_1_test_5(FoeType foeType) {
      String testName = String.format("has %d %s cards", foeType.getDefaultCount(), foeType.name());
      assertEquals(foeType.getDefaultCount(), adventureDeck.getFoeCards().get(foeType), testName);
    }

    @ParameterizedTest(name = "RESP_1_test_6_{index}: has correct number of {0} cards")
    @EnumSource(WeaponType.class)
    void RESP_1_test_6(WeaponType weaponType) {
      String testName = String.format("has %d %s cards", weaponType.getDefaultCount(), weaponType.name());
      assertEquals(weaponType.getDefaultCount(), adventureDeck.getWeaponCards().get(weaponType), testName);
    }
  }

  @Nested
  @DisplayName("RESP_2: Event Deck Setup")
  class RESP_2 {
    private final EventDeck eventDeck = new EventDeck();

    @Test
    @DisplayName("RESP_2_test_1: has 17 cards")
    void RESP_2_test_1() {
      assertEquals(17, eventDeck.size(), "Total cards equal 17");
    }

    @Test
    @DisplayName("RESP_2_test_2: has 17 cards with only Q and E cards")
    void RESP_2_test_2() {
      int totalQAndECards = eventDeck.size();
      assertEquals(17, totalQAndECards, "Q and E cards equal 17");
    }

    @Test
    @DisplayName("RESP_2_test_3: has 12 Q cards")
    void RESP_2_test_3() {
      assertEquals(12, eventDeck.getNumQCards());
    }

    @Test
    @DisplayName("RESP_2_test_4: has 5 E cards")
    void RESP_2_test_4() {
      assertEquals(5, eventDeck.getNumECards());
    }

    @ParameterizedTest(name = "RESP_2_test_5_{index}: has correct number of {0} cards")
    @EnumSource(QType.class)
    void RESP_2_test_5(QType qType) {
      String testName = String.format("has %d %s cards", qType.getDefaultCount(), qType.name());
      assertEquals(qType.getDefaultCount(), eventDeck.getQCards().get(qType), testName);
    }

    @ParameterizedTest(name = "RESP_2_test_6_{index}: has correct number of {0} cards")
    @EnumSource(EType.class)
    void RESP_2_test_6(EType eType) {
      String testName = String.format("has %d %s cards", eType.getDefaultCount(), eType.name());
      assertEquals(eType.getDefaultCount(), eventDeck.getECards().get(eType), testName);
    }
  }

  @Nested
  @DisplayName("RESP_3: Shuffles Decks")
  class RESP_3 {
    private final AdventureDeck adventureDeck = new AdventureDeck();
    private final EventDeck eventDeck = new EventDeck();

    @Test
    @DisplayName("RESP_3_test_1: shuffles Adventure Deck")
    void RESP_3_test_1() {
      List<AdventureCard> originalDeck = new ArrayList<>(adventureDeck.getCards());
      adventureDeck.shuffle();
      assertNotEquals(originalDeck, adventureDeck.getCards(), "Adventure Deck has been shuffled");
    }

    @Test
    @DisplayName("RESP_3_test_2: shuffles Event Deck")
    void RESP_3_test_2() {
      List<EventCard> originalDeck = new ArrayList<>(eventDeck.getCards());
      eventDeck.shuffle();
      assertNotEquals(originalDeck, eventDeck.getCards(), "Event Deck has been shuffled");
    }
  }
}

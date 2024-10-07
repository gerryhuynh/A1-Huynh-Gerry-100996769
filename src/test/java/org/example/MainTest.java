package org.example;

import org.junit.jupiter.api.*;

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

    @Nested
    @DisplayName("RESP_1_test_3: Foe Cards")
    class RESP_1_test_3 {
      @Test
      @DisplayName("RESP_1_test_3_1: has 50 cards")
      void RESP_1_test_3_1() {
        assertEquals(50, adventureDeck.getNumFoeCards());
      }

      @Test
      @DisplayName("RESP_1_test_3_2: has 8 F5 cards")
      void RESP_1_test_3_2() {
        assertEquals(8, adventureDeck.getFoeCards().get("F5"));
      }

      @Test
      @DisplayName("RESP_1_test_3_3: has 7 F10 cards")
      void RESP_1_test_3_3() {
        assertEquals(7, adventureDeck.getFoeCards().get("F10"));
      }

      @Test
      @DisplayName("RESP_1_test_3_4: has 8 F15 cards")
      void RESP_1_test_3_4() {
        assertEquals(8, adventureDeck.getFoeCards().get("F15"));
      }

      @Test
      @DisplayName("RESP_1_test_3_5: has 7 F20 cards")
      void RESP_1_test_3_5() {
        assertEquals(7, adventureDeck.getFoeCards().get("F20"));
      }

      @Test
      @DisplayName("RESP_1_test_3_6: has 7 F25 cards")
      void RESP_1_test_3_6() {
        assertEquals(7, adventureDeck.getFoeCards().get("F25"));
      }

      @Test
      @DisplayName("RESP_1_test_3_7: has 4 F30 cards")
      void RESP_1_test_3_7() {
        assertEquals(4, adventureDeck.getFoeCards().get("F30"));
      }

      @Test
      @DisplayName("RESP_1_test_3_8: has 4 F35 cards")
      void RESP_1_test_3_8() {
        assertEquals(4, adventureDeck.getFoeCards().get("F35"));
      }

      @Test
      @DisplayName("RESP_1_test_3_9: has 2 F40 cards")
      void RESP_1_test_3_9() {
        assertEquals(2, adventureDeck.getFoeCards().get("F40"));
      }

      @Test
      @DisplayName("RESP_1_test_3_10: has 2 F50 cards")
      void RESP_1_test_3_10() {
        assertEquals(2, adventureDeck.getFoeCards().get("F50"));
      }

      @Test
      @DisplayName("RESP_1_test_3_11: has 1 F70 card")
      void RESP_1_test_3_11() {
        assertEquals(1, adventureDeck.getFoeCards().get("F70"));
      }
    }

    @Nested
    @DisplayName("RESP_1_test_4: Weapon Cards")
    class RESP_1_test_4 {
      @Test
      @DisplayName("RESP_1_test_4_1: has 50 cards")
      void RESP_1_test_4_1() {
        assertEquals(50, adventureDeck.getNumWeaponCards());
      }

      @Test
      @DisplayName("RESP_1_test_4_2: has 6 D5 cards")
      void RESP_1_test_4_2() {
        assertEquals(6, adventureDeck.getWeaponCards().get("D5"));
      }

      @Test
      @DisplayName("RESP_1_test_4_3: has 12 H10 cards")
      void RESP_1_test_4_3() {
        assertEquals(12, adventureDeck.getWeaponCards().get("H10"));
      }

      @Test
      @DisplayName("RESP_1_test_4_4: has 16 S10 cards")
      void RESP_1_test_4_4() {
        assertEquals(16, adventureDeck.getWeaponCards().get("S10"));
      }

      @Test
      @DisplayName("RESP_1_test_4_5: has 8 B15 cards")
      void RESP_1_test_4_5() {
        assertEquals(8, adventureDeck.getWeaponCards().get("B15"));
      }

      @Test
      @DisplayName("RESP_1_test_4_6: has 6 L20 cards")
      void RESP_1_test_4_6() {
        assertEquals(6, adventureDeck.getWeaponCards().get("L20"));
      }

      @Test
      @DisplayName("RESP_1_test_4_7: has 2 E30 cards")
      void RESP_1_test_4_7() {
        assertEquals(2, adventureDeck.getWeaponCards().get("E30"));
      }
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

    @Nested
    @DisplayName("RESP_2_test_3: Q Cards")
    class RESP_2_test_3 {
      @Test
      @DisplayName("RESP_2_test_3_1: has 12 cards")
      void RESP_2_test_3_1() {
        assertEquals(12, eventDeck.getNumQCards());
      }

      @Test
      @DisplayName("RESP_2_test_3_2: has 3 Q2 cards")
      void RESP_2_test_3_2() {
        assertEquals(3, eventDeck.getQCards().get("Q2"));
      }

      @Test
      @DisplayName("RESP_2_test_3_3: has 4 Q3 cards")
      void RESP_2_test_3_3() {
        assertEquals(4, eventDeck.getQCards().get("Q3"));
      }

      @Test
      @DisplayName("RESP_2_test_3_4: has 3 Q4 cards")
      void RESP_2_test_3_4() {
        assertEquals(3, eventDeck.getQCards().get("Q4"));
      }

      @Test
      @DisplayName("RESP_2_test_3_5: has 2 Q5 cards")
      void RESP_2_test_3_5() {
        assertEquals(2, eventDeck.getQCards().get("Q5"));
      }
    }

    @Nested
    @DisplayName("RESP_2_test_4: E Cards")
    class RESP_2_test_4 {
      @Test
      @DisplayName("RESP_2_test_4_1: has 5 cards")
      void RESP_2_test_4_1() {
        assertEquals(5, eventDeck.getNumECards());
      }

      @Test
      @DisplayName("RESP_2_test_4_2: has 1 Plague card")
      void RESP_2_test_4_2() {
        assertEquals(1, eventDeck.getECards().get("Plague"));
      }

      @Test
      @DisplayName("RESP_2_test_4_3: has 2 Queen's Favor card")
      void RESP_2_test_4_3() {
        assertEquals(2, eventDeck.getECards().get("Queen's Favor"));
      }

      @Test
      @DisplayName("RESP_2_test_4_4: has 2 Prosperity card")
      void RESP_2_test_4_4() {
        assertEquals(2, eventDeck.getECards().get("Prosperity"));
      }
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

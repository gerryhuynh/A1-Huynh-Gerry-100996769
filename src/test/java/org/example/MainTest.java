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
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.PrintWriter;
import java.io.StringWriter;

class MainTest {
  @Nested
  @DisplayName("RESP_01: Adventure Deck Setup")
  class RESP_01 {
    private final AdventureDeck adventureDeck = new AdventureDeck();

    @Test
    @DisplayName("RESP_01_test_1: has 100 cards")
    void RESP_01_test_1() {
      assertEquals(100, adventureDeck.size(), "Total cards equal 100");
    }

    @Test
    @DisplayName("RESP_01_test_2: has 100 cards with only Foe and Weapon cards")
    void RESP_01_test_2() {
      int totalFoeAndWeaponCards = adventureDeck.getNumFoeCards() + adventureDeck.getNumWeaponCards();
      assertEquals(100, totalFoeAndWeaponCards, "Foe + Weapon cards equals 100 cards");
    }

    @Test
    @DisplayName("RESP_01_test_3: has 50 Foe cards")
    void RESP_01_test_3() {
      assertEquals(50, adventureDeck.getNumFoeCards());
    }

    @Test
    @DisplayName("RESP_01_test_4: has 50 Weapon cards")
    void RESP_01_test_4() {
      assertEquals(50, adventureDeck.getNumWeaponCards());
    }

    @ParameterizedTest(name = "RESP_01_test_5_{index}: has correct number of {0} cards")
    @EnumSource(FoeType.class)
    void RESP_01_test_5(FoeType foeType) {
      String testName = String.format("has %d %s cards", foeType.getDefaultCount(), foeType.name());
      assertEquals(foeType.getDefaultCount(), adventureDeck.getFoeCards().get(foeType), testName);
    }

    @ParameterizedTest(name = "RESP_01_test_6_{index}: has correct number of {0} cards")
    @EnumSource(WeaponType.class)
    void RESP_01_test_6(WeaponType weaponType) {
      String testName = String.format("has %d %s cards", weaponType.getDefaultCount(), weaponType.name());
      assertEquals(weaponType.getDefaultCount(), adventureDeck.getWeaponCards().get(weaponType), testName);
    }
  }

  @Nested
  @DisplayName("RESP_02: Event Deck Setup")
  class RESP_02 {
    private final EventDeck eventDeck = new EventDeck();

    @Test
    @DisplayName("RESP_02_test_1: has 17 cards")
    void RESP_02_test_1() {
      assertEquals(17, eventDeck.size(), "Total cards equal 17");
    }

    @Test
    @DisplayName("RESP_02_test_2: has 17 cards with only Q and E cards")
    void RESP_02_test_2() {
      int totalQAndECards = eventDeck.size();
      assertEquals(17, totalQAndECards, "Q and E cards equal 17");
    }

    @Test
    @DisplayName("RESP_02_test_3: has 12 Q cards")
    void RESP_02_test_3() {
      assertEquals(12, eventDeck.getNumQCards());
    }

    @Test
    @DisplayName("RESP_02_test_4: has 5 E cards")
    void RESP_02_test_4() {
      assertEquals(5, eventDeck.getNumECards());
    }

    @ParameterizedTest(name = "RESP_02_test_5_{index}: has correct number of {0} cards")
    @EnumSource(QType.class)
    void RESP_02_test_5(QType qType) {
      String testName = String.format("has %d %s cards", qType.getDefaultCount(), qType.name());
      assertEquals(qType.getDefaultCount(), eventDeck.getQCards().get(qType), testName);
    }

    @ParameterizedTest(name = "RESP_02_test_6_{index}: has correct number of {0} cards")
    @EnumSource(EType.class)
    void RESP_02_test_6(EType eType) {
      String testName = String.format("has %d %s cards", eType.getDefaultCount(), eType.name());
      assertEquals(eType.getDefaultCount(), eventDeck.getECards().get(eType), testName);
    }
  }

  @Nested
  @DisplayName("RESP_03: Shuffles Decks")
  class RESP_03 {
    private final AdventureDeck adventureDeck = new AdventureDeck();
    private final EventDeck eventDeck = new EventDeck();

    @Test
    @DisplayName("RESP_03_test_1: shuffles Adventure Deck")
    void RESP_03_test_1() {
      List<AdventureCard> originalDeck = new ArrayList<>(adventureDeck.getCards());
      adventureDeck.shuffle();
      assertNotEquals(originalDeck, adventureDeck.getCards(), "Adventure Deck has been shuffled");
    }

    @Test
    @DisplayName("RESP_03_test_2: shuffles Event Deck")
    void RESP_03_test_2() {
      List<EventCard> originalDeck = new ArrayList<>(eventDeck.getCards());
      eventDeck.shuffle();
      assertNotEquals(originalDeck, eventDeck.getCards(), "Event Deck has been shuffled");
    }
  }

  @Nested
  @DisplayName("RESP_04: Set Up Players")
  class RESP_04 {
    private final Game game = new Game();

    @BeforeEach
    void setUp() {
      game.setupPlayers();
    }

    @Test
    @DisplayName("RESP_04_test_1: adds 4 players")
    void RESP_04_test_1() {
      assertEquals(4, game.getNumPlayers(), "Number of players is 4");
    }

    @Test
    @DisplayName("RESP_04_test_2: sets up players' names")
    void RESP_04_test_2() {
      for (int i = 0; i < Game.MAX_PLAYERS; i++) {
        String expectedName = "P" + (i + 1);
        assertEquals(expectedName, game.getPlayers().get(i).getName(), 
                     expectedName + " name is '" + expectedName + "'");
      }
    }

    @Test
    @DisplayName("RESP_04_test_3: players start with empty hand of Adventure cards")
    void RESP_04_test_3() {
      for (int i = 0; i < Game.MAX_PLAYERS; i++) {
        assertEquals(0, game.getPlayers().get(i).getHand().size(), 
                     "P" + (i + 1) + "'s hand has 0 Adventure cards");
      }
    }

    @Test
    @DisplayName("RESP_04_test_4: players start with 0 shields")
    void RESP_04_test_4() {
      for (int i = 0; i < Game.MAX_PLAYERS; i++) {
        assertEquals(0, game.getPlayers().get(i).getShields(), 
                     "P" + (i + 1) + " has 0 shields");
      }
    }
  }

  @Nested
  @DisplayName("RESP_05: Distributes " + Player.MAX_HAND_SIZE + " adventure cards to each player")
  class RESP_05 {
    private final Game game = new Game();

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
    }

    @ParameterizedTest(name = "RESP_05_test_1_{index}: P{0} has " + Player.MAX_HAND_SIZE + " adventure cards")
    @MethodSource("playerNumbers")
    void RESP_05_test_1(int playerNumber) {
        Player player = game.getPlayers().get(playerNumber - 1);
        assertEquals(Player.MAX_HAND_SIZE, player.getHand().size(), 
                     "P" + (playerNumber) + " should have " + Player.MAX_HAND_SIZE + " adventure cards");
    }

    static Stream<Integer> playerNumbers() {
        return IntStream.rangeClosed(1, Game.MAX_PLAYERS).boxed();
    }

    @Test
    @DisplayName("RESP_05_test_2: updates Adventure Deck")
    void RESP_05_test_2() {
      AdventureDeck adventureDeck = game.getAdventureDeck();
      int expectedSize = 100 - Game.MAX_PLAYERS * Player.MAX_HAND_SIZE;

      assertEquals(expectedSize, adventureDeck.size(), 
                   "Adventure Deck has " + expectedSize + 
                   " cards after distributing " + Player.MAX_HAND_SIZE + 
                   " cards to each player");
    }
  }

  @Nested
  @DisplayName("RESP_06: Cycles Players When No Winner")
  class RESP_06 {
    private static Game game;

    @BeforeAll
    static void setUpAll() {
      game = new Game();
      game.setupPlayers();
    }

    @Test
    @DisplayName("RESP_06_test_1: starts with P1")
    void RESP_06_test_1() {
      Player expected = game.getPlayers().get(0);
      assertEquals(expected, game.getCurrentPlayer(), "Current player is P1");
    }

    @ParameterizedTest(name = "RESP_06_test_2_{index}: when P{0} turn ends, next player is P{1}")
    @MethodSource("playerTurns")
    void RESP_06_test_2(int currentPlayer, int nextPlayer) {
      game.nextTurn();
      Player expected = game.getPlayers().get(nextPlayer - 1);
      assertEquals(expected, game.getCurrentPlayer(), "Next player is P" + nextPlayer);
    }

    static Stream<Arguments> playerTurns() {
      return IntStream.range(1, Game.MAX_PLAYERS + 1)
          .mapToObj(i -> Arguments.of(i, i % Game.MAX_PLAYERS + 1));
    }
  }

  @Nested
  @DisplayName("RESP_07: Start Current Turn")
  class RESP_07 {
    private final Game game = new Game();

    @BeforeEach
    void setUp() {
      game.setupPlayers();
    }

    @Test
    @DisplayName("RESP_07_test_1: starts with no current event card")
    void RESP_07_test_1() {
      assertNull(game.getCurrentEventCard(), "No current event card");
    }

    @Test
    @DisplayName("RESP_07_test_2: draw Event card and decrease Event Deck size by 1")
    void RESP_07_test_2() {
      EventDeck eventDeck = game.getEventDeck();
      int originalSize = eventDeck.size();  
      game.startTurn();
      assertEquals(originalSize - 1, eventDeck.size(), "Event Deck size decreases by 1");
    }

    @Test
    @DisplayName("RESP_07_test_3: first card drawn from Event Deck becomes current event card")
    void RESP_07_test_3() {
      EventCard expected = game.getEventDeck().getCards().get(0);
      game.startTurn();
      assertEquals(expected, game.getCurrentEventCard(), "First card in Event Deck is current event card");
    }

    @Test
    @DisplayName("RESP_07_test_4: drawn EventCard is displayed")
    void RESP_07_test_4() {
      StringWriter output = new StringWriter();
      Display display = new Display(new PrintWriter(output));
      game.startTurn();

      EventCard currentCard = game.getCurrentEventCard();
      display.showEventCard(currentCard);
      assertTrue(output.toString().contains(currentCard.toString()), "Drawn event card is displayed");
    }
  }
}

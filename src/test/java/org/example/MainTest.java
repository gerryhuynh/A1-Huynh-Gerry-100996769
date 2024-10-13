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
import java.util.Scanner;

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
      display.print(currentCard.toString());
      assertTrue(output.toString().contains(currentCard.toString()), "Drawn event card is displayed");
    }
  }

  @Nested
  @DisplayName("RESP_08: Player Draws an E Card: Plague Card")
  class RESP_08 {
    private final Game game = new Game();
    private StringWriter output = new StringWriter();
    private Display display = new Display(new PrintWriter(output));
    private int originalShields;
    private Player player;
    
    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(EType.PLAGUE));

      player = game.getCurrentPlayer();
      player.setShields(2);
      originalShields = player.getShields();
    }

    @Test
    @DisplayName("RESP_08_test_1: current player loses 2 shields if they have at least 2 shields")
    void RESP_08_test_1() {
      game.playEventCard();
      assertEquals(originalShields - 2, player.getShields(), "Player loses 2 shields");
    }

    @Test
    @DisplayName("RESP_08_test_2: prints updated shield count for current player")
    void RESP_08_test_2() {
      String eventResult = game.playEventCard();
      display.print(eventResult);
      String expectedOutput = String.format("%s's shields: %d -> %d%n", 
                                             player.getName(), originalShields, originalShields - 2);
      assertEquals(expectedOutput, output.toString(), "Updated shield count is printed");
    }

    @Test
    @DisplayName("RESP_08_test_3: current player shields cannot go below 0")
    void RESP_08_test_3() {
      player.setShields(1);

      game.playEventCard();
      assertEquals(0, player.getShields(), "Player shields cannot go below 0");
    }

    @Test
    @DisplayName("RESP_08_test_4: other players are unaffected by the plague card")
    void RESP_08_test_4() {
      Player otherPlayer = game.getPlayers().get(1);
      otherPlayer.setShields(2);
      originalShields = otherPlayer.getShields();

      game.playEventCard();
      assertEquals(originalShields, otherPlayer.getShields(), "Other players are unaffected");
    }
  }

  @Nested
  @DisplayName("RESP_09: Player Draws an E Card: Queen's Favor Card")
  class RESP_09 {
    private final Game game = new Game();

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(EType.QUEENS_FAVOR));
    }

    @Test
    @DisplayName("RESP_09_test_1: player draws 2 adventure cards when hand doesn't need to be trimmed")
    void RESP_09_test_1() {
      Player player = game.getCurrentPlayer();

      game.playEventCard();
      assertEquals(2, player.getHand().size(), "Player draws 2 adventure cards");
    }

    @Test
    @DisplayName("RESP_09_test_2: prints player's updated hand")
    void RESP_09_test_2() {
      StringWriter output = new StringWriter();
      Display display = new Display(new PrintWriter(output));
      Player player = game.getCurrentPlayer();

      display.print(game.playEventCard());
      String expectedOutput = String.format("%s drew 2 adventure cards%n", player.getName());
      assertEquals(expectedOutput, output.toString(), "Prints player drew 2 adventure cards");
    }

    // TODO: test when hand needs to be trimmed
  }

  @Nested
  @DisplayName("RESP_10: Computes Number of Cards to Trim")
  class RESP_10 {
    private final Game game = new Game();
    private Player player;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      player = game.getCurrentPlayer();
    }

    @Test
    @DisplayName("RESP_10_test_1: returns 0 if resulting hand size is less than " + Player.MAX_HAND_SIZE)
    void RESP_10_test_1() {
      assertEquals(0, player.computeNumCardsToTrim(2), "Hand size is less than " + Player.MAX_HAND_SIZE);
    }

    @Test
    @DisplayName("RESP_10_test_2: returns 0 if resulting hand size is equal to " + Player.MAX_HAND_SIZE)
    void RESP_10_test_2() {
      int diff = 2;
      for (int i = 0; i < Player.MAX_HAND_SIZE - diff; i++) {
        player.getHand().add(new AdventureCard(FoeType.F5));
      }

      assertEquals(0, player.computeNumCardsToTrim(diff), "Hand size is equal to " + Player.MAX_HAND_SIZE);
    }

    @Test
    @DisplayName("RESP_10_test_3: returns number of cards to trim if resulting hand size exceeds " + Player.MAX_HAND_SIZE)
    void RESP_10_test_3() {
      game.dealAdventureCards();
      assertEquals(2, player.computeNumCardsToTrim(2), "Hand size exceeds " + Player.MAX_HAND_SIZE);
    }
  }

  @Nested
  @DisplayName("RESP_11: Prompt Player to Choose a Card Position from Hand")
  class RESP_11 {
    private final Game game = new Game();
    private List<AdventureCard> hand;
    private StringWriter output;
    private String input;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      hand = game.getCurrentPlayer().getHand();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
    }

    @Test
    @DisplayName("RESP_11_test_1: prints hand")
    void RESP_11_test_1() {
      display.printHand(hand);
      assertTrue(output.toString().contains(hand.toString()), "Hand is printed");
    }

    @Test
    @DisplayName("RESP_11_test_2: prompts player to choose a card number")
    void RESP_11_test_2() {
      input = "1";
      display.promptForCardIndex(new Scanner(input), hand.size());
      assertTrue(output.toString().contains("Choose a card position:"), "Player is prompted to choose a card position");
    }

    @Test
    @DisplayName("RESP_11_test_3: prints not a valid number message if player enters invalid input")
    void RESP_11_test_3() {
      input = "invalid\n1";
      display.promptForCardIndex(new Scanner(input), hand.size());
      assertTrue(output.toString().contains("Not a valid number"), "Not a valid number message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_4: prints out of range message if player enters number greater than max index")
    void RESP_11_test_4() {
      input = String.valueOf(hand.size() + 2) + "\n1";
      display.promptForCardIndex(new Scanner(input), hand.size());
      assertTrue(output.toString().contains("Out of range"), "Out of range message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_5: prints out of range message if player enters number less than 1")
    void RESP_11_test_5() {
      input = "0\n1";
      display.promptForCardIndex(new Scanner(input), hand.size());
      assertTrue(output.toString().contains("Out of range"), "Out of range message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_6: prints empty input message if player enters empty input")
    void RESP_11_test_6() {
      input = "\n1";
      display.promptForCardIndex(new Scanner(input), hand.size());
      assertTrue(output.toString().contains("Empty input"), "Empty input message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_7: returns index of card if player enters valid input")
    void RESP_11_test_7() {
      input = "2";
      int removeCardIndex = display.promptForCardIndex(new Scanner(input), hand.size());
      assertEquals(1, removeCardIndex, "Returns index of card to discard");
    }
  }
}
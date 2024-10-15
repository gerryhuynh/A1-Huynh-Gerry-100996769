package org.example;

import org.example.cards.AdventureCard;
import org.example.cards.EventCard;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;
import org.example.enums.adventure.AdventureType;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.enums.event.EType;
import org.example.enums.event.QType;
import org.example.quest.Participant;
import org.example.quest.Quest;
import org.example.quest.Stage;
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
import java.util.Arrays;
import java.util.stream.Collectors;

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
    private static StringWriter output;
    private static Display display;

    @BeforeAll
    static void setUpAll() {
      game = new Game();
      game.setupPlayers();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
    }

    @Test
    @DisplayName("RESP_06_test_1: starts with P1")
    void RESP_06_test_1() {
      Player expected = game.getPlayers().get(0);
      assertEquals(expected, game.getCurrentPlayer(), "Current player is P1");
    }

    // 4 tests; 1 for each player
    @ParameterizedTest(name = "RESP_06_test_2_{index}: when P{0} turn ends, next player is P{1}")
    @MethodSource("playerTurns")
    void RESP_06_test_2(int currentPlayer, int nextPlayer) {
      display.setScanner(new Scanner("\n"));
      game.endTurn();
      Player expected = game.getPlayers().get(nextPlayer - 1);
      assertEquals(expected, game.getCurrentPlayer(), "Next player is P" + nextPlayer);
    }

    static Stream<Arguments> playerTurns() {
      return IntStream.range(1, Game.MAX_PLAYERS + 1)
          .mapToObj(i -> Arguments.of(i, i % Game.MAX_PLAYERS + 1));
    }

    @Test
    @DisplayName("RESP_06_test_3: game over remains false when no player has won")
    void RESP_06_test_3() {
      assertFalse(game.isGameOver(), "Game is not over");
    }
  }

  @Nested
  @DisplayName("RESP_07: Start Current Turn")
  class RESP_07 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;


    @BeforeEach
    void setUp() {
      game.setupPlayers();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
    }

    @Test
    @DisplayName("RESP_07_test_1: starts with no current event card")
    void RESP_07_test_1() {
      assertNull(game.getCurrentEventCard(), "No current event card");
    }

    @Test
    @DisplayName("RESP_07_test_2: prints current player")
    void RESP_07_test_2() {
      Player currentPlayer = game.getCurrentPlayer();
      game.startTurn();
      assertTrue(output.toString().contains(currentPlayer.getName()), "Current player is printed");
    }

    @Test
    @DisplayName("RESP_07_test_3: prints current player's hand")
    void RESP_07_test_3() {
      Player currentPlayer = game.getCurrentPlayer();

      game.startTurn();
      String outputString = output.toString();
      for (int i = 0; i < currentPlayer.getHand().size(); i++) {
          String expectedCardString = String.format("%d. %s", i + 1, currentPlayer.getHand().get(i));
          assertTrue(outputString.contains(expectedCardString), "Card " + (i + 1) + " is printed correctly");
      }
    }

    @Test
    @DisplayName("RESP_07_test_4: draw Event card and decrease Event Deck size by 1")
    void RESP_07_test_4() {
      EventDeck eventDeck = game.getEventDeck();
      int originalSize = eventDeck.size();
      game.startTurn();
      assertEquals(originalSize - 1, eventDeck.size(), "Event Deck size decreases by 1");
    }

    @Test
    @DisplayName("RESP_07_test_5: first card drawn from Event Deck becomes current event card")
    void RESP_07_test_5() {
      EventCard expected = game.getEventDeck().getCards().get(0);
      game.startTurn();
      assertEquals(expected, game.getCurrentEventCard(), "First card in Event Deck is current event card");
    }

    @Test
    @DisplayName("RESP_07_test_6: prints drawn EventCard")
    void RESP_07_test_6() {
      game.startTurn();
      EventCard currentCard = game.getCurrentEventCard();
      assertTrue(output.toString().contains(currentCard.toString()), "Drawn event card is printed");
    }

    @Test
    @DisplayName("RESP_07_test_7: prints event card effect")
    void RESP_07_test_7() {
      game.startTurn();
      EventCard currentCard = game.getCurrentEventCard();
      assertTrue(output.toString().contains(currentCard.getType().getEffectDesc()), "Event card effect is printed");
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
      game.setDisplay(display);
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
      game.playEventCard();
      String expectedOutput = String.format("%s's shields: %d -> %d",
                                             player.getName(), originalShields, originalShields - 2);
      assertTrue(output.toString().contains(expectedOutput), "Updated shield count is printed");
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
    private Player player;
    private StringWriter output = new StringWriter();
    private Display display = new Display(new PrintWriter(output));

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(EType.QUEENS_FAVOR));
      player = game.getCurrentPlayer();
      game.setDisplay(display);
    }

    @Test
    @DisplayName("RESP_09_test_1: player draws 2 adventure cards when hand doesn't need to be trimmed")
    void RESP_09_test_1() {
      game.playEventCard();
      assertEquals(2, player.getHand().size(), "Player draws 2 adventure cards");
    }

    @Test
    @DisplayName("RESP_09_test_2: prints Queen's Favor effect message")
    void RESP_09_test_2() {
      game.playEventCard();
      String expectedOutput = String.format("%s drew 2 adventure cards", player.getName());
      assertTrue(output.toString().contains(expectedOutput), "Prints player drew 2 adventure cards");
    }

    @Test
    @DisplayName("RESP_09_test_3: trims hand if adding cards to hand exceeds " + Player.MAX_HAND_SIZE)
    void RESP_09_test_3() {
      game.dealAdventureCards();
      display.setScanner(new Scanner("1\n2\n"));
      game.playEventCard();
      assertEquals(Player.MAX_HAND_SIZE, player.getHand().size(), "Hand size is " + Player.MAX_HAND_SIZE);
    }
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
      String outputString = output.toString();
      for (int i = 0; i < hand.size(); i++) {
          String expectedCardString = String.format("%d. %s", i + 1, hand.get(i));
          assertTrue(outputString.contains(expectedCardString), "Card " + (i + 1) + " is printed correctly");
      }
    }

    @Test
    @DisplayName("RESP_11_test_2: prompts player to choose a card number")
    void RESP_11_test_2() {
      display.setScanner(new Scanner("1\n"));
      display.promptForCardIndex(hand.size());
      assertTrue(output.toString().contains("CHOOSE A CARD POSITION:"), "Player is prompted to choose a card position");
    }

    @Test
    @DisplayName("RESP_11_test_3: prints not a valid number message if player enters invalid input")
    void RESP_11_test_3() {
      display.setScanner(new Scanner("invalid\n1"));
      display.promptForCardIndex(hand.size());
      assertTrue(output.toString().contains("Not a valid number"), "Not a valid number message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_4: prints out of range message if player enters number greater than max index")
    void RESP_11_test_4() {
      String input = String.valueOf(hand.size() + 2) + "\n1";
      display.setScanner(new Scanner(input));
      display.promptForCardIndex(hand.size());
      assertTrue(output.toString().contains("Out of range"), "Out of range message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_5: prints out of range message if player enters number less than 1")
    void RESP_11_test_5() {
      display.setScanner(new Scanner("0\n1"));
      display.promptForCardIndex(hand.size());
      assertTrue(output.toString().contains("Out of range"), "Out of range message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_6: prints empty input message if player enters empty input")
    void RESP_11_test_6() {
      display.setScanner(new Scanner("\n1"));
      display.promptForCardIndex(hand.size());
      assertTrue(output.toString().contains("Empty input"), "Empty input message is printed");
    }

    @Test
    @DisplayName("RESP_11_test_7: returns index of card if player enters valid input")
    void RESP_11_test_7() {
      display.setScanner(new Scanner("2"));
      int removeCardIndex = display.promptForCardIndex(hand.size());
      assertEquals(1, removeCardIndex, "Returns index of card to discard");
    }
  }

  @Nested
  @DisplayName("RESP_12: Prompt Player to Choose a Card to Discard")
  class RESP_12 {
    private final Game game = new Game();
    private List<AdventureCard> hand;
    private StringWriter output;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      hand = game.getCurrentPlayer().getHand();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      display.setScanner(new Scanner("2"));
    }

    @Test
    @DisplayName("RESP_12_test_1: prompts player to choose a card to discard")
    void RESP_12_test_1() {
      display.promptForCardToDiscard(hand);
      assertTrue(output.toString().contains("You must trim your hand. Please discard a card."), "Player is prompted to choose a card to discard");
    }

    @Test
    @DisplayName("RESP_12_test_2: prints hand")
    void RESP_12_test_2() {
      display.promptForCardToDiscard(hand);
      String outputString = output.toString();
      for (int i = 0; i < hand.size(); i++) {
          String expectedCardString = String.format("%d. %s", i + 1, hand.get(i));
          assertTrue(outputString.contains(expectedCardString), "Card " + (i + 1) + " is printed correctly");
      }
    }

    @Test
    @DisplayName("RESP_12_test_3: returns index of card to discard once valid input is entered")
    void RESP_12_test_3() {
      int removeCardIndex = display.promptForCardToDiscard(hand);
      assertEquals(1, removeCardIndex, "Returns index of card to discard");
    }
  }

  @Nested
  @DisplayName("RESP_13: Trims Hand")
  class RESP_13 {
    private final Game game = new Game();
    private Player player;
    private StringWriter output;
    private Display display;
    private int numCardsToTrim;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      player = game.getCurrentPlayer();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      display.setScanner(new Scanner("1\n2\n"));
      numCardsToTrim = 2;
    }

    @Test
    @DisplayName("RESP_13_test_1: removes the number of cards to trim from the player's hand")
    void RESP_13_test_1() {
      int originalSize = player.getHand().size();

      player.trimHand(numCardsToTrim, display);
      assertEquals(originalSize - numCardsToTrim, player.getHand().size(),
                   "Removes the number of cards to trim from the player's hand");
    }

    @Test
    @DisplayName("RESP_13_test_2: doesn't remove any cards if number of cards to trim is 0")
    void RESP_13_test_2() {
      numCardsToTrim = 0;
      int originalSize = player.getHand().size();

      display.setScanner(new Scanner(""));

      player.trimHand(numCardsToTrim, display);
      assertEquals(originalSize, player.getHand().size(), "Doesn't remove any cards if number of cards to trim is 0");
    }

    @Test
    @DisplayName("RESP_13_test_3: returns a list of adventure cards the same size as the number of cards to trim")
    void RESP_13_test_3() {
      List<AdventureCard> trimmedCards = player.trimHand(numCardsToTrim, display);
      assertEquals(numCardsToTrim, trimmedCards.size(), "Returns a list of adventure cards the same size as the number of cards to trim");
    }

    @Test
    @DisplayName("RESP_13_test_4: returns empty list if number of cards to trim is 0")
    void RESP_13_test_4() {
      numCardsToTrim = 0;
      display.setScanner(new Scanner(""));

      List<AdventureCard> trimmedCards = player.trimHand(numCardsToTrim, display);
      assertTrue(trimmedCards.isEmpty(), "Returns empty list if number of cards to trim is 0");
    }
  }

  @Nested
  @DisplayName("RESP_14: Add Adventure Cards to Hand")
  class RESP_14 {
    private final Game game = new Game();
    private Player player;
    private StringWriter output;
    private Display display;
    private List<AdventureCard> cardsToAdd;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      player = game.getCurrentPlayer();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      display.setScanner(new Scanner("1\n2\n"));

      cardsToAdd = new ArrayList<>();
      cardsToAdd.add(new AdventureCard(FoeType.F5));
      cardsToAdd.add(new AdventureCard(FoeType.F10));
    }

    @Test
    @DisplayName("RESP_14_test_1: prints cards to add to hand")
    void RESP_14_test_1() {
      player.addToHand(cardsToAdd, display);
      assertTrue(output.toString().contains(cardsToAdd.toString()), "Prints cards to add to hand");
    }

    @Test
    @DisplayName("RESP_14_test_2: adds cards to hand")
    void RESP_14_test_2() {
      player.addToHand(cardsToAdd, display);
      assertTrue(player.getHand().containsAll(cardsToAdd), "Adds cards to hand");
    }

    @Test
    @DisplayName("RESP_14_test_3: returns same number of cards as cards to trim if hand was trimmed")
    void RESP_14_test_3() {
      game.dealAdventureCards();
      int originalSize = player.getHand().size();
      List<AdventureCard> trimmedCards = player.addToHand(cardsToAdd, display);

      assertEquals(Player.MAX_HAND_SIZE, originalSize, "Original hand size is equal to " + Player.MAX_HAND_SIZE);
      assertEquals(cardsToAdd.size(), trimmedCards.size(), "Returns same number of cards as cards to trim if hand was trimmed");
    }

    @Test
    @DisplayName("RESP_14_test_4: returns empty list if number of cards to trim is 0")
    void RESP_14_test_4() {
      List<AdventureCard> trimmedCards = player.addToHand(cardsToAdd, display);
      assertTrue(trimmedCards.isEmpty(), "Returns empty list if number of cards to trim is 0");
    }

    @Test
    @DisplayName("RESP_14_test_5: prints updated hand")
    void RESP_14_test_5() {
      player.addToHand(cardsToAdd, display);
      assertTrue(output.toString().contains(player.getHand().toString()), "Prints updated hand");
    }

    @Test
    @DisplayName("RESP_14_test_6: sorts hand correctly")
    void RESP_14_test_6() {
      player.addToHand(Arrays.asList(
        new AdventureCard(WeaponType.H10),
        new AdventureCard(FoeType.F15),
        new AdventureCard(WeaponType.S10),
        new AdventureCard(FoeType.F5),
        new AdventureCard(WeaponType.D5),
        new AdventureCard(FoeType.F30),
        new AdventureCard(WeaponType.B15)
      ), display);

      List<AdventureType> sortedHand = player.getHand().stream()
        .map(AdventureCard::getType)
        .collect(Collectors.toList());

      List<AdventureType> expectedOrder = Arrays.asList(
        FoeType.F5, FoeType.F15, FoeType.F30,
        WeaponType.D5, WeaponType.S10, WeaponType.H10, WeaponType.B15
      );

      assertEquals(expectedOrder, sortedHand, "Hand should be sorted in the correct order");
    }
  }

  @Nested
  @DisplayName("RESP_15: Player Draws an E Card: Prosperity Card")
  class RESP_15 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(EType.PROSPERITY));
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      display.setScanner(new Scanner("\n".repeat(game.getPlayers().size())));
      game.setDisplay(display);
    }

    @Test
    @DisplayName("RESP_15_test_1: prints prosperity card effect message")
    void RESP_15_test_1() {
      game.playEventCard();
      assertTrue(output.toString().contains("All players drew 2 adventure cards"), "Prints prosperity card effect message");
    }

    @Test
    @DisplayName("RESP_15_test_2: all players draw 2 adventure cards")
    void RESP_15_test_2() {
      game.playEventCard();
      for (Player player : game.getPlayers()) {
        assertEquals(2, player.getHand().size(), String.format("%s drew 2 adventure cards", player.getName()));
      }
    }

    @Test
    @DisplayName("RESP_15_test_3: trims hands if adding cards to hand exceeds " + Player.MAX_HAND_SIZE)
    void RESP_15_test_3() {
      game.dealAdventureCards();
      String input = "1\n2\n\n".repeat(game.getPlayers().size());
      display.setScanner(new Scanner(input));

      game.playEventCard();
      for (Player player : game.getPlayers()) {
        assertEquals(Player.MAX_HAND_SIZE, player.getHand().size(), String.format("%s's hand size is equal to %d", player.getName(), Player.MAX_HAND_SIZE));
      }
    }

    @Test
    @DisplayName("RESP_15_test_4: prompts player to press the return key to clear the display for the next player")
    void RESP_15_test_4() {
      game.playEventCard();
      assertTrue(output.toString().contains("Press the return key to clear the display for the next player"), "Prompts player to press the return key to clear the display for the next player");
    }

    @Test
    @DisplayName("RESP_15_test_5: clears display after each player has drawn cards")
    void RESP_15_test_5() {
      game.playEventCard();
      assertTrue(output.toString().contains(Display.CLEAR_SCREEN_COMMAND), "Clears display after each player has drawn cards");
    }
  }

  @Nested
  @DisplayName("RESP_16: Ends Current Player's Turn and Clears Display")
  class RESP_16 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
    }

    @Test
    @DisplayName("RESP_16_test_1: indicates the current player's turn has ended")
    void RESP_16_test_1() {
      display.setScanner(new Scanner("\n"));
      display.promptEndTurn(game.getCurrentPlayer().getName());
      assertTrue(output.toString().contains(
        String.format("%s'S TURN ENDED", game.getCurrentPlayer().getName())),
        "Indicates the current player's turn has ended");
    }

    @Test
    @DisplayName("RESP_16_test_2: prompts player to press the return key to end their turn")
    void RESP_16_test_2() {
      display.setScanner(new Scanner("\n"));
      display.promptEndTurn(game.getCurrentPlayer().getName());
      assertTrue(output.toString().contains("Press the return key to end your turn and clear the display"), "Prompts player to press the return key to end their turn");
    }

    @Test
    @DisplayName("RESP_16_test_3: clears display")
    void RESP_16_test_3() {
      display.setScanner(new Scanner("\n"));
      display.clear();
      assertEquals(Display.CLEAR_SCREEN_COMMAND, output.toString(), "Clears display");
    }

    @Test
    @DisplayName("RESP_16_test_4: clears current turn's event card")
    void RESP_16_test_4() {
      game.startTurn();
      display.setScanner(new Scanner("\n"));
      game.endTurn();
      assertNull(game.getCurrentTurn().getEventCard(), "Clears current turn's event card");
    }

  }

  @Nested
  @DisplayName("RESP_17: Checks Winners")
  class RESP_17 {
    private final Game game = new Game();

    @BeforeEach
    void setUp() {
      game.setupPlayers();
    }

    @Test
    @DisplayName("RESP_17_test_1: returns winner if a player has won")
    void RESP_17_test_1() {
      Player player = game.getPlayers().get(0);
      player.setShields(Game.SHIELDS_TO_WIN);

      List<Player> winners = game.checkWinners();
      assertEquals(1, winners.size(), "Returns a list of winners");
      assertEquals(player, winners.get(0), "Returns the player who has won");
    }

    @Test
    @DisplayName("RESP_17_test_2: returns empty list if no player has won")
    void RESP_17_test_2() {
      Player player = game.getPlayers().get(0);
      player.setShields(Game.SHIELDS_TO_WIN - 1);

      List<Player> winners = game.checkWinners();
      assertTrue(winners.isEmpty(), "Returns an empty list if no player has won");
    }

    @Test
    @DisplayName("RESP_17_test_3: returns all winners if all players won")
    void RESP_17_test_3() {
      for (Player player : game.getPlayers()) {
        player.setShields(Game.SHIELDS_TO_WIN);
      }

      List<Player> winners = game.checkWinners();
      assertEquals(game.getPlayers(), winners, "Returns all winners");
    }
  }

  @Nested
  @DisplayName("RESP_18: Game Over")
  class RESP_18 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
    }

    @Test
    @DisplayName("RESP_18_test_1: sets game over to true if a player has won")
    void RESP_18_test_1() {
      game.getPlayers().get(0).setShields(Game.SHIELDS_TO_WIN);
      display.setScanner(new Scanner("\n"));

      game.endTurn();
      assertTrue(game.isGameOver(), "Sets game over to true if a player has won");
    }

    @Test
    @DisplayName("RESP_18_test_2: prints game over message")
    void RESP_18_test_2() {
      game.getPlayers().get(0).setShields(Game.SHIELDS_TO_WIN);
      display.setScanner(new Scanner("\n"));

      game.endTurn();
      assertTrue(output.toString().contains("Game Over! Winners:"), "Prints game over message");
    }

    @Test
    @DisplayName("RESP_18_test_3: prints winners")
    void RESP_18_test_3() {
      game.getPlayers().get(1).setShields(Game.SHIELDS_TO_WIN);
      display.setScanner(new Scanner("\n"));

      game.endTurn();
      assertTrue(output.toString().contains(game.getPlayers().get(1).getName()), "Prints winners");
    }

    @Test
    @DisplayName("RESP_18_test_4: prints multiple winners")
    void RESP_18_test_4() {
      game.getPlayers().get(0).setShields(Game.SHIELDS_TO_WIN);
      game.getPlayers().get(1).setShields(Game.SHIELDS_TO_WIN);
      display.setScanner(new Scanner("\n"));

      game.endTurn();
      assertTrue(output.toString().contains(game.getPlayers().get(0).getName()), "Prints winners");
      assertTrue(output.toString().contains(game.getPlayers().get(1).getName()), "Prints winners");
    }
  }

  @Nested
  @DisplayName("RESP_19: Player Draws Q Card - Create Quest")
  class RESP_19 {
    private final Game game = new Game();

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.createQuest(2);
    }

    @Test
    @DisplayName("RESP_19_test_1: creates a quest with the correct number of stages")
    void RESP_19_test_1() {
      assertEquals(QType.Q2.getNumStages(), game.getQuest().getNumStages(), "Creates a quest with the correct number of stages");
    }

    @Test
    @DisplayName("RESP_19_test_2: quest is set to active")
    void RESP_19_test_2() {
      assertTrue(game.getQuest().isActive(), "Quest is set to active");
    }

    @Test
    @DisplayName("RESP_19_test_3: current stage is set to first stage")
    void RESP_19_test_3() {
      Stage firstStage = game.getQuest().getStages().get(0);
      assertEquals(firstStage, game.getQuest().getCurrentStage(), "Current stage is set to first stage");
    }

    @Test
    @DisplayName("RESP_19_test_4: quest starts with no sponsor")
    void RESP_19_test_4() {
      assertNull(game.getQuest().getSponsor(), "Quest starts with no sponsor");
    }

    @Test
    @DisplayName("RESP_19_test_5: participants list is empty")
    void RESP_19_test_5() {
      assertTrue(game.getQuest().getParticipants().isEmpty(), "Participants list is empty");
    }

    @Test
    @DisplayName("RESP_19_test_6: current participant is null")
    void RESP_19_test_6() {
      assertNull(game.getQuest().getCurrentParticipant(), "Current participant is null");
    }
  }

  @Nested
  @DisplayName("RESP_20: Find Quest Sponsor")
  class RESP_20 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
      game.createQuest(2);
    }

    @Test
    @DisplayName("RESP_20_test_1: prompts current player to be the sponsor first")
    void RESP_20_test_1() {
      display.setScanner(new Scanner("Y\n\n"));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      String expectedMessage = String.format("%s, do you want to be the sponsor for this %d-stage quest?", game.getCurrentPlayer().getName(), game.getQuest().getNumStages());
      assertTrue(output.toString().contains(expectedMessage), "Prompts player to be the sponsor");
    }

    @Test
    @DisplayName("RESP_20_test_2: prompts next player to be the sponsor if current player declines")
    void RESP_20_test_2() {
      display.setScanner(new Scanner("N\n\nY\n\n"));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      int nextPlayerIndex = (game.getPlayers().indexOf(game.getCurrentPlayer()) + 1) % game.getPlayers().size();
      String nextPlayer = game.getPlayers().get(nextPlayerIndex).getName();
      String expectedMessage = String.format("%s, do you want to be the sponsor for this %d-stage quest?", nextPlayer, game.getQuest().getNumStages());
      assertTrue(output.toString().contains(expectedMessage), "Prompts next player to be the sponsor if current player declines");
    }

    @Test
    @DisplayName("RESP_20_test_3: sets sponsor if player accepts")
    void RESP_20_test_3() {
      display.setScanner(new Scanner("Y\n\n"));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      assertEquals(game.getCurrentPlayer(), game.getQuest().getSponsor(), "Sets sponsor if player accepts");
    }

    @Test
    @DisplayName("RESP_20_test_4: does not set sponsor if no player accepts")
    void RESP_20_test_4() {
      display.setScanner(new Scanner("N\n\n".repeat(game.getPlayers().size())));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      assertNull(game.getQuest().getSponsor(), "Does not set sponsor if no player accepts");
    }

    @Test
    @DisplayName("RESP_20_test_5: prints invalid input message if player enters invalid input")
    void RESP_20_test_5() {
      display.setScanner(new Scanner("invalid\n\nY\n\n"));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      assertTrue(output.toString().contains("Invalid input"), "Prints invalid input message if player enters invalid input");
    }

    @Test
    @DisplayName("RESP_20_test_6: prints sponsor found message if player accepts")
    void RESP_20_test_6() {
      display.setScanner(new Scanner("Y\n\n"));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      String expectedMessage = String.format("%s will be the sponsor for this quest", game.getCurrentPlayer().getName());
      assertTrue(output.toString().contains(expectedMessage), "Prints sponsor found message if player accepts");
    }

    @Test
    @DisplayName("RESP_20_test_7: prints sponsor declined message if player declines")
    void RESP_20_test_7() {
      display.setScanner(new Scanner("N\n\n".repeat(game.getPlayers().size())));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      String expectedMessage = String.format("%s declined to be the sponsor", game.getCurrentPlayer().getName());
      assertTrue(output.toString().contains(expectedMessage), "Prints sponsor declined message if player declines");
    }

    @Test
    @DisplayName("RESP_20_test_8: prints no sponsor found message if no player accepts and ends quest and turn")
    void RESP_20_test_8() {
      display.setScanner(new Scanner("N\n\n".repeat(game.getPlayers().size())));
      game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), game.getDisplay());
      String expectedMessage = String.format("No sponsor found");
      assertTrue(output.toString().contains(expectedMessage), "Prints no sponsor found message if no player accepts");
    }
  }

  @Nested
  @DisplayName("RESP_21: Quest Setup - Prompt Input")
  class RESP_21 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
      game.createQuest(2);
      game.getQuest().setSponsor(game.getCurrentPlayer());
    }

    @Test
    @DisplayName("RESP_21_test_1: prints hand of sponsor")
    void RESP_21_test_1() {
      Player sponsor = game.getQuest().getSponsor();
      display.printStageSetup(1, sponsor.getHand());
      String outputString = output.toString();
      for (int i = 0; i < sponsor.getHand().size(); i++) {
        String expectedCardString = String.format("%d. %s", i + 1, sponsor.getHand().get(i));
        assertTrue(outputString.contains(expectedCardString), "Card " + (i + 1) + " is printed correctly");
      }
    }

    @Test
    @DisplayName("RESP_21_test_2: prints stage setup rules")
    void RESP_21_test_2() {
      Player sponsor = game.getQuest().getSponsor();
      display.printStageSetup(1, sponsor.getHand());
      assertTrue(output.toString().contains("STAGE SETUP RULES: Each stage must consist of a single Foe card and 0/+ non-repeated Weapon cards."), "Prints stage setup rules");
    }

    @Test
    @DisplayName("RESP_21_test_3: stage setup rules include QUIT option")
    void RESP_21_test_3() {
      Player sponsor = game.getQuest().getSponsor();
      display.printStageSetup(1, sponsor.getHand());
      assertTrue(output.toString().contains("Enter QUIT once you are ready to proceed to the next stage"), "Prints stage setup rules");
    }

    @Test
    @DisplayName("RESP_21_test_4: prompts sponsor to choose a card number")
    void RESP_21_test_4() {
      Player sponsor = game.getQuest().getSponsor();
      display.setScanner(new Scanner("1\n"));
      display.promptForCardIndexWithQuit(sponsor.getHand().size(), true);
      assertTrue(output.toString().contains("CHOOSE A CARD POSITION:"), "Prints stage setup rules");
    }

    @Test
    @DisplayName("RESP_21_test_5: returns index of card if input is in valid range")
    void RESP_21_test_5() {
      Player sponsor = game.getQuest().getSponsor();
      display.setScanner(new Scanner("2\n"));
      int index = display.promptForCardIndexWithQuit(sponsor.getHand().size(), true);
      assertEquals(1, index, "Returns index of card if input is in valid range");
    }

    @Test
    @DisplayName("RESP_21_test_6: returns QUIT if input is QUIT")
    void RESP_21_test_6() {
      Player sponsor = game.getQuest().getSponsor();
      display.setScanner(new Scanner("QUIT\n"));
      int index = display.promptForCardIndexWithQuit(sponsor.getHand().size(), true);
      assertEquals(Quest.QUIT, index, "Returns QUIT if input is QUIT");
    }
  }

  @Nested
  @DisplayName("RESP_22: Quest Setup - Validate Card")
  class RESP_22 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;
    private Quest quest;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
      game.createQuest(2);
      quest = game.getQuest();
      quest.setSponsor(game.getCurrentPlayer());
    }

    @Test
    @DisplayName("RESP_22_test_1: returns true if card is valid")
    void RESP_22_test_1() {
      Stage stage = new Stage();
      boolean validCard = quest.validateStageSetupCard(stage, quest.getSponsor().getHand().get(0), display);
      assertTrue(validCard, "Returns true if card is valid");
    }

    @Test
    @DisplayName("RESP_22_test_2: returns false if stage already has a Foe card")
    void RESP_22_test_2() {
      Stage stage = new Stage();
      stage.addCard(new AdventureCard(FoeType.F5));
      boolean validCard = quest.validateStageSetupCard(stage, new AdventureCard(FoeType.F10), display);
      assertFalse(validCard, "Returns false if stage already has a Foe card");
    }

    @Test
    @DisplayName("RESP_22_test_3: returns false if stage for a duplicate Weapon card")
    void RESP_22_test_3() {
      Stage stage = new Stage();
      stage.addCard(new AdventureCard(WeaponType.D5));
      boolean validCard = quest.validateStageSetupCard(stage, new AdventureCard(WeaponType.D5), display);
      assertFalse(validCard, "Returns false if stage already has a Weapon card");
    }

    @Test
    @DisplayName("RESP_22_test_4: adds valid card to stage")
    void RESP_22_test_4() {
      Stage stage = new Stage();
      quest.addCardToStage(stage, quest.getSponsor().getHand().get(0), display);
      assertEquals(1, stage.getCards().size(), "Adds valid card to stage");
    }

    @Test
    @DisplayName("RESP_22_test_5: removes card from sponsor's hand")
    void RESP_22_test_5() {
      int originalHandSize = quest.getSponsor().getHand().size();
      Stage stage = new Stage();
      quest.addCardToStage(stage, quest.getSponsor().getHand().get(0), display);
      assertEquals(originalHandSize - 1, quest.getSponsor().getHand().size(), "Removes card from sponsor's hand");
    }

    @Test
    @DisplayName("RESP_22_test_6: increments sponsorNumCardsUsed when adding valid card to stage")
    void RESP_22_test_6() {
      Stage stage = new Stage();
      quest.addCardToStage(stage, quest.getSponsor().getHand().get(0), display);
      assertEquals(1, quest.getSponsorNumCardsUsed(), "Increments sponsorNumCardsUsed");
    }

    @Test
    @DisplayName("RESP_22_test_7: prints cards added to stage")
    void RESP_22_test_7() {
      Stage stage = new Stage();
      AdventureCard card = quest.getSponsor().getHand().get(0);
      quest.addCardToStage(stage, card, display);
      assertTrue(output.toString().contains(card.toString()), "Prints cards added to stage");
    }
  }

  @Nested
  @DisplayName("RESP_23: Quest Setup - Handle QUIT")
  class RESP_23 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;
    private Quest quest;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
      game.startTurn();
      game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
      game.createQuest(2);
      quest = game.getQuest();
      quest.setSponsor(game.getCurrentPlayer());
    }

    @Test
    @DisplayName("RESP_23_test_1: returns false if stage is empty")
    void RESP_23_test_1() {
      Stage stage = new Stage();
      boolean validQuit = quest.validateStageSetupQuit(stage, display);
      assertFalse(validQuit, "Returns false if stage is empty");
    }

    @Test
    @DisplayName("RESP_23_test_2: prints cannot be empty message if stage is empty")
    void RESP_23_test_2() {
      Stage stage = new Stage();
      quest.validateStageSetupQuit(stage, display);
      assertTrue(output.toString().contains("A stage cannot be empty."), "Prints cannot be empty message");
    }

    @Test
    @DisplayName("RESP_23_test_3: returns false if previous stage has higher value")
    void RESP_23_test_3() {
      display.setScanner(new Scanner("\n"));
      quest.getStages().get(0).addCard(new AdventureCard(FoeType.F15));
      quest.getStages().get(1).addCard(new AdventureCard(FoeType.F5));
      Stage currentStage = quest.getStages().get(1);
      boolean validQuit = quest.validateStageSetupQuit(currentStage, display);
      assertFalse(validQuit, "Returns false if previous stage has higher value");
    }

    @Test
    @DisplayName("RESP_23_test_4: prints insufficient value message if previous stage has higher value")
    void RESP_23_test_4() {
      display.setScanner(new Scanner("\n"));
      quest.getStages().get(0).addCard(new AdventureCard(FoeType.F15));
      quest.getStages().get(1).addCard(new AdventureCard(FoeType.F5));
      Stage currentStage = quest.getStages().get(1);
      quest.validateStageSetupQuit(currentStage, display);
      assertTrue(output.toString().contains("Insufficient value for this stage."), "Prints insufficient value message");
    }

    @Test
    @DisplayName("RESP_23_test_5: returns true if all conditions are met")
    void RESP_23_test_5() {
      display.setScanner(new Scanner("\n"));
      quest.getStages().get(0).addCard(new AdventureCard(FoeType.F5));
      quest.getStages().get(1).addCard(new AdventureCard(FoeType.F15));
      Stage currentStage = quest.getStages().get(1);
      boolean validQuit = quest.validateStageSetupQuit(currentStage, display);
      assertTrue(validQuit, "Returns true if all conditions are met");
    }

    @Test
    @DisplayName("RESP_23_test_6: prints cards used for stage")
    void RESP_23_test_6() {
      display.setScanner(new Scanner("\n"));
      quest.getStages().get(0).addCard(new AdventureCard(FoeType.F5));
      quest.getStages().get(1).addCard(new AdventureCard(FoeType.F15));
      Stage currentStage = quest.getStages().get(1);
      List<AdventureCard> cards = currentStage.getCards();
      quest.validateStageSetupQuit(currentStage, display);
      assertTrue(output.toString().contains(cards.toString()), "Prints cards used for stage");
    }
  }

  @Nested
  @DisplayName("RESP_24: Quest Setup - Quest Attack - Display and Prompt Eligible Participants")
  class RESP_24 {
    private final Game game = new Game();
    private StringWriter output;
    private Display display;
    private Quest quest;

    @BeforeEach
    void setUp() {
      game.setupPlayers();
      game.dealAdventureCards();
      output = new StringWriter();
      display = new Display(new PrintWriter(output));
      game.setDisplay(display);
      game.createQuest(2);
      quest = game.getQuest();

    }

    @Test
    @DisplayName("RESP_24_test_1: prints eligible participants")
    void RESP_24_test_1() {
      display.setScanner(new Scanner("Y\n\n".repeat(quest.getParticipants().size())));
      List<Participant> participants = quest.getParticipants();
      display.printParticipants(participants);
      assertTrue(output.toString().contains(participants.toString()), "Prints eligible participants");
    }

    @Test
    @DisplayName("RESP_24_test_2: prompts for eligible participants")
    void RESP_24_test_2() {
      quest.setSponsor(game.getPlayers().get(0));
      for (Player player : game.getPlayers()) {
        if (player != quest.getSponsor()) {
          quest.getParticipants().add(new Participant(player));
        }
      }
      display.setScanner(new Scanner("N\n\nY\n\nY\n\n"));
      List<Participant> participants = display.promptForParticipants(quest.getParticipants());
      assertEquals(2, participants.size(), "Prompts for participants and withdraws player");
    }

    @Test
    @DisplayName("RESP_24_test_3: sponsor not in participants")
    void RESP_24_test_3() {
      quest.setSponsor(game.getPlayers().get(0));
      quest.addAllPlayersExceptSponsorToParticipants(quest.getStages().get(0), game.getPlayers());
      assertEquals(game.getPlayers().size() - 1, quest.getParticipants().size(), "Sponsor not in participants");
    }
  }

}

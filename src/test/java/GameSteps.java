import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.*;

import shared.A1Scenario;
import shared.TestAdventureDeck;
import shared.TestEventDeck;
import shared.TwoWinnerGameTwoWinnerQuest;
import shared.OneWinnerGameWithEvents;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game.Display;
import game.Game;
import game.Player;
import game.cards.AdventureCard;
import game.cards.EventCard;
import game.decks.AdventureDeck;
import game.decks.EventDeck;
import game.enums.adventure.AdventureType;
import game.enums.adventure.FoeType;
import game.enums.adventure.WeaponType;
import game.enums.event.QType;
import game.quest.Quest;

public class GameSteps {
  private Game game;
  private final StringWriter output = new StringWriter();
  private final Display display = new Display(new PrintWriter(output));
  private Quest quest;

  @Given("the game is setup with rigged deck for {string}")
  public void the_game_is_setup_with_rigged_deck_for_scenario(String scenario) {
    AdventureDeck adventureDeck = getAdventureDeckForScenario(scenario);
    EventDeck eventDeck = getEventDeckForScenario(scenario);

    game = new Game(adventureDeck, eventDeck, display);
    game.setupPlayers();
  }

  @Given("the game deals adventure cards to players")
  public void the_game_deals_adventure_cards_to_players() {
    game.dealAdventureCards();
  }

  @Then("Player {int}'s hand is:")
  public void player_s_hand_is(int playerNumber, String hand) {
    Player player = game.getPlayers().get(playerNumber - 1);

    List<String> handList = Arrays.asList(hand.split(", "));
    List<AdventureType> expectedTypes = handList.stream()
        .map(this::parseCardType)
        .collect(Collectors.toList());
    List<AdventureType> actualTypes = player.getHand().stream()
        .map(AdventureCard::getType)
        .collect(Collectors.toList());

    assertEquals(expectedTypes, actualTypes, "Player " + playerNumber + " has correct hand");
  }

  @When("the game starts turn")
  public void the_game_starts_turn() {
    game.startTurn();
  }

  @Then("a {string} event card is drawn")
  public void a_event_card_is_drawn(String eventType) {
    EventCard drawnCard = game.getCurrentEventCard();

    assertEquals(eventType, drawnCard.getType().toString(), "Drawn event card is correct");
  }

  @Then("the game creates quest for current event card")
  public void the_game_creates_quest_for_current_event_card() {
    QType questCard = (QType) game.getCurrentEventCard().getType();
    quest = game.createQuest(questCard.getNumStages());
  }

  // Really should be "Player declines to sponsor quest"
  // But this is more readable in the context of the feature file
  @Then("Player {int} declines to sponsor quest")
  public void player_declines_to_sponsor_quest(int playerNumber) {
    display.setInput("N\n\n");

    quest.promptForSponsor(game.getCurrentPlayer(), display);
  }

  // Really should be "Player accepts to sponsor quest"
  // But this is more readable in the context of the feature file
  @Then("Player {int} accepts to sponsor quest")
  public void player_accepts_to_sponsor_quest(int playerNumber) {
    display.setInput("Y\n\n");

    quest.promptForSponsor(game.getCurrentPlayer(), display);
  }

  @Then("quest gets next potential sponsor")
  public void quest_gets_next_potential_sponsor() {
    quest.getNextPotentialSponsor(game.getPlayers(), game.getCurrentPlayer());
  }

  // Really should be "Player builds the quest stages for {string}"
  // But this is more readable in the context of the feature file
  @Then("Player {int} builds the quest stages for {string}")
  public void player_builds_the_quest_stages_for(int playerNumber, String scenario) {
    buildQuestStagesForScenario(scenario);
    quest.addAllPlayersExceptSponsorToParticipants(game.getPlayers());
  }

  @Then("stage {int} is setup with:")
  public void stage_is_setup_with(int stageNumber, String stageSetup) {
    List<String> stageSetupList = Arrays.asList(stageSetup.split(", "));
    List<AdventureType> expectedStageSetup = stageSetupList.stream()
        .map(this::parseCardType)
        .collect(Collectors.toList());
    List<AdventureType> actualStageSetup = quest.getStages().get(stageNumber - 1).getCards().stream()
        .map(AdventureCard::getType)
        .collect(Collectors.toList());

    assertEquals(expectedStageSetup, actualStageSetup, "Stage " + stageNumber + " is setup with correct foes");
  }

  @Then("{string} participants participate in quest")
  public void participants_participate_in_quest(String condition) {
    String input = getParticipantsInputForCondition(condition);
    display.setInput(input);

    quest.promptForParticipants(display);
  }

  @Then("participant draws an adventure card")
  public void participant_draws_an_adventure_card() {
    String input = String.format("%s\n", isFirstStage() ? "1\n" : "");
    display.setInput(input.repeat(quest.getParticipants().size()));

    quest.getCurrentParticipant().drawCard(game.getAdventureDeck(), display);
  }

  @Then("Player {int} sets up attack for {string} on stage {int}")
  public void player_sets_up_attack_for(int playerNumber, String scenario, int stageNumber) {
    display.setInput(getAttackSetupInputForScenario(scenario, playerNumber, stageNumber));

    quest.setupAttack(display);
  }

  @Then("participant's stage attack is:")
  public void participant_s_stage_attack_is(String stageAttack) {
    List<String> stageAttackList = Arrays.asList(stageAttack.split(", "));
    List<AdventureType> expectedStageAttack = stageAttackList.stream()
        .map(this::parseCardType)
        .collect(Collectors.toList());
    List<AdventureType> actualStageAttack = quest.getCurrentParticipant().getAttackCards().stream()
        .map(AdventureCard::getType)
        .collect(Collectors.toList());

    assertEquals(expectedStageAttack, actualStageAttack, "Participant has correct stage attack");
  }

  @Then("next participant's turn")
  public void next_participant_s_turn() {
    quest.getNextParticipant();
  }

  @Then("the quest resolves attacks on stage")
  public void the_quest_resolves_attacks_on_stage() {
    display.setInput("\n".repeat(quest.getParticipants().size()));

    quest.resolveAttacks(display);
  }

  @Then("Player {int}'s attack succeeds and remains a participant")
  public void player_s_attack_succeeds_and_remains_a_participant(int playerNumber) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertTrue(quest.getParticipantsAsPlayers().contains(player), "Player " + playerNumber + " remains a participant");
  }

  @Then("next stage starts")
  public void the_next_stage_starts() {
    quest.getNextStage();
  }

  @Then("Player {int}'s attack fails and is removed from quest")
  public void player_s_attack_fails_and_is_removed_from_quest(int playerNumber) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertFalse(quest.getParticipantsAsPlayers().contains(player), "Player " + playerNumber + " is removed from quest");
  }

  @Then("there are no more stages")
  public void there_are_no_more_stages() {
    assertNull(quest.getCurrentStage(), "There are no more stages");
  }

  @Then("shields are rewarded")
  public void shields_are_rewarded() {
    display.setInput("\n".repeat(quest.getParticipants().size()));

    quest.rewardShields(display);
  }

  @Then("Player {int} has {int} shields")
  public void player_has_shields(int playerNumber, int shields) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertEquals(shields, player.getShields(), "Player " + playerNumber + " has correct shields");
  }

  @Then("replenishing sponsor's hand for {string}")
  public void replenishing_sponsor_s_hand_for(String scenario) {
    display.setInput(getReplenishSponsorHandInputForScenario(scenario));

    quest.replenishSponsorHand(display, game.getAdventureDeck());
  }

  @Then("Player {int}'s hand has {int} cards")
  public void player_s_hand_has_cards(int playerNumber, int numCards) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertEquals(numCards, player.getHand().size(), "Player " + playerNumber + " has correct hand size");
  }

  @Then("next player's turn")
  public void next_player_s_turn() {
    game.nextTurn();
  }

  @Then("checking for winners")
  public void checking_for_winners() {
    game.checkWinners();
  }

  @Then("Player {int} is a winner")
  public void player_is_a_winner(int playerNumber) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertTrue(game.getWinners().contains(player), "Player " + playerNumber + " is a winner");
  }

  @Then("Player {int} loses {int} shields")
  public void player_loses_shields(int playerNumber, int shields) {
    Player player = game.getPlayers().get(playerNumber - 1);
    int shieldsBefore = player.getShields();

    game.playEventCard();
    int shieldsAfter = player.getShields();

    assertEquals(shieldsBefore - shields, shieldsAfter, "Player " + playerNumber + " has correct shields");
  }

  @Then("all players draw 2 adventure cards for {string}")
  public void all_players_draw_adventure_cards_for(String scenario) {
    display.setInput(getProsperityDrawCardsInputForScenario(scenario));

    game.playEventCard();
  }

  // Helper methods

  private String getProsperityDrawCardsInputForScenario(String scenario) {
    switch (scenario) {
      case "1winner_game_with_events":
        return OneWinnerGameWithEvents.getProsperityDrawCardsInput(game.getPlayers().size());
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private String getReplenishSponsorHandInputForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return A1Scenario.getReplenishSponsorHandInput();
      case "2winner_game_2winner_quest-q1":
        return TwoWinnerGameTwoWinnerQuest.getQ1ReplenishSponsorHandInput();
      case "2winner_game_2winner_quest-q2":
        return TwoWinnerGameTwoWinnerQuest.getQ2ReplenishSponsorHandInput();
      case "1winner_game_with_events-q1":
        return OneWinnerGameWithEvents.getQ1ReplenishSponsorHandInput();
      case "1winner_game_with_events-q2":
        return OneWinnerGameWithEvents.getQ2ReplenishSponsorHandInput();
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private String getAttackSetupInputForScenario(String scenario, int playerNumber, int stageNumber) {
    switch (scenario) {
      case "A1 scenario":
        return A1Scenario.getAttackSetupInput(playerNumber, stageNumber);
      case "2winner_game_2winner_quest-q1":
        return TwoWinnerGameTwoWinnerQuest.getQ1AttackSetupInput(playerNumber, stageNumber);
      case "2winner_game_2winner_quest-q2":
        return TwoWinnerGameTwoWinnerQuest.getQ2AttackSetupInput(playerNumber, stageNumber);
      case "1winner_game_with_events-q1":
        return OneWinnerGameWithEvents.getQ1AttackSetupInput(playerNumber, stageNumber);
      case "1winner_game_with_events-q2":
        return OneWinnerGameWithEvents.getQ2AttackSetupInput(playerNumber, stageNumber);
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private boolean isFirstStage() {
    return quest.getStages().getFirst() == quest.getCurrentStage();
  }

  private void buildQuestStagesForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        display.setInput(A1Scenario.getQuestStagesSetupInput());
        break;
      case "2winner_game_2winner_quest-q1":
        display.setInput(TwoWinnerGameTwoWinnerQuest.getQ1StagesSetupInput());
        break;
      case "2winner_game_2winner_quest-q2":
        display.setInput(TwoWinnerGameTwoWinnerQuest.getQ2StagesSetupInput());
        break;
      case "1winner_game_with_events-q1":
        display.setInput(OneWinnerGameWithEvents.getQ1StagesSetupInput());
        break;
      case "1winner_game_with_events-q2":
        display.setInput(OneWinnerGameWithEvents.getQ2StagesSetupInput());
        break;
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }

    quest.setup(display);
  }

  private String getParticipantsInputForCondition(String condition) {
    switch (condition) {
      case "all":
        return "Y\n\n".repeat(quest.getParticipants().size());
      case "all except first":
        return "N\n\n" + "Y\n\n".repeat(quest.getParticipants().size() - 1);
      default:
        throw new IllegalArgumentException("Unknown condition: " + condition);
    }
  }

  private AdventureDeck getAdventureDeckForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return new TestAdventureDeck(A1Scenario.getAdventureCards());
      case "2winner_game_2winner_quest":
        return new TestAdventureDeck(TwoWinnerGameTwoWinnerQuest.getAdventureCards());
      case "1winner_game_with_events":
        return new TestAdventureDeck(OneWinnerGameWithEvents.getAdventureCards());
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private EventDeck getEventDeckForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return new TestEventDeck(A1Scenario.getEventCards());
      case "2winner_game_2winner_quest":
        return new TestEventDeck(TwoWinnerGameTwoWinnerQuest.getEventCards());
      case "1winner_game_with_events":
        return new TestEventDeck(OneWinnerGameWithEvents.getEventCards());
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private AdventureType parseCardType(String card) {
    for (FoeType foeType : FoeType.values()) {
      if (card.equals(foeType.name())) {
        return foeType;
      }
    }

    for (WeaponType weaponType : WeaponType.values()) {
      if (card.equals(weaponType.name())) {
        return weaponType;
      }
    }

    throw new IllegalArgumentException("Unknown card type: " + card);
  }
}

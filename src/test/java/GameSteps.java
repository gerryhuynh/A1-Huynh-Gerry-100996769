import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.example.Display;
import org.example.Game;
import org.example.Player;
import org.example.cards.AdventureCard;
import org.example.cards.EventCard;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;
import org.example.enums.adventure.AdventureType;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.enums.event.QType;
import org.example.quest.Quest;

import common.A1Scenario;

import rigs.TestAdventureDeck;
import rigs.TestEventDeck;

public class GameSteps {
  private Game game;
  private StringWriter output = new StringWriter();
  private Display display = new Display(new PrintWriter(output));
  private Quest quest;

  @Given("the game is setup with rigged deck for {string}")
  public void the_game_is_setup_with_rigged_deck_for_scenario(String scenario) {
    AdventureDeck adventureDeck = getAdventureDeckForScenario(scenario);
    EventDeck eventDeck = getEventDeckForScenario(scenario);

    game = new Game(adventureDeck, eventDeck, display);
    game.setupPlayers();
  }

  @When("the game deals adventure cards to players")
  public void the_game_deals_adventure_cards_to_players() {
    game.dealAdventureCards();
  }

  @Then("Player {int}'s starting hand is:")
  public void player_s_starting_hand_is(int playerNumber, String hand) {
    Player player = game.getPlayers().get(playerNumber - 1);

    List<String> handList = Arrays.asList(hand.split(", "));
    List<AdventureType> expectedTypes = handList.stream()
        .map(this::parseCardType)
        .collect(Collectors.toList());
    List<AdventureType> actualTypes = player.getHand().stream()
        .map(AdventureCard::getType)
        .collect(Collectors.toList());

    assertEquals(expectedTypes, actualTypes, "Player " + playerNumber + " has correct starting hand");
  }

  @Given("the game starts turn")
  public void the_game_starts_turn() {
    game.startTurn();
  }

  @Then("a {string} event card is drawn")
  public void a_event_card_is_drawn(String eventType) {
    EventCard drawnCard = game.getCurrentEventCard();

    assertEquals(eventType, drawnCard.getType().toString(), "Drawn event card is correct");
  }

  @Given("the game creates quest for current event card")
  public void the_game_creates_quest_for_current_event_card() {
    QType questCard = (QType) game.getCurrentEventCard().getType();
    quest = game.createQuest(questCard.getNumStages());
  }

  @Given("Player 1 declines to sponsor and Player 2 accepts to sponsor quest")
  public void player_declines_to_sponsor() {
    display.setInput("N\n\nY\n\n");

    quest.findSponsor(game.getPlayers(), game.getCurrentPlayer(), display);
  }

  @When("Player builds the quest stages for {string}")
  public void player_builds_the_quest_stages_for(String scenario) {
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

  @Given("all participants participate in quest")
  public void all_participants_participate_in_quest() {
    display.setInput("Y\n\n".repeat(quest.getParticipants().size()));

    quest.promptForParticipants(display);
  }

  @Given("participant draws an adventure card")
  public void participant_draws_an_adventure_card() {
    String input = String.format("%s\n", isFirstStage() ? "1\n" : "");
    display.setInput(input.repeat(quest.getParticipants().size()));

    quest.getCurrentParticipant().drawCard(game.getAdventureDeck(), display);
  }

  @When("Player {int} sets up attack for {string} on stage {int}")
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

  @Given("next participant's turn")
  public void next_participant_s_turn() {
    quest.getNextParticipant();
  }

  @Given("the quest resolves attacks on stage")
  public void the_quest_resolves_attacks_on_stage() {
    display.setInput("\n".repeat(quest.getParticipants().size()));

    quest.resolveAttacks(display);
  }

  @Then("Player {int}'s attack succeeds and remains a participant")
  public void player_s_attack_succeeds_and_remains_a_participant(int playerNumber) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertTrue(quest.getParticipantsAsPlayers().contains(player), "Player " + playerNumber + " remains a participant");
  }

  @Given("next stage starts")
  public void the_next_stage_starts() {
    quest.getNextStage();
  }

  @Then("Player {int}'s attack fails and is removed from quest")
  public void player_s_attack_fails_and_is_removed_from_quest(int playerNumber) {
    Player player = game.getPlayers().get(playerNumber - 1);

    assertFalse(quest.getParticipantsAsPlayers().contains(player), "Player " + playerNumber + " is removed from quest");
  }

  // Helper methods

  private String getAttackSetupInputForScenario(String scenario, int playerNumber, int stageNumber) {
    switch (scenario) {
      case "A1 scenario":
        return A1Scenario.getAttackSetupInput(playerNumber, stageNumber);
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private boolean isFirstStage() {
    return quest.getStages().get(0) == quest.getCurrentStage();
  }

  private void buildQuestStagesForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        display.setInput(A1Scenario.getQuestStagesSetupInput());
        break;
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }

    quest.setup(display);
  }

  private AdventureDeck getAdventureDeckForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return new TestAdventureDeck(A1Scenario.getAdventureCards());
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private EventDeck getEventDeckForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return new TestEventDeck(A1Scenario.getEventCards());
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

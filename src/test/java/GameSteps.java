import io.cucumber.java.en.*;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.example.Game;
import org.example.Player;
import org.example.Display;
import org.example.cards.AdventureCard;
import org.example.enums.adventure.AdventureType;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;
import org.example.decks.AdventureDeck;
import org.example.decks.EventDeck;
import rigs.A1Scenario;
import rigs.TestAdventureDeck;
import rigs.TestEventDeck;
import org.example.cards.EventCard;
import org.example.enums.event.QType;

public class GameSteps {
  private Game game;
  private StringWriter output = new StringWriter();
  private Display display = new Display(new PrintWriter(output));
  private AdventureDeck adventureDeck;
  private EventDeck eventDeck;

  @Given("the game is setup with rigged deck for {string}")
  public void the_game_is_setup_with_rigged_deck_for_scenario(String scenario) {
    adventureDeck = getAdventureDeckForScenario(scenario);
    eventDeck = getEventDeckForScenario(scenario);

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

  @Given("the game draws an event card")
  public void the_game_draws_an_event_card() {
    game.drawEventCard();
  }

  @Then("a {string} event card is drawn")
  public void a_event_card_is_drawn(String eventType) {
    EventCard drawnCard = game.getCurrentEventCard();
    assertEquals(eventType, drawnCard.getType().toString(), "Drawn event card is correct");
  }

  @Given("the game creates quest for current event card")
  public void the_game_creates_quest_for_current_event_card() {
    QType questCard = (QType) game.getCurrentEventCard().getType();
    game.createQuest(questCard.getNumStages());
  }

  @Given("Player 1 declines to sponsor and Player 2 accepts to sponsor quest")
  public void player_declines_to_sponsor() {
    display.setInput("N\n\nY\n\n");
    game.getQuest().findSponsor(game.getPlayers(), game.getCurrentPlayer(), display);
  }

  @When("Player builds the quest stages for {string}")
  public void player_builds_the_quest_stages_for(String scenario) {
    buildQuestStagesForScenario(scenario);
  }

  @Then("Stage {int} is setup with:")
  public void stage_is_setup_with(int stageNumber, String stageSetup) {
    List<String> stageSetupList = Arrays.asList(stageSetup.split(", "));
    List<AdventureType> expectedStageSetup = stageSetupList.stream()
        .map(this::parseCardType)
        .collect(Collectors.toList());
    List<AdventureType> actualStageSetup = game.getQuest().getStages().get(stageNumber - 1).getCards().stream()
        .map(AdventureCard::getType)
        .collect(Collectors.toList());

    assertEquals(expectedStageSetup, actualStageSetup, "Stage " + stageNumber + " is setup with correct foes");
  }

  // Helper methods

  private void buildQuestStagesForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        display.setInput(A1Scenario.getA1ScenarioQuestStagesSetupInput());
        break;
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }

    game.getQuest().setup(display);
  }

  private AdventureDeck getAdventureDeckForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return new TestAdventureDeck(A1Scenario.getA1ScenarioAdventureCards());
      default:
        throw new IllegalArgumentException("Unknown scenario: " + scenario);
    }
  }

  private EventDeck getEventDeckForScenario(String scenario) {
    switch (scenario) {
      case "A1 scenario":
        return new TestEventDeck(A1Scenario.getA1ScenarioEventCards());
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

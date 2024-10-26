import io.cucumber.java.en.*;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Game;
import org.example.Player;
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

public class GameSteps {
  private Game game;
  private AdventureDeck adventureDeck;
  private EventDeck eventDeck;

  @Given("the game is setup with rigged deck for {string}")
  public void the_game_is_setup_with_rigged_deck_for_scenario(String scenario) {
    adventureDeck = getAdventureDeckForScenario(scenario);
    eventDeck = getEventDeckForScenario(scenario);

    game = new Game(adventureDeck, eventDeck);
    game.setupPlayers();
  }

  @When("the game deals adventure cards")
  public void the_game_deals_adventure_cards() {
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

  // Helper methods

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

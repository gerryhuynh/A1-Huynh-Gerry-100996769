package game;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.quest.Quest;
import game.enums.event.EType;
import game.enums.event.QType;
import game.cards.AdventureCard;
import game.quest.Stage;
// import game.cards.EventCard;
import game.quest.Participant;
import shared.A1Scenario;
import shared.TwoWinnerGameTwoWinnerQuest;
import shared.OneWinnerGameWithEvents;
import shared.ZeroWinnerQuest;
import shared.TestAdventureDeck;
import shared.TestEventDeck;
import game.decks.AdventureDeck;
import game.decks.EventDeck;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class GameController {

  private Game game;
  private Quest quest;

  @GetMapping("/startGame")
  public Map<String, Object> startGame() {
    resetGame();

    return gameSetup();
  }

  @GetMapping("/startScenario")
  public Map<String, Object> startScenario(@RequestParam String scenario) {
    resetGame(scenario);

    return gameSetup();
  }

  public Map<String, Object> gameSetup() {
    Map<String, Object> response = new HashMap<>();
    List<Map<String, Object>> players = new ArrayList<>();

    for (int i = 0; i < game.getPlayers().size(); i++) {
      Player player = game.getPlayers().get(i);
      Map<String, Object> playerData = new HashMap<>();

      playerData.put("playerName", player.getName());
      playerData.put("shields", player.getShields());
      playerData.put("cardCount", player.getHand().size());
      playerData.put("cards", player.getHand().toString());

      players.add(playerData);
    }

    response.put("players", players);
    response.put("currentGameTurn", game.getCurrentTurn().getPlayer().getName());
    response.put("message", String.format("%s's turn.",
        game.getCurrentTurn().getPlayer().getName()));

    return response;
  }

  @GetMapping("/endGame")
  public void endGame() {
    if (game != null) {
      game.getPlayers().clear();
      game = null;
    }

    System.out.println("\nGame ended.");
  }

  @GetMapping("/drawEventCard")
  public Map<String, Object> drawEventCard() {
    Map<String, Object> response = new HashMap<>();
    game.drawEventCard();
    // game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));
    // game.getCurrentTurn().setEventCard(new EventCard(EType.PLAGUE));

    response.put("eventCardType", game.getCurrentEventCard().getType());
    response.put(
      "eventCardDescription",
      game.getCurrentEventCard().getType().getEffectDesc()
    );
    response.put("message", "");
    response.put("isQuest", false);

    if (game.getCurrentEventCard().getType() instanceof QType) {
      QType questType = (QType) game.getCurrentEventCard().getType();
      quest = game.createQuest(questType.getNumStages());

      response.put("isQuest", true);
      response.put("questNumStages", quest.getNumStages());
      response.put("message", getNextPotentialSponsorPrompt());
    } else {
      Player player = game.getCurrentTurn().getPlayer();

      switch ((EType) game.getCurrentEventCard().getType()) {
        case PLAGUE:
          int originalShields = player.getShields();
          player.setShields(Math.max(player.getShields() - 2, 0));
          response.put("message", String.format("%s's shields: %d -> %d", player.getName(), originalShields, player.getShields()));
          break;
        case QUEENS_FAVOR:
          List<AdventureCard> drawnCards = game.getAdventureDeck().draw(2);
          String message = String.format("%s drew 2 adventure cards.\n\n", player.getName());
          message += addToHand(player, drawnCards);
          response.put("message", message);
          response.put("needToTrim", player.needToTrimHand());
          break;
        case PROSPERITY:
          // TODO
          break;
      }
    }

    return response;
  }

  @PostMapping("/submitSponsorChoice")
  public Map<String, Object> submitSponsorChoice(@RequestParam String sponsorChoice) {
    Map<String, Object> response = new HashMap<>();
    response.put("sponsor", null);
    response.put("message", "");
    response.put("noSponsor", false);

    if (sponsorChoice.equalsIgnoreCase("y") || sponsorChoice.equalsIgnoreCase("yes")) {
      quest.setSponsor(quest.getCurrentPotentialSponsor());
      response.put("sponsor", quest.getSponsor().getName());
      response.put("message", String.format("%s will be the sponsor for this quest.", quest.getSponsor().getName()));
    } else {
      response.put("message", String.format("%s declined to be the sponsor. Get next player.",
          quest.getCurrentPotentialSponsor().getName()));
      quest.getNextPotentialSponsor(game.getPlayers(), game.getCurrentPlayer());

      if (quest.getCurrentPotentialSponsor() == null) {
        response.put("message", String.format("No sponsor found. Quest will not continue. %s's turn ends. Get next player.",
        game.getCurrentTurn().getPlayer().getName()));
        endTurn(); // turn switches to next player
        response.put("noSponsor", true);
      }
    }

    return response;
  }

  @GetMapping("/nextPotentialSponsor")
  public Map<String, Object> nextPotentialSponsor() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", getNextPotentialSponsorPrompt());
    return response;
  }

  @GetMapping("/nextPlayerTurn")
  public Map<String, Object> nextPlayerTurn() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", String.format("%s's turn.",
        game.getCurrentTurn().getPlayer().getName()));
    response.put("currentGameTurn", game.getCurrentTurn().getPlayer().getName());
    return response;
  }

  @GetMapping("/nextStage")
  public Map<String, Object> nextStage() {
    Map<String, Object> response = new HashMap<>();
    response.put("currentStage", quest.getCurrentStage().getStageNumber());
    response.put("message", String.format("""
      <strong>ATTACK PHASE...</strong>

      <strong>ELIGIBLE PARTICIPANTS:</strong> %s

      %s, do you want to be a participant for this stage?

      %s
        """, quest.getParticipants().toString(),
        quest.getCurrentParticipant().getPlayer().getName(),
        getHandList(quest.getCurrentParticipant().getPlayer().getHand())
        ));
    return response;
  }

  @PostMapping("/submitStageSetupChoice")
  public Map<String, Object> submitStageSetupChoice(@RequestParam String cardPosition) {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");
    response.put("validCardAdded", false);
    response.put("stageSetupComplete", false);
    response.put("questSetupComplete", false);

    if (cardPosition.equalsIgnoreCase("quit")) {
      Map.Entry<Boolean, String> validQuit = quest.validateStageSetupQuit(quest.getCurrentSetupStage());
      boolean isValid = validQuit.getKey();
      String errorMessage = validQuit.getValue();

      if (isValid) {
        response.put("message", String.format("""
          <strong>STAGE %d SETUP COMPLETE.</strong>

          <strong>CARDS ADDED TO STAGE:</strong> %s

          Continue to the next stage.
          """, quest.getCurrentSetupStage().getStageNumber(),
            quest.getCurrentSetupStage().getCards().toString()
          ));
        response.put("stageSetupComplete", true);
        quest.getNextSetupStage();
      } else {
        response.put("message", getHandAndPrintRulesWithError(errorMessage, getStageSetupRules()));
      }
    } else {
      int cardIndex = Integer.parseInt(cardPosition) - 1;
      AdventureCard selectedCard = quest.getSponsor().getHand().get(cardIndex);

      Map.Entry<Boolean, String> validCard = quest.validateStageSetupCard(
        quest.getCurrentSetupStage(),
        selectedCard
      );
      boolean isValid = validCard.getKey();
      String errorMessage = validCard.getValue();

      if (isValid) {
        quest.addCardToStage(quest.getCurrentSetupStage(), selectedCard);
        response.put("message", String.format("""
          <strong>CARDS ADDED TO STAGE:</strong> %s

          %s
          """, quest.getCurrentSetupStage().getCards().toString(),
            getHandList(quest.getSponsor().getHand())
          ));
        response.put("validCardAdded", true);
      } else {
        response.put("message", getHandAndPrintRulesWithError(errorMessage, getStageSetupRules()));
      }
    }


    if (quest.getCurrentSetupStage() == null) {
      // END OF QUEST SETUP
      response.put("questSetupComplete", true);
      response.put("message", String.format("""
          <strong>QUEST SETUP COMPLETE.</strong>

          %s
          """, getQuestCards()
        ));
      quest.addAllPlayersExceptSponsorToParticipants(game.getPlayers());
    }

    return response;
  }

  @GetMapping("/nextStageSetup")
  public Map<String, Object> nextStageSetup() {
    Map<String, Object> response = new HashMap<>();
    response.put("currentStage", quest.getCurrentSetupStage().getStageNumber());
    response.put("message", String.format("""
      <strong>SETTING UP STAGE %d...</strong>

        %s

        %s
        """, quest.getCurrentSetupStage().getStageNumber(),
        getHandList(quest.getSponsor().getHand()),
        getStageSetupRules()
        ));
    return response;
  }

  @PostMapping("/submitParticipationChoice")
  public Map<String, Object> submitParticipationChoice(@RequestParam String participationChoice) {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");

    if (participationChoice.equalsIgnoreCase("y") || participationChoice.equalsIgnoreCase("yes")) {
      quest.getActiveParticipants().add(quest.getCurrentPotentialParticipant());
      response.put("message", String.format("%s will be a participant for this stage.",
          quest.getCurrentPotentialParticipant()));
    } else {
      response.put("message", String.format("%s will not be a participant for this stage.",
          quest.getCurrentPotentialParticipant()));
    }

    quest.getNextPotentialParticipant();

    if (quest.getCurrentPotentialParticipant() == null) {
      // END OF PARTICIPANTS FINDING
      quest.replaceParticipants();
    }

    return response;
  }

  @GetMapping("/nextPotentialParticipant")
  public Map<String, Object> nextPotentialParticipant() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");
    response.put("continueQuest", false);
    response.put("questComplete", false);

    if (quest.getCurrentPotentialParticipant() == null && quest.getParticipants().size() == 0) {
      response.put("message", """
        <strong>QUEST COMPLETE!</strong>
        No participants were successful in defeating the quest's stages. Return to the sponsor.
        """);
      response.put("questComplete", true);
    } else if (quest.getCurrentPotentialParticipant() == null) {
      response.put("message", String.format("""
        <strong>Participants found:</strong> %s

        Starting Stage %d for %s.
        """, quest.getParticipants().toString(),
          quest.getCurrentStage().getStageNumber(),
          quest.getCurrentParticipant().getPlayer().getName()
        ));
      response.put("continueQuest", true);
    } else {
      response.put("message", String.format("""
        %s, do you want to be a participant for this stage?

      %s
        """, quest.getCurrentPotentialParticipant().getPlayer().getName(),
        getHandList(quest.getCurrentPotentialParticipant().getPlayer().getHand())
        ));
    }
    return response;
  }

  @GetMapping("/nextAttackTurn")
  public Map<String, Object> nextAttackTurn() {
    Player currentParticipant = quest.getCurrentParticipant().getPlayer();
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");
    response.put("questAttackTurn", currentParticipant.getName());
    response.put("needToTrim", true);

    List<AdventureCard> drawnCards = game.getAdventureDeck().draw(1);

    String message = String.format("<strong>%s's turn.</strong>\n\n", currentParticipant.getName());

    message += addToHand(currentParticipant, drawnCards);

    response.put("message", message);
    response.put("needToTrim", currentParticipant.needToTrimHand());

    return response;
  }

  @GetMapping("/replenishSponsorHands")
  public Map<String, Object> replenishSponsorHands() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");
    response.put("needToTrim", true);

    String baseMessage = String.format(
        "<strong>REPLENISHING SPONSOR %s HANDS...</strong> (%d cards)\n\n",
          quest.getSponsor().getName(),
          quest.getNumCardsToAddToHand()
      );

    List<AdventureCard> drawnCards = game.getAdventureDeck().draw(quest.getNumCardsToAddToHand());
    String message = addToHand(quest.getSponsor(), drawnCards);

    response.put("needToTrim", quest.getSponsor().needToTrimHand());
    response.put("message", baseMessage + message);

    return response;
  }

  @GetMapping("/updatePlayersInfo")
  public Map<String, Object> updatePlayersInfo() {
    Map<String, Object> response = new HashMap<>();
    List<Map<String, Object>> players = new ArrayList<>();

    for (int i = 0; i < game.getPlayers().size(); i++) {
      Player player = game.getPlayers().get(i);
      Map<String, Object> playerData = new HashMap<>();

      playerData.put("playerName", player.getName());
      playerData.put("shields", player.getShields());
      playerData.put("cardCount", player.getHand().size());
      playerData.put("cards", player.getHand().toString());

      players.add(playerData);
    }

    response.put("players", players);
    return response;
  }

  @PostMapping("/submitTrimChoice")
  public Map<String, Object> submitTrimChoice(@RequestParam String cardPosition, @RequestParam int playerNum) {
    Map<String, Object> response = new HashMap<>();
    response.put("trimComplete", false);
    response.put("message", "");

    Player player = game.getPlayers().get(playerNum - 1);

    int cardIndex = Integer.parseInt(cardPosition) - 1;
    List<AdventureCard> trimmedCards = player.getTrimmedCards();

    trimmedCards.add(player.getHand().remove(cardIndex));

    if (player.needToTrimHand()) {
      response.put("message", String.format("""
        You must continue trimming your hand. Please discard another card.

        %s
        """, getHandList(player.getHand())
        ));
    } else {
      response.put("message", String.format("""
        Trim complete.

        %s
        """, getHandList(player.getHand())
        ));
      response.put("trimComplete", true);
    }

    return response;
  }

  @GetMapping("/setupAttack")
  public Map<String, Object> setupAttack() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", String.format("""
      <strong>\n%s SETTING UP ATTACK FOR STAGE %d...</strong>

      %s
      """, quest.getCurrentParticipant().getPlayer().getName(),
        quest.getCurrentStage().getStageNumber(),
        getAttackSetupRules()
      ));
    return response;
  }

  @PostMapping("/submitAttackSetupChoice")
  public Map<String, Object> submitAttackSetupChoice(@RequestParam String cardPosition) {
    Participant currentParticipant = quest.getCurrentParticipant();
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");
    response.put("attackSetupComplete", false);
    response.put("stageAttackSetupComplete", false);

    if (cardPosition.equalsIgnoreCase("quit")) {
      String message = String.format("""
            <strong>STAGE %d ATTACK SETUP COMPLETE FOR %s.</strong>

            <strong>CARDS IN ATTACK:</strong>
            %s

            Continue to the next player.
            """,
            quest.getCurrentStage().getStageNumber(),
            currentParticipant.getPlayer().getName(),
            currentParticipant.getAttackCards()
        );

      response.put("attackSetupComplete", true);
      response.put("message", message);
      quest.getNextParticipant();
    } else {
      int cardIndex = Integer.parseInt(cardPosition) - 1;
      AdventureCard selectedCard = currentParticipant.getPlayer().getHand().get(cardIndex);

      Map.Entry<Boolean, String> validCard = quest.validateAttackCard(currentParticipant.getAttackCards(), selectedCard);
      boolean isValid = validCard.getKey();
      String errorMessage = validCard.getValue();

      if (isValid) {
        currentParticipant.addCardToAttack(selectedCard);
        response.put("message", String.format("""
          <strong>CARDS IN ATTACK:</strong>
          %s

          %s
          """,
          currentParticipant.getAttackCards().toString(),
          getHandList(currentParticipant.getPlayer().getHand())
        ));
      } else {
        response.put("message", String.format("""
          <strong>Invalid card selection.</strong> %s

          <strong>CARDS IN ATTACK:</strong>
          %s

          %s

          %s
          """,
          errorMessage,
          currentParticipant.getAttackCards().toString(),
          getHandList(currentParticipant.getPlayer().getHand()),
          getAttackSetupRules()
        ));
      }
    }

    if (quest.getCurrentParticipant() == null) {
      // END OF ATTACK SETUP
      response.put("stageAttackSetupComplete", true);
    }
    return response;
  }

  @GetMapping("/resolveAttacks")
  public Map<String, Object> resolveAttacks() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");

    int stageValue = quest.getCurrentStage().getValue();
    int participantAttackValue = 0;
    String message = String.format(
      """
      <strong>RESOLVING STAGE %d ATTACKS...</strong>

      <strong>Sponsor's Defense:</strong> %s. <strong>Value:</strong> %d
      """, quest.getCurrentStage().getStageNumber(),
        quest.getCurrentStage().getCards().toString(),
        stageValue
      );

    for (Participant participant : quest.getParticipants()) {
      participantAttackValue = participant.getAttackValue();
      message += String.format("\n<strong>%s ATTACK VALUE:</strong> %d", participant.getPlayer().getName(), participantAttackValue);

      if (participantAttackValue >= stageValue) {
        message += String.format("\n%s's attack is successful! You will continue to the next stage.\n", participant.getPlayer().getName());
        quest.getActiveParticipants().add(participant);
      } else {
        message += String.format("\n%s's attack is unsuccessful! You are no longer eligible to continue the quest.\n", participant.getPlayer().getName());
      }

      participant.clearAttackCards();
    }

    quest.replaceParticipants();
    quest.setCurrentPotentialParticipant(quest.getCurrentParticipant());
    quest.getNextStage();

    if (quest.getCurrentStage() == null || quest.getParticipants().size() == 0) {
      response.put("questComplete", true);
    }

    response.put("message", message);

    return response;
  }

  @GetMapping("/rewardShields")
  public Map<String, Object> rewardShields() {
    Map<String, Object> response = new HashMap<>();
    String message = quest.rewardShields();
    response.put("message", message);
    return response;
  }

  @GetMapping("/endTurn")
  public Map<String, Object> endTurn() {
    Map<String, Object> response = new HashMap<>();
    response.put("winners", "");

    List<Player> winners = game.endTurn();
    quest = null;

    if (winners.size() > 0) {
      String winnersMessage = "";
      for (Player winner : winners) {
        winnersMessage += String.format("- %s\n", winner.toString());
      }
      response.put("winners", winnersMessage);
    }

    return response;
  }

  @GetMapping("/getWinners")
  public Map<String, Object> getWinners() {
    Map<String, Object> response = new HashMap<>();
    response.put("winners", game.getWinners().toString());
    return response;
  }

  // helper methods

  private String addToHand(Player player, List<AdventureCard> cardsToAdd) {
    player.addToHand(cardsToAdd);

    String message = String.format("""
      <strong>ADVENTURE CARDS DRAWN:</strong>
      %s
      """, cardsToAdd.toString());

    message += player.needToTrimHand()
      ? "\nYou must trim your hand. Please discard a card.\n\n"
      : "No need to trim your hand.\n\n";

    message += getHandList(player.getHand());

    return message;
  }

  private String getQuestCards() {
    StringBuilder sb = new StringBuilder();
    sb.append("<strong>Cards used for the quest:</strong>\n");
    for (Stage stage : quest.getStages()) {
      sb.append(String.format("<strong>Stage %d:</strong> %s\n", stage.getStageNumber(), stage.getCards().toString()));
    }
    return sb.toString();
  }

  private String getHandAndPrintRulesWithError(String errorMessage, String rules) {
    return String.format("""
      <strong>%s</strong>

      %s

      %s
      """, errorMessage,
        getHandList(quest.getSponsor().getHand()),
        rules
      );
  }

  private String getHandList(List<AdventureCard> hand) {
    StringBuilder sb = new StringBuilder();
    sb.append("<strong>YOUR HAND:</strong>\n");
    for (int i = 0; i < hand.size(); i++) {
      sb.append(String.format("%d. %s\n", i + 1, hand.get(i)));
    }
    return sb.toString();
  }

  private String getAttackSetupRules() {
    return String.format("""
      <strong>ATTACK SETUP RULES:</strong> Each stage attack must consist of only non-repeated Weapon cards.
      Choose the cards you want to use to attack this stage.
      Enter QUIT once you are satisfied with your attack setup.
      """);
  }

  private String getStageSetupRules() {
    return String.format("""
        <strong>STAGE SETUP RULES:</strong> Each stage must consist of a single Foe card and 0/+ non-repeated Weapon cards.
        Choose the cards you want to add to this stage by entering the card position.
        Enter QUIT once you are ready to proceed to the next stage.
        """);
  }

  private String getNextPotentialSponsorPrompt() {
    return String.format("%s, do you want to be the sponsor for this %d-stage quest?",
        quest.getCurrentPotentialSponsor().getName(),
        quest.getNumStages());
  }

  private void resetGame() {
    game = new Game();
    game.setupPlayers();
    game.dealAdventureCards();
  }

  private void resetGame(String scenario) {
    AdventureDeck adventureDeck = getAdventureDeckForScenario(scenario);
    EventDeck eventDeck = getEventDeckForScenario(scenario);
    game = new Game(adventureDeck, eventDeck);
    game.setupPlayers();
    game.dealAdventureCards();
  }

  private AdventureDeck getAdventureDeckForScenario(String scenario) {
    return switch (scenario) {
      case "A1_scenario" -> new TestAdventureDeck(A1Scenario.getAdventureCards());
      case "2winner_game_2winner_quest" -> new TestAdventureDeck(TwoWinnerGameTwoWinnerQuest.getAdventureCards());
      case "1winner_game_with_events" -> new TestAdventureDeck(OneWinnerGameWithEvents.getAdventureCards());
      case "0_winner_quest" -> new TestAdventureDeck(ZeroWinnerQuest.getAdventureCards());
      default -> throw new IllegalArgumentException("Unknown scenario: " + scenario);
    };
  }

  private EventDeck getEventDeckForScenario(String scenario) {
    return switch (scenario) {
      case "A1_scenario" -> new TestEventDeck(A1Scenario.getEventCards());
      case "2winner_game_2winner_quest" -> new TestEventDeck(TwoWinnerGameTwoWinnerQuest.getEventCards());
      case "1winner_game_with_events" -> new TestEventDeck(OneWinnerGameWithEvents.getEventCards());
      case "0_winner_quest" -> new TestEventDeck(ZeroWinnerQuest.getEventCards());
      default -> throw new IllegalArgumentException("Unknown scenario: " + scenario);
    };
  }
}

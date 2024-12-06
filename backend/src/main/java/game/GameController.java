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
import game.enums.event.QType;
import game.cards.AdventureCard;
import game.quest.Stage;
import game.cards.EventCard;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class GameController {

  private Game game;
  private Quest quest;

  @GetMapping("/startGame")
  public Map<String, Object> startGame() {
    resetGame();

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
    // game.drawEventCard();
    // TODO. TEST. TEMP
    game.getCurrentTurn().setEventCard(new EventCard(QType.Q2));

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

    System.out.println(quest.toString());

    if (cardPosition.equalsIgnoreCase("quit")) {
      Map.Entry<Boolean, String> validQuit = quest.validateStageSetupQuit(quest.getCurrentSetupStage());
      boolean isValid = validQuit.getKey();
      String errorMessage = validQuit.getValue();

      if (isValid) {
        response.put("message", String.format("""
          STAGE %d SETUP COMPLETE.

          CARDS ADDED TO STAGE: %s

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
      System.out.println("\n\nCURRENT PARTICIPANT:");
      System.out.println(quest.getCurrentParticipant());
    }

    return response;
  }

  @GetMapping("/nextPotentialParticipant")
  public Map<String, Object> nextPotentialParticipant() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");
    response.put("questComplete", false);

    if (quest.getCurrentPotentialParticipant() == null && quest.getActiveParticipants().size() == 0) {
      response.put("message", """
        QUEST COMPLETE!
        No participants were successful in defeating the quest's stages. Return to the sponsor.
        """);
      response.put("questComplete", true);
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

  @GetMapping("/replenishSponsorHands")
  public Map<String, Object> replenishSponsorHands() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "");

    quest.setCardsToReplenish(game.getAdventureDeck().draw(quest.getNumCardsToReplenish()));

    String baseMessage = String.format("""
      <strong>REPLENISHING SPONSOR %s HANDS...</strong> (%d cards)

      <strong>ADVENTURE CARDS TO ADD TO HAND:</strong>
      %s

      """, quest.getSponsor().getName(),
        quest.getNumCardsToReplenish(),
        quest.getCardsToReplenish().toString()
      );

    List<AdventureCard> cards = quest.getCardsToReplenish();
    List<AdventureCard> trimmedCards = quest.getSponsor().getTrimmedCards();
    Player sponsor = quest.getSponsor();

    sponsor.setNumCardsToTrim(sponsor.computeNumCardsToTrim(cards.size()));
    if (sponsor.getNumCardsToTrim() > 0) {
      while (cards.size() > Player.MAX_HAND_SIZE) {
        trimmedCards.add(cards.remove(0));
        sponsor.setNumCardsToTrim(sponsor.getNumCardsToTrim() - 1);
      }
    }

    String trimMessage = sponsor.getNumCardsToTrim() > 0 ?
      "You must trim your hand. Please discard a card.\n\n" : "";

    response.put("message", baseMessage + trimMessage + getHandList(sponsor.getHand()));

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
  public Map<String, Object> submitTrimChoice(@RequestParam String cardPosition) {
    List<AdventureCard> cards = new ArrayList<>();
    List<AdventureCard> trimmedCards = new ArrayList<>();
    Player player = null;
    int cardIndex = 0;

    if (isQuest()) {
      cards = quest.getCardsToReplenish();
      trimmedCards = quest.getSponsor().getTrimmedCards();
      player = quest.getSponsor();
      cardIndex = Integer.parseInt(cardPosition) - 1;
    }

    Map<String, Object> response = new HashMap<>();
    response.put("trimComplete", false);
    response.put("message", "");

    trimmedCards.add(player.getHand().remove(cardIndex));
    player.setNumCardsToTrim(player.getNumCardsToTrim() - 1);
    response.put("message", String.format("""
      You must continue trimming your hand. Please discard another card.

      %s
      """, getHandList(player.getHand())
      ));

    if (player.getNumCardsToTrim() < 1) {
      player.getHand().addAll(cards);
      player.sortHand();
      response.put("message", String.format("""
        Trim complete.

        %s
        """, getHandList(player.getHand())
        ));
      response.put("trimComplete", true);
    }

    return response;
  }

  @GetMapping("/endTurn")
  public Map<String, Object> endTurn() {
    Map<String, Object> response = new HashMap<>();
    response.put("winners", "");

    List<Player> winners = game.endTurn();
    quest = null;

    if (winners.size() > 0) {
      response.put("winners", winners.toString());
    }

    return response;
  }

  // helper methods

  private boolean isQuest() {
    return quest != null;
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
}

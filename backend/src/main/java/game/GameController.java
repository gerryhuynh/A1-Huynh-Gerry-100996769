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

      playerData.put("playerName", "P" + (i + 1));
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
        game.endTurn(); // turn switches to next player
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

  // helper methods

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

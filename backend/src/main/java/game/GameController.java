package game;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    game.drawEventCard();

    if (game.getCurrentEventCard().getType() instanceof QType) {
      QType questCard = (QType) game.getCurrentEventCard().getType();
      quest = game.createQuest(questCard.getNumStages());
    }

    Map<String, Object> response = new HashMap<>();
    response.put("eventCardType", game.getCurrentEventCard().getType());
    response.put(
      "eventCardDescription",
      game.getCurrentEventCard().getType().getEffectDesc()
    );
    return response;
  }

  private void resetGame() {
    game = new Game();
    game.setupPlayers();
    game.dealAdventureCards();
  }
}

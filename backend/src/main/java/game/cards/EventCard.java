package game.cards;

import java.util.List;

import game.Display;
import game.Game;
import game.Player;
import game.decks.AdventureDeck;
import game.enums.event.EType;
import game.enums.event.EventType;
import game.enums.event.QType;

public class EventCard extends Card<EventType> {
  public EventCard(EventType type) {
    super(type);
  }

  public String play(Game game) {
    EventType type = getType();
    if (type == EType.PLAGUE) return plagueEffect(game.getCurrentPlayer());
    if (type == EType.QUEENS_FAVOR) return queensFavorEffect(game.getCurrentPlayer(), game.getAdventureDeck(), game.getDisplay());
    if (type == EType.PROSPERITY) return prosperityEffect(game);
    if (type instanceof QType) return startQuest(game);
    return "";
  }

  private String startQuest(Game game) {
    game.createQuest(((QType) getType()).getNumStages());
    game.startQuest();
    return "";
  }

  private String plagueEffect(Player player) {
    int originalShields = player.getShields();
    player.setShields(Math.max(player.getShields() - 2, 0));
    return String.format("\n%s's shields: %d -> %d", player.getName(), originalShields, player.getShields());
  }

  private String queensFavorEffect(Player player, AdventureDeck adventureDeck, Display display) {
    return drawTwoAdventureCards(player, adventureDeck, display);
  }

  private String prosperityEffect(Game game) {
    List<Player> players = game.getPlayers();
    for (int i = 0; i < players.size(); i++) {
        Player player = players.get(i);
        if (i != game.getPlayers().indexOf(game.getCurrentPlayer())) {
          game.getDisplay().printEventCardEffect(this);
          game.getDisplay().printCurrentPlayer(player);
        }
        drawTwoAdventureCards(player, game.getAdventureDeck(), game.getDisplay());

        if (i < players.size() - 1) {
            game.getDisplay().promptNextPlayer();
        } else {
            game.getDisplay().promptReturnToOriginalPlayer();
        }
        game.getDisplay().clear();
    }
    return "\nAll players drew 2 adventure cards.";
  }

  private String drawTwoAdventureCards(Player player, AdventureDeck adventureDeck, Display display) {
    player.addToHand(adventureDeck.draw(2), display);
    return String.format("\n%s drew 2 adventure cards.", player.getName());
  }

  @Override
  public String toString() {
    return getType().toString();
  }
}

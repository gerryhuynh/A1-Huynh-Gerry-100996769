package shared;

import java.util.List;

import game.cards.AdventureCard;
import game.decks.AdventureDeck;

public class TestAdventureDeck extends AdventureDeck {
  public TestAdventureDeck(List<AdventureCard> cards) {
    super();
    addToTopOfDeck(cards);
  }
}

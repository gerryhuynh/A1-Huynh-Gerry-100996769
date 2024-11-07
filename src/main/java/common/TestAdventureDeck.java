package common;

import org.example.decks.AdventureDeck;
import org.example.cards.AdventureCard;

import java.util.List;

public class TestAdventureDeck extends AdventureDeck {
  public TestAdventureDeck(List<AdventureCard> cards) {
    super();
    addToTopOfDeck(cards);
  }
}

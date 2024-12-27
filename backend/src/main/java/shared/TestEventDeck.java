package shared;

import java.util.List;

import game.cards.EventCard;
import game.decks.EventDeck;

public class TestEventDeck extends EventDeck {
  public TestEventDeck(List<EventCard> cards) {
    super();
    addToTopOfDeck(cards);
  }
}

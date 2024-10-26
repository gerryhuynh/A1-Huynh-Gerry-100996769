package rigs;

import org.example.decks.EventDeck;
import org.example.cards.EventCard;

import java.util.List;

public class TestEventDeck extends EventDeck {
  public TestEventDeck(List<EventCard> cards) {
    super();
    addToTopOfDeck(cards);
  }
}

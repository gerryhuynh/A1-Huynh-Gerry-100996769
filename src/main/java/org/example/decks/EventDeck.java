package org.example.decks;

import java.util.Map;
import org.example.cards.EventCard;

public class EventDeck extends Deck<EventCard> {

	@Override
	protected void prepareDeck() {
		cards.clear();

		// Q cards
		addCards("Q", "Q2", 3);
		addCards("Q", "Q3", 4);
		addCards("Q", "Q4", 3);
		addCards("Q", "Q5", 2);

		// E cards
		addCards("E", "Plague", 1);
		addCards("E", "Queen's Favor", 2);
		addCards("E", "Prosperity", 2);
	}

	private void addCards(String type, String subtype, int count) {
		for (int i = 0; i < count; i++) {
			cards.add(new EventCard(type, subtype, () -> {}));
		}
	}

	public int getNumQCards() {
		return getNumCardsByType("Q");
	}

	public int getNumECards() {
		return getNumCardsByType("E");
	}

	public Map<String, Integer> getQCards() {
		return getCardsByType("Q");
	}

	public Map<String, Integer> getECards() {
		return getCardsByType("E");
	}
}

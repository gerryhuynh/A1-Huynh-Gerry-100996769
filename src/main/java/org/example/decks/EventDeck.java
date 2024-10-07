package org.example.decks;

import java.util.Map;
import org.example.cards.EventCard;
import org.example.enums.event.EventType;
import org.example.enums.event.QType;
import org.example.enums.event.EType;
public class EventDeck extends Deck<EventCard, EventType> {

	@Override
	protected void prepareDeck() {
		cards.clear();

		// Q cards
		addCards(QType.Q2, 3);
		addCards(QType.Q3, 4);
		addCards(QType.Q4, 3);
		addCards(QType.Q5, 2);

		// E cards
		addCards(EType.PLAGUE, 1);
		addCards(EType.QUEENS_FAVOR, 2);
		addCards(EType.PROSPERITY, 2);
	}

	@Override
	protected void addCards(EventType type, int count) {
		for (int i = 0; i < count; i++) {
			cards.add(new EventCard(type, () -> {}));
		}
	}

	public int getNumQCards() {
		return getNumCardsByType(QType.class);
	}

	public int getNumECards() {
		return getNumCardsByType(EType.class);
	}

	public Map<QType, Integer> getQCards() {
		return getCardsByType(QType.class);
	}

	public Map<EType, Integer> getECards() {
		return getCardsByType(EType.class);
	}
}

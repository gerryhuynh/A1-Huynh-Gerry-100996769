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

		for (QType qType : QType.values()) {
			addCards(qType);
		}

		for (EType eType : EType.values()) {
			addCards(eType);
		}
	}

	@Override
	protected void addCards(EventType type) {
		for (int i = 0; i < type.getDefaultCount(); i++) {
			cards.add(new EventCard(type));
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

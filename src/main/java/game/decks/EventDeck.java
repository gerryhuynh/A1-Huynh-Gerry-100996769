package game.decks;

import java.util.Map;

import game.cards.EventCard;
import game.enums.event.EType;
import game.enums.event.EventType;
import game.enums.event.QType;
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

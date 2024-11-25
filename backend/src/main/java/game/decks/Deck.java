package game.decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import game.cards.Card;
import game.enums.CardType;

import java.util.HashMap;

public abstract class Deck<T extends Card<S>, S extends CardType> {
	protected final List<T> cards;

	public Deck() {
		this.cards = new ArrayList<>();
		prepareDeck();
	}

	protected abstract void prepareDeck();

	protected abstract void addCards(S type);

	public T draw() {
		return cards.remove(0);
	}

	public void addToTopOfDeck(List<T> cards) {
		this.cards.addAll(0, cards);
	}

	public int size() {
		return cards.size();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<T> getCards() {
		return new ArrayList<>(cards);
	}


	protected <R extends S> int getNumCardsByType(Class<R> typeClass) {
		int count = 0;
		for (T card : cards) {
			if (typeClass.isInstance(card.getType())) {
				count++;
			}
		}
		return count;
	}

	protected <R extends S> Map<R, Integer> getCardsByType(Class<R> typeClass) {
		Map<R, Integer> cardMap = new HashMap<>();

		for (T card : cards) {
			S type = card.getType();
			if (typeClass.isInstance(type)) {
				R castedType = typeClass.cast(type);
				cardMap.put(castedType, cardMap.getOrDefault(castedType, 0) + 1);
			}
		}

		return cardMap;
	}
}

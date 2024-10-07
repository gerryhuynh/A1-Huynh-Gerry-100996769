package org.example.decks;

import org.example.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Deck<T extends Card> {
	protected final List<T> cards;

	public Deck() {
		this.cards = new ArrayList<>();
		prepareDeck();
	}

	protected abstract void prepareDeck();

	public int size() {
		return cards.size();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<T> getCards() {
		return new ArrayList<>(cards);
	}

	protected int getNumCardsByType(String type) {
		return (int) cards.stream().filter(card -> card.getType().equals(type)).count();
	}

	protected Map<String, Integer> getCardsByType(String type) {
		Map<String, Integer> cardMap = new HashMap<>();
		for (T card : cards) {
			if (card.getType().equals(type)) {
				cardMap.put(card.getSubtype(), cardMap.getOrDefault(card.getSubtype(), 0) + 1);
			}
		}
		return cardMap;
	}
}

package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDeck {
  private final List<EventCard> cards;

  public EventDeck() {
    this.cards = new ArrayList<>();

    prepareDeck();
  }

  private void prepareDeck() {
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

  public int size() {
    return cards.size();
  }

  public void shuffle() {
    return;
  }

  public List<EventCard> getCards() {
    return new ArrayList<>();
  }

  public int getNumQCards() {
    return (int) cards.stream().filter(card -> card.getType().equals("Q")).count();
  }

  public int getNumECards() {
    return (int) cards.stream().filter(card -> card.getType().equals("E")).count();
  }

  public Map<String, Integer> getQCards() {
    Map<String, Integer> qCards = new HashMap<>();
    for (EventCard card : cards) {
      if (card.getType().equals("Q")) {
        qCards.put(card.getSubtype(), qCards.getOrDefault(card.getSubtype(), 0) + 1);
      }
    }
    return qCards;
  }

  public Map<String, Integer> getECards() {
    Map<String, Integer> eCards = new HashMap<>();
    for (EventCard card : cards) {
      if (card.getType().equals("E")) {
        eCards.put(card.getSubtype(), eCards.getOrDefault(card.getSubtype(), 0) + 1);
      }
    }
    return eCards;
  }
}

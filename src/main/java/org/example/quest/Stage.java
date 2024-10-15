package org.example.quest;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.AdventureCard;
public class Stage {
  private List<AdventureCard> cards;

  public Stage() {
    this.cards = new ArrayList<>();
  }

  public void addCard(AdventureCard card) {
    cards.add(card);
  }

  public int getValue() {
    return cards.stream().mapToInt(AdventureCard::getValue).sum();
  }

  public List<AdventureCard> getCards() {
    return cards;
  }
}

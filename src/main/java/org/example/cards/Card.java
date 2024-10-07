package org.example.cards;

import org.example.enums.CardType;

public abstract class Card<T extends CardType> {
  private final T type;

  public Card(T type) {
    this.type = type;
  }

  public T getType() {
    return type;
  }
}

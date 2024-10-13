package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.AdventureCard;

public class Player {
  public static final int MAX_HAND_SIZE = 12;

  private final String name;
  private final List<AdventureCard> hand;
  private int shields;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.shields = 0;
    }

    public int computeNumCardsToTrim(int numCardsToAdd) {
      return 0;
    }

    public String getName() {
        return name;
    }

    public List<AdventureCard> getHand() {
        return hand;
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }
}

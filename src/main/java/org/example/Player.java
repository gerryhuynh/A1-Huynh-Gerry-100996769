package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import org.example.cards.AdventureCard;
import org.example.enums.adventure.WeaponType;

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

    public List<AdventureCard> addToHand(List<AdventureCard> cards, Display display) {
      return addToHand(cards, display, false);
    }

    public List<AdventureCard> addToHand(List<AdventureCard> cards, Display display, boolean setup) {
      List<AdventureCard> trimmedCards = new ArrayList<>();
      if (!setup) display.printCardsToAdd(cards);

      int numCardsToTrim = computeNumCardsToTrim(cards.size());
      if (numCardsToTrim > 0) {
        trimmedCards = trimHand(numCardsToTrim, display);
      }
      hand.addAll(cards);
      sortHand();

      if (!setup) display.printHand(hand);
      return trimmedCards;
    }

    public int computeNumCardsToTrim(int numCardsToAdd) {
      int numCardsToTrim = hand.size() + numCardsToAdd - MAX_HAND_SIZE;
      return numCardsToTrim > 0 ? numCardsToTrim : 0;
    }

    public List<AdventureCard> trimHand(int numCards, Display display) {
      List<AdventureCard> trimmedCards = new ArrayList<>();
      while (hand.size() + numCards > MAX_HAND_SIZE) {
        int removeCardIndex = display.promptForCardToDiscard(hand);
        trimmedCards.add(hand.remove(removeCardIndex));
      }
      return trimmedCards;
    }

    public void sortHand() {
      hand.sort(Comparator
        .comparing((AdventureCard c) -> c.getType().getClass().getSimpleName())
        .thenComparing(AdventureCard::getValue)
        .thenComparing((AdventureCard c1, AdventureCard c2) -> {
          if (c1.getType() instanceof WeaponType && c2.getType() instanceof WeaponType) {
            WeaponType w1 = (WeaponType) c1.getType();
            WeaponType w2 = (WeaponType) c2.getType();
            if (w1.getValue() == w2.getValue()) {
              return w2.name().compareTo(w1.name());
            }
          }
          return 0;
        }));
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

    @Override
    public String toString() {
        return String.format("%s {Shields: %d}", name, shields);
    }
}

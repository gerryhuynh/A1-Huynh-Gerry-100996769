package game;

import java.util.ArrayList;
import java.util.List;

import game.cards.AdventureCard;
import game.enums.adventure.WeaponType;

import java.util.Comparator;

public class Player {
  public static final int MAX_HAND_SIZE = 12;

  private final String name;
  private final List<AdventureCard> hand;
  private int shields;
  private int numCardsToTrim;
  private List<AdventureCard> trimmedCards;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.shields = 0;
        this.numCardsToTrim = 0;
        this.trimmedCards = new ArrayList<>();
    }

    public void overWriteHand(List<AdventureCard> cards, Display display) {
      hand.clear();
      addToHand(cards, display, true);
    }

    public List<AdventureCard> addToHand(List<AdventureCard> cards, Display display) {
      return addToHand(cards, display, false);
    }

    public List<AdventureCard> addToHand(List<AdventureCard> cards, Display display, boolean setup) {
      List<AdventureCard> trimmedCards = new ArrayList<>();
      if (!setup) display.printCardsToAdd(cards);

      int numCardsToTrim = computeNumCardsToTrim(cards.size());
      if (numCardsToTrim > 0) {
        while (cards.size() > Player.MAX_HAND_SIZE) {
          trimmedCards.add(cards.remove(0));
        }
        trimmedCards.addAll(trimHand(numCardsToTrim, display));
      }
      hand.addAll(cards);
      sortHand();

      if (!setup) display.printHand(hand);
      return trimmedCards;
    }

    public int computeNumCardsToTrim(int numCardsToAdd) {
      int numCardsToTrim = hand.size() + numCardsToAdd - MAX_HAND_SIZE;
      if (numCardsToTrim > hand.size()) numCardsToTrim = hand.size();
      return numCardsToTrim > 0 ? numCardsToTrim : 0;
    }

    public List<AdventureCard> trimHand(int numCards, Display display) {
      List<AdventureCard> trimmedCards = new ArrayList<>();
      for (int i = 0; i < numCards; i++) {
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

    public void setNumCardsToTrim(int numCardsToTrim) {
      this.numCardsToTrim = numCardsToTrim;
    }

    public int getNumCardsToTrim() {
      return numCardsToTrim;
    }

    public List<AdventureCard> getTrimmedCards() {
      return trimmedCards;
    }

    @Override
    public String toString() {
        return String.format("%s {Shields: %d}", name, shields);
    }
}

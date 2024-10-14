package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public List<AdventureCard> addToHand(List<AdventureCard> cards, Display display) {
      List<AdventureCard> trimmedCards = new ArrayList<>();
      display.printCardsToAdd(cards);

      int numCardsToTrim = computeNumCardsToTrim(cards.size());
      if (numCardsToTrim > 0) {
        trimmedCards = trimHand(numCardsToTrim, display);
      }
      hand.addAll(cards);

      display.printHand(hand);
      return trimmedCards;
    }

    public int computeNumCardsToTrim(int numCardsToAdd) {
      int numCardsToTrim = hand.size() + numCardsToAdd - MAX_HAND_SIZE;
      return numCardsToTrim > 0 ? numCardsToTrim : 0;
    }

    public List<AdventureCard> trimHand(int numCards, Display display) {
      List<AdventureCard> trimmedCards = new ArrayList<>();
      while (hand.size() + numCards > MAX_HAND_SIZE) {
        int removeCardIndex = display.promptForCardToDiscard(new Scanner(System.in), hand);
        trimmedCards.add(hand.remove(removeCardIndex));
        display.printHand(hand);
      }
      return trimmedCards;
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

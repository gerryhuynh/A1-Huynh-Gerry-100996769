package org.example.quest;

import java.util.ArrayList;
import java.util.List;

import org.example.Display;
import org.example.Player;
import org.example.cards.AdventureCard;
import org.example.decks.AdventureDeck;

public class Participant {
  private Player player;
  private List<AdventureCard> attackCards;

  public Participant(Player player) {
    this.player = player;
    this.attackCards = new ArrayList<>();
  }

  public Player getPlayer() {
    return player;
  }

  public void drawCard(AdventureDeck adventureDeck, Display display) {
    display.print(String.format("%s draws an adventure card...", player.getName()));
    player.addToHand(adventureDeck.draw(1), display);
  }

  public void addCardToAttack(AdventureCard card, Display display) {
    attackCards.add(card);
    player.getHand().remove(card);
    display.printCardAddedToAttack(attackCards);
  }

  public List<AdventureCard> getAttackCards() {
    return attackCards;
  }

  @Override
  public String toString() {
    return String.format("%s", player.getName());
  }
}

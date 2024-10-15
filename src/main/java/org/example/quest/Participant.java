package org.example.quest;

import org.example.Player;
import org.example.cards.AdventureCard;

import java.util.ArrayList;
import java.util.List;

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

  public List<AdventureCard> getAttackCards() {
    return attackCards;
  }

  public void addAttackCard(AdventureCard card) {
    attackCards.add(card);
  }
}

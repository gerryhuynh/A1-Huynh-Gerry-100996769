package game.quest;

import java.util.ArrayList;
import java.util.List;

import game.Display;
import game.Player;
import game.cards.AdventureCard;
import game.decks.AdventureDeck;

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
    display.promptContinueToAttack();
  }

  public void addCardToAttack(AdventureCard card, Display display) {
    attackCards.add(card);
    player.getHand().remove(card);
    display.printCardAddedToAttack(attackCards);
  }

  public List<AdventureCard> getAttackCards() {
    return attackCards;
  }

  public int getAttackValue() {
    return attackCards.stream().mapToInt(AdventureCard::getValue).sum();
  }

  public void clearAttackCards() {
    attackCards.clear();
  }

  @Override
  public String toString() {
    return String.format("%s", player.getName());
  }
}

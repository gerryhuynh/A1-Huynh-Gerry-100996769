package org.example.quest;

import java.util.ArrayList;
import java.util.List;
import org.example.cards.AdventureCard;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;

public class Stage {
  private List<AdventureCard> cards;
  private int stageNumber;

  public Stage(int stageNumber) {
    this.cards = new ArrayList<>();
    this.stageNumber = stageNumber;
  }

  public boolean hasFoe() {
    return cards.stream()
      .anyMatch(card -> card.getType() instanceof FoeType);
  }

  public boolean hasWeapon(AdventureCard weaponCard) {
    if (!(weaponCard.getType() instanceof WeaponType)) {
      return false;
    }
    return cards.stream()
      .filter(card -> card.getType() instanceof WeaponType)
      .anyMatch(card -> card.getType() == weaponCard.getType());
  }

  public void addCard(AdventureCard card) {
    cards.add(card);
  }

  public int getValue() {
    return cards.stream().mapToInt(AdventureCard::getValue).sum();
  }

  public int getStageNumber() {
    return stageNumber;
  }

  public List<AdventureCard> getCards() {
    return cards;
  }
}

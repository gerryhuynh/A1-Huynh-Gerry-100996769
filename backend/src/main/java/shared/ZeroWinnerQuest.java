package shared;

import java.util.Arrays;
import java.util.List;

import game.cards.AdventureCard;
import game.cards.EventCard;
import game.enums.adventure.AdventureType;
import game.enums.adventure.FoeType;
import game.enums.adventure.WeaponType;
import game.enums.event.QType;


public class ZeroWinnerQuest {
  private static final AdventureType[] P1_HAND = {
    FoeType.F5,
    FoeType.F10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10,
    WeaponType.S10
  };

  public static List<AdventureCard> getAdventureCards() {
    return Setup.createHand(P1_HAND);
  }

  public static List<EventCard> getEventCards() {
    return Arrays.asList(
      new EventCard(QType.Q2)
    );
  }
}

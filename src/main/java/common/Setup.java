package common;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.AdventureCard;
import org.example.enums.adventure.AdventureType;

public class Setup {
  public static List<AdventureCard> createDeck(AdventureType... types) {
    List<AdventureCard> deck = new ArrayList<>();
    for (AdventureType type : types) {
      deck.add(new AdventureCard(type));
    }
    return deck;
  }

  public static List<AdventureCard> createHand(AdventureType[] types) {
    return createDeck(types);
  }
}

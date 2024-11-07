package game.decks;

import java.util.Map;

import game.cards.AdventureCard;
import game.enums.adventure.AdventureType;
import game.enums.adventure.FoeType;
import game.enums.adventure.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class AdventureDeck extends Deck<AdventureCard, AdventureType> {

    @Override
    protected void prepareDeck() {
        cards.clear();

        for (FoeType foeType : FoeType.values()) {
            addCards(foeType);
        }

        for (WeaponType weaponType : WeaponType.values()) {
            addCards(weaponType);
        }
    }

    public List<AdventureCard> draw(int numCards) {
        numCards = Math.min(numCards, cards.size());
        List<AdventureCard> drawnCards = new ArrayList<>(cards.subList(0, numCards));
        cards.subList(0, numCards).clear();
        return drawnCards;
    }

    @Override
    protected void addCards(AdventureType type) {
        for (int i = 0; i < type.getDefaultCount(); i++) {
            cards.add(new AdventureCard(type));
        }
    }

    public int getNumFoeCards() {
        return getNumCardsByType(FoeType.class);
    }

    public int getNumWeaponCards() {
        return getNumCardsByType(WeaponType.class);
    }

    public Map<FoeType, Integer> getFoeCards() {
        return getCardsByType(FoeType.class);
    }

    public Map<WeaponType, Integer> getWeaponCards() {
        return getCardsByType(WeaponType.class);
    }

    public String toString() {
        return cards.toString();
    }
}

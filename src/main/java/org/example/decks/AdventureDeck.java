package org.example.decks;

import java.util.Map;
import org.example.cards.AdventureCard;
import org.example.enums.adventure.AdventureType;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;

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
}

package org.example.decks;

import java.util.Map;
import org.example.cards.AdventureCard;
import org.example.enums.adventure.AdventureType;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;

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

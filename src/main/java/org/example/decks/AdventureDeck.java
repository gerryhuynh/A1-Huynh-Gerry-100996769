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

        // Foe cards
        addCards(FoeType.F5, 8);
        addCards(FoeType.F10, 7);
        addCards(FoeType.F15, 8);
        addCards(FoeType.F20, 7);
        addCards(FoeType.F25, 7);
        addCards(FoeType.F30, 4);
        addCards(FoeType.F35, 4);
        addCards(FoeType.F40, 2);
        addCards(FoeType.F50, 2);
        addCards(FoeType.F70, 1);

        // Weapon cards
        addCards(WeaponType.D5, 6);
        addCards(WeaponType.H10, 12);
        addCards(WeaponType.S10, 16);
        addCards(WeaponType.B15, 8);
        addCards(WeaponType.L20, 6);
        addCards(WeaponType.E30, 2);
    }

    @Override
    protected void addCards(AdventureType type, int count) {
        for (int i = 0; i < count; i++) {
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

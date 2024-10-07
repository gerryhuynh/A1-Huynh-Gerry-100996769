package org.example.decks;

import java.util.Map;
import org.example.cards.AdventureCard;

public class AdventureDeck extends Deck<AdventureCard> {

    @Override
    protected void prepareDeck() {
        cards.clear();

        // Foe cards
        addCards("F", "F5", 5, 8);
        addCards("F", "F10", 10, 7);
        addCards("F", "F15", 15, 8);
        addCards("F", "F20", 20, 7);
        addCards("F", "F25", 25, 7);
        addCards("F", "F30", 30, 4);
        addCards("F", "F35", 35, 4);
        addCards("F", "F40", 40, 2);
        addCards("F", "F50", 50, 2);
        addCards("F", "F70", 70, 1);

        // Weapon cards
        addCards("W", "D5", 5, 6);
        addCards("W", "H10", 10, 12);
        addCards("W", "S10", 10, 16);
        addCards("W", "B15", 15, 8);
        addCards("W", "L20", 20, 6);
        addCards("W", "E30", 30, 2);
    }

    private void addCards(String type, String subtype, int value, int count) {
        for (int i = 0; i < count; i++) {
            cards.add(new AdventureCard(type, subtype, value));
        }
    }

    public int getNumFoeCards() {
        return getNumCardsByType("F");
    }

    public int getNumWeaponCards() {
        return getNumCardsByType("W");
    }

    public Map<String, Integer> getFoeCards() {
        return getCardsByType("F");
    }

    public Map<String, Integer> getWeaponCards() {
        return getCardsByType("W");
    }
}

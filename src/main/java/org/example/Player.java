package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.AdventureCard;

public class Player {
    public String getName() {
        return "";
    }

    public List<AdventureCard> getHand() {
        return new ArrayList<>();
    }

    public int getShields() {
        return -1;
    }
}

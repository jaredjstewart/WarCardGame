package com.jaredjstewart;

import java.util.List;

public class Dealer {
    public static void deal(Deck deck, List<Player> players) {
        int nextPlayerToBeDealt = 0;

        while (!deck.isEmpty()) {
            players.get(nextPlayerToBeDealt).pickUpCardDealt(deck.deal());
            nextPlayerToBeDealt = (nextPlayerToBeDealt + 1) % players.size();
        }
    }
}

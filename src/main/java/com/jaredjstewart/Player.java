package com.jaredjstewart;

import java.util.ArrayList;
import java.util.List;

public class Player {
    Pile hand;
    Pile wonPile;

    int playerNumber;

    public Card playCard() {
        if (!hasCards()) {
            throw new RuntimeException("This player has no cards remaining to play.");
        } else if (hand.isEmpty()) {
            hand = wonPile;
            hand.shuffle();
            wonPile = new Pile();
        }

        return hand.drawCard();
    }

    public void pickUp(Pile pile) {
        while (!pile.isEmpty()) {
            wonPile.add(pile.drawCard());
        }
    }

    public boolean hasCards() {
        return !hand.isEmpty() || !wonPile.isEmpty();
    }


    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        hand = new Pile();
        wonPile = new Pile();
    }

    public int cardsRemaining() {
        return hand.size() + wonPile.size();
    }

    public static List<Player> createPlayers(int numberOfPlayers) {
        List<Player> players = new ArrayList<Player>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(i));
        }
        return players;
    }
}

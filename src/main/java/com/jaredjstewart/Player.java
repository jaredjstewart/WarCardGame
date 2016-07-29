package com.jaredjstewart;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private Pile hand;
    private Pile wonPile;

    public final int playerNumber;

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

    public void pickUpCardsWon(Pile pile) {
        while (!pile.isEmpty()) {
            wonPile.add(pile.drawCard());
        }
    }

    public void pickUpCardDealt(Card card) {
        hand.add(card);
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
        return IntStream.range(0, numberOfPlayers)
                .mapToObj(Player::new)
                .collect(Collectors.toList());
    }
}

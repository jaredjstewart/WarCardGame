package com.jaredjstewart;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class DeckTest {
    @Test
    public void weCanCreateADeckAndItHasTheRightNumberOfCards() {
        Deck deck = new Deck(4, 13);
        for (int i = 0; i < 4 * 13; i++) {
            assertNotNull(deck.deal());
        }
        assertNull(deck.deal());
    }

    @Test
    public void shuffleActuallyShuffles() {
        Deck deck = new Deck(1000, 1000);
        Card firstCard = deck.cards.peek();

        deck.shuffle();
        Card newFirstCard = deck.cards.peek();

        //This assertion will fail with probability 10^-9, but that's probably sufficiently inconsequential.
        assertNotEquals( newFirstCard, firstCard);
    }
}

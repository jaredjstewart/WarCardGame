package com.jaredjstewart;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class DealerTest {

    @Test
    public void dealerDealsProperly() {
        Deck deck = new Deck(4, 13);
        List<Player> players = Arrays.asList(new Player(0), new Player(1));

        Dealer.deal(deck, players);

        assertEquals(players.get(0).cardsRemaining(), 26);
        assertEquals(players.get(1).cardsRemaining(), 26);
    }
}

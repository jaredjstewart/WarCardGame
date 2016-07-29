package com.jaredjstewart;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    @Test
    public void createPlayersCreatesTheCorrectNumberOfPlayers() {
        List<Player> players = Player.createPlayers(3);

        assertEquals( 3, players.size());
        for (int i = 0; i < 3; i++) {
            assertEquals( i, players.get(i).playerNumber);
        }
    }

    @Test
    public void playCard() throws Exception {
        Player player = new Player(0);
        List<Card> cardsDealt = Arrays.asList(new Card(1, 1), new Card(2, 2));
        player.pickUpCardsWon(new Pile(cardsDealt));
        Card cardPlayed = player.playCard();

        assertTrue(cardsDealt.contains(cardPlayed));
        assertEquals( 1, player.cardsRemaining());
    }
}
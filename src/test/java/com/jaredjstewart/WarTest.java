package com.jaredjstewart;

import org.junit.Test;
import spock.lang.Specification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

class WarTest extends Specification {
    @Test
    public void processOneTurnWorksAsExpectedWithNoWar() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        List<Player> players = Arrays.asList(player0, player1);
        player0.hand = new Pile(Arrays.asList(new Card(1, 1), new Card(1, 4)));
        player1.hand = new Pile(Arrays.asList(new Card(2, 3), new Card(2, 2)));

        War war = new War();
        war.remainingPlayers = players;

        war.playOneTurn();

        assertEquals(player0.wonPile.size(), 0);
        assertEquals(player1.wonPile.size(), 2);

        assertEquals(player0.hand.size(), 1);
        assertEquals(player1.hand.size(), 1);
    }

    @Test
    public void processOneTurnHandlesAWarBetweenTwoPlayerx() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        List<Player> players = Arrays.asList(player0, player1);
        player0.hand = new Pile(Arrays.asList(new Card(1, 1), new Card(1, 2), new Card(1, 3), new Card(1, 5), new Card(1, 6)));
        player1.hand = new Pile(Arrays.asList(new Card(2, 1), new Card(2, 2), new Card(2, 3), new Card(2, 4), new Card(2, 6)));

        War war = new War();
        war.remainingPlayers = players;

        war.playOneTurn();

        assertEquals(player0.wonPile.size(), 8);
        assertEquals(player1.wonPile.size(), 0);
        assertEquals(player0.hand.size(), 1);
        assertEquals(player1.hand.size(), 1);

    }

    @Test
    public void processOneTurnProperlyHandlesAWarBetweenMoreThanTwoPlayers() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        List<Player> players = Arrays.asList(player0, player1, player2);
        player0.hand = new Pile(Arrays.asList(new Card(1, 1), new Card(1, 2), new Card(1, 3), new Card(1, 5), new Card(1, 6)));
        player1.hand = new Pile(Arrays.asList(new Card(2, 1), new Card(2, 2), new Card(2, 3), new Card(2, 4), new Card(2, 6)));
        player2.hand = new Pile(Arrays.asList(new Card(3, 1), new Card(3, 2)));

        War war = new War();
        war.remainingPlayers = players;


        war.playOneTurn();

        assertEquals(player0.wonPile.size(), 10);
        assertEquals(player1.wonPile.size(), 0);
        assertEquals(player0.hand.size(), 1);
        assertEquals(player1.hand.size(), 1);
        assertEquals(player2.wonPile.size(), 0);
        assertEquals(player2.wonPile.size(), 0);
        assertEquals(war.remainingPlayers.size(), 2);
        assertEquals(war.remainingPlayers.get(0), player0);
        assertEquals(war.remainingPlayers.get(1), player1);
    }

    @Test
    public void playCardsWorksInASimpleCase() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        List<Player> players = Arrays.asList(player0, player1);
        player0.hand = new Pile(Arrays.asList(new Card(1, 1), new Card(1, 4)));
        player1.hand = new Pile(Arrays.asList(new Card(2, 3), new Card(2, 2)));

        War war = new War();
        war.remainingPlayers = players;


        HashMap<Player, Card> playedCards = war.playCards();

        assertEquals(playedCards.size(), 2);
        assertEquals(playedCards.get(player0).rank, 1);
        assertEquals(playedCards.get(player0).suit, 1);

        assertEquals(playedCards.get(player1).suit, 2);
        assertEquals(playedCards.get(player1).rank, 3);

        assertEquals(player0.cardsRemaining(), 1);
        assertEquals(player1.cardsRemaining(), 1);
    }

}

package com.jaredjstewart;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WarTest {
    @Test
    public void processOneTurnWorksAsExpectedWithNoWar() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        List<Player> players = Arrays.asList(player0, player1);
        pickUpCardsDealt(player0, Arrays.asList(new Card(1, 1), new Card(1, 4)));
        pickUpCardsDealt(player1, Arrays.asList(new Card(2, 3), new Card(2, 2)));

        War war = new War();

        war.playOneTurn(players);

        assertEquals( 1, player0.cardsRemaining());
        assertEquals( 3, player1.cardsRemaining());

    }

    @Test
    public void processOneTurnHandlesAWarBetweenTwoPlayers() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        List<Player> players = Arrays.asList(player0, player1);
        pickUpCardsDealt(player0, Arrays.asList(new Card(1, 1), new Card(1, 2), new Card(1, 3), new Card(1, 5), new Card(1, 6)));
        pickUpCardsDealt(player1, Arrays.asList(new Card(2, 1), new Card(2, 2), new Card(2, 3), new Card(2, 4), new Card(2, 6)));

        War war = new War();

        war.playOneTurn(players);

        assertEquals( 9, player0.cardsRemaining());
        assertEquals( 1, player1.cardsRemaining());

    }

    @Test
    public void processOneTurnProperlyHandlesAWarBetweenMoreThanTwoPlayers() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        List<Player> players = Arrays.asList(player0, player1, player2);
        pickUpCardsDealt(player0, Arrays.asList(new Card(1, 1), new Card(1, 2), new Card(1, 3), new Card(1, 5), new Card(1, 6)));
        pickUpCardsDealt(player1, Arrays.asList(new Card(2, 1), new Card(2, 2), new Card(2, 3), new Card(2, 4), new Card(2, 6)));
        pickUpCardsDealt(player2, Arrays.asList(new Card(3, 1), new Card(3, 2)));

        War war = new War();

        List<Player> remainingPlayers = war.playOneTurn(players);

        assertEquals( 11, player0.cardsRemaining());
        assertEquals( 1, player1.cardsRemaining());
        assertEquals( 0, player2.cardsRemaining());

        assertEquals( 2, remainingPlayers.size());
        assertEquals( player0, remainingPlayers.get(0));
        assertEquals( player1, remainingPlayers.get(1));
    }

    @Test
    public void playCardsWorksInASimpleCase() {
        Player player0 = new Player(0);
        Player player1 = new Player(1);
        List<Player> players = Arrays.asList(player0, player1);
        pickUpCardsDealt(player0, Arrays.asList(new Card(1, 1), new Card(1, 4)));
        pickUpCardsDealt(player1, Arrays.asList(new Card(2, 3), new Card(2, 2)));

        War war = new War();


        HashMap<Player, Card> playedCards = war.playCards(players);

        assertEquals( 2, playedCards.size());
        assertEquals( 1, playedCards.get(player0).rank);
        assertEquals( 1, playedCards.get(player0).suit);

        assertEquals( 2, playedCards.get(player1).suit);
        assertEquals( 3, playedCards.get(player1).rank);

        assertEquals( 1, player0.cardsRemaining());
        assertEquals( 1, player1.cardsRemaining());
    }
    
    private void pickUpCardsDealt(Player player, Collection<Card> cards) {
        for (Card card: cards) {
            player.pickUpCardDealt(card);
        }
    }
}

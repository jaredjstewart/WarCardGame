package com.jaredjstewart;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void createPlayersCreatesTheCorrectNumberOfPlayers() {
        War war = new War();

        List<Player> players = Player.createPlayers(3);

        assertEquals(players.size(), 3);
        for (int i = 0; i < 4; i++) {
            assertEquals(players.get(i).playerNumber, i);
        }
    }

    @Test
    public void playCard() throws Exception {
        Player player = new Player(0);
        Card cardToPlay = new Card(1,1);
        player.hand = new Pile(Arrays.asList(cardToPlay, new Card(2,2)));

        assertEquals(player.playCard(), cardToPlay);
    }
}
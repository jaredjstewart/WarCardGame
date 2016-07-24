package com.jaredjstewart;

import java.util.*;
import java.util.stream.Collectors;

public class War {
    List<Player> remainingPlayers;

    public static void main(String[] args) {
       GameConfiguration gameConfiguration = GameConfiguration.initializeFromUserInput();
        new War().play(gameConfiguration);
    }

    public void play (GameConfiguration gameConfiguration){
        play(gameConfiguration.numberOfSuits, gameConfiguration.numberOfRanks, gameConfiguration.numberOfPlayers);
    }

    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Deck deck = new Deck(numberOfSuits, numberOfRanks);
        remainingPlayers = Player.createPlayers(numberOfPlayers);
        Dealer.deal(deck, remainingPlayers);

        while (remainingPlayers.size() > 1) {
            playOneTurn();
        }

        printResult();
    }

    private void printResult() {
        String result = remainingPlayers.isEmpty() ? "Game over.  No one wins." : ("Congratulations, player " + remainingPlayers.get(0).playerNumber + ".  You win!");
        System.out.println(result);
    }

    protected void playOneTurn() {
        System.out.println("--------");
        HashMap<Player, Card> cardsPlayed = playCards();
        List<Player> winningPlayers = findPlayersWithHighestRankCard(cardsPlayed);

        Pile playedPile = new Pile(cardsPlayed.values());

        boolean warHasBeenInitiated = false;
        while (warIsInProgress(winningPlayers)) {
            if (!warHasBeenInitiated) {
                System.out.println("WAR!!");
                warHasBeenInitiated = true;
            }

            cardsPlayed = playCards();
            winningPlayers = findPlayersWithHighestRankCard(cardsPlayed);
            playedPile.add(cardsPlayed.values());
        }

        if (winningPlayers.size() == 1) {

            Player winningPlayer = winningPlayers.get(0);
            winningPlayer.pickUp(playedPile);

            if (warHasBeenInitiated) {
                System.out.println("War won by player " + winningPlayer.playerNumber +".");
            } else {
                System.out.println("Player " + winningPlayer.playerNumber + " wins this turn.");
            }

        }
    }

    private boolean warIsInProgress(List<Player> winningPlayers) {
        return winningPlayers.size() > 1 && remainingPlayers.size() > 1;
    }

    private List<Player> findPlayersWithHighestRankCard(HashMap<Player, Card> cardsPlayed) {
        int maxRank = findHighestRankCard(cardsPlayed);

        return cardsPlayed.entrySet().stream()
                .filter(entry -> entry.getValue().rank == maxRank)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private int findHighestRankCard(HashMap<Player, Card> cardsPlayed) {
        return cardsPlayed.values().stream()
                .map(card -> card.rank)
                .max(Integer::compare)
                .get();
    }

    protected HashMap<Player, Card> playCards() {
        removePlayersWhoRanOutOfCards();

        HashMap<Player, Card> cardsPlayed = new HashMap<>(remainingPlayers.size());
        for (Player player : remainingPlayers) {
            Card card = player.playCard();
            System.out.println("Player " + player.playerNumber + " played " + card.rank + " of suit " + card.suit
                    + " and has " + player.cardsRemaining() + " cards remaining.");
            cardsPlayed.put(player, card);
        }
        return cardsPlayed;
    }

    private void removePlayersWhoRanOutOfCards() {
        remainingPlayers.removeAll(remainingPlayers.stream().filter(player -> !player.hasCards()).collect(Collectors.toList()));
    }


}

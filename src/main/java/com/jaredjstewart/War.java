package com.jaredjstewart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class War {
    public static void main(String[] args) {
        GameConfiguration gameConfiguration = GameConfiguration.initializeFromUserInput();
        new War().play(gameConfiguration);
    }

    public void play(GameConfiguration gameConfiguration) {
        play(gameConfiguration.numberOfSuits, gameConfiguration.numberOfRanks, gameConfiguration.numberOfPlayers);
    }

    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Deck deck = new Deck(numberOfSuits, numberOfRanks);
        List<Player> remainingPlayers = Player.createPlayers(numberOfPlayers);
        Dealer.deal(deck, remainingPlayers);

        while (remainingPlayers.size() > 1) {
            remainingPlayers = playOneTurn(remainingPlayers);
        }

        printFinalGameResult(remainingPlayers);
    }

    private void printFinalGameResult(List<Player> remainingPlayers) {
        String result = remainingPlayers.isEmpty() ? "Game over.  No one wins." : ("Congratulations, player " + remainingPlayers.get(0).playerNumber + ".  You win!");
        System.out.println(result);
    }

    public List<Player> playOneTurn(List<Player> remainingPlayers) {
        System.out.println("--------");
        remainingPlayers = removePlayersWhoRanOutOfCards(remainingPlayers);
        HashMap<Player, Card> cardsPlayed = playCards(remainingPlayers);
        List<Player> winningPlayers = findPlayersWithHighestRankCard(cardsPlayed);

        Pile playedPile = new Pile(cardsPlayed.values());

        boolean warHasBeenInitiated = false;
        while (warIsInProgress(winningPlayers, remainingPlayers)) {
            if (!warHasBeenInitiated) {
                System.out.println("WAR!!");
                warHasBeenInitiated = true;
            }

            remainingPlayers = removePlayersWhoRanOutOfCards(remainingPlayers);
            cardsPlayed = playCards(remainingPlayers);
            winningPlayers = findPlayersWithHighestRankCard(cardsPlayed);
            playedPile.add(cardsPlayed.values());
        }

        //If every player runs out of cards and there is still a tie, the game would end and this turn would have no winner.
        boolean turnHadWinner = winningPlayers.size() == 1;
        if (turnHadWinner) {

            Player winningPlayer = winningPlayers.get(0);
            winningPlayer.pickUpCardsWon(playedPile);

            if (warHasBeenInitiated) {
                System.out.println("War won by player " + winningPlayer.playerNumber + ".");
            } else {
                System.out.println("Player " + winningPlayer.playerNumber + " wins this turn.");
            }

        }

        return remainingPlayers;
    }

    private boolean warIsInProgress(List<Player> winningPlayers, List<Player> remainingPlayers) {
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

    protected HashMap<Player, Card> playCards(List<Player> remainingPlayers) {
        removePlayersWhoRanOutOfCards(remainingPlayers);

        HashMap<Player, Card> cardsPlayed = new HashMap<>(remainingPlayers.size());
        for (Player player : remainingPlayers) {
            Card card = player.playCard();
            System.out.println("Player " + player.playerNumber + " played " + card.rank + " of suit " + card.suit
                    + " and has " + player.cardsRemaining() + " cards remaining.");
            cardsPlayed.put(player, card);
        }
        return cardsPlayed;
    }

    private List<Player> removePlayersWhoRanOutOfCards(List<Player> remainingPlayers) {
        return remainingPlayers.stream()
                .filter(Player::hasCards)
                .collect(Collectors.toList());
    }


}

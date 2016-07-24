package com.jaredjstewart

import spock.lang.Specification

class WarTest extends Specification {
    def "processOneTurn works as expected with no war"() {
        given:
        Player player0 = new Player(0)
        Player player1 = new Player(1)
        List<Player> players = [player0, player1]
        player0.hand = new Pile([new Card(1, 1), new Card(1, 4)])
        player1.hand = new Pile([new Card(2, 3), new Card(2, 2)])

        War war = new War(remainingPlayers: players)

        when:
        war.playOneTurn()

        then:
        player0.wonPile.size() == 0
        player1.wonPile.size() == 2

        player0.hand.size() == 1
        player1.hand.size() == 1
    }

    def "processOneTurn handle a war between two players"() {
        given:
        Player player0 = new Player(0)
        Player player1 = new Player(1)
        List<Player> players = [player0, player1]
        player0.hand = new Pile([new Card(1, 1), new Card(1, 2), new Card(1, 3), new Card(1, 5), new Card(1, 6)])
        player1.hand = new Pile([new Card(2, 1), new Card(2, 2), new Card(2, 3), new Card(2, 4), new Card(2, 6)])

        War war = new War(remainingPlayers: players)

        when:
        war.playOneTurn()

        then:
        player0.wonPile.size() == 8
        player1.wonPile.size() == 0
        player0.hand.size() == 1
        player1.hand.size() == 1

    }

        def "processOneTurn properly handles a war between more than two players"() {
            given:
            Player player0 = new Player(0)
            Player player1 = new Player(1)
            Player player2 = new Player(2)
            List<Player> players = [player0, player1, player2]
            player0.hand = new Pile([new Card(1, 1), new Card(1, 2), new Card (1, 3), new Card (1,5), new Card(1,6)])
            player1.hand = new Pile([new Card(2, 1), new Card(2, 2), new Card (2,3), new Card (2,4), new Card (2,6)])
            player2.hand = new Pile([new Card(3, 1), new Card(3, 2)])

            War war = new War(remainingPlayers: players)

            when:
            war.playOneTurn()

            then:
            player0.wonPile.size() == 10
            player1.wonPile.size() == 0
            player0.hand.size() == 1
            player1.hand.size() == 1
            player2.wonPile.size() == 0
            player2.wonPile.size() == 0
            war.remainingPlayers == [player1, player2]
        }


        def "PlayCards works as expected in a simple case" () {
        given:
        Player player0 = new Player(0)
        Player player1 = new Player(1)
        List<Player> players = [player0, player1 ]
        player0.hand = new Pile([new Card (1,1), new Card(1,4)])
        player1.hand = new Pile([new Card(2,3), new Card(2,2)])

        War war = new War(remainingPlayers: players)

        when:
        HashMap<Player, Card> playedCards = war.playCards()

        then:
        playedCards.size() == 2
        playedCards.get(player0).rank == 1
        playedCards.get(player0).suit == 1

        playedCards.get(player1).suit == 2
        playedCards.get(player1).rank == 3

        player0.cardsRemaining() == 1
        player1.cardsRemaining() == 1
    }

    def "createPlayers creates the correct number of players" () {
        given:
        War war = new War()

        when:
        List<Player> players = war.createPlayers(3)
       players.size() == 3
        players.collect {Player player -> player.playerNumber} == [0,1,2]
    }
}

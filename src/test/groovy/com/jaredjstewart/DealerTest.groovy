package com.jaredjstewart

import spock.lang.Specification

class DealerTest extends Specification {
    def "Dealer deals half the cards to each player with a standard deck of cards"() {
        given:
        List<Player> players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));

        and:
        Deck deck = new Deck (4, 13);

        when:
        Dealer.deal(deck, players);

        then:
        players.get(0).cardsRemaining() == 26
        players.get(1).cardsRemaining() == 26
    }
}

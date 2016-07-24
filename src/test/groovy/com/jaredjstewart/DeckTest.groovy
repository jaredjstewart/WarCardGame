package com.jaredjstewart

class DeckTest extends spock.lang.Specification {
    def "We can create a new deck and deal the right number of  cards" () {
        given:
        Deck deck = new Deck(4, 13)
        when:
        for (int i = 0; i < 4 * 13; i++ ){
            assert deck.deal() != null
        }
        then:
        deck.deal() == null
    }

    def "Shuffle actually shuffles"() {
        given:
        Deck deck = new Deck()
        Card firstCard = deck.cards.peek()

        when:
        deck.shuffle()
        Card newFirstCard = deck.cards.peek()

        then:
        !firstCard.equals(newFirstCard)
    }
}

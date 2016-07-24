package com.jaredjstewart;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Pile {
    private LinkedList<Card> cards ;

    public Pile () {
        cards = new LinkedList<>();
    }

    public Pile(Collection<Card> cards) {
        this();
        add(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(Collection<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }

    public Card drawCard() {
        return cards.remove();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}

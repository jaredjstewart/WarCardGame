package com.jaredjstewart;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    protected final LinkedList<Card> cards = new LinkedList<Card>();

    public Deck() {
        this(4, 13);
    }

    public Deck(int numberOfSuits, int numberOfRanks) {
        validateParameters(numberOfSuits, numberOfRanks);
        populateCards(numberOfSuits, numberOfRanks);
        shuffle();
    }

    private void populateCards(int numberOfSuits, int numberOfRanks) {
        for (int suit = 0; suit < numberOfSuits; suit++) {
            for (int rank = 0; rank < numberOfRanks; rank++) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    private void validateParameters(int numberOfSuits, int numberOfRanks) {
        if (numberOfSuits < 0) {
            throw new IllegalArgumentException("The number of suits in a deck of cards cannot be negative");
        } else if (numberOfRanks < 1) {
            throw new IllegalArgumentException("The number of ranks for a deck of cards must be non-negative");
        }
    }

    public Card deal() {
        return isEmpty() ? null : cards.remove();
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
package com.jaredjstewart;

public class Card {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 13;

    public final int suit;
    public final int rank;

    public Card(int suit, int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("Illegal card rank.");
        }

        this.suit = suit;
        this.rank = value;
    }
}

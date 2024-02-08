package com.interview.poker;

/**
 * A card in a standard 52 card deck
 */
public class Card {
    public enum CardSuit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }

    /**
     * the rank of the card is represented by a character
     */
    public enum CardValue {
        TWO('a'),
        THREE('b'),
        FOUR('c'),
        FIVE('d'),
        SIX('e'),
        SEVEN('f'),
        EIGHT('g'),
        NINE('h'),
        TEN('i'),
        JACK('j'),
        QUEEN('k'),
        KING('l'),
        ACE('m'),;

        public final char rank;

        CardValue(final char rank) {
            this.rank = rank;
        }

    }

    public final CardSuit suit;
    public final CardValue value;

    public Card(final CardSuit suit, final CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public String toString() {
        return value + " of " + suit;
    }

    // Factory methods for creating cards
    public static Card twoOf(final CardSuit suit) {
        return new Card(suit, CardValue.TWO);
    }

    public static Card threeOf(final CardSuit suit) {
        return new Card(suit, CardValue.THREE);
    }

    public static Card fourOf(final CardSuit suit) {
        return new Card(suit, CardValue.FOUR);
    }

    public static Card fiveOf(final CardSuit suit) {
        return new Card(suit, CardValue.FIVE);
    }

    public static Card sixOf(final CardSuit suit) {
        return new Card(suit, CardValue.SIX);
    }

    public static Card sevenOf(final CardSuit suit) {
        return new Card(suit, CardValue.SEVEN);
    }

    public static Card eightOf(final CardSuit suit) {
        return new Card(suit, CardValue.EIGHT);
    }

    public static Card nineOf(final CardSuit suit) {
        return new Card(suit, CardValue.NINE);
    }

    public static Card tenOf(final CardSuit suit) {
        return new Card(suit, CardValue.TEN);
    }

    public static Card jackOf(final CardSuit suit) {
        return new Card(suit, CardValue.JACK);
    }

    public static Card queenOf(final CardSuit suit) {
        return new Card(suit, CardValue.QUEEN);
    }

    public static Card kingOf(final CardSuit suit) {
        return new Card(suit, CardValue.KING);
    }

    public static Card aceOf(final CardSuit suit) {
        return new Card(suit, CardValue.ACE);
    }

}

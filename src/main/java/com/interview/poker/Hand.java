package com.interview.poker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.interview.poker.Card.CardValue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A poker hand of 5 cards
 * it can be compared to other hands.
 * We assume that there are no duplicate cards, neither in one hand nor in
 * different hands. This is not checked anwhere in the code.
 * 
 * 
 * these are the rules of ranking two hands:
 * A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked
 * by the following partial order from lowest to highest.
 * 
 * Categories:
 * 
 * High Card: Hands which do not fit any higher category are ranked by the
 * value of their highest card. If the highest cards have the same value, the
 * hands are ranked by the next highest, and so on.
 * 
 * Pair: 2 of the 5 cards in the hand have the same value. Hands which both
 * contain a pair are ranked by the value of the cards forming the pair. If
 * these values are the same, the hands are ranked by the values of the cards
 * not forming the pair, in decreasing order.
 * 
 * Two Pairs: The hand contains 2 different pairs. Hands which both contain 2
 * pairs are ranked by the value of their highest pair. Hands with the same
 * highest pair are ranked by the value of their other pair. If these values are
 * the same the hands are ranked by the value of the remaining card.
 * 
 * Three of a Kind: Three of the cards in the hand have the same value. Hands
 * which both contain three of a kind are ranked by the value of the 3 cards.
 * 
 * Straight: Hand contains 5 cards with consecutive values. Hands which both
 * contain a straight are ranked by their highest card.
 * 
 * Flush: Hand contains 5 cards of the same suit. Hands which are both
 * flushes
 * are ranked using the rules for High Card.
 * 
 * Full House: 3 cards of the same value, with the remaining 2 cards forming
 * a
 * pair. Ranked by the value of the 3 cards.
 * 
 * Four of a kind: 4 cards with the same value. Ranked by the value of the 4
 * cards.
 * 
 * Straight flush: 5 cards of the same suit with consecutive values. Ranked
 * by
 * the highest card in the hand.
 */
public class Hand {

    private static final String HIGH_CARD_RANK = "a";
    private static final String PAIR_RANK = "b";
    private static final String TWO_PAIRS_RANK = "c";
    private static final String THREE_OF_A_KIND_RANK = "d";

    /**
     * the cards in the hand, sorted by value, high to low
     */
    private final List<Card> cards;

    /**
     * constructor which takes 5 cards, there is no Hand with a different number of
     * cards
     */
    public Hand(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        var sortedCards = Arrays.asList(card1, card2, card3, card4, card5);
        sortedCards.sort((c1, c2) -> -c1.value.compareTo(c2.value));
        cards = sortedCards;
    }

    /**
     * compare this hand to another hand (we do not use compareTo as we do not want
     * to override equals and hashcode)
     * 
     * * Algorithm idea:
     * Get a string value for the hand, we can then use string alphabetical order to
     * compare two hands.
     * A string that is later in the alphabetical order is that of a better hand
     * String creation follows the ranking rules described above.
     * 
     * The category is the first character of the string. With "High Card" being
     * 'a', Pair being 'b',
     * "Two Pairs" being 'c' etc
     * 
     * Ranking within the category is done according to the rules of the category
     * via concatenated substrings.
     * 
     * @param otherHand the hand to compare to
     * @return > 0 if this hand is better, 0 if they are equal, < 0 if the other
     *         hand
     *         is better
     */
    public int compareToHand(final Hand otherHand) {
        return this.getRankingString().compareTo(otherHand.getRankingString());
    }

    /**
     * convenience function
     * 
     * @return true if this hand is better than the other hand, false otherwise
     */
    public boolean isBetterThan(final Hand otherHand) {
        return this.compareToHand(otherHand) > 0;
    }

    /**
     * calculate the rankin string from the cards
     * 
     */
    protected String getRankingString() {
        // we start with the best hand and go down to the worst, if a type of hand is
        // found, we return the string

        final var threeOfAKindRankingString = createThreeOfAKindRankingString();
        if (threeOfAKindRankingString != null) {
            return threeOfAKindRankingString;
        }
        final var twoPairsRankingString = createTwoPairsRankingString();
        if (twoPairsRankingString != null) {
            return twoPairsRankingString;
        }

        final var pairRankingString = createPairRankingString();
        if (pairRankingString != null) {
            return pairRankingString;
        }

        return createHighCardRankingString();
    }

    private String createThreeOfAKindRankingString() {
        // cards with the same value follow each other, as the cards are sorted by value
        CardValue lastValue = null;
        int numFound = 0;

        for (Card card : cards) {
            if (card.value == lastValue) {
                ++numFound;
                if (numFound == 3) {
                    break;
                }
            } else {
                lastValue = card.value;
                numFound = 1;
            }
        }

        if (numFound < 3) {
            return null;
        }
        final var cardsWithoutThreeOfAKind = new ArrayList<>(cards);
        final var removeAllCardsWithThisValue = lastValue; // final for lambda
        cardsWithoutThreeOfAKind.removeIf(card -> card.value == removeAllCardsWithThisValue);
        return THREE_OF_A_KIND_RANK + lastValue.rank + createHighCardRankingString(cardsWithoutThreeOfAKind);
    }

    protected String createTwoPairsRankingString() {
        // cards with the same value follow each other, as the cards are sorted by value
        CardValue lastValue = null;
        CardValue pair1CardValue = null;
        CardValue pair2CardValue = null;
        for (Card card : cards) {
            if (card.value == lastValue) {
                if (pair1CardValue == null) {
                    pair1CardValue = card.value;
                } else {
                    pair2CardValue = card.value;
                    break; // do not look further, tripples are handled elsewhere
                }
            } else {
                lastValue = card.value;
            }
        }

        if (pair2CardValue == null) {
            // only one or zero pairs found
            return null;
        }
        final var cardsWithoutPair = new ArrayList<>(cards);
        final var removeAllCardsWithPair1Value = pair1CardValue; // final for lambda
        cardsWithoutPair.removeIf(card -> card.value == removeAllCardsWithPair1Value);
        final var removeAllCardsWithPair2Value = pair2CardValue; // final for lambda
        cardsWithoutPair.removeIf(card -> card.value == removeAllCardsWithPair2Value);
        // pair1 has higher value than pair2, as they were sorted by value
        return TWO_PAIRS_RANK + pair1CardValue.rank + pair2CardValue.rank
                + createHighCardRankingString(cardsWithoutPair);
    }

    private String createPairRankingString() {
        // cards with the same value follow each other, as the cards are sorted by value
        CardValue lastValue = null;
        boolean found = false;
        for (Card card : cards) {
            if (card.value == lastValue) {
                found = true;
                break; // do not look further, tripples and two pairs are handled elsewhere
            } else {
                lastValue = card.value;
            }
        }
        if (!found) {
            return null;
        }
        final var cardsWithoutPair = new ArrayList<>(cards);
        final var removeAllCardsWithThisValue = lastValue; // final for lambda
        cardsWithoutPair.removeIf(card -> card.value == removeAllCardsWithThisValue);
        return PAIR_RANK + lastValue.rank + createHighCardRankingString(cardsWithoutPair);
    }

    /**
     * High Card: Hands which do not fit any higher category are ranked by the
     * value of their highest card. If the highest cards have the same value, the
     * hands are ranked by the next highest, and so on.
     * 
     * @return
     */
    private String createHighCardRankingString() {
        return HIGH_CARD_RANK + createHighCardRankingString(cards);
    }

    private String createHighCardRankingString(List<Card> cards) {
        final StringBuilder sb = new StringBuilder();
        for (final Card card : cards) {
            sb.append(card.value.rank);
        }
        return sb.toString();
    }

    public String toString() {
        return cards.toString();
    }
}

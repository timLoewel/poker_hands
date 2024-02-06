package com.interview.poker;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A poker hand of 5 cards
 * it can be compared to other hands
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

    private final List<Card> hand;

    /**
     * constructor which takes 5 cards, there is no Hand with a different number of
     * cards
     */
    public Hand(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        hand = Arrays.asList(card1, card2, card3, card4, card5);
    }

    /**
     * Algorithm idea:
     * Get a string value for the hand, we can then use string alphabetical order to
     * compare two hands.
     * A string that is later in the alphabetical order is that of a better hand
     * String creation follows the ranking rules described above.
     * 
     * The category is the first character of the string. With "High Card" being 'a'
     * and "Straight Flush" being 'i' these characters have been chosen for their
     * alphabetic order.
     * 
     * Ranking within the category is done according to the rules of the category
     * via substrings.
     * 
     */
    protected String getRankingString() {
        return createHighCardRankingString();
    }

    /**
     * High Card: Hands which do not fit any higher category are ranked by the
     * value of their highest card. If the highest cards have the same value, the
     * hands are ranked by the next highest, and so on.
     * 
     * @return
     */
    private String createHighCardRankingString() {
        final List<Card> sortedHand = new ArrayList<>(hand);
        sortedHand.sort((c1, c2) -> c2.value.compareTo(c1.value));
        final StringBuilder sb = new StringBuilder("a");
        for (final Card card : sortedHand) {
            sb.append(card.value.rank);
        }
        return sb.toString();
    }

    /**
     * compare this hand to another hand (we do not use compareTo as we do not want
     * to override equals and hashcode)
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
     */
    public boolean isBetterThan(final Hand otherHand) {
        return this.compareToHand(otherHand) > 0;
    }
}

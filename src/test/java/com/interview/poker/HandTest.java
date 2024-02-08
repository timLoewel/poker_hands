package com.interview.poker;

import static com.interview.poker.Card.eightOf;
import static com.interview.poker.Card.fiveOf;
import static com.interview.poker.Card.fourOf;
import static com.interview.poker.Card.kingOf;
import static com.interview.poker.Card.sevenOf;
import static com.interview.poker.Card.sixOf;
import static com.interview.poker.Card.threeOf;
import static com.interview.poker.Card.twoOf;
import static com.interview.poker.Card.CardSuit.CLUBS;
import static com.interview.poker.Card.CardSuit.DIAMONDS;
import static com.interview.poker.Card.CardSuit.HEARTS;
import static com.interview.poker.Card.CardSuit.SPADES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Specific tests for Hand validation
 * the bunch of the tests are in the GenericHandTests class
 */
class HandTest {

        @Test
        void testRankingStringSorting() {
                final Hand hand = new Hand(
                                twoOf(CLUBS),
                                threeOf(HEARTS),
                                fourOf(DIAMONDS),
                                fiveOf(SPADES),
                                sevenOf(CLUBS));
                final Hand sameHandDifferentSorting = new Hand(
                                threeOf(HEARTS),
                                fiveOf(SPADES),
                                fourOf(DIAMONDS),
                                twoOf(CLUBS),
                                sevenOf(CLUBS));
                assertEquals(0, hand.compareToHand(sameHandDifferentSorting));
        }

        @Test
        void testRankingStringSortingOrder() {
                final Hand winningHand = new Hand(
                                twoOf(HEARTS),
                                threeOf(CLUBS),
                                fourOf(DIAMONDS),
                                fiveOf(CLUBS),
                                kingOf(CLUBS));
                final Hand losingHand = new Hand(
                                threeOf(HEARTS),
                                fourOf(DIAMONDS),
                                fiveOf(SPADES),
                                sixOf(CLUBS),
                                eightOf(CLUBS));

                assertTrue(winningHand.isBetterThan(losingHand));
        }

        @Test
        void testHighCardVsHighCardSameRank() {
                final Hand highCardTwo = new Hand(
                                twoOf(CLUBS),
                                fourOf(DIAMONDS),
                                fiveOf(SPADES),
                                sevenOf(CLUBS),
                                eightOf(HEARTS));

                final Hand highCardThree = new Hand(
                                twoOf(DIAMONDS),
                                fourOf(CLUBS),
                                fiveOf(HEARTS),
                                sevenOf(DIAMONDS),
                                eightOf(SPADES));
                assertFalse(highCardThree.isBetterThan(highCardTwo));
                assertFalse(highCardTwo.isBetterThan(highCardThree));
        }

        @Test
        void testPairSameRank() {
                final Hand pairOfTwosWithAce = new Hand(
                                twoOf(CLUBS),
                                twoOf(HEARTS),
                                fourOf(DIAMONDS),
                                fiveOf(SPADES),
                                kingOf(HEARTS));
                final Hand pairOfTwosWithKing = new Hand(
                                twoOf(HEARTS),
                                twoOf(DIAMONDS),
                                fourOf(SPADES),
                                fiveOf(CLUBS),
                                kingOf(CLUBS));
                assertFalse(pairOfTwosWithAce.isBetterThan(pairOfTwosWithKing));
                assertFalse(pairOfTwosWithKing.isBetterThan(pairOfTwosWithAce));
        }

        @Test
        void testStraightString() {
                final Hand straight = new Hand(
                                twoOf(CLUBS),
                                threeOf(HEARTS),
                                fourOf(DIAMONDS),
                                fiveOf(SPADES),
                                sixOf(CLUBS));
                final Hand pairOfTwosWithKing = new Hand(
                                twoOf(HEARTS),
                                twoOf(DIAMONDS),
                                fourOf(SPADES),
                                fiveOf(CLUBS),
                                kingOf(CLUBS));
                assertTrue(straight.isBetterThan(pairOfTwosWithKing));

        }

}

package com.interview.poker;

import static com.interview.poker.Card.*;
import static com.interview.poker.Card.CardSuit.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Specific tests for Hand validation
 * the bunch of the tests are in the GenericHandTests class
 */
public class HandTest {

        @Test
        public void testRankingStringSorting() {
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
        public void testRankingStringSortingOrder() {
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
        public void testHighCardVsHighCardSameRank() {
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
        public void testPairSameRank() {
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
        public void testStraightString() {
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

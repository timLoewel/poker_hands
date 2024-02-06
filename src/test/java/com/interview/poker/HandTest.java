package com.interview.poker;

import static com.interview.poker.Card.*;
import static com.interview.poker.Card.CardSuit.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/** Unit test for Hand validation */
public class HandTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HandTest(String testName) {
        super(testName);
    }

    /** @return the suite of tests being tested */
    public static Test suite() {
        return new TestSuite(HandTest.class);
    }

    // High Card
    public void testHighCardVsHighCard() {
        Hand highCardSeven = new Hand(
                twoOf(CLUBS),
                threeOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                sevenOf(CLUBS));
        Hand highCardAce = new Hand(
                twoOf(HEARTS),
                threeOf(DIAMONDS),
                fourOf(SPADES),
                fiveOf(CLUBS),
                aceOf(CLUBS));
        assertTrue(highCardAce.isBetterThan(highCardSeven));
        assertFalse(highCardSeven.isBetterThan(highCardAce));
    }

    public void testHighCardVsHighCardNotSorted() {
        Hand highCardSeven = new Hand(
                sevenOf(CLUBS),
                threeOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                twoOf(CLUBS));
        Hand highCardAce = new Hand(
                threeOf(HEARTS),
                twoOf(CLUBS),
                aceOf(SPADES),
                fiveOf(DIAMONDS),
                fourOf(CLUBS));
        assertTrue(highCardAce.isBetterThan(highCardSeven));
        assertFalse(highCardSeven.isBetterThan(highCardAce));
    }

    public void testHighCardVsHighCardLastCardDifferent() {
        Hand highCardTwo = new Hand(
                twoOf(CLUBS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                sevenOf(CLUBS),
                eightOf(HEARTS));

        Hand highCardThree = new Hand(
                threeOf(CLUBS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                sevenOf(CLUBS),
                eightOf(HEARTS));
        assertTrue(highCardThree.isBetterThan(highCardTwo));
        assertFalse(highCardTwo.isBetterThan(highCardThree));
    }

    public void testHighCardVsHighCardSameRank() {
        Hand highCardTwo = new Hand(
                twoOf(CLUBS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                sevenOf(CLUBS),
                eightOf(HEARTS));

        Hand highCardThree = new Hand(
                twoOf(DIAMONDS),
                fourOf(CLUBS),
                fiveOf(HEARTS),
                sevenOf(DIAMONDS),
                eightOf(SPADES));
        assertFalse(highCardThree.isBetterThan(highCardTwo));
        assertFalse(highCardTwo.isBetterThan(highCardThree));
    }

    // Pair
    public void testPairVsHighCard() {
        Hand highCardSeven = new Hand(
                sevenOf(CLUBS),
                threeOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                twoOf(CLUBS));
        Hand pairOfThrees = new Hand(
                threeOf(HEARTS),
                threeOf(DIAMONDS),
                fourOf(SPADES),
                fiveOf(CLUBS),
                aceOf(CLUBS));
        assertTrue(pairOfThrees.isBetterThan(highCardSeven));
        assertFalse(highCardSeven.isBetterThan(pairOfThrees));
    }

    public void testTwoDifferentPairs() {
        Hand pairOfTwos = new Hand(
                twoOf(CLUBS),
                twoOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                sevenOf(CLUBS));
        Hand pairOfThrees = new Hand(
                threeOf(HEARTS),
                threeOf(DIAMONDS),
                fourOf(SPADES),
                fiveOf(CLUBS),
                aceOf(CLUBS));
        assertTrue(pairOfThrees.isBetterThan(pairOfTwos));
        assertFalse(pairOfTwos.isBetterThan(pairOfThrees));
    }

    public void testTwoSamePairs() {
        Hand pairOfTwosWithAce = new Hand(
                twoOf(CLUBS),
                twoOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                aceOf(CLUBS));
        Hand pairOfTwosWithKing = new Hand(
                twoOf(HEARTS),
                twoOf(DIAMONDS),
                fourOf(SPADES),
                fiveOf(CLUBS),
                kingOf(CLUBS));
        assertTrue(pairOfTwosWithAce.isBetterThan(pairOfTwosWithKing));
        assertFalse(pairOfTwosWithKing.isBetterThan(pairOfTwosWithAce));
    }

    public void testTwoSameRank() {
        Hand pairOfTwosWithAce = new Hand(
                twoOf(CLUBS),
                twoOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                kingOf(HEARTS));
        Hand pairOfTwosWithKing = new Hand(
                twoOf(HEARTS),
                twoOf(DIAMONDS),
                fourOf(SPADES),
                fiveOf(CLUBS),
                kingOf(CLUBS));
        assertFalse(pairOfTwosWithAce.isBetterThan(pairOfTwosWithKing));
        assertFalse(pairOfTwosWithKing.isBetterThan(pairOfTwosWithAce));
    }

}

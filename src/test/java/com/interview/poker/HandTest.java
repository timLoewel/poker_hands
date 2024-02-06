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

    public void testHighCard() {
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

    public void testHighCardNotSorted() {
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

}

package com.interview.poker;

import static com.interview.poker.Card.*;
import static com.interview.poker.Card.CardSuit.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/** Unit test for Hand validation */
public class CardTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CardTest(String testName) {
        super(testName);
    }

    /** @return the suite of tests being tested */
    public static Test suite() {
        return new TestSuite(CardTest.class);
    }

    public void testHighCard() {
        Hand highCardSeven = new Hand(
                twoOf(CLUBS),
                threeOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                sevenOf(CLUBS));
        Hand highCardAce = new Hand(
                twoOf(CLUBS),
                threeOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                aceOf(CLUBS));
        assertTrue(highCardAce.isBetterThan(highCardSeven));
    }

    public void testHighCardNotSorted() {
        Hand highCardSeven = new Hand(
                sevenOf(CLUBS),
                threeOf(HEARTS),
                fourOf(DIAMONDS),
                fiveOf(SPADES),
                twoOf(CLUBS));
        Hand highCardAce = new Hand(
                threeOf(CLUBS),
                twoOf(HEARTS),
                aceOf(DIAMONDS),
                fiveOf(SPADES),
                fourOf(CLUBS));
        assertTrue(highCardAce.isBetterThan(highCardSeven));
    }
}

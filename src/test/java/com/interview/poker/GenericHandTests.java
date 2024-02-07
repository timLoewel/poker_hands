package com.interview.poker;

import static com.interview.poker.Card.*;
import static com.interview.poker.Card.CardSuit.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * compares all combinations hands and checks if the comparison is correct
 */
public class GenericHandTests {

    @ParameterizedTest
    @MethodSource("provideAllHandCombinations")
    public void testCompareHands(Hand losingHand, Hand winningHand, int indexLoser, int indexWinner) {
        assertTrue(winningHand.isBetterThan(losingHand),
                "expected \n\t" + indexWinner + winningHand + "\n" + winningHand.getRankingString()
                        + "\nto be better than\n\t" + indexLoser
                        + losingHand + "\n" + losingHand.getRankingString());
        assertFalse(losingHand.isBetterThan(winningHand),
                "expected \n\t" + losingHand + "\nto be worse than\n\t" + winningHand);
    }

    private static Stream<Arguments> provideAllHandCombinations() {
        // create all pairwise combinations of the sampleHands
        List<Arguments> result = new ArrayList<>();
        for (int i = 0; i < sampleHands.length; i++) {
            for (int j = i + 1; j < sampleHands.length; j++) {
                result.add(Arguments.of(sampleHands[i], sampleHands[j], i, j));
            }
        }
        return result.stream();
    }

    /**
     * a big list of hands to test
     * lower index means lower rank
     * For the comparison of two hands, it does not matter, if the two hands could
     * be
     * drawn from the same deck. So several cards of the same Suit and Value are
     * allowed here.
     */
    private final static Hand[] sampleHands = {
            // high card
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)), // 0
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)), // 1
            new Hand(threeOf(CLUBS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS), sevenOf(CLUBS)), // 2
            new Hand(threeOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), aceOf(CLUBS)), // 4
            new Hand(tenOf(CLUBS), jackOf(HEARTS), queenOf(DIAMONDS), kingOf(SPADES), aceOf(CLUBS)), // 3
            // pair
            new Hand(twoOf(CLUBS), twoOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)), // 5
            new Hand(twoOf(CLUBS), twoOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)), // 6
            new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)), // 7
            new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)), // 8
            new Hand(fourOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), sevenOf(CLUBS)), // 9
            // two pairs
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES),
            // fourOf(CLUBS)), // 10
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES),
            // fiveOf(CLUBS)), // 11
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES),
            // fiveOf(CLUBS)), // 12
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES),
            // sixOf(CLUBS)), // 13
            // new Hand(fourOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), fiveOf(SPADES),
            // sixOf(CLUBS)), // 14
            // three of a kind
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), threeOf(SPADES),
            // fourOf(CLUBS)),
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), threeOf(SPADES),
            // fiveOf(CLUBS)),
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), fourOf(SPADES),
            // fiveOf(CLUBS)),
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), fourOf(SPADES),
            // sixOf(CLUBS)),
            // new Hand(fourOf(CLUBS), fourOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES),
            // sixOf(CLUBS)),
            // straight
            // new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES),
            // sixOf(CLUBS)),
            // new Hand(threeOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES),
            // sevenOf(CLUBS)),
            // new Hand(fourOf(CLUBS), fiveOf(HEARTS), sixOf(DIAMONDS), sevenOf(SPADES),
            // eightOf(CLUBS)),
            // new Hand(fiveOf(CLUBS), sixOf(HEARTS), sevenOf(DIAMONDS), eightOf(SPADES),
            // nineOf(CLUBS)),
            // new Hand(sixOf(CLUBS), sevenOf(HEARTS), eightOf(DIAMONDS), nineOf(SPADES),
            // tenOf(CLUBS)),
            // // flush
            // new Hand(twoOf(CLUBS), threeOf(CLUBS), fourOf(CLUBS), fiveOf(CLUBS),
            // sixOf(CLUBS)),
            // new Hand(threeOf(CLUBS), fourOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS),
            // sevenOf(CLUBS)),
            // new Hand(fourOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS),
            // eightOf(CLUBS)),
            // new Hand(fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS),
            // nineOf(CLUBS)),
            // new Hand(sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS), nineOf(CLUBS),
            // tenOf(CLUBS)),
            // // full house
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES),
            // threeOf(CLUBS)),
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES),
            // fourOf(CLUBS)),
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES),
            // fourOf(CLUBS)),
            // new Hand(fourOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), fiveOf(SPADES),
            // fiveOf(CLUBS)),
            // new Hand(fiveOf(CLUBS), fiveOf(HEARTS), sixOf(DIAMONDS), sixOf(SPADES),
            // sixOf(CLUBS)),
            // // four of a kind
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), twoOf(SPADES),
            // threeOf(CLUBS)),
            // new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), twoOf(SPADES),
            // fourOf(CLUBS)),
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES),
            // fourOf(CLUBS)),
            // new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES),
            // fiveOf(CLUBS)),
            // new Hand(fourOf(CLUBS), fourOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES),
            // fiveOf(CLUBS)),
            // // straight flush
            // new Hand(twoOf(CLUBS), threeOf(CLUBS), fourOf(CLUBS), fiveOf(CLUBS),
            // sixOf(CLUBS)),
            // new Hand(threeOf(CLUBS), fourOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS),
            // sevenOf(CLUBS)),
            // new Hand(fourOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS),
            // eightOf(CLUBS)),
            // new Hand(fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS),
            // nineOf(CLUBS)),
            // new Hand(sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS), nineOf(CLUBS),
            // tenOf(CLUBS))
    };

}
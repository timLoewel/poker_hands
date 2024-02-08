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
     * 
     * for every type of hand there is a hand with the lowest possible rank and
     * another hand with the highest possible rank
     * 
     * For the comparison of two hands, it does not matter, if the two hands could
     * be drawn from the same deck. So several cards of the same Suit and Value are
     * allowed here.
     */
    private final static Hand[] sampleHands = {
            // high card
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)),
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), eightOf(CLUBS)),
            new Hand(threeOf(CLUBS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS), eightOf(CLUBS)),
            new Hand(threeOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), aceOf(CLUBS)),
            new Hand(nineOf(CLUBS), jackOf(HEARTS), queenOf(DIAMONDS), kingOf(SPADES), aceOf(CLUBS)),
            // pair
            new Hand(twoOf(CLUBS), twoOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(twoOf(CLUBS), twoOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)),
            new Hand(fourOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), sevenOf(CLUBS)),
            new Hand(jackOf(CLUBS), queenOf(HEARTS), kingOf(DIAMONDS), aceOf(SPADES), aceOf(CLUBS)),
            // two pairs
            new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES), fourOf(CLUBS)),
            new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES), fiveOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES), fiveOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES), sixOf(CLUBS)),
            new Hand(fourOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(queenOf(CLUBS), kingOf(HEARTS), kingOf(DIAMONDS), aceOf(SPADES), aceOf(CLUBS)),
            // three of a kind
            new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), fourOf(SPADES), fiveOf(CLUBS)),
            new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), threeOf(SPADES), sixOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), fourOf(SPADES), fiveOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), fourOf(SPADES), sixOf(CLUBS)),
            new Hand(fourOf(CLUBS), fourOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(queenOf(CLUBS), kingOf(HEARTS), aceOf(DIAMONDS), aceOf(SPADES), aceOf(CLUBS)),
            // straight
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(threeOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), sevenOf(CLUBS)),
            new Hand(tenOf(CLUBS), jackOf(HEARTS), queenOf(DIAMONDS), kingOf(SPADES), aceOf(CLUBS)),
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
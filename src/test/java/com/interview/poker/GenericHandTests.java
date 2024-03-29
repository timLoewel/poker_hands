package com.interview.poker;

import static com.interview.poker.Card.aceOf;
import static com.interview.poker.Card.eightOf;
import static com.interview.poker.Card.fiveOf;
import static com.interview.poker.Card.fourOf;
import static com.interview.poker.Card.jackOf;
import static com.interview.poker.Card.kingOf;
import static com.interview.poker.Card.nineOf;
import static com.interview.poker.Card.queenOf;
import static com.interview.poker.Card.sevenOf;
import static com.interview.poker.Card.sixOf;
import static com.interview.poker.Card.tenOf;
import static com.interview.poker.Card.threeOf;
import static com.interview.poker.Card.twoOf;
import static com.interview.poker.Card.CardSuit.CLUBS;
import static com.interview.poker.Card.CardSuit.DIAMONDS;
import static com.interview.poker.Card.CardSuit.HEARTS;
import static com.interview.poker.Card.CardSuit.SPADES;
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
class GenericHandTests {

    @ParameterizedTest
    @MethodSource("provideAllHandCombinations")
    void testCompareHands(Hand losingHand, Hand winningHand, int indexLoser, int indexWinner) {
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
     * For the comparison of most hands, it does not matter, if the two hands could
     * be drawn from the same deck. So several cards of the same Suit and Value are
     * allowed most of the time.
     */
    private final static Hand[] sampleHands = {
            // high card
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS)),
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), eightOf(CLUBS)),
            new Hand(threeOf(CLUBS), fourOf(DIAMONDS), fiveOf(SPADES), sevenOf(CLUBS), eightOf(CLUBS)),
            new Hand(threeOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), aceOf(CLUBS)),
            new Hand(nineOf(CLUBS), jackOf(HEARTS), queenOf(DIAMONDS), kingOf(SPADES), aceOf(CLUBS)),
            // pair
            new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), fourOf(SPADES), fiveOf(CLUBS)),
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
            new Hand(fourOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), sixOf(CLUBS)),
            new Hand(queenOf(CLUBS), kingOf(HEARTS), kingOf(DIAMONDS), aceOf(SPADES), aceOf(CLUBS)),
            // three of a kind, no two hands can have the same card value for the three
            // cards
            new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), fourOf(SPADES), fiveOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(twoOf(CLUBS), fourOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES), fiveOf(CLUBS)),
            new Hand(queenOf(CLUBS), kingOf(HEARTS), aceOf(DIAMONDS), aceOf(SPADES), aceOf(CLUBS)),
            // straight
            new Hand(twoOf(CLUBS), threeOf(HEARTS), fourOf(DIAMONDS), fiveOf(SPADES), sixOf(CLUBS)),
            new Hand(threeOf(CLUBS), fourOf(HEARTS), fiveOf(DIAMONDS), sixOf(SPADES), sevenOf(CLUBS)),
            new Hand(tenOf(CLUBS), jackOf(HEARTS), queenOf(DIAMONDS), kingOf(SPADES), aceOf(CLUBS)),
            // flush
            new Hand(twoOf(CLUBS), fourOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS)),
            new Hand(twoOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS)),
            new Hand(threeOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS)),
            new Hand(sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS), nineOf(CLUBS), jackOf(CLUBS)),
            new Hand(sixOf(CLUBS), sevenOf(CLUBS), eightOf(CLUBS), nineOf(CLUBS), queenOf(CLUBS)),

            // full house, no two full houses can have the same card value for the three
            // cards
            new Hand(twoOf(CLUBS), twoOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES), threeOf(CLUBS)),
            new Hand(kingOf(CLUBS), kingOf(HEARTS), fourOf(DIAMONDS), fourOf(SPADES), fourOf(CLUBS)),
            new Hand(fiveOf(DIAMONDS), fiveOf(SPADES), fiveOf(CLUBS), fourOf(CLUBS), fourOf(HEARTS)),
            new Hand(queenOf(CLUBS), queenOf(HEARTS), aceOf(DIAMONDS), aceOf(SPADES), aceOf(CLUBS)),

            // four of a kind, no two hands can have the same card value for the four cards
            new Hand(twoOf(CLUBS), twoOf(HEARTS), twoOf(DIAMONDS), twoOf(SPADES), threeOf(CLUBS)),
            new Hand(threeOf(CLUBS), threeOf(HEARTS), threeOf(DIAMONDS), threeOf(SPADES), fourOf(CLUBS)),
            new Hand(aceOf(CLUBS), aceOf(HEARTS), aceOf(DIAMONDS), aceOf(SPADES), kingOf(CLUBS)),

            // straight flush
            new Hand(twoOf(CLUBS), threeOf(CLUBS), fourOf(CLUBS), fiveOf(CLUBS), sixOf(CLUBS)),
            new Hand(threeOf(HEARTS), fourOf(HEARTS), fiveOf(HEARTS), sixOf(HEARTS), sevenOf(HEARTS)),
            new Hand(sixOf(DIAMONDS), sevenOf(DIAMONDS), eightOf(DIAMONDS), nineOf(DIAMONDS), tenOf(DIAMONDS)),
            new Hand(tenOf(SPADES), jackOf(SPADES), queenOf(SPADES), kingOf(SPADES), aceOf(SPADES))
    };

}
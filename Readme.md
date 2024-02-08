# Poker Hand Ranking

In this repository, there is a simple algorithm for evaluating poker hands.

## Building

You need maven and java installed.

I tried it with Maven 3.6.3 and Java 21.

## Running

In the base directory run:

```sh
mvn test
```

to run the tests.

If you want to try your own hand, take a look at the [HandTest](src/test/java/com/interview/poker/HandTest.java)
and add your test there.

A hand is described by some helper functions in the code:

```java
 final Hand fullHouse = new Hand( kingOf(CLUBS),
                            kingOf(HEARTS),
                            aceOf(DIAMONDS),
                            aceOf(SPADES),
                            aceOf(CLUBS));
```

## Ranking Algorithm

### Idea

Create a string value for each hand, we can then use string alphabetical order to
compare two hands.

A string that is later in the alphabetical order is that of a better hand.
String creation follows the ranking rules described below.

The category is the first character of the string. With "High Card" being
'a', "Pair" being 'b', "Two Pairs" being 'c' etc.

Ranking within the category is done according to the rules of the category
via concatenated substrings.

### Ranking Rules

These are the rules of ranking two hands:
  
A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked
  by the following partial order from lowest to highest.
  
#### Categories
  
* High Card: Hands which do not fit any higher category are ranked by the
  value of their highest card. If the highest cards have the same value, the
  hands are ranked by the next highest, and so on.
  
* Pair: 2 of the 5 cards in the hand have the same value. Hands which both
  contain a pair are ranked by the value of the cards forming the pair. If
  these values are the same, the hands are ranked by the values of the cards
  not forming the pair, in decreasing order.
  
* Two Pairs: The hand contains 2 different pairs. Hands which both contain 2
  pairs are ranked by the value of their highest pair. Hands with the same
  highest pair are ranked by the value of their other pair. If these values are
  the same the hands are ranked by the value of the remaining card.
  
* Three of a Kind: Three of the cards in the hand have the same value. Hands
  which both contain three of a kind are ranked by the value of the 3 cards.
  
* Straight: Hand contains 5 cards with consecutive values. Hands which both
  contain a straight are ranked by their highest card.
  
* Flush: Hand contains 5 cards of the same suit. Hands which are both
  flushes are ranked using the rules for High Card.
  
* Full House: 3 cards of the same value, with the remaining 2 cards forming
  a pair. Ranked by the value of the 3 cards.
  
* Four of a kind: 4 cards with the same value. Ranked by the value of the 4
  cards.
  
* Straight flush: 5 cards of the same suit with consecutive values. Ranked
  by he highest card in the hand.
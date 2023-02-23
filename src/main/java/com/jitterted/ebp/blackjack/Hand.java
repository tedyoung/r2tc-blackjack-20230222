package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
    private final List<Card> cards = new ArrayList<Card>();

    public Hand() {
    }

    public Hand(List<Card> cards) {
        this.cards.addAll(cards);
    }

    void drawCardFrom(Deck deck) {
        cards.add(deck.draw());
    }

    int value() {
        int handValue = cards
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = cards
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    void display() {
        System.out.println(cards.stream()
                                .map(Card::display)
                                .collect(Collectors.joining(
                                        ansi().cursorUp(6).cursorRight(1).toString())));
    }

    boolean isBusted() {
        return value() > 21;
    }

    boolean beats(Hand otherHand) {
        if (isBusted() || otherHand.isBusted()) {
            throw new IllegalArgumentException();
        }
        return otherHand.value() < value();
    }

    boolean pushes(Hand thisHand) {
        return thisHand.value() == value();
    }

    // --- Dealer-specific behavior below: --

    Card faceUpCard() {
        return cards.get(0);
    }

    boolean dealerShouldHit() {
        return value() <= 16;
    }

}
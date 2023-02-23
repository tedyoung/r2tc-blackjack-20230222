package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HandTest {

    private static final Suit DUMMY_SUIT = Suit.CLUBS;

    @Test
    void handWithHigherValueBeatsHandWithLowerValue() {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "K"),
                                   new Card(DUMMY_SUIT, "Q"));
        Hand handValuedAt20 = new Hand(cards);
        cards = List.of(new Card(DUMMY_SUIT, "8"),
                        new Card(DUMMY_SUIT, "9"));
        Hand handValuedAt17 = new Hand(cards);

        assertThat(handValuedAt20.beats(handValuedAt17))
                .isTrue();
    }


    @Test
    void bustedHandWithHigherValueMustNotBeatNonBustedHandWithLowerValue() {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "K"),
                                   new Card(DUMMY_SUIT, "A"));
        Hand handValuedAt21 = new Hand(cards);
        cards = List.of(new Card(DUMMY_SUIT, "8"),
                        new Card(DUMMY_SUIT, "9"),
                        new Card(DUMMY_SUIT, "7"));
        Hand bustedHand = new Hand(cards);

        assertThatThrownBy(() -> bustedHand.beats(handValuedAt21))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
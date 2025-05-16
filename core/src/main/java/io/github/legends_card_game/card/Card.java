package io.github.legends_card_game.card;

import io.github.legends_card_game.card.suite.Suite;

public class Card {
    private final int value;
    private final Suite suite;

    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;
    }

    public int getValue() {
        return value;
    }

    public Suite getSuite() {
        return suite;
    }

    public String toString() {
        return value + "";
    }
}

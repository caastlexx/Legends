package io.github.legends_card_game.card;

import com.badlogic.gdx.graphics.Texture;
import io.github.legends_card_game.card.suite.Suite;

public class Card {
    private final int value;
    private final Suite suite;

    private final Texture texture;

    private int x;
    private int y;

    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;

        x = 0;
        y = 0;

        texture = new Texture("playing_card.png");
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

    public Texture getTexture() {
        return texture;
    }

    public Card setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

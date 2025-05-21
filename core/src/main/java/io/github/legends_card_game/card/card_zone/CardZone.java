package io.github.legends_card_game.card.card_zone;

import io.github.legends_card_game.card.Card;

import java.util.ArrayList;


public class CardZone {
    public static final ArrayList<CardZone> cardZones = new ArrayList<>();

    public static final int height = 106;
    public static final int width = 74;

    public int x;
    public int y;

    private Card heldCard;

    public CardZone(Card initialCard, int x, int y) {
        heldCard = initialCard;

        this.x = x;
        this.y = y;

        cardZones.add(this);
    }

    public CardZone(int x, int y) {
        heldCard = null;

        this.x = x;
        this.y = y;

        cardZones.add(this);
    }

    private static boolean isWithinRange(int num, int min, int max) {
        return num > min && num < max;
    }

    public static CardZone findCardZone(int x, int y) {
        for (CardZone cardZone : cardZones) {
            boolean withinRange = isWithinRange(x, cardZone.x, cardZone.x + width) && isWithinRange(y, cardZone.y, cardZone.y + height);

            if (withinRange) return cardZone;
        }

        return null;
    }

    public boolean hasCard() {
        return heldCard != null;
    }

    public Card removeCard() {
        Card removedCard = heldCard;
        heldCard = null;

        return removedCard;
    }

    public Card addCard(Card card) {
        heldCard = card.setPosition(x + 5, y + 5);

        return card;
    }

    public Card getHeldCard() {
        return heldCard;
    }
}

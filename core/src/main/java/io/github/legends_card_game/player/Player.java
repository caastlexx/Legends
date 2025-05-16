package io.github.legends_card_game.player;

import io.github.legends_card_game.card.Card;

public class Player {
    private Card[] botLaneCards;
    private Card[] midLaneCards;
    private Card[] topLaneCards;

    public Player(Card[] initBotLaneCards, Card initMidLaneCard, Card initTopLaneCard) {
        botLaneCards = new Card[]{null, null, initBotLaneCards[0], initBotLaneCards[1]};
        midLaneCards = new Card[]{null, initMidLaneCard};
        topLaneCards = new Card[]{null, initTopLaneCard};
    }

    public String getCards(Card[] cards) {
        StringBuilder cardsString = new StringBuilder();

        for (Card card : cards) {
            if (!(card == null)) {
                cardsString.append(card.getValue()).append(" ");
            }

        }

        return cardsString.toString();
    }

    public String getBotLaneCards() {
        return getCards(botLaneCards);
    }

    public String getTopLaneCards() {
        return getCards(topLaneCards);
    }

    public String getMidLaneCards() {
        return getCards(midLaneCards);
    }

    public int getLaneValue(Card[] cards) {
        int value = 0;

        for (Card card : cards) {
            if (!(card == null)) {
                value += card.getValue();
            }

        }

        return value;
    }

    public int getBotLaneValue() {
        return getLaneValue(botLaneCards);
    }

    public int getMidLaneValue() {
        return getLaneValue(midLaneCards);
    }

    public int getTopLaneValue() {
        return getLaneValue(topLaneCards);
    }
}

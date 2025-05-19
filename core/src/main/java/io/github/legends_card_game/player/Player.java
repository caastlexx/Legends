package io.github.legends_card_game.player;

import io.github.legends_card_game.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Player {
    private final Card[] botLaneCards;
    private final Card[] midLaneCards;
    private final Card[] topLaneCards;

    private final ArrayList<Card> jungleCards;

    public Player(Card[] initBotLaneCards, Card initMidLaneCard, Card initTopLaneCard, Card initJungleCard) {
        botLaneCards = new Card[]{null, null, initBotLaneCards[0], initBotLaneCards[1]};
        midLaneCards = new Card[]{null, initMidLaneCard};
        topLaneCards = new Card[]{null, initTopLaneCard};

        jungleCards = new ArrayList<>();
        jungleCards.add(initJungleCard);
    }

    public String getCards(Collection<Card> cards) {
        StringBuilder cardsString = new StringBuilder();

        for (Card card : cards) {
            if (!(card == null)) {
                cardsString.append(card.getValue()).append(" ");
            }

        }

        return cardsString.toString();
    }

    public String getCards(Card[] cards) {
        return getCards(Arrays.asList(cards));
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

    public String getJungleCards() {
        return getCards(jungleCards);
    }
}

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

    public ArrayList<Card> getJungleCards() {
        return jungleCards;
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

    public void addBotLaneCards(Card[] cardsToAdd) {
        botLaneCards[0] = cardsToAdd[0];
        botLaneCards[1] = cardsToAdd[1];
    }

    public void addMidLaneCard(Card cardToAdd) {
        midLaneCards[0] = cardToAdd;
    }

    public void addTopLaneCard(Card cardToAdd) {
        topLaneCards[0] = cardToAdd;
    }

    public Card[] discardOldBotLaneCards() {
        Card[] cardsToDiscard = new Card[2];

        cardsToDiscard[0] = botLaneCards[2];
        cardsToDiscard[1] = botLaneCards[3];

        // move new cards into old card positions in the botLaneCars array
        botLaneCards[2] = botLaneCards[0];
        botLaneCards[3] = botLaneCards[1];

        return cardsToDiscard;
    }

    public Card discardOldMidLaneCard() {
        Card cardToDiscard = midLaneCards[1];

        midLaneCards[1] = midLaneCards[0];

        return cardToDiscard;
    }

    public Card discardOldTopLaneCard() {
        Card cardToDiscard = topLaneCards[1];

        topLaneCards[1] = topLaneCards[0];

        return cardToDiscard;
    }

    public Card[] discardOldCards() {
        Card[] cardsToDiscard = new Card[4];

        Card[] oldBotLaneCards = discardOldBotLaneCards();

        cardsToDiscard[0] = oldBotLaneCards[0];
        cardsToDiscard[1] = oldBotLaneCards[1];

        cardsToDiscard[2] = discardOldMidLaneCard();
        cardsToDiscard[3] = discardOldTopLaneCard();

        return cardsToDiscard;
    }
}

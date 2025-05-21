package io.github.legends_card_game.player;

import io.github.legends_card_game.card.Card;

import java.util.*;

public class Player {
    // Lane card queues
    private final Queue<Card> botLaneCards;
    private final Queue<Card> midLaneCards;
    private final Queue<Card> topLaneCards;

    // Jungle Cards
    private final ArrayList<Card> jungleCards;

    public Player(Card[] initBotLaneCards, Card initMidLaneCard, Card initTopLaneCard, Card initJungleCard) {
        // initialize lane card queues and add initial cards
        botLaneCards = new LinkedList<>();
        botLaneCards.add(initBotLaneCards[0]);
        botLaneCards.add(initBotLaneCards[1]);

        midLaneCards = new LinkedList<>();
        midLaneCards.add(initMidLaneCard);

        topLaneCards = new LinkedList<>();
        topLaneCards.add(initTopLaneCard);

        // initialize jungle card arraylist and add initial jungle
        jungleCards = new ArrayList<>();
        jungleCards.add(initJungleCard);
    }

    // return cards in the form of a formatted string
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

    public Queue<Card> getMidLaneCards() {
        return midLaneCards;
    }

    public ArrayList<Card> getJungleCards() {
        return jungleCards;
    }

    public int getLaneValue(Collection<Card> cards) {
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
        botLaneCards.add(cardsToAdd[0]);
        botLaneCards.add(cardsToAdd[1]);
    }

    public void addMidLaneCard(Card cardToAdd) {
        midLaneCards.add(cardToAdd);
    }

    public void addTopLaneCard(Card cardToAdd) {
        midLaneCards.add(cardToAdd);
    }

    public Card[] discardOldBotLaneCards() {
        Card[] cardsToDiscard = new Card[2];

        cardsToDiscard[0] = botLaneCards.remove();
        cardsToDiscard[1] = botLaneCards.remove();

        return cardsToDiscard;
    }

    public Card discardOldMidLaneCard() {
        return midLaneCards.remove();
    }

    public Card discardOldTopLaneCard() {
        return  topLaneCards.remove();
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

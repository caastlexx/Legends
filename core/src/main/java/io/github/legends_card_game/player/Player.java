package io.github.legends_card_game.player;

public class Player {
    private int[] botLaneCards;
    private int[] midLaneCards;
    private int[] topLaneCards;

    public Player() {
        botLaneCards = new int[]{0, 0, 0, 0};
        midLaneCards = new int[]{0, 0};
        topLaneCards = new int[]{0, 0};
    }

    public String getCards(int[] cardValues) {
        StringBuilder cardsString = new StringBuilder();

        for (int card : cardValues) {
            cardsString.append(card).append(" ");
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
}

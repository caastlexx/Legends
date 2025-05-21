package io.github.legends_card_game.card.card_deck;

import io.github.legends_card_game.card.Card;
import io.github.legends_card_game.card.suite.Suite;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final ArrayList<Card> deck;
    private final ArrayList<Card> discard;

    public CardDeck() {
        deck = new ArrayList<>(52);
        discard = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Suite suite = Suite.HEARTS;

            switch (i) {
                case 1:
                    suite = Suite.DIAMONDS;
                    break;
                case 2:
                    suite = Suite.CLUBS;
                    break;
                case 3:
                    suite = Suite.SPADES;
            }

            for (int j = 0; j < 13; j++) {
                deck.add(new Card(j + 1, suite));
            }
        }
    }

    public Card drawCard() {
        int cardIdx = (int) (Math.random() * deck.size());
        Card card = deck.get(cardIdx);

        deck.remove(cardIdx);

        // refresh deck with discard when empty
        if (deck.isEmpty()) {
            deck.addAll(discard);
            discard.clear();
        }

        return card;
    }

    public Card drawCardTo(int x, int y) {
        return drawCard().setPosition(x, y);
    }

    public Card[] drawCards(int count) {
        Card[] cards = new Card[count];

        for (int i = 0; i < count; i++) {
            cards[i] = drawCard();
        }

        return cards;
    }

    public void discard(Card card) {
        discard.add(card);
    }

    public void discard(Card[] cards) {
        for (Card card : cards) {
            discard(card);
        }
    }
}

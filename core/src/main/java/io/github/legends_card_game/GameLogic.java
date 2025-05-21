package io.github.legends_card_game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.legends_card_game.card.Card;
import io.github.legends_card_game.card.card_deck.CardDeck;
import io.github.legends_card_game.card.card_zone.CardZone;

import java.util.Arrays;

public class GameLogic {
    public InputProcessor MyInputProcessor = new InputProcessor() {
        private Card touchedCard;
        private CardZone cardSource;

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (!(button == 0)) {
                return true;
            }

            Vector3 vector = new Vector3(screenX, screenY, 0);
            camera.unproject(vector);

            cardSource = CardZone.findCardZone(Math.round(vector.x), Math.round(vector.y));

            if (cardSource == null || !cardSource.hasCard()) {

                return true;
            }

            touchedCard = cardSource.getHeldCard();
            cardSource.removeCard();

            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (!(button == 0) || touchedCard == null) {
                return true;
            }

            Vector3 vector = new Vector3(screenX, screenY, 0);
            camera.unproject(vector);

            CardZone touchedCardZone = CardZone.findCardZone(Math.round(vector.x), Math.round(vector.y));

            if (touchedCardZone == null) {
                cardSource.addCard(touchedCard);
                cardSource = null;
                return true;
            }

            touchedCardZone.addCard(touchedCard);
            touchedCard = null;

            return true;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            if (touchedCard == null) {
                return true;
            }

            Vector3 vector = new Vector3(screenX, screenY, 0);
            camera.unproject(vector);

            touchedCard.setPosition(Math.round(vector.x), Math.round(vector.y));
            return true;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    };
    private Card[] hand;
    private boolean handGenerated;
    private Camera camera;

    public GameLogic(Camera camera) {
        this.camera = camera;
    }

    public void dealCards(SpriteBatch batch, CardDeck deck, CardZone[] handZones) {
        if (!handGenerated) {
            hand = deck.drawCards(4);

            for (int i = 0; i < 4; i++) {
                handZones[i].addCard(hand[i]);
            }

            handGenerated = true;
        }

        for (Card card : hand) {
            batch.draw(card.getTexture(), card.getX(), card.getY());
        }
    }


}

package io.github.legends_card_game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.legends_card_game.card.card_deck.CardDeck;
import io.github.legends_card_game.card.card_zone.CardZone;
import io.github.legends_card_game.player.Player;

import java.util.Objects;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont bmFont;
    private Viewport viewport;
    private OrthographicCamera camera;

    private ShapeRenderer shapeRenderer;

    private GameLogic game;

    private Player player;
    private Player bot;
    private CardDeck deck;

    private enum Phase {
        DEAL,
        PLACE,
        JUNGLE,
        BATTLE,
        UPDATE
    }

    private Phase phase;

    private CardZone cardZoneBot1, cardZoneBot2, cardZoneMid, cardZoneTop, cardZoneHand1, cardZoneHand2, cardZoneHand3, cardZoneHand4;
    private CardZone[] handZones;

    public void initializeCardZones() {
        cardZoneBot1 = new CardZone(700, 200);
        cardZoneBot2 = new CardZone(600, 200);
        cardZoneMid = new CardZone(400, 200);
        cardZoneTop = new CardZone(200, 200);

        cardZoneHand1 = new CardZone(500, 400);
        cardZoneHand2 = new CardZone(400, 400);
        cardZoneHand3 = new CardZone(300, 400);
        cardZoneHand4 = new CardZone(200, 400);

        handZones = new CardZone[]{cardZoneHand1, cardZoneHand2, cardZoneHand3, cardZoneHand4};
    }

    public void renderCardZones() {
        shapeRenderer.rect(cardZoneBot1.x, cardZoneBot1.y, CardZone.width, CardZone.height);
        shapeRenderer.rect(cardZoneBot2.x, cardZoneBot2.y, CardZone.width, CardZone.height);
        shapeRenderer.rect(cardZoneMid.x, cardZoneMid.y, CardZone.width, CardZone.height);
        shapeRenderer.rect(cardZoneTop.x, cardZoneTop.y, CardZone.width, CardZone.height);

        shapeRenderer.rect(cardZoneHand1.x, cardZoneHand1.y, CardZone.width, CardZone.height);
        shapeRenderer.rect(cardZoneHand2.x, cardZoneHand2.y, CardZone.width, CardZone.height);
        shapeRenderer.rect(cardZoneHand3.x, cardZoneHand3.y, CardZone.width, CardZone.height);
        shapeRenderer.rect(cardZoneHand4.x, cardZoneHand4.y, CardZone.width, CardZone.height);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bmFont = new BitmapFont();

        camera = new OrthographicCamera(800, 800);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);

        shapeRenderer = new ShapeRenderer();

        game = new GameLogic(camera);

        deck = new CardDeck();

        player = new Player(deck.drawCards(2), deck.drawCard(), deck.drawCard(), deck.drawCard());
        bot = new Player(deck.drawCards(2), deck.drawCard(), deck.drawCard(), deck.drawCard());

        phase = Phase.DEAL;

        initializeCardZones();

        Gdx.input.setInputProcessor(game.MyInputProcessor);
    }

    @Override
    public void render() {
        camera.update();

        ScreenUtils.clear(Color.GRAY);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(Color.RED);
        renderCardZones();

        shapeRenderer.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // ask oc for charger back

        bmFont.draw(batch, player.getTopLaneCards(), 200, 200);
        bmFont.draw(batch, player.getBotLaneCards(), 600, 200);


        bmFont.draw(batch, bot.getTopLaneCards(), 200, 600);
        batch.draw(player.getMidLaneCards().peek().getTexture(), 400, 600);
        bmFont.draw(batch, bot.getBotLaneCards(), 600, 600);

        if (phase == Phase.DEAL) {
            game.dealCards(batch, deck, handZones);
        }

        if (phase == Phase.PLACE) {
            // deal

            phase = Phase.JUNGLE;
        }

        if (phase == Phase.JUNGLE) {
            // deal

            phase = Phase.BATTLE;
        }

        if (phase == Phase.BATTLE) {
            // check bot lane
            if (player.getBotLaneValue() > bot.getBotLaneValue()) {
                // do something
            }
            phase = Phase.JUNGLE;
            if (player.getMidLaneValue() > bot.getMidLaneValue()) {
                // do something
            }
            phase = Phase.JUNGLE;
            if (player.getTopLaneValue() > bot.getTopLaneValue()) {
                // do something
            }

            phase = Phase.UPDATE;
        }

        if (phase == Phase.UPDATE) {
            // deal

            phase = Phase.DEAL;
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}

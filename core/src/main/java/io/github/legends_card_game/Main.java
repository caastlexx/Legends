package io.github.legends_card_game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.legends_card_game.card.card_deck.CardDeck;
import io.github.legends_card_game.player.Player;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont bmFont;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Player player;
    private Player bot;
    private CardDeck deck;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bmFont = new BitmapFont();

        camera = new OrthographicCamera(800, 800);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);

        deck = new CardDeck();

        player = new Player(deck.drawCards(2), deck.drawCard(), deck.drawCard());
        bot = new Player(deck.drawCards(2), deck.drawCard(), deck.drawCard());
    }

    @Override
    public void render() {
        camera.update();

        ScreenUtils.clear(Color.GRAY);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bmFont.draw(batch, player.getTopLaneCards(), 200, 200);
        bmFont.draw(batch, player.getMidLaneCards(), 400, 200);
        bmFont.draw(batch, player.getBotLaneCards(), 600, 200);

        bmFont.draw(batch, bot.getTopLaneCards(), 200, 600);
        bmFont.draw(batch, bot.getMidLaneCards(), 400, 600);
        bmFont.draw(batch, bot.getBotLaneCards(), 600, 600);
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

package io.github.legends_card_game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.legends_card_game.player.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont bmFont;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        bmFont = new BitmapFont();

        camera = new OrthographicCamera(800, 800);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);

        player = new Player();
    }

    @Override
    public void render() {
        camera.update();

        ScreenUtils.clear(Color.GRAY);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bmFont.draw(batch, player.getTopLaneCards(), 200, 400);
        bmFont.draw(batch, player.getMidLaneCards(), 400, 400);
        bmFont.draw(batch, player.getBotLaneCards(), 600, 400);
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

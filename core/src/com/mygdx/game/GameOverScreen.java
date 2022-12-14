package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	private static GameOverScreen instancia;
	private final GameVehiculo game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Music music;

	// constructor en privado para seguir el patrón singleton.
	private GameOverScreen(final GameVehiculo game) {
		this.game = game;
		this.batch = game.getBatch();
		this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.music = Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));
	}

	// obtiene la instancia sin necesidad de volver a crear una si ésta ya existe.
	public static GameOverScreen getInstancia(final GameVehiculo game) {
		if (instancia == null)
			instancia = new GameOverScreen(game);
		return instancia;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(255, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		music.setLooping(false);
		music.play();
		music.setVolume(0.40f);
		font.draw(batch, "GAME OVER! ", 100, 200);
		font.draw(batch, "Pulsa ESPACIO para reiniciar.", 100, 100);
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			GameScreen g = new GameScreen(game);
			game.setScreen(g.usarGameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		music.stop();
	}

}

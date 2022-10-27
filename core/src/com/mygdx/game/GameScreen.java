package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final GameVehiculo game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Auto tarro;
	private Lluvia lluvia;
	private Texture background1;
	private Texture background2;
	private float yBG, timeState;
	private int bgSpeed = 500;

	   
	//boolean activo = true;

	public GameScreen(final GameVehiculo game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.wav"));
		  tarro = new Auto(new Texture(Gdx.files.internal("bucket.png")),hurtSound);
         
	      // load the drop sound effect and the rain background "music" 
         Texture gota = new Texture(Gdx.files.internal("drop.png"));
         Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
         
         // carga de fondo
         background1 = new Texture(Gdx.files.internal("backgroundDLC.png"));
         background2 = new Texture(Gdx.files.internal("backgroundDLC.png"));
         yBG = 0;
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        
	     Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
         lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      tarro.crear();
	      
	      // creacion de la lluvia
	      lluvia.crear();
	      
	      timeState = 0f;
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		
		yBG -= bgSpeed * Gdx.graphics.getDeltaTime();
		if (yBG + 600 <= 0) {
			yBG = 0;
		}
		
		batch.begin();
		batch.draw(background1,0,yBG);
		batch.draw(background2,0,yBG+600);
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(tarro)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		timeState+=Gdx.graphics.getDeltaTime();
		if (timeState > 0.1f)
			tarro.sumarPuntos(1);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      tarro.destruir();
      lluvia.destruir();

	}

}

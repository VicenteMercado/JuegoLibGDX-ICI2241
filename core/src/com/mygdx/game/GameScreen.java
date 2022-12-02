package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.obj.Auto;
import com.mygdx.obj.Item;
import com.mygdx.obj.Objeto;
import com.mygdx.obj.Obstaculo;

public class GameScreen implements Screen {
	private final GameVehiculo game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Auto auto;
	private Objeto obstacles;
	private Objeto items;
	private Texture background1;
	private Texture background2;
	private float yBG, timeState;
	private int bgSpeed = 400;
	private Music music;
	
	//Constructor de la clase
	public GameScreen(final GameVehiculo game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        //Se cargan los assets
        cargar();
		  
        //Se inicializa música de fondo, se reproduce y modifica su volúmen.
	    this.music = Gdx.audio.newMusic(Gdx.files.internal("game_music.mp3"));
	    music.setLooping(true);
		music.play();
		music.setVolume(0.40f);
		
	    // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();
	    // creacion del auto
	    auto.crear();
	    
	    // creacion de los obstaculos
	    obstacles.crear();
	    timeState = 0f;
	    
	    //Creación de los items.
	    items.crear();
	}
	
	private void cargar() {
		 //carga la textura del auto y su sonido de daño	     
		 Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.wav"));
		 auto = new Auto(new Texture(Gdx.files.internal("car.png")),hurtSound);
		 
		 //carga las texturas de los obstáculos
		 Texture obstacle1 = new Texture(Gdx.files.internal("obstacle1.png"));
		 Texture obstacle2 = new Texture(Gdx.files.internal("obstacle2.png"));
		 Texture obstacle3 = new Texture(Gdx.files.internal("obstacle3.png"));
		 obstacles = new Obstaculo(obstacle1, obstacle2, obstacle3);
		 
		 //Carga de texturas de items y sonidos de efecto.
		 Texture item1 = new Texture(Gdx.files.internal("item1.jpg"));
		 Texture item2 = new Texture(Gdx.files.internal("item2.png"));
		 Sound soundEscudo = Gdx.audio.newSound(Gdx.files.internal("soundEscudo.ogg"));
		 Sound soundRalent = Gdx.audio.newSound(Gdx.files.internal("soundRalent.mp3"));
		 items = new Item(item1,item2,soundEscudo,soundRalent);
         
         // carga de fondo
         background1 = new Texture(Gdx.files.internal("backgroundDLC.png"));
         background2 = new Texture(Gdx.files.internal("backgroundDLC.png"));
         yBG = 0;
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		
		// se genera el scroll del fondo
		yBG -= bgSpeed * Gdx.graphics.getDeltaTime();
		if (yBG + 600 <= 0) {
			yBG = 0;
		}
	
		batch.begin();
		//se dibujan los fondos
		batch.draw(background1,0,yBG);
		batch.draw(background2,0,yBG+600);
		//dibujar textos
		font.draw(batch, "Puntaje: " + auto.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + auto.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!auto.estaHerido()) {
			// movimiento del tarro desde teclado
	        auto.actualizarMovimiento();
	        items.actualizarMovimiento(auto, obstacles);
			// caida de la lluvia 
	       if (!obstacles.actualizarMovimiento(auto, obstacles)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<auto.getPuntos())
	    		  game.setHigherScore(auto.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual (se aplica patrón singleton)
	    	  GameOverScreen over = GameOverScreen.getInstancia(game);
	    	  game.setScreen(over);
	    	  dispose();
	       }
		}
		
		//Actualizar dibujos de los objetos en pantalla
		auto.dibujar(batch);
		obstacles.actualizarDibujoObjeto(batch);
		items.actualizarDibujoObjeto(batch);
		//ir aumentando puntaje de acuerdo al tiempo
		timeState+=Gdx.graphics.getDeltaTime();
		if (timeState > 0.1f)
			auto.sumarPuntos(1);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con música
	  music.play();
	  music.setVolume(0.40f);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		//pausa del juego
		music.stop();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		//se destruyen cada variable al cerrar el juego
      auto.destruir();
      obstacles.destruir();
      items.destruir();
      music.stop();
	}

}

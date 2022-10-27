package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class Objetos {
	private Array<Rectangle> objectPos; 
	private Array<Integer> objectType;
	private long lastObjectTime;
	private Texture objectTexture;
	private Sound objectSound;
	
	public Objetos(Texture objetoEnCarretera, Sound ss) {
		objectSound = ss;
		objectTexture = objetoEnCarretera;
	}
	
	public void crear(){
		objectPos = new Array<Rectangle>();
		objectType = new Array<Integer>();
		crearObjeto();
		
	}
	
	public abstract void crearObjeto();
	
	public abstract boolean actualizarMovimiento();
	
	public void dispose() {
		objectSound.dispose();
	}
	
	
}

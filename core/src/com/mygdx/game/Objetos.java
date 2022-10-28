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
	private int velY;
	private Texture objectTexture;
	private Sound objectSound;
	
	public void crear(){
		crearObjeto();
		
	}
	
	public abstract void crearObjeto();
	
	public abstract boolean actualizarMovimiento(Auto auto);
	
	public void dispose() {
		objectSound.dispose();
	}

	
	
}

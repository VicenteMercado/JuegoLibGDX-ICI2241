package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class Objetos {
	protected int velY;
	private Texture objectTexture;
	private Sound objectSound;
	
	public abstract void crearObjeto();
	
	public abstract boolean actualizarMovimiento(Auto auto);
	
	public abstract void actualizarDibujoObjeto(SpriteBatch batch);
	
	public abstract void destruir();
}

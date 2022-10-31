package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Objetos {
	protected int velY;
	
	public abstract void crearObjeto();
	
	public abstract boolean actualizarMovimiento(Auto auto, Obstaculos obstacle);
	
	public abstract void actualizarDibujoObjeto(SpriteBatch batch);
	
	public abstract void destruir();
}

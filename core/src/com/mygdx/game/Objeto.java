package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Objeto { //Items y Obstáculos.
	protected int velY; //Velocidad de desplazamiento de un objeto.
	
	public abstract void crearObjeto(); //Crea un objeto en el juego.
	
	public abstract boolean actualizarMovimiento(Auto auto, Obstaculo obstacle); //Actualiza el movimiento del objeto.
	
	public abstract void actualizarDibujoObjeto(SpriteBatch batch); //Dibuja un objeto en la escena.
	
	public abstract void destruir(); //Elimina los archivos de todos los objetos si se cierra el juego.
}

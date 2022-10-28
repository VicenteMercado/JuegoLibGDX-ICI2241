package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class Objetos {
<<<<<<< Updated upstream
	private int velY;
	private Texture objectTexture;
	private Sound objectSound;
	
	public void crear(){
		crearObjeto();
		
	}
=======
	protected int velY;
>>>>>>> Stashed changes
	
	public abstract void crearObjeto();
	
	public abstract boolean actualizarMovimiento(Auto auto);
	
<<<<<<< Updated upstream
	public void dispose() {
		objectSound.dispose();
	}

	
	
=======
	public abstract void destruir();
>>>>>>> Stashed changes
}

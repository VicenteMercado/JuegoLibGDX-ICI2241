package com.mygdx.obj;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ItemType {
	private Texture textura;
	private Sound sound;
	
	public void setTexture(Texture textura) {
		this.textura = textura;
	}
	
	public Texture getTexture() {
		return textura;
	}
	
	public Sound getSound() {
		return sound;
	}
	
	public void setSound(Sound sound) {
		this.sound = sound;
	}
	
}

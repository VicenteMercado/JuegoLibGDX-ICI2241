package com.mygdx.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.itfc.Builder;

public class Director {
	private Builder builder;

	public Director() {

	}

	public void crearEscudo(Builder builder) {
		builder.reset();
		builder.setTextura(new Texture(Gdx.files.internal("item1.jpg")));
		builder.setSonido(Gdx.audio.newSound(Gdx.files.internal("soundEscudo.ogg")));
	}

	public void crearRalentizador(Builder builder) {
		builder.reset();
		builder.setTextura(new Texture(Gdx.files.internal("item2.png")));
		builder.setSonido(Gdx.audio.newSound(Gdx.files.internal("soundRalent.mp3")));
	}
}

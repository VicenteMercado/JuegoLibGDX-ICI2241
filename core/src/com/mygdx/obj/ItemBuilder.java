package com.mygdx.obj;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.itfc.Builder;

public class ItemBuilder implements Builder {
	private TipoDeItem IT;

	public ItemBuilder() {
		this.reset();
	}

	@Override
	public void reset() {
		this.IT = new TipoDeItem();
	}

	@Override
	public void setTextura(Texture tex) {
		IT.setTexture(tex);
	}

	@Override
	public void setSonido(Sound ss) {
		IT.setSound(ss);

	}

	public TipoDeItem getProducto() {
		TipoDeItem producto = this.IT;
		this.reset();
		return producto;
	}

}

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

public class Items extends Objetos{
	private long lastItemTime;
	private Array<Integer> itemType;
	private Array<Rectangle> itemPos;
	private Texture itemEscudo;
	private Sound soundEscudo;
	private Texture itemRalent;
	private Sound soundRalent;
	
	public Items(Texture itemEscudo, Texture itemRalent, Sound soundEscudo, Sound soundRalent) {
		this.itemEscudo = itemEscudo;
		this.itemRalent = itemRalent;
		this.soundEscudo = soundEscudo;
		this.soundRalent = soundRalent;
		velY = 400;
	}
    
	public void crear() {
		itemPos = new Array<Rectangle>();
		itemType = new Array<Integer>();
		crearObjeto();
	}
	
	@Override
	public void crearObjeto() {
		Rectangle item = new Rectangle();
		item.x = MathUtils.random(135,675-64);
		item.y = 480;
		item.width = 64;
		item.height = 64;
		itemPos.add(item);
	
		//Se ve el tipo de item.
		if(MathUtils.random(1,10)<5)
			itemType.add(1); //Escudo
		else
			itemType.add(2); //Ralentizador
		lastItemTime = TimeUtils.nanoTime(); 
	}

	@Override
	public boolean actualizarMovimiento(Auto auto) {
		//Generar un item.
		if(TimeUtils.nanoTime() - lastItemTime > 500000000) 
			crearObjeto();
		
		for(int i = 0; i < itemPos.size; i++) {
			Rectangle item = itemPos.get(i);
			item.y -=velY * Gdx.graphics.getDeltaTime();
			//Si sale de la parte inferior de la pantalla, se elimina.
			if (item.y + 64 < 0) {
				itemPos.removeIndex(i);
				itemType.removeIndex(i);
			}
			//Si choca con el auto...
			if(item.overlaps(auto.getArea())) {
		    	 // Se recolecta el item, se activa y se reproduce su sonido.
				 if(itemType.get(i)==1) { //Modo escudo
					 soundEscudo.play();
			         itemPos.removeIndex(i);
			         itemType.removeIndex(i);
			         auto.setInvencible(5);
				 }
				 else{ //Modo ralentizador
					 soundRalent.play();
			         itemPos.removeIndex(i);
			         itemType.removeIndex(i);
			         
			         //FALTA IMPLEMENTAR EFECTO DEL RALENTIZADOR.
				 }
		    }
		      
		}
		return true;		
	}

	@Override
	public void destruir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarDibujoObjeto(SpriteBatch batch) {
		//se dibuja la textura del item dependiendo de cuál toque
		for (int i = 0; i < itemPos.size; i++) {
			Rectangle item = itemPos.get(i);
			if (itemType.get(i) == 1)
				batch.draw(itemEscudo, item.x, item.y);
			if (itemType.get(i) == 2)
				batch.draw(itemRalent, item.x, item.y);
		}
		
	}
}

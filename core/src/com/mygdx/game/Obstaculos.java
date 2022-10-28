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

public class Obstaculos extends Objetos{
	private Array<Integer> obstaculosType;
	private Array<Rectangle> obstaculosPos;
	private Texture obstacle1, obstacle2, obstacle3;
	
	public Obstaculos(Texture ob1, Texture ob2, Texture ob3){
		velY = 500;
		this.obstacle1 = ob1;
		this.obstacle2 = ob2;
		this.obstacle3 = ob3;
	}
	
	public void crear() {
		obstaculosType = new Array<Integer>();
		obstaculosPos = new Array<Rectangle>();
		this.crearObjeto();
	}

	@Override
	public void crearObjeto() {
		Rectangle obstaculo = new Rectangle();
		obstaculo.x = MathUtils.random(135, 675-64);
		obstaculo.y = 480;
		int tipo = MathUtils.random(1,3);
		if (tipo == 1) {
			obstaculo.width = 45;
			obstaculo.height = 64;
			obstaculosPos.add(obstaculo);
		}
		
		if (tipo == 2) {
			obstaculo.width = 86;
			obstaculo.height = 64;
			obstaculosPos.add(obstaculo);
		}
		
		if (tipo == 3) {
			obstaculo.width = 50;
			obstaculo.height = 50;
			obstaculosPos.add(obstaculo);
		}
		//lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public boolean actualizarMovimiento(Auto auto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destruir() {
		// TODO Auto-generated method stub
		
	}
}

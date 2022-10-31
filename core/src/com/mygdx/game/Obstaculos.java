package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Obstaculos extends Objetos implements SPEEDABLE{
	private Array<Rectangle> obstaculosPos;
	private Array<Integer> obstaculosType;
	private Texture obstacle1, obstacle2, obstacle3;
	private long lastObstacleTime;
	private int estadoVelObs = NORMAL;
	
	//Constructor de la clase
	public Obstaculos(Texture ob1, Texture ob2, Texture ob3){
		velY = 400;
		this.obstacle1 = ob1;
		this.obstacle2 = ob2;
		this.obstacle3 = ob3;
	}
	
	//se inicializan los valores y se crea un nuevo obstáculo
	public void crear() {
		obstaculosPos = new Array<Rectangle>();
		obstaculosType = new Array<Integer>();
		crearObjeto();
	}

	@Override
	public void crearObjeto() {
		Rectangle obstaculo = new Rectangle();
		obstaculo.x = MathUtils.random(135, 675-64);
		obstaculo.y = 480;
		int tipo = MathUtils.random(1,3); //se diferencia entre cada obstáculo
		if (tipo == 1) {
			obstaculo.width = 45;
			obstaculo.height = 64;
			obstaculosPos.add(obstaculo);
			obstaculosType.add(1);
		}
		
		if (tipo == 2) {
			obstaculo.width = 86;
			obstaculo.height = 64;
			obstaculosPos.add(obstaculo);
			obstaculosType.add(2);
		}
		
		if (tipo == 3) {
			obstaculo.width = 50;
			obstaculo.height = 50;
			obstaculosPos.add(obstaculo);
			obstaculosType.add(3);
		}
		
		lastObstacleTime = TimeUtils.nanoTime();
	}

	@Override
	public boolean actualizarMovimiento(Auto auto) {
		//generar obstaculo
		if (TimeUtils.nanoTime() - lastObstacleTime > 500000000) crearObjeto();
		
		// revisar si las gotas cayeron al suelo o chocaron con el tarro
		for (int i = 0; i < obstaculosPos.size; i++) {
			Rectangle obstacle = obstaculosPos.get(i);
			if(estadoVelObs != NORMAL) {
				velY = 200;
			    estadoVelObs -= Gdx.graphics.getDeltaTime();
			}
			else {
			    normalizar();
			    velY = 400;
			}
			obstacle.y -= velY * Gdx.graphics.getDeltaTime();
			//cae al suelo y se elimina
			if (obstacle.y + 64 < 0) {
				obstaculosPos.removeIndex(i);
				obstaculosType.removeIndex(i);
			}
			if (obstacle.overlaps(auto.getArea())) { //si toca la hitbox del auto
				auto.dañar();
				if (auto.getVidas() <= 0)
					return false;
				obstaculosPos.removeIndex(i);
				obstaculosType.removeIndex(i);
			}
		}
		
		return true;
	}
	
	@Override
	public void actualizarDibujoObjeto(SpriteBatch batch) {
		//se dibuja la textura del obstáculo dependiendo de cuál toque
		for (int i = 0; i < obstaculosPos.size; i++) {
			Rectangle obstacle = obstaculosPos.get(i);
			if (obstaculosType.get(i) == 1)
				batch.draw(obstacle1, obstacle.x, obstacle.y);
			if (obstaculosType.get(i) == 2)
				batch.draw(obstacle2, obstacle.x, obstacle.y);
			if (obstaculosType.get(i) == 3)
				batch.draw(obstacle3, obstacle.x, obstacle.y);
		}
	}

	@Override
	public void destruir() {
		// TODO Auto-generated method stub
	}

	@Override
	public void normalizar() {
		estadoVelObs = SPEEDABLE.NORMAL;
		
	}

	@Override
	public void ralentizar() {
		estadoVelObs = SPEEDABLE.LENTO;
	}
}

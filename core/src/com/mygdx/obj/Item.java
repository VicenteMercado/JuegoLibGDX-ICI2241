package com.mygdx.obj;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.itfc.Speedable;

public class Item extends Objeto implements Speedable{
	private long lastItemTime; //Tiempo de creación del item anterior.
	private Array<Integer> itemType; //Arreglo de tipos de item generados.
	private Array<Rectangle> itemPos; //Arreglo de posiciones de items generados.
	private float estadoVelItem = NORMAL; //Verificador de velocidad de desplazamientos de items.
	
	//Constructor
	public Item() {
		velY = 400;
	}
	
	//método que crea el item de acuerdo al entero recibido. sigue el patrón builder
	public TipoDeItem makeItem(int i) {
		TipoDeItem IT = new TipoDeItem();
		Director director = new Director();
		ItemBuilder builder = new ItemBuilder();
		
		if(i==1) {
			director.crearEscudo(builder);
			IT = builder.getProducto();
		}
		if(i==2) {
			director.crearRalentizador(builder);
			IT = builder.getProducto();
		}
		
		return IT;
	}
	
	public float getEstado() { //Retorna el estado de la velocidad de los items.
		return estadoVelItem;
	}
    
	public void crear() { //Se inicializan los items para ser utilizados en GameScreen.
		itemPos = new Array<Rectangle>();
		itemType = new Array<Integer>();
		crearObjeto();
	}
	
	@Override
	public void crearObjeto() { //Función que genera los items dentro del juego.
		Rectangle item = new Rectangle();
		item.x = MathUtils.random(135,675-64); //Posicionamiento en el eje x.
		item.y = 480; //Altura a la cual se genera.
		item.width = 64; 
		item.height = 64; //Largo y ancho del item.
		itemPos.add(item);
	
		//Se ve el tipo de item.
		if(MathUtils.random(1,10)<5)
			itemType.add(1); //Escudo
		else
			itemType.add(2); //Ralentizador
		lastItemTime = TimeUtils.nanoTime(); //Se guarda el tiempo de creación de este item.
	}

	@Override
	public boolean actualizarMovimiento(Auto auto, Objeto o) {
		//Generar un item.
		if(TimeUtils.nanoTime() - lastItemTime > 10000000000.0f) //Tras 10 segundos desde la aparición del último item, se genera el siguiente.
			crearObjeto();
		
		for(int i = 0; i < itemPos.size; i++) {
			Rectangle item = itemPos.get(i); //Se toman los items en pantalla.
			if(estadoVelItem > NORMAL) { //Si el efecto del item Ralentizar está en efecto, se reduce velocidad de desplazamiento del item.
				velY = 200;
			    estadoVelItem -= Gdx.graphics.getDeltaTime(); //Disminuye duración del efecto hasta llegar a 0.
			}
			else { 
			    normalizar();
			    ((Obstaculo) o).normalizar();
			    velY = 400;
			}
			item.y -=velY * Gdx.graphics.getDeltaTime(); //El item va "cayendo" en la pantalla.
			
			//Si sale de la parte inferior de la pantalla, se elimina.
			if (item.y + 64 < 0) {
				itemPos.removeIndex(i);
				itemType.removeIndex(i);
			}
			
			//Si choca con el auto...
			if(item.overlaps(auto.getArea())) {
		    	 // Se recolecta el item, se activa y se reproduce su sonido.
				 if(itemType.get(i)==1) { //Modo escudo
					 TipoDeItem it = makeItem(1);
					 it.getSound().play();
					 //soundEscudo.play();
			         itemPos.removeIndex(i); //Se elimina el item.
			         itemType.removeIndex(i);
			         auto.setInvencible(5); //Se inician 5 segundos de total invencibilidad.
				 }
				 else{ //Modo ralentizador
					 TipoDeItem it = makeItem(2);
					 it.getSound().play();
					 //soundRalent.play();
			         itemPos.removeIndex(i); //Se elimina el item.
			         itemType.removeIndex(i);
			         ((Obstaculo) o).ralentizar(); //Se ralentizan obstáculos.
			         ralentizar(); //Se ralentizan items.
				 }
		    }
		      
		}
		return true;		
	}

	@Override
	public void destruir() {

	}

	@Override
	public void actualizarDibujoObjeto(SpriteBatch batch) {
		//se dibuja la textura del item dependiendo de cuál toque
		for (int i = 0; i < itemPos.size; i++) {
			Rectangle item = itemPos.get(i);
			if (itemType.get(i) == 1) {//Textura de escudo
				TipoDeItem IT = makeItem(1);
				batch.draw(IT.getTexture(), item.x, item.y);
			}
			if (itemType.get(i) == 2) { //Textura de ralentizador
				TipoDeItem IT = makeItem(2);
			    batch.draw(IT.getTexture(), item.x, item.y);
			}
		}
		
	}

	@Override
	public void normalizar() { //Se pone la velocidad de los items a la estándar.
		this.estadoVelItem = Speedable.NORMAL;
	}

	@Override
	public void ralentizar() { //Se ralentiza la velocidad de los items.
		this.estadoVelItem = Speedable.LENTO;
	}
}

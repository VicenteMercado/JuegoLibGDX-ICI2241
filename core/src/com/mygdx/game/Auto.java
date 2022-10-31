package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Auto {
	//Variables de instancia
	   private Rectangle car;
	   private Texture carImage;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 500;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   private float invencible = 0;
	   
	   
	   //Constructor de la clase
	   public Auto(Texture tex, Sound ss) {
		   carImage = tex;
		   sonidoHerido = ss;
	   }
	   	
	   	//Setters y getters para diferentes valores
		public int getVidas() {
			return vidas;
		}
	
		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return car;
		}
		//Sumador de puntos
		public void sumarPuntos(long pp) {
			puntos+=pp;
		}
		
		public float getInvencible() {
			return invencible;
		}
		
		public void setInvencible(float sec) {
			invencible = sec;
			
		}
		
		
		//Se crea el auto en pantalla
		public void crear() {
		      car = new Rectangle();
		      car.x = 800 / 2 - 64 / 2;
		      car.y = 20;
		      car.width = 54;
		      car.height = 96;
		}
		
		public void dañar() {
		  if(invencible <= 0) { //Si el auto no es invencible, si éste se daña pierde una vida y detiene a los objetos por unos momentos
			  vidas--;
			  herido = true;
			  tiempoHerido=tiempoHeridoMax;
			  sonidoHerido.play(0.15f);
		  }
		}
		
		//dibuja al auto
		public void dibujar(SpriteBatch batch) {
		 if(invencible > 0) { //si es invencible, se dibuja el auto de color verde
			 invencible -= Gdx.graphics.getDeltaTime();
			 
			 float f = (System.currentTimeMillis()%100)/100;
			 batch.setColor(f,1-f,f,1.0f);
			 batch.draw(carImage, car.x, car.y);
			 batch.setColor(1f,1f,1f,1.0f);
		 }
		 else {
			 if (!herido)  
				   batch.draw(carImage, car.x, car.y);
				 else { //si el auto está herido se agita por unos momentos
				   batch.draw(carImage, car.x, car.y+ MathUtils.random(-5,5));
				   tiempoHerido--;
				   if (tiempoHerido<=0) herido = false;
				 } 
		 }
		} 
	   
	   
		public void actualizarMovimiento() { 
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) car.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) car.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(car.x < 135) car.x = 135;
		   if(car.x > 675 - 64) car.x = 675 - 64;
		}
	    

		public void destruir() { //se destruyen las variables al terminar el juego
		    carImage.dispose();
		    sonidoHerido.dispose();
		}
	
		public boolean estaHerido() { //validador de si el auto está herido. Utilizado en dañar
		   return herido;
		}
}

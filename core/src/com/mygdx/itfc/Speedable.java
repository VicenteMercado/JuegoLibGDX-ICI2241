package com.mygdx.itfc;

enum Constantes{
	NORMAL(0), LENTO(15);

	Constantes(int i) {
		// TODO Auto-generated constructor stub
	}
}

public interface Speedable { //Interfaz que maneja la velocidad del juego.
  public static final float NORMAL= 0; //Velocidad normal.
  public static final float LENTO = 15; //Velocidad reducida, activada por item Ralentizador.
  
  public void normalizar(); //Establece la velocidad del juego a la estándar.
  
  public void ralentizar(); //Ralentiza la velocidad del juego.
  
}
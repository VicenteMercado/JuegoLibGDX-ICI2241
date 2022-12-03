package com.mygdx.game;

//Clase que sirve como plantilla para crear una nueva GameScreen. Sigue el patrón de comportamiento Template Method
public abstract class PlantillaGameScreen {
	public GameScreen usarGameScreen(GameVehiculo game) {
		GameScreen g = new GameScreen(game); //debido al contexto en que se utiliza, es necesario instanciar un nuevo GameScreen para retornar
		g.cargarAssets(); //se cargan assets
		g.reproducirMusica(); //se reproduce música
		return g;
	}

	public abstract void cargarAssets();

	public abstract void reproducirMusica();
}

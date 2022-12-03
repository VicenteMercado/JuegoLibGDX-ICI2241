package com.mygdx.obj;

import com.mygdx.itfc.Strategy;

public class Velocidad {
	private Strategy strategy;

	public Velocidad(Strategy strat) {
		this.strategy = strat;
	}

	public int executeStrategy() {
		return strategy.operar();
	}

}

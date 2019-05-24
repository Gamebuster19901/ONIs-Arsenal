package com.gamebuster19901.halo.client.item.capability.reticle;

import java.util.concurrent.Callable;

public class ReticleFactory implements Callable<Reticle>{
	public static final ReticleFactory INSTANCE = new ReticleFactory();
	
	@Override
	public Reticle call() throws Exception {
		return new ReticleDefaultImpl();
	}
}

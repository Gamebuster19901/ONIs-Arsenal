package com.gamebuster19901.halo.client.item.capability.overlay;

import java.util.concurrent.Callable;

public class OverlayFactory implements Callable<Overlay>{
	public static final OverlayFactory INSTANCE = new OverlayFactory();

	@Override
	public Overlay call() throws Exception {
		return new OverlayDefaultImpl();
	}
}

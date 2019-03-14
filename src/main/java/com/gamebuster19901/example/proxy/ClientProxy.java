package com.gamebuster19901.example.proxy;

import com.gamebuster19901.example.Main;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends Proxy{
	
	public ClientProxy() {
		super();
		addListener(this::clientSetup);
	}
	
	@SuppressWarnings("unused")
	private void clientSetup(FMLClientSetupEvent e) {
		Main.LOGGER.info("CLIENT SETUP");
	}
	
}

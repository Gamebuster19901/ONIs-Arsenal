package com.gamebuster19901.oni.proxy;

import com.gamebuster19901.oni.Main;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class ServerProxy extends Proxy{
	
	public ServerProxy() {
		super();
		addListener(this::serverSetup);
	}
	
	@SubscribeEvent
	@SuppressWarnings("unused")
	public void serverSetup(FMLServerStartingEvent e) {
		Main.LOGGER.info("SERVER SETUP");
	}
	
}

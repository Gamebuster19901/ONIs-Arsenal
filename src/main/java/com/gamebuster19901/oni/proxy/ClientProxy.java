package com.gamebuster19901.oni.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends Proxy{

	public ClientProxy() {
		super();
		addListener(this::clientSetup);
	}
	
	@SubscribeEvent
	@SuppressWarnings("unused")
	public void clientSetup(FMLClientSetupEvent e){
		MinecraftForge.EVENT_BUS.register(this);
	}

}

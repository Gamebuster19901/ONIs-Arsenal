package com.gamebuster19901.halo.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends Proxy{

	public ClientProxy() {
		super();
		addListener(this::clientSetup);
	}
	
	@SubscribeEvent
	public void clientSetup(FMLClientSetupEvent e){
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void modelRegistryEvent(ModelRegistryEvent e) {
		//ModelLoader.setCustomModelResourceLocation(AssaultRifle.INSTANCE, 0, new ModelResourceLocation(AssaultRifle.INSTANCE.getRegistryName().toString()));
	}
	
}

package com.gamebuster19901.halo.proxy;

import com.gamebuster19901.halo.common.item.weapons.assaultrifle.AssaultRifle;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends Proxy{

	public void preInit(FMLPreInitializationEvent e){
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void init(FMLInitializationEvent e){
		super.init(e);
	}
	
	public void postInit(FMLPostInitializationEvent e){
		super.postInit(e);
	}
	
	@SubscribeEvent
	public void modelRegistryEvent(ModelRegistryEvent e) {
		ModelLoader.setCustomModelResourceLocation(AssaultRifle.INSTANCE, 0, new ModelResourceLocation(AssaultRifle.INSTANCE.getRegistryName().toString()));
	}
	
}

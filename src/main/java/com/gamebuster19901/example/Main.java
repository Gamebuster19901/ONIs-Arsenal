package com.gamebuster19901.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gamebuster19901.example.proxy.Proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION, canBeDeactivated = false, acceptableRemoteVersions = "*")
public class Main {
	public static final String MODID = "yourmodid";
	public static final String MODNAME = "Your Mod Name";
	public static final String VERSION = "Version - MCVersion";
	public static final Logger LOGGER = LogManager.getLogger(MODNAME);
	
	@SidedProxy(clientSide = "com.gamebuster19901.example.proxy.ClientProxy", serverSide = "com.gamebuster19901.example.proxy.ServerProxy")
	public static Proxy proxy;
	private static Main instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		instance = this;
		proxy.preInit(e);
	}
	
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		proxy.postInit(e);
	}
	
	public static Main getInstance(){
		return instance;
	}
}

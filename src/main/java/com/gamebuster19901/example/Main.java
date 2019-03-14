package com.gamebuster19901.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gamebuster19901.example.Main.MODID;
import com.gamebuster19901.example.proxy.*;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;


@Mod(value = MODID)
public class Main {
	public static final String MODID = "yourmodid"; //be sure to replace all instances of "yourmodid" in mods.toml
	public static final String MODNAME = "Your Mod Name";
	public static final String VERSION = "Version - MCVersion";
	public static final Logger LOGGER = LogManager.getLogger(MODNAME);
	
	public static Proxy proxy;
	
	private static Main instance;
	
	{
		proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	}
	
	public static Main getInstance(){
		return instance;
	}
}

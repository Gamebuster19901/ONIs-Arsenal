package com.gamebuster19901.halo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gamebuster19901.halo.proxy.*;
import static com.gamebuster19901.halo.Main.MODID;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(value = MODID)
public class Main {
	public static final String MODID = "halo";
	public static final String MODNAME = "Halo Mod";
	public static final String VERSION = "0.0.0.0 - 1.13.2";
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

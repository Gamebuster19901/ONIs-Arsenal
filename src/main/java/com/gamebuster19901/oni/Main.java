package com.gamebuster19901.oni;

import static com.gamebuster19901.oni.Main.MODID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gamebuster19901.oni.proxy.*;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(value = MODID)
public class Main {
	public static final String MODID = "oni";
	public static final String MODNAME = "Oni's Arsenal";
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

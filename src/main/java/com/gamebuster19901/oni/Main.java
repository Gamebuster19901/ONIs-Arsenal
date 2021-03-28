/*
 * Oni's Arsenal Copyright 2019 - 2020 Gamebuster19901
 * 
 * Halo Copyright Microsoft Corporation
 *
 * Oni's Arsenal was created under Microsoft's
 * "Game Content Usage Rules", using assets
 * based on the Halo universe. Oni's Arsenal
 * is not endorsed by or affiliated with
 * Microsoft.
 * 
 * The Game Content Usage Rules can be found at:
 * 
 * https://www.xbox.com/en-us/developers/rules
 * 
 */

package com.gamebuster19901.oni;

import static com.gamebuster19901.oni.Main.MODID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gamebuster19901.oni.proxy.ClientProxy;
import com.gamebuster19901.oni.proxy.Proxy;
import com.gamebuster19901.oni.proxy.ServerProxy;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = MODID)
public class Main {
	public static final String MODID = "oni";
	public static final String MODNAME = "Oni's Arsenal";
	public static final String VERSION = "0.0.0.0 - 1.14.3";
	public static final Logger LOGGER = LogManager.getLogger(MODNAME);
	
	public static Proxy proxy;
	
	{
		proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
		FMLJavaModLoadingContext.get().getModEventBus().register(proxy);
	}

}

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

package com.gamebuster19901.oni.proxy;

import com.gamebuster19901.oni.Main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class ServerProxy extends Proxy{
	
	public ServerProxy() {
		super();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	@SuppressWarnings("unused")
	public void serverSetup(FMLServerStartingEvent e) {
		Main.LOGGER.info("SERVER SETUP");
	}
	
}

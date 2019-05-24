package com.gamebuster19901.halo.client.render;

import com.gamebuster19901.halo.client.item.capability.reticle.Reticle;
import com.gamebuster19901.halo.client.item.capability.reticle.ReticleDefaultImpl;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderHelper{
	public static final Minecraft mc = Minecraft.getInstance();
	
	@SubscribeEvent
	public static void onRender(RenderGameOverlayEvent.Pre e) {
		if(e.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
			LazyOptional<Reticle> reticleCapability = mc.player.getHeldItemMainhand().getCapability(ReticleDefaultImpl.CAPABILITY);
			if(reticleCapability.isPresent()) {
				reticleCapability.orElseThrow(AssertionError::new).render(e.getPartialTicks(), mc.mainWindow.getScaledWidth(), mc.mainWindow.getScaledHeight());
				e.setCanceled(true);
			}
			return;
		}
	}
}

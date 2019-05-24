package com.gamebuster19901.halo.client.item.capability.reticle;

import com.gamebuster19901.halo.client.render.Renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public interface Reticle extends Renderer{

	public int width();
	
	public int height();
	
	public ResourceLocation getImage();
	
	@Override
	public default void bind() {
		mc.textureManager.bindTexture(getImage());
        GlStateManager.enableBlend();
        GlStateManager.enableAlphaTest();
	}
	
}

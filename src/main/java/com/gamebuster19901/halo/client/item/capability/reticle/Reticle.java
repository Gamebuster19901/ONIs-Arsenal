package com.gamebuster19901.halo.client.item.capability.reticle;

import org.lwjgl.opengl.GL11;

import com.gamebuster19901.halo.client.render.Renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public interface Reticle extends Renderer{

	public int width();
	
	public int height();
	
	public ResourceLocation getImage();
	
	@Override
	public default void bind() {
		GL11.glPushMatrix();
		mc.textureManager.bindTexture(getImage());
		GlStateManager.enableBlend();
		GlStateManager.enableAlphaTest();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	}
	
	@Override
	public default void unbind() {
		GL11.glPopMatrix();
	}
	
}

package com.gamebuster19901.halo.client.render;

import static com.gamebuster19901.halo.Main.MODID;

import net.minecraft.client.Minecraft;

public interface Renderer {
	public static final String DEFAULT_DIRECTORY = MODID + ":textures/gui/default/";
	public static final Minecraft mc = Minecraft.getInstance();
	
	public void render(float partialTicks, int scaledWidth, int scaledHeight);
	
	public void bind();
	
	public void unbind();
}

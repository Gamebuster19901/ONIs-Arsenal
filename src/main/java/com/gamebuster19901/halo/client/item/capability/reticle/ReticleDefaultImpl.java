package com.gamebuster19901.halo.client.item.capability.reticle;

import com.gamebuster19901.halo.common.item.capability.shootable.Shootable;
import com.gamebuster19901.halo.common.item.capability.shootable.ShootableDefaultImpl;

import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class ReticleDefaultImpl extends Gui implements Reticle{

	@CapabilityInject(Reticle.class)
	public static Capability<Reticle> CAPABILITY = null;
	public static final ResourceLocation DEFAULT_RETICLE_IMAGE = new ResourceLocation(DEFAULT_DIRECTORY + "reticle");

	@Override
	public int width() {
		return 11;
	}

	@Override
	public int height() {
		return 11;
	}

	@Override
	public ResourceLocation getImage() {
		return DEFAULT_RETICLE_IMAGE;
	}

	@Override
	public void render(float partialTicks, int scaledWidth, int scaledHeight) {
		GameSettings gameSettings = mc.gameSettings;
		if(gameSettings.thirdPersonView == 0) {
			bind();
			this.drawTexturedModalRect(scaledWidth / 2, scaledHeight / 2, 0, 0, 11, 11);
		}
	}
	
	public void render(ItemStack item, float partialTicks, int scaledWidth, int scaledHeight) {
		render(partialTicks, scaledWidth, scaledHeight);
		int x = scaledWidth / 2;
		int y = scaledHeight / 2;
		LazyOptional<Shootable> shootableCapability = item.getCapability(ShootableDefaultImpl.CAPABILITY);
		if(shootableCapability.isPresent()) {
			Shootable shootable = shootableCapability.orElseThrow(AssertionError::new);
			int bloom = MathHelper.ceil(shootable.getBloom());
			this.drawTexturedModalRect(x + bloom, y, 0, 12, 1, 4);
			this.drawTexturedModalRect(x - bloom, y, 0, 12, 1, 4);
			this.drawTexturedModalRect(x, y + bloom, 12, 0, 4, 1);
			this.drawTexturedModalRect(x, y - bloom, 12, 0, 4, 1);
		}
	}

	@Override
	public void unbind() {
		mc.getTextureManager().bindTexture(Gui.ICONS);
	}
	
}

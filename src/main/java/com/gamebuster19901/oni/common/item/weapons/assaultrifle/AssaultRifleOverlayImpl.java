/*
 * Oni's Arsenal Copyright 2019 Gamebuster19901
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

package com.gamebuster19901.oni.common.item.weapons.assaultrifle;

import static com.gamebuster19901.oni.Main.MODID;

import com.gamebuster19901.guncore.capability.client.item.overlay.Overlay;
import com.gamebuster19901.guncore.capability.common.item.shootable.Shootable;
import com.gamebuster19901.guncore.capability.common.item.shootable.ShootableDefaultImpl;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.common.util.LazyOptional;

public class AssaultRifleOverlayImpl extends AbstractGui implements Overlay{

	private static final ResourceLocation OVERLAY = new ResourceLocation(MODID + ":/textures/item/assault_rifle/overlay.png");

	@Override
	public void render(float partialTicks, int scaledWidth, int scaledHeight) {
		
	}

	@Override
	public void render(ItemStack item, float partialTicks, int scaledWidth, int scaledHeight) {
		GameSettings gameSettings = mc.gameSettings;
		if(gameSettings.thirdPersonView == 0) {
			bind();
			
			GlStateManager.scaled(0.5, 0.5, 0.5);
			
			render(partialTicks, scaledWidth, scaledHeight);
			
			LazyOptional<Shootable> shootableCapability = item.getCapability(ShootableDefaultImpl.CAPABILITY);
			AssaultRifleImpl impl = (AssaultRifleImpl) shootableCapability.orElseThrow(AssertionError::new);
			
			int ammoCount = impl.getAmountInMagazine();
			int magSize = impl.getMagazineSize();
			
			int gunIconX = (scaledWidth - 115) * 2 - 10;
			int gunIconY = 10;
			
			this.blit(gunIconX, gunIconY, this.blitOffset, 0, 8, 228, 57, width(), height());
			
			GlStateManager.scaled(2, 2, 2);
			
			int bulletX = gunIconX / 2 + (25 / 2);
			int bulletY = 67 / 2;
			
			int bulletWidth = 4;
			int bulletHeight = 7;
			
			int bulletTexX;
			int bulletTexY = 0;
			
			for(int total = 0, bullet = 0, row = 0; total < magSize || total < ammoCount; total++, bullet++, row = total / 18, bullet = total % 18) {
				if(total < ammoCount) {
					if(total < magSize) {
						bulletTexX = 0;
					}
					else {
						bulletTexX = bulletWidth * 2;
					}
				}
				else {
					bulletTexX = bulletWidth;
				}
				this.blit(bulletX + (bullet * (bulletWidth + 1)), 5 + bulletY + (bulletHeight + 1) * row, this.blitOffset, bulletTexX, bulletTexY, bulletWidth, bulletHeight, width(), height());
			}
			
			GlStateManager.scaled(2, 2, 2);
			
			int stringWidth = mc.fontRenderer.getStringWidth(impl.getAmountInMagazine() + "");
			mc.fontRenderer.drawString(impl.getAmountInMagazine() + "", gunIconX / 4 - stringWidth + 4, 19, 0x6063FF);
			
			GlStateManager.scaled(0.25, 0.25, 0.25);
			
			unbind();
		}
	}

	@Override
	public void render(Pre e, ItemStack item, float partialTicks, int scaledWidth, int scaledHeight) {
		if(e.getType() == ElementType.VIGNETTE) {
			render(item, partialTicks, scaledWidth, scaledHeight);
		}
	}

	@Override
	public int width() {
		return 256;
	}


	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 256;
	}

	@Override
	public void bind() {
		GlStateManager.pushMatrix();
		mc.textureManager.bindTexture(getImage());
		GlStateManager.enableAlphaTest();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	}
	
	@Override
	public void unbind() {
		GlStateManager.disableAlphaTest();
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getImage() {
		return OVERLAY;
	}

}

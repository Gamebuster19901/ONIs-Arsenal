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

package com.gamebuster19901.oni.common.item.weapons.assaultrifle;

import static com.gamebuster19901.oni.Main.MODID;

import com.gamebuster19901.guncore.capability.client.item.reticle.Reticle;
import com.gamebuster19901.guncore.capability.common.item.shootable.Shootable;
import com.gamebuster19901.guncore.capability.common.item.shootable.ShootableDefaultImpl;

import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.common.util.LazyOptional;

public class AssaultRifleReticleImpl extends AbstractGui implements Reticle{

	private static final ResourceLocation RETICLE = new ResourceLocation(MODID + ":/textures/item/assault_rifle/reticle.png");
	
	@Override
	public void render(float partialTicks, int scaledWidth, int scaledHeight) {
		
	}

	@Override
	public void render(ItemStack item, float partialTicks, int scaledWidth, int scaledHeight) {
		GameSettings gameSettings = mc.gameSettings;
		if(gameSettings.thirdPersonView == 0) {
			bind();
			render(partialTicks, scaledWidth, scaledHeight);
			render(partialTicks, scaledWidth, scaledHeight);
			int x = scaledWidth / 2 - 16;
			int y = scaledHeight / 2 - 16;
			LazyOptional<Shootable> shootableCapability = item.getCapability(ShootableDefaultImpl.CAPABILITY);
			Shootable shootable = shootableCapability.orElseThrow(AssertionError::new);
			int bloom = MathHelper.ceil(shootable.getBloom());
			
			//this.blit(xpos, ypos, blitOffset(unused), minX, minY, maxX, maxY, texWidth, texHeight);
			
			//this.blit(x, y, this.blitOffset, 0, 0, 32, 32, width(), height());
			
			this.blit(x - bloom, y - bloom, this.blitOffset, 0, 0, 14, 14, width(), height());
			this.blit(x + 16 + bloom + 3, y - bloom, this.blitOffset, 19, 0, 14, 14, width(), height());
			this.blit(x - bloom, y + 16 + bloom + 3, this.blitOffset, 0, 19, 14, 14, width(), height());
			this.blit(x + 16 + bloom + 3, y + 16 + bloom + 3, this.blitOffset, 19, 19, 14, 14, width(), height());
			
			this.blit(x + 15, y + bloom * 2 + 17, this.blitOffset, 15, 9, 2, 6, width(), height());
			this.blit(x + 15, y - bloom * 2 + 9, this.blitOffset, 15, 9, 2, 6, width(), height());
			
			this.blit(x + 9 - bloom * 2, y + 15, this.blitOffset, 9, 15, 6, 2, width(), height());
			this.blit(x + 17 + bloom * 2, y + 15, this.blitOffset, 9, 15, 6, 2, width(), height());
			
			unbind();
		}
	}

	@Override
	public void render(Pre e, ItemStack item, float partialTicks, int scaledWidth, int scaledHeight) {
		if(e.getType() == ElementType.CROSSHAIRS) {
			render(item, partialTicks, scaledWidth, scaledHeight);
		}
	}

	@Override
	public ResourceLocation getImage() {
		return RETICLE;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 32;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return 32;
	}

}

package com.gamebuster19901.halo.capability.client.item.overlay;

import java.awt.Color;

import com.gamebuster19901.halo.capability.common.item.reloadable.Reloadable;
import com.gamebuster19901.halo.capability.common.item.reloadable.ReloadableDefaultImpl;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class OverlayDefaultImpl implements Overlay{

	@CapabilityInject(Overlay.class)
	public static Capability<Overlay> CAPABILITY;
	
	@Override
	public void render(float partialTicks, int scaledWidth, int scaledHeight) {
		bind();
	}

	@Override
	public void render(ItemStack stack, float partialTicks, int scaledWidth, int scaledHeight) {
		render(partialTicks, scaledWidth, scaledHeight);
		FontRenderer renderer = mc.fontRenderer;
		Reloadable reloadable = stack.getCapability(ReloadableDefaultImpl.CAPABILITY).orElseThrow(AssertionError::new);
		String text = reloadable.getAmountInMagazine() + "/" + reloadable.getMagazineSize() + " " + reloadable.getAmmoType().getIcon().getString();
		int width = renderer.getStringWidth(text);
		int height = 8;
		renderer.drawStringWithShadow(text, scaledWidth - width - 2, 0 + height, Color.WHITE.hashCode());
		unbind();
	}

	@Override
	public void bind() {}

	@Override
	public void unbind() {
	}

	@Override
	public void render(Pre e, ItemStack stack, float partialTicks, int scaledWidth, int scaledHeight) {
		if(e.getType() == ElementType.CHAT) {
			render(stack, partialTicks, scaledWidth, scaledHeight);
		}
	}

}

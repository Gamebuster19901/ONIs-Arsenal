package com.gamebuster19901.halo.client.render;

import java.lang.reflect.Field;

import com.gamebuster19901.halo.client.item.capability.reticle.Reticle;
import com.gamebuster19901.halo.client.item.capability.reticle.ReticleDefaultImpl;
import com.gamebuster19901.halo.common.item.abstracts.HeldWeapon;
import com.gamebuster19901.halo.common.util.ForgeReflectionHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderHelper{
	public static final Minecraft mc = Minecraft.getInstance();
	public static final Field MAIN_HAND_PREV = ForgeReflectionHelper.findField(FirstPersonRenderer.class, "prevEquippedProgressMainHand");
	public static final Field MAIN_HAND = ForgeReflectionHelper.findField(FirstPersonRenderer.class, "equippedProgressMainHand");
	public static final Field OFF_HAND_PREV = ForgeReflectionHelper.findField(FirstPersonRenderer.class, "prevEquippedProgressMainHand");
	public static final Field OFF_HAND = ForgeReflectionHelper.findField(FirstPersonRenderer.class, "equippedProgressOffHand");
	
	@SubscribeEvent
	public static void onRender(RenderGameOverlayEvent.Pre e) {
		if(e.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
			ItemStack stack = mc.player.getHeldItemMainhand();
			LazyOptional<Reticle> reticleCapability = stack.getCapability(ReticleDefaultImpl.CAPABILITY);
			if(reticleCapability.isPresent()) {
				reticleCapability.orElseThrow(AssertionError::new).render(stack, e.getPartialTicks(), mc.mainWindow.getScaledWidth(), mc.mainWindow.getScaledHeight());
				e.setCanceled(true);
			}
			return;
		}
	}
	
	@SubscribeEvent
	public static void onHandRender(RenderSpecificHandEvent e) {
		EnumHand hand = e.getHand();
		if(mc.player.getHeldItem(hand).getItem() instanceof HeldWeapon) {
			e.setCanceled(true);
			mc.getFirstPersonRenderer().renderItemInFirstPerson(mc.player, e.getPartialTicks(), e.getInterpolatedPitch(), hand, e.getSwingProgress(), e.getItemStack(), 0f);
		}
	}
}

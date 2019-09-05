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

package com.gamebuster19901.oni.proxy;

import static com.gamebuster19901.oni.Main.MODID;

import java.util.HashSet;
import com.gamebuster19901.guncore.common.item.abstracts.Ammo;
import com.gamebuster19901.guncore.common.util.EasyLocalization;
import com.gamebuster19901.oni.common.entity.AssaultRifleBullet;
import com.gamebuster19901.oni.common.entity.PlasmaPistolBullet;
import com.gamebuster19901.oni.common.item.weapons.ammo.AssaultRifleAmmo;
import com.gamebuster19901.oni.common.item.weapons.assaultrifle.AssaultRifle;
import com.gamebuster19901.oni.common.item.weapons.plasmapistol.PlasmaPistol;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

public abstract class Proxy extends com.gamebuster19901.guncore.proxy.Proxy{
	
	HashSet<Ammo> registeredAmmo = new HashSet<Ammo>();
	HashSet<Class<? extends Entity>> registeredProjectile = new HashSet<Class<? extends Entity>>();
	
	public Proxy() {

	}
	
	@SubscribeEvent
	public void setup(FMLCommonSetupEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	protected static IEventBus getBus() {
		return FMLJavaModLoadingContext.get().getModEventBus();
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(AssaultRifle.INSTANCE);
		event.getRegistry().register(PlasmaPistol.INSTANCE);
		registerAmmo(event);
	}
	
	@ObjectHolder("oni:assault_rifle_bullet")
	public static EntityType<AssaultRifleBullet> assaultRifleBullet;
	@ObjectHolder("oni:plasma_pistol_bullet")
	public static EntityType<PlasmaPistolBullet> plasmaPistolBullet;
	
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		IForgeRegistry registry = event.getRegistry();
		HashSet<IForgeRegistryEntry> entries = new HashSet<IForgeRegistryEntry>();
		
		ResourceLocation ar = EasyLocalization.getResourceLocation(MODID, AssaultRifleBullet.class);
		registry.register(EntityType.Builder
			.create(AssaultRifleBullet::new, EntityClassification.MISC)
			.size(1f / 16,  1f / 16)
			.setTrackingRange(200)
			.setUpdateInterval(1)
			.setShouldReceiveVelocityUpdates(false)
			.setCustomClientFactory((spawnEntity, world) -> new AssaultRifleBullet(assaultRifleBullet, world))
			.build(ar.toString())
			.setRegistryName(ar));
		
		ResourceLocation pp = EasyLocalization.getResourceLocation(MODID, PlasmaPistolBullet.class);
		registry.register(EntityType.Builder
			.create(PlasmaPistolBullet::new, EntityClassification.MISC)
			.size(2f / 16, 1f / 16)
			.setTrackingRange(200)
			.setUpdateInterval(1)
			.setShouldReceiveVelocityUpdates(false)
			.setCustomClientFactory((spawnEntity, world) -> new PlasmaPistolBullet(plasmaPistolBullet, world))
			.build(pp.toString())
			.setRegistryName(pp));
		
		registry.registerAll(entries.toArray(new IForgeRegistryEntry[]{}));
	}
	
	private void registerAmmo(RegistryEvent.Register<Item> event) {
		registerAmmo(event, AssaultRifleAmmo.INSTANCE);
	}
	
	public void registerAmmo(RegistryEvent.Register<Item> event, Ammo ammo) {
		registeredAmmo.add(ammo);
		event.getRegistry().register(ammo);
	}
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().registerAll(
			AssaultRifleBullet.shootingSound = new SoundEvent(EasyLocalization.getResourceLocation(MODID, AssaultRifleBullet.class)).setRegistryName(EasyLocalization.getResourceLocation(MODID, AssaultRifleBullet.class)),
			PlasmaPistolBullet.shootingSound = new SoundEvent(EasyLocalization.getResourceLocation(MODID, PlasmaPistolBullet.class)).setRegistryName(EasyLocalization.getResourceLocation(MODID, PlasmaPistolBullet.class))
		);
	}
	
}

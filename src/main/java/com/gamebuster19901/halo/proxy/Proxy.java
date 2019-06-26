package com.gamebuster19901.halo.proxy;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;

import com.gamebuster19901.guncore.common.item.abstracts.Ammo;
import com.gamebuster19901.guncore.common.util.EasyLocalization;

import static com.gamebuster19901.halo.Main.MODID;
import com.gamebuster19901.halo.common.entity.AssaultRifleBullet;
import com.gamebuster19901.halo.common.entity.PlasmaPistolBullet;
import com.gamebuster19901.halo.common.item.weapons.ammo.AssaultRifleAmmo;
import com.gamebuster19901.halo.common.item.weapons.assaultrifle.AssaultRifle;
import com.gamebuster19901.halo.common.item.weapons.plasmapistol.PlasmaPistol;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

public abstract class Proxy extends com.gamebuster19901.guncore.proxy.Proxy{
	
	HashSet<Ammo> registeredAmmo = new HashSet<Ammo>();
	HashSet<Class<? extends Entity>> registeredProjectile = new HashSet<Class<? extends Entity>>();
	
	public Proxy() {
		getBus().register(this);
	}
	
	@SubscribeEvent
	public void setup(FMLCommonSetupEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	protected static IEventBus getBus() {
		return FMLJavaModLoadingContext.get().getModEventBus();
	}
	
	protected <T extends Event> void addListener(Consumer<T> consumer) {
		getBus().addListener(consumer);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(AssaultRifle.INSTANCE);
		event.getRegistry().register(PlasmaPistol.INSTANCE);
		registerAmmo(event);
	}
	
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		IForgeRegistry<EntityType<?>> registry = event.getRegistry();
		registry.register(AssaultRifleBullet.TYPE = registerEntity(AssaultRifleBullet.class, AssaultRifleBullet::new));
		registry.register(PlasmaPistolBullet.TYPE = registerEntity(PlasmaPistolBullet.class, PlasmaPistolBullet::new));
	}
	
	private EntityType<Entity> registerEntity(Class<? extends Entity> entityClass, Function<? super World, ? extends Entity> constructor) {
		ResourceLocation location = EasyLocalization.getResourceLocation(MODID, entityClass);
		return (EntityType<Entity>) EntityType.Builder.create(entityClass, constructor).tracker(200, 1, false).build(location.toString()).setRegistryName(location);
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

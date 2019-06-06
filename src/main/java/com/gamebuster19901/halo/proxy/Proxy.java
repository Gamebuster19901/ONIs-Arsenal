package com.gamebuster19901.halo.proxy;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;

import com.gamebuster19901.halo.client.item.capability.overlay.Overlay;
import com.gamebuster19901.halo.client.item.capability.overlay.OverlayFactory;
import com.gamebuster19901.halo.client.item.capability.overlay.OverlayStorage;
import com.gamebuster19901.halo.client.item.capability.reticle.Reticle;
import com.gamebuster19901.halo.client.item.capability.reticle.ReticleFactory;
import com.gamebuster19901.halo.client.item.capability.reticle.ReticleStorage;
import com.gamebuster19901.halo.common.entity.AssaultRifleBullet;
import com.gamebuster19901.halo.common.entity.capability.shooterOwner.ShooterOwner;
import com.gamebuster19901.halo.common.entity.capability.shooterOwner.ShooterOwnerFactory;
import com.gamebuster19901.halo.common.entity.capability.shooterOwner.ShooterOwnerStorage;
import com.gamebuster19901.halo.common.item.NullAmmo;
import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.abstracts.Projectile;
import com.gamebuster19901.halo.common.item.capability.reloadable.Reloadable;
import com.gamebuster19901.halo.common.item.capability.reloadable.ReloadableFactory;
import com.gamebuster19901.halo.common.item.capability.reloadable.ReloadableStorage;
import com.gamebuster19901.halo.common.item.capability.shootable.Shootable;
import com.gamebuster19901.halo.common.item.capability.shootable.ShootableFactory;
import com.gamebuster19901.halo.common.item.capability.shootable.ShootableStorage;
import com.gamebuster19901.halo.common.item.capability.weapon.Weapon;
import com.gamebuster19901.halo.common.item.capability.weapon.WeaponFactory;
import com.gamebuster19901.halo.common.item.capability.weapon.WeaponStorage;
import com.gamebuster19901.halo.common.item.weapons.ammo.AssaultRifleAmmo;
import com.gamebuster19901.halo.common.item.weapons.assaultrifle.AssaultRifle;
import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class Proxy {
	
	HashSet<Ammo> registeredAmmo = new HashSet<Ammo>();
	HashSet<Class<? extends Entity>> registeredProjectile = new HashSet<Class<? extends Entity>>();
	
	public Proxy() {
		getBus().register(this);
	}
	
	@SubscribeEvent
	@SuppressWarnings("unused")
	public void setup(FMLCommonSetupEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(Projectile.class);
		CapabilityManager.INSTANCE.register(Weapon.class, new WeaponStorage(), new WeaponFactory());
		CapabilityManager.INSTANCE.register(Shootable.class, new ShootableStorage(), new ShootableFactory());
		CapabilityManager.INSTANCE.register(Reloadable.class,  new ReloadableStorage(),  new ReloadableFactory());
		CapabilityManager.INSTANCE.register(ShooterOwner.class, new ShooterOwnerStorage(), new ShooterOwnerFactory());
		CapabilityManager.INSTANCE.register(Reticle.class, new ReticleStorage(), new ReticleFactory());
		CapabilityManager.INSTANCE.register(Overlay.class, new OverlayStorage(), new OverlayFactory());
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
		registerAmmo(event);
	}
	
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(AssaultRifleBullet.TYPE = registerEntity(AssaultRifleBullet.class, AssaultRifleBullet::new));
	}
	
	private EntityType<Entity> registerEntity(Class<? extends Entity> entityClass, Function<? super World, ? extends Entity> constructor) {
		ResourceLocation location = EasyLocalization.getResourceLocation(entityClass);
		return (EntityType<Entity>) EntityType.Builder.create(entityClass, constructor).tracker(200, 1, false).build(location.toString()).setRegistryName(location);
	}
	
	private void registerAmmo(RegistryEvent.Register<Item> event) {
		registerAmmo(event, NullAmmo.INSTANCE);
		registerAmmo(event, AssaultRifleAmmo.INSTANCE);
	}
	
	public void registerAmmo(RegistryEvent.Register<Item> event, Ammo ammo) {
		registeredAmmo.add(ammo);
		event.getRegistry().register(ammo);
	}
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().registerAll(
			AssaultRifleBullet.shootingSound = new SoundEvent(EasyLocalization.getResourceLocation(AssaultRifleBullet.class)).setRegistryName(EasyLocalization.getResourceLocation(AssaultRifleBullet.class))
		);
	}
	
}

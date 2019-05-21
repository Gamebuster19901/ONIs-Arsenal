package com.gamebuster19901.halo.proxy;

import java.util.HashSet;
import java.util.function.Consumer;

import com.gamebuster19901.halo.common.entity.AssaultRifleBullet;
import com.gamebuster19901.halo.common.item.NullAmmo;
import com.gamebuster19901.halo.common.item.abstracts.Ammo;
import com.gamebuster19901.halo.common.item.abstracts.Projectile;
import com.gamebuster19901.halo.common.item.capability.Reloadable;
import com.gamebuster19901.halo.common.item.capability.ReloadableFactory;
import com.gamebuster19901.halo.common.item.capability.ReloadableStorage;
import com.gamebuster19901.halo.common.item.capability.Shootable;
import com.gamebuster19901.halo.common.item.capability.ShootableFactory;
import com.gamebuster19901.halo.common.item.capability.ShootableStorage;
import com.gamebuster19901.halo.common.item.capability.ShooterOwner;
import com.gamebuster19901.halo.common.item.capability.ShooterOwnerFactory;
import com.gamebuster19901.halo.common.item.capability.ShooterOwnerStorage;
import com.gamebuster19901.halo.common.item.capability.Weapon;
import com.gamebuster19901.halo.common.item.capability.WeaponFactory;
import com.gamebuster19901.halo.common.item.capability.WeaponStorage;
import com.gamebuster19901.halo.common.item.weapons.ammo.AssaultRifleAmmo;
import com.gamebuster19901.halo.common.item.weapons.assaultrifle.AssaultRifle;
import com.gamebuster19901.halo.common.util.EasyLocalization;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
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
	@SuppressWarnings("unused")
	public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		AssaultRifleBullet.TYPE = registerEntity(AssaultRifleBullet.class);
	}
	
	private int networkID = 0;
	private EntityType<Entity> registerEntity(Class<? extends Entity> entityClass) {
		return EntityType.register(EasyLocalization.getEZTranslationKey(entityClass), EntityType.Builder.create(entityClass, AssaultRifleBullet::new).tracker(200, networkID++, true));
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

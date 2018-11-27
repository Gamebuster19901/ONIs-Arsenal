package com.gamebuster19901.halo.proxy;

import java.util.HashSet;

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
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public abstract class Proxy {
	
	HashSet<Ammo> registeredAmmo = new HashSet<Ammo>();
	HashSet<Class<? extends Entity>> registeredProjectile = new HashSet<Class<? extends Entity>>();
	
	@SuppressWarnings("unused")
	public void preInit(FMLPreInitializationEvent e){
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(Projectile.class);
		CapabilityManager.INSTANCE.register(Weapon.class, new WeaponStorage(), new WeaponFactory());
		CapabilityManager.INSTANCE.register(Shootable.class, new ShootableStorage(), new ShootableFactory());
		CapabilityManager.INSTANCE.register(Reloadable.class,  new ReloadableStorage(),  new ReloadableFactory());
		CapabilityManager.INSTANCE.register(ShooterOwner.class, new ShooterOwnerStorage(), new ShooterOwnerFactory());
	}
	
	
	@SuppressWarnings("unused")
	public void init(FMLInitializationEvent e){
		
	}
	
	@SuppressWarnings("unused")
	public void postInit(FMLPostInitializationEvent e){
		
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(AssaultRifle.INSTANCE);
		registerAmmo(event);
	}
	
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll(
			registerEntity(AssaultRifleBullet.class).build()
		);
	}
	
	private int networkID = 0;
	private EntityEntryBuilder registerEntity(Class<? extends Entity> entityClass) {
		return EntityEntryBuilder.create()
			.id(EasyLocalization.getResourceLocation(entityClass), ++networkID)
			.name(EasyLocalization.getEZTranslationKey(entityClass))
			.entity(entityClass)
			.tracker(200, 1, true);
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
	public void soundRegistryEvent(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().registerAll(
			AssaultRifleBullet.sound = new SoundEvent(EasyLocalization.getResourceLocation(AssaultRifleBullet.class)).setRegistryName(EasyLocalization.getResourceLocation(AssaultRifleBullet.class))
		);
	}
	
}

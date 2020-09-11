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

package com.gamebuster19901.oni.common.item.weapons.plasmapistol;

import com.gamebuster19901.guncore.capability.client.item.overlay.OverlayDefaultImpl;
import com.gamebuster19901.guncore.capability.client.item.reticle.ReticleDefaultImpl;
import com.gamebuster19901.guncore.capability.common.energy.EnergyDefaultImpl;
import com.gamebuster19901.guncore.capability.common.heat.OverheatDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.shootable.ShootableDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.weapon.WeaponDefaultImpl;
import com.gamebuster19901.oni.common.item.OniWeapon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlasmaPistol extends OniWeapon{

	public static final PlasmaPistol INSTANCE = new PlasmaPistol();
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).isPresent()) {
			PlasmaPistolImpl impl = (PlasmaPistolImpl) stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);
			if(impl.canFire(playerIn)) {
				impl.fire(playerIn);
			}
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return new ICapabilitySerializable<CompoundNBT>() {

			public final PlasmaPistolImpl impl = (PlasmaPistolImpl) getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);
			
			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
				if(capability == WeaponDefaultImpl.CAPABILITY || capability == ShootableDefaultImpl.CAPABILITY || capability == EnergyDefaultImpl.CAPABILITY || capability == OverheatDefaultImpl.CAPABILITY || capability == ReticleDefaultImpl.CAPABILITY || capability == OverlayDefaultImpl.CAPABILITY) {
					return (LazyOptional<T>) LazyOptional.of(this::getImpl);
				}
				return LazyOptional.empty();
			}
			
			private PlasmaPistolImpl getImpl() {
				if(impl != null) {
					return impl;
				}
				return new PlasmaPistolImpl();
			}

			@Override
			public CompoundNBT serializeNBT() {
				return impl.serializeNBT();
			}

			@Override
			public void deserializeNBT(CompoundNBT nbt) {
				impl.deserializeNBT(nbt);
			}
			
		};
	}

}

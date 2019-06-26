package com.gamebuster19901.halo.common.item.weapons.plasmapistol;

import com.gamebuster19901.guncore.capability.client.item.overlay.OverlayDefaultImpl;
import com.gamebuster19901.guncore.capability.client.item.reticle.ReticleDefaultImpl;
import com.gamebuster19901.guncore.capability.common.energy.EnergyDefaultImpl;
import com.gamebuster19901.guncore.capability.common.heat.OverheatDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.shootable.ShootableDefaultImpl;
import com.gamebuster19901.guncore.capability.common.item.weapon.WeaponDefaultImpl;

import com.gamebuster19901.halo.common.item.HaloWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlasmaPistol extends HaloWeapon{

	public static final PlasmaPistol INSTANCE = new PlasmaPistol();
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, world, entityIn, itemSlot, isSelected);
		PlasmaPistolImpl impl = (PlasmaPistolImpl) stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);
		if(isSelected) {
			impl.addBloom((float) MathHelper.clamp(Math.max(Math.abs(entityIn.motionX), Math.abs(entityIn.motionZ)) * 4, 0, impl.getMaxBloom() / 2));
			if(!entityIn.onGround && (entityIn.getLowestRidingEntity() instanceof EntityPlayer || entityIn.getLowestRidingEntity() instanceof EntityLiving)) {
				if(entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					if(!player.isCreative()) {
						return;
					}
				}
				impl.addBloom(impl.getBloomDecreasePerTick() * 4);
			}
			impl.addTemp(-impl.getTempDecrease());
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).isPresent()) {
			PlasmaPistolImpl impl = (PlasmaPistolImpl) stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);
			if(impl.canFire(playerIn)) {
				impl.fire(playerIn);
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilitySerializable<NBTTagCompound>() {

			public final PlasmaPistolImpl impl = (PlasmaPistolImpl) getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);
			
			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing side) {
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
			public NBTTagCompound serializeNBT() {
				return impl.serializeNBT();
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				impl.deserializeNBT(nbt);
			}
			
		};
	}

}

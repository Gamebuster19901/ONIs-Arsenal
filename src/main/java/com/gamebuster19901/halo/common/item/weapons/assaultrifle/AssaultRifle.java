package com.gamebuster19901.halo.common.item.weapons.assaultrifle;

import com.gamebuster19901.halo.common.item.abstracts.GunReloadable;
import com.gamebuster19901.halo.common.item.capability.ICapabilityProviderSerializeable;
import com.gamebuster19901.halo.common.item.capability.ReloadableDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.ReloadableStorage;
import com.gamebuster19901.halo.common.item.capability.ShootableDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.ShootableStorage;
import com.gamebuster19901.halo.common.item.capability.WeaponDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.WeaponStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class AssaultRifle extends GunReloadable{

	public static final AssaultRifle INSTANCE = new AssaultRifle();
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(stack.hasCapability(WeaponDefaultImpl.CAPABILITY, null)) {
			AssaultRifleImpl impl = (AssaultRifleImpl) stack.getCapability(WeaponDefaultImpl.CAPABILITY, null);
			if(impl.canFire(playerIn)) {
				impl.fire(playerIn);
			}
			else {
				impl.reload(playerIn.inventory);
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		
		ICapabilityProvider sup = super.initCapabilities(stack, nbt);
		
		return new ICapabilityProviderSerializeable<NBTTagCompound>(){
			
			public final AssaultRifleImpl impl = new AssaultRifleImpl();
			
			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				if (capability == WeaponDefaultImpl.CAPABILITY || capability == ShootableDefaultImpl.CAPABILITY || capability == ReloadableDefaultImpl.CAPABILITY) {
					return true;
				}
				return sup.hasCapability(capability, facing);
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				if(capability == WeaponDefaultImpl.CAPABILITY || capability == ShootableDefaultImpl.CAPABILITY || capability == ReloadableDefaultImpl.CAPABILITY) {
					return (T) impl;
				}
				return sup.getCapability(capability, facing);
			}

			@Override
			public NBTTagCompound serializeNBT() {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setTag("weapon", WeaponStorage.INSTANCE.writeNBT(WeaponDefaultImpl.CAPABILITY, impl, null));
				nbt.setTag("shootable", ShootableStorage.INSTANCE.writeNBT(ShootableDefaultImpl.CAPABILITY, impl, null));
				nbt.setTag("reloadable", ReloadableStorage.INSTANCE.writeNBT(ReloadableDefaultImpl.CAPABILITY, impl, null));
				return nbt;
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				NBTTagCompound weapon = nbt.getCompoundTag("weapon");
				NBTTagCompound shootable = nbt.getCompoundTag("shootable");
				NBTTagCompound reloadable = nbt.getCompoundTag("reloadable");
				WeaponStorage.INSTANCE.readNBT(WeaponDefaultImpl.CAPABILITY, impl, null, weapon);
				ShootableStorage.INSTANCE.readNBT(ShootableDefaultImpl.CAPABILITY, impl, null, shootable);
				ReloadableStorage.INSTANCE.readNBT(ReloadableDefaultImpl.CAPABILITY, impl, null, reloadable);
			}
		};
	}
}

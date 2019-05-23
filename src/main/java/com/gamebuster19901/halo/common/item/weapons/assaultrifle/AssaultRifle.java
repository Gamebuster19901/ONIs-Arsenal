package com.gamebuster19901.halo.common.item.weapons.assaultrifle;

import com.gamebuster19901.halo.common.item.abstracts.GunReloadable;
import com.gamebuster19901.halo.common.item.capability.ICapabilityProviderSerializeable;
import com.gamebuster19901.halo.common.item.capability.reloadable.ReloadableDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.reloadable.ReloadableStorage;
import com.gamebuster19901.halo.common.item.capability.shootable.ShootableDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.shootable.ShootableStorage;
import com.gamebuster19901.halo.common.item.capability.weapon.WeaponDefaultImpl;
import com.gamebuster19901.halo.common.item.capability.weapon.WeaponStorage;

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
import net.minecraftforge.common.util.LazyOptional;

public class AssaultRifle extends GunReloadable{

	public static final AssaultRifle INSTANCE = new AssaultRifle();
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).isPresent()) {
			AssaultRifleImpl impl = (AssaultRifleImpl) stack.getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);
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
		return new ICapabilityProviderSerializeable<NBTTagCompound>(){
			
			public final AssaultRifleImpl impl = (AssaultRifleImpl) getCapability(WeaponDefaultImpl.CAPABILITY, null).orElseThrow(AssertionError::new);

			@Override
			public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
				if(capability == WeaponDefaultImpl.CAPABILITY || capability == ShootableDefaultImpl.CAPABILITY || capability == ReloadableDefaultImpl.CAPABILITY) {
					return (LazyOptional<T>) LazyOptional.of(this::getImpl);
				}
				return LazyOptional.empty();
			}
			
			private AssaultRifleImpl getImpl() {
				if(impl != null) {
					return impl;
				}
				return new AssaultRifleImpl();
			}

			@Override
			public NBTTagCompound serializeNBT() {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.put("weapon", WeaponStorage.INSTANCE.writeNBT(WeaponDefaultImpl.CAPABILITY, impl, null));
				nbt.put("shootable", ShootableStorage.INSTANCE.writeNBT(ShootableDefaultImpl.CAPABILITY, impl, null));
				nbt.put("reloadable", ReloadableStorage.INSTANCE.writeNBT(ReloadableDefaultImpl.CAPABILITY, impl, null));
				return nbt;
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				NBTTagCompound weapon = nbt.getCompound("weapon");
				NBTTagCompound shootable = nbt.getCompound("shootable");
				NBTTagCompound reloadable = nbt.getCompound("reloadable");
				WeaponStorage.INSTANCE.readNBT(WeaponDefaultImpl.CAPABILITY, impl, null, weapon);
				ShootableStorage.INSTANCE.readNBT(ShootableDefaultImpl.CAPABILITY, impl, null, shootable);
				ReloadableStorage.INSTANCE.readNBT(ReloadableDefaultImpl.CAPABILITY, impl, null, reloadable);
			}
		};
	}
}

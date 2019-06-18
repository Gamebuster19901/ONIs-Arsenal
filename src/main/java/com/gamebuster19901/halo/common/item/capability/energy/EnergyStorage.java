package com.gamebuster19901.halo.common.item.capability.energy;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EnergyStorage implements IStorage<Energy>{

	@Override
	public INBTBase writeNBT(Capability<Energy> capability, Energy instance, EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readNBT(Capability<Energy> capability, Energy instance, EnumFacing side, INBTBase nbt) {
		// TODO Auto-generated method stub
		
	}

}

package com.gamebuster19901.halo.common.item.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface ICapabilityProviderSerializeable<T extends NBTBase> extends ICapabilityProvider, ICapabilitySerializable<T>{}

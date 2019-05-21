package com.gamebuster19901.halo.common.item.capability;

import net.minecraft.nbt.INBTBase;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface ICapabilityProviderSerializeable<T extends INBTBase> extends ICapabilityProvider, ICapabilitySerializable<T>{}

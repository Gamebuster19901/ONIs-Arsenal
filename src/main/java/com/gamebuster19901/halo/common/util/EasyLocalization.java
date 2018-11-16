package com.gamebuster19901.halo.common.util;

import com.gamebuster19901.halo.Main;

import net.minecraft.util.ResourceLocation;

public interface EasyLocalization<T extends Object> {
	public default ResourceLocation getResourceLocation() {
		return new ResourceLocation(Main.MODID + ':' + toSnakeCase(this.getClass().getSimpleName()));
	}
	
	public default String getEZUnlocalizedName() {
		return getResourceLocation().toString().replace(':', '.');
	}
	
	public static ResourceLocation getResourceLocation(Class clazz) {
		return new ResourceLocation(Main.MODID + ':' + toSnakeCase(clazz.getSimpleName()));
	}
	
	public static String toSnakeCase(String s) {
		s = s.replaceAll("([A-Z])", "_$1").toLowerCase();
		return s.substring(1);
	}
}

package com.gamebuster19901.halo.common.util;

import com.gamebuster19901.halo.Main;

import net.minecraft.util.ResourceLocation;

public interface EasyLocalization<T extends Object> {
	public default ResourceLocation getResourceLocation() {
		checkClass(this.getClass());
		return new ResourceLocation(Main.MODID + ':' + toSnakeCase(this.getClass().getSimpleName()));
	}
	
	public default String getEZTranslationKey() {
		checkClass(this.getClass());
		return getResourceLocation().toString().replace(':', '.');
	}
	
	public static ResourceLocation getResourceLocation(Class clazz) {
		checkClass(clazz);
		return new ResourceLocation(Main.MODID + ':' + toSnakeCase(clazz.getSimpleName()));
	}
	
	public static String getEZTranslationKey(Class clazz) {
		checkClass(clazz);
		return getResourceLocation(clazz).toString().replace(':', '.');
	}
	
	public static String toSnakeCase(String s) {
		s = s.replaceAll("([A-Z])", "_$1").toLowerCase();
		return s.substring(1);
	}
	
	public static void checkClass(Class clazz) {
		if(!clazz.getCanonicalName().contains("com.gamebuster19901.halo")) {
			throw new UnsupportedOperationException("Halo EZLocalization for internal use only. Use your own namespace!!");
		}
	}
}

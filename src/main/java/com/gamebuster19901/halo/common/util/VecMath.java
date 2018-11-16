package com.gamebuster19901.halo.common.util;

import net.minecraft.util.math.Vec3d;

public class VecMath {
	public static Vec3d traverse(Vec3d pos, Vec3d dir, float distance) {
		return new Vec3d(pos.x + (dir.x * distance), pos.y + (dir.y * distance), pos.z + (dir.z * distance));
	}
}

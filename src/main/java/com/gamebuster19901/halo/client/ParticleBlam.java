package com.gamebuster19901.halo.client;

import org.lwjgl.opengl.GL11;

import com.gamebuster19901.halo.Main;
import com.gamebuster19901.halo.common.util.VecMath;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleBlam extends Particle{

	private float redInside = 1f;
	private float greenInside = 1f;
	private float blueInside = 1f;
	
	private float redOutside = 1f;
	private float greenOutside = 1f;
	private float blueOutside = 1f;
	
	public static final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation(Main.MODID + ":textures/particle/blam");
	
	public ParticleBlam(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.particleMaxAge = 2;
	}
	
	public ParticleBlam(EntityPlayer player, float redInside, float greenInside, float blueInside, float redOutside, float greenOutside, float blueOutside) {
		this(player.world, getParticleLocation(player).x, getParticleLocation(player).y, getParticleLocation(player).z, 0,0,0);
		this.redInside = redInside;
		this.blueInside = blueInside;
		this.greenInside = greenInside;
		this.redOutside = redOutside;
		this.blueOutside = blueOutside;
		this.greenOutside = greenOutside;
	}
	
	@Override
	public void renderParticle(BufferBuilder worldRenderer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		GlStateManager.pushMatrix();
		GlStateManager.depthFunc(GL11.GL_ALWAYS);
		GlStateManager.disableLighting();
		RenderHelper.disableStandardItemLighting();
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(PARTICLE_TEXTURE);
	}
	
	private static Vec3d getParticleLocation(EntityPlayer player) {
		return VecMath.traverse(player.getPositionEyes(1f), player.getLook(1f), 1.5f);
	}

}

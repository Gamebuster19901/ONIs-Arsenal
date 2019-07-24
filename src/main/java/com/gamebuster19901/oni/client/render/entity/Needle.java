/*
 * Oni's Arsenal Copyright 2019 Gamebuster19901
 * 
 * Halo Copyright Microsoft Corporation
 *
 * Oni's Arsenal was created under Microsoft's
 * "Game Content Usage Rules", using assets
 * based on the Halo universe. Oni's Arsenal
 * is not endorsed by or affiliated with
 * Microsoft.
 * 
 * The Game Content Usage Rules can be found at:
 * 
 * https://www.xbox.com/en-us/developers/rules
 * 
 */

//Made with Blockbench

package com.gamebuster19901.oni.client.render.entity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class Needle extends EntityModel {
	private final RendererModel bottomSouth;
	private final RendererModel bottomNorth;
	private final RendererModel bottomEast;
	private final RendererModel bottomWest;
	private final RendererModel bottom;
	private final RendererModel top;
	private final RendererModel topWest;
	private final RendererModel topEast;
	private final RendererModel topSouth;
	private final RendererModel topNorth;

	public Needle() {
		textureWidth = 16;
		textureHeight = 16;
		
		bottom = new RendererModel(this);
		bottom.setRotationPoint(0.0F, 24.0F, 0.0F);

		top = new RendererModel(this);
		top.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(top, 3.1416F, 0.0F, 0.0F);

		bottomSouth = new RendererModel(this);
		bottomSouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bottomSouth, -0.0873F, 0.0F, 0.0F);
		bottom.addChild(bottomSouth);
		bottomSouth.cubeList.add(new ModelBox(bottomSouth, 0, 0, -0.1F, -1.0F, -0.1F, 0, 1, 0, 0.0F, false));

		bottomNorth = new RendererModel(this);
		bottomNorth.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bottomNorth, 0.0873F, 0.0F, 0.0F);
		bottom.addChild(bottomNorth);
		bottomNorth.cubeList.add(new ModelBox(bottomNorth, 0, 0, -0.1F, -1.0F, -0.1F, 0, 1, 0, 0.0F, false));

		bottomEast = new RendererModel(this);
		bottomEast.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bottomEast, 0.0F, 0.0F, -0.0873F);
		bottom.addChild(bottomEast);
		bottomEast.cubeList.add(new ModelBox(bottomEast, 0, 0, -0.1F, -1.0F, -0.1F, 0, 1, 0, 0.0F, false));

		bottomWest = new RendererModel(this);
		bottomWest.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bottomWest, 0.0F, 0.0F, 0.0873F);
		bottom.addChild(bottomWest);
		bottomWest.cubeList.add(new ModelBox(bottomWest, 0, 0, -0.1F, -1.0F, -0.1F, 0, 1, 0, 0.0F, false));

		topWest = new RendererModel(this);
		topWest.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(topWest, 0.0F, 0.0F, 0.0873F);
		top.addChild(topWest);
		topWest.cubeList.add(new ModelBox(topWest, 0, 0, 0.1069F, 1.3644F, -0.1F, 0, 1, 0, 0.0F, false));

		topEast = new RendererModel(this);
		topEast.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(topEast, 0.0F, 0.0F, -0.0873F);
		top.addChild(topEast);
		topEast.cubeList.add(new ModelBox(topEast, 0, 0, -0.3069F, 1.3644F, -0.1F, 0, 1, 0, 0.0F, false));

		topSouth = new RendererModel(this);
		topSouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(topSouth, 0.0873F, 0.0F, 0.0F);
		top.addChild(topSouth);
		topSouth.cubeList.add(new ModelBox(topSouth, 0, 0, -0.1F, 1.3644F, -0.3069F, 0, 1, 0, 0.0F, false));

		topNorth = new RendererModel(this);
		topNorth.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(topNorth, -0.0873F, 0.0F, 0.0F);
		top.addChild(topNorth);
		topNorth.cubeList.add(new ModelBox(topNorth, 0, 0, -0.1F, 1.3644F, 0.1069F, 0, 1, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bottom.render(f5);
		top.render(f5);
	}
	
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
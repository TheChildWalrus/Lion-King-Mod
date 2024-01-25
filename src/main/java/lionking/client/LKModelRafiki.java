package lionking.client;

import lionking.common.LKIngame;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class LKModelRafiki extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer head;
	private final ModelRenderer hair;
	private final ModelRenderer hat;

	public LKModelRafiki() {
		body = new ModelRenderer(this, 16, 16).setTextureSize(64, 64);
		body.addBox(0.0F, 0.0F, 0.0F, 8, 12, 4);
		body.setRotationPoint(-4.0F, 1.0F, -2.825F);
		setRotation(body, 0.3F, 0.0F, 0.0F);

		rightarm = new ModelRenderer(this, 40, 17).setTextureSize(64, 64);
		rightarm.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3);
		rightarm.setRotationPoint(-7.0F, 2.0F, -1.5F);

		leftarm = new ModelRenderer(this, 40, 17).setTextureSize(64, 64);
		leftarm.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3);
		leftarm.setRotationPoint(4.0F, 2.0F, -1.5F);

		rightleg = new ModelRenderer(this, 0, 16).setTextureSize(64, 64);
		rightleg.addBox(0.0F, 0.0F, 0.0F, 4, 13, 4);
		rightleg.setRotationPoint(-4.1F, 11.0F, 0.0F);

		leftleg = new ModelRenderer(this, 0, 16).setTextureSize(64, 64);
		leftleg.addBox(0.0F, 0.0F, 0.0F, 4, 13, 4);
		leftleg.setRotationPoint(0.1F, 11.0F, 0.0F);

		tail1 = new ModelRenderer(this, 0, 58).setTextureSize(64, 64);
		tail1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
		tail1.setRotationPoint(-0.5F, 11.0F, 3.0F);
		setRotation(tail1, 1.0F, 0.0F, 0.0F);

		tail2 = new ModelRenderer(this, 12, 59).setTextureSize(64, 64);
		tail2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		tail2.setRotationPoint(-0.5F, 7.0F, 6.0F);

		tail3 = new ModelRenderer(this, 22, 60).setTextureSize(64, 64);
		tail3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
		tail3.setRotationPoint(-0.5F, 7.0F, 10.2F);
		setRotation(tail3, -1.0F, 0.0F, 0.0F);

		tail4 = new ModelRenderer(this, 30, 56).setTextureSize(64, 64);
		tail4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
		tail4.setRotationPoint(-0.5F, 9.5F, 11.7F);
		setRotation(tail4, -2.0F, 0.0F, 0.0F);

		head = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		head.addBox(-3.5F, -1.0F, -5.0F, 7, 7, 6);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);

		hair = new ModelRenderer(this, 28, 0).setTextureSize(64, 64);
		hair.addBox(-5.0F, -2.0F, -3.0F, 10, 10, 5);
		hair.setRotationPoint(0.0F, 0.0F, 0.0F);

		hat = new ModelRenderer(this, 0, 33).setTextureSize(64, 64);
		hat.addBox(-4.0F, -10.0F, -3.1F, 8, 9, 1);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		tail3.render(f5);
		tail4.render(f5);
		head.render(f5);
		hair.render(f5);
		if (LKIngame.isChristmas()) {
			hat.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		head.rotateAngleY = f3 / 57.29578F;
		head.rotateAngleX = f4 / 57.29578F;
		hair.rotateAngleY = f3 / 57.29578F;
		hair.rotateAngleX = f4 / 57.29578F + -11.0F / 180.0F * (float) Math.PI;
		hat.rotateAngleY = f3 / 57.29578F;
		hat.rotateAngleX = f4 / 57.29578F;

		rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
		leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		rightarm.rotateAngleZ = 0.0F;
		leftarm.rotateAngleZ = 0.0F;
		rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		rightleg.rotateAngleY = 0.0F;
		leftleg.rotateAngleY = 0.0F;

		rightarm.rotateAngleY = 0.0F;
		leftarm.rotateAngleY = 0.0F;

		rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	}

	public ModelRenderer getRightArm() {
		return rightarm;
	}
}

package lionking.client;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LKEntityCustomFX extends Entity {
	private final int particleTextureIndex;
	private final boolean glow;
	private int particleAge;
	private final int particleMaxAge;
	private float particleScale;
	private float particleGravity;
	private final float particleScaleOverTime;

	public LKEntityCustomFX(World world, int texture, int age, boolean flag, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world);
		setSize(0.2F, 0.2F);
		yOffset = height / 2.0F;
		setPosition(d, d1, d2);
		motionX = 0.009999999776482582D + d3;
		motionY = 0.109999999776482582D + d4;
		motionZ = 0.009999999776482582D + d5;
		particleScale = (world.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
		particleScaleOverTime = particleScale;
		particleMaxAge = age;
		particleAge = 0;
		particleTextureIndex = texture;
		glow = flag;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge) {
			setDead();
		}

		moveEntity(motionX, motionY, motionZ);

		if (posY == prevPosY) {
			motionX *= 1.1D;
			motionZ *= 1.1D;
		}

		motionX *= 0.8600000143051147D;
		motionY *= 0.8600000143051147D;
		motionZ *= 0.8600000143051147D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}

	@Override
	public int getBrightnessForRender(float f) {
		if (glow) {
			float f1 = (particleAge + f) / particleMaxAge;

			if (f1 < 0.0F) {
				f1 = 0.0F;
			}

			if (f1 > 1.0F) {
				f1 = 1.0F;
			}

			int i = super.getBrightnessForRender(f);
			int j = i & 255;
			int k = i >> 16 & 255;
			j += (int) (f1 * 15.0F * 16.0F);

			if (j > 240) {
				j = 240;
			}

			return j | k << 16;
		}
		return super.getBrightnessForRender(f);
	}

	@Override
	public float getBrightness(float f) {
		if (glow) {
			float f1 = (particleAge + f) / particleMaxAge;

			if (f1 < 0.0F) {
				f1 = 0.0F;
			}

			if (f1 > 1.0F) {
				f1 = 1.0F;
			}

			float f2 = super.getBrightness(f);
			return f2 * f1 + (1.0F - f1);
		}
		return super.getBrightness(f);
	}

	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
		float f6 = (particleAge + f) / particleMaxAge * 32.0F;

		if (f6 < 0.0F) {
			f6 = 0.0F;
		}

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		particleScale = particleScaleOverTime * f6;

		float f7 = (particleTextureIndex % 16) / 16.0F;
		float f8 = f7 + 0.0624375F;
		float f9 = (float) particleTextureIndex / 16 / 16.0F;
		float f10 = f9 + 0.0624375F;
		float f11 = 0.1F * particleScale;
		float f12 = (float) (prevPosX + (posX - prevPosX) * f - EntityFX.interpPosX);
		float f13 = (float) (prevPosY + (posY - prevPosY) * f - EntityFX.interpPosY);
		float f14 = (float) (prevPosZ + (posZ - prevPosZ) * f - EntityFX.interpPosZ);
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tessellator.addVertexWithUV(f12 - f1 * f11 - f4 * f11, f13 - f2 * f11, f14 - f3 * f11 - f5 * f11, f8, f10);
		tessellator.addVertexWithUV(f12 - f1 * f11 + f4 * f11, f13 + f2 * f11, f14 - f3 * f11 + f5 * f11, f8, f9);
		tessellator.addVertexWithUV(f12 + f1 * f11 + f4 * f11, f13 + f2 * f11, f14 + f3 * f11 + f5 * f11, f7, f9);
		tessellator.addVertexWithUV(f12 + f1 * f11 - f4 * f11, f13 - f2 * f11, f14 + f3 * f11 - f5 * f11, f7, f10);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
	}

	@Override
	public boolean canAttackWithItem() {
		return false;
	}
}

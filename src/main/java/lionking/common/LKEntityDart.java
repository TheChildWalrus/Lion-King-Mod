package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.List;

public abstract class LKEntityDart extends Entity {
	private boolean doesArrowBelongToPlayer;
	public int arrowShake;
	protected Entity shootingEntity;
	public boolean silverFired;
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int inData;
	private boolean inGround;
	private int ticksInGround;
	private int ticksInAir;

	protected LKEntityDart(World world) {
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		doesArrowBelongToPlayer = true;
		arrowShake = 0;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
	}

	protected LKEntityDart(World world, double d, double d1, double d2) {
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		doesArrowBelongToPlayer = true;
		arrowShake = 0;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
		setPosition(d, d1, d2);
		yOffset = 0.0F;
	}

	protected LKEntityDart(World world, EntityLivingBase entityliving, float f, boolean flag) {
		super(world);
		silverFired = flag;
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		doesArrowBelongToPlayer = true;
		arrowShake = 0;
		ticksInAir = 0;
		shootingEntity = entityliving;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		posX -= MathHelper.cos(rotationYaw / 180.0F * 3.141593F) * 0.16F;
		posY -= 0.10000000149011612D;
		posZ -= MathHelper.sin(rotationYaw / 180.0F * 3.141593F) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin(rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(rotationPitch / 180.0F * 3.141593F);
		motionZ = MathHelper.cos(rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(rotationPitch / 180.0F * 3.141593F);
		motionY = -MathHelper.sin(rotationPitch / 180.0F * 3.141593F);
		setDartHeading(motionX, motionY, motionZ, f * 1.5F, 1.0F);
	}

	@Override
	protected void entityInit() {
	}

	public void setDartHeading(double d, double d1, double d2, float f, float f1) {
		double d3 = d;
		double d11 = d1;
		double d21 = d2;
		float f2 = MathHelper.sqrt_double(d3 * d3 + d11 * d11 + d21 * d21);
		d3 /= f2;
		d11 /= f2;
		d21 /= f2;
		d3 += worldObj.rand.nextGaussian() * 0.0074999998323619366D * f1;
		d11 += worldObj.rand.nextGaussian() * 0.0074999998323619366D * f1;
		d21 += worldObj.rand.nextGaussian() * 0.0074999998323619366D * f1;
		d3 *= f;
		d11 *= f;
		d21 *= f;
		motionX = d3;
		motionY = d11;
		motionZ = d21;
		float f3 = MathHelper.sqrt_double(d3 * d3 + d21 * d21);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(d3, d21) * 180.0D / 3.1415927410125732D);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(d11, f3) * 180.0D / 3.1415927410125732D);
		ticksInGround = 0;
	}

	@Override
	public void setVelocity(double d, double d1, double d2) {
		motionX = d;
		motionY = d1;
		motionZ = d2;
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			rotationYaw = (float) (Math.atan2(d, d2) * 180.0D / 3.1415927410125732D);
			rotationPitch = (float) (Math.atan2(d1, f) * 180.0D / 3.1415927410125732D);
			prevRotationPitch = rotationPitch;
			prevRotationYaw = rotationYaw;
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (isBurning()) {
			setDead();
		}
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float) (Math.atan2(motionY, f) * 180.0D / 3.1415927410125732D);
		}
		int i = worldObj.getBlockId(xTile, yTile, zTile);
		if (i > 0) {
			Block.blocksList[i].setBlockBoundsBasedOnState(worldObj, xTile, yTile, zTile);
			AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(worldObj, xTile, yTile, zTile);
			if (axisalignedbb != null && axisalignedbb.isVecInside(worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ))) {
				inGround = true;
			}
		}
		if (arrowShake > 0) {
			arrowShake--;
		}
		if (inGround) {
			int j = worldObj.getBlockId(xTile, yTile, zTile);
			int k = worldObj.getBlockMetadata(xTile, yTile, zTile);

			if (j == inTile && k == inData) {
				inGroundEvent();
				ticksInGround++;

				if (ticksInGround == 1200) {
					setDead();
				}
			} else {
				inGround = false;
				motionX *= worldObj.rand.nextFloat() * 0.2F;
				motionY *= worldObj.rand.nextFloat() * 0.2F;
				motionZ *= worldObj.rand.nextFloat() * 0.2F;
				ticksInGround = 0;
				ticksInAir = 0;
			}
		} else {
			ticksInAir++;
			Vec3 vec3d = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
			Vec3 vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);
			MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
			vec3d = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
			vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);
			if (movingobjectposition != null) {
				vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}
			Entity entity = null;
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
			double d = 0.0D;
			for (Object o : list) {
				Entity entity1 = (Entity) o;
				if (!entity1.canBeCollidedWith() || entity1 == shootingEntity && ticksInAir < 5) {
					continue;
				}
				float f5 = 0.3F;
				AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f5, f5, f5);
				MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);
				if (movingobjectposition1 == null) {
					continue;
				}
				double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
				if (d1 < d || d == 0.0D) {
					entity = entity1;
					d = d1;
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}
			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null && !(movingobjectposition.entityHit instanceof EntityPlayer)) {
					float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					int j1 = getDamage();
					if (silverFired) {
						j1 += worldObj.rand.nextInt(2) + 1;
					}
					if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), j1)) {
						if (movingobjectposition.entityHit instanceof EntityLiving) {
							EntityLivingBase hitEntity = (EntityLivingBase) movingobjectposition.entityHit;
							onHitEntity(hitEntity);
						}
						worldObj.playSoundAtEntity(this, "random.drr", 1.0F, 1.2F / (worldObj.rand.nextFloat() * 0.2F + 0.9F));
						setDead();
					} else {
						motionX *= -0.10000000149011612D;
						motionY *= -0.10000000149011612D;
						motionZ *= -0.10000000149011612D;
						rotationYaw += 180.0F;
						prevRotationYaw += 180.0F;
						ticksInAir = 0;
					}
				} else {
					xTile = movingobjectposition.blockX;
					yTile = movingobjectposition.blockY;
					zTile = movingobjectposition.blockZ;
					inTile = worldObj.getBlockId(xTile, yTile, zTile);
					inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
					motionX = (float) (movingobjectposition.hitVec.xCoord - posX);
					motionY = (float) (movingobjectposition.hitVec.yCoord - posY);
					motionZ = (float) (movingobjectposition.hitVec.zCoord - posZ);
					float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					posX -= motionX / f2 * 0.05000000074505806D;
					posY -= motionY / f2 * 0.05000000074505806D;
					posZ -= motionZ / f2 * 0.05000000074505806D;
					worldObj.playSoundAtEntity(this, "random.drr", 1.0F, 1.2F / (worldObj.rand.nextFloat() * 0.2F + 0.9F));
					inGround = true;
					arrowShake = 7;
				}
			}
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / 3.1415927410125732D);
			for (rotationPitch = (float) (Math.atan2(motionY, f3) * 180.0D / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F) {
			}
			for (; rotationPitch - prevRotationPitch >= 180.0F; prevRotationPitch += 360.0F) {
			}
			for (; rotationYaw - prevRotationYaw < -180.0F; prevRotationYaw -= 360.0F) {
			}
			for (; rotationYaw - prevRotationYaw >= 180.0F; prevRotationYaw += 360.0F) {
			}
			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
			float f4 = 0.99F;
			float f6 = getSpeedReduction();
			if (silverFired) {
				f6 /= 2.5F;
			}
			if (isInWater()) {
				for (int k1 = 0; k1 < 4; k1++) {
					float f7 = 0.25F;
					worldObj.spawnParticle("bubble", posX - motionX * f7, posY - motionY * f7, posZ - motionZ * f7, motionX, motionY, motionZ);
				}
				f4 = 0.8F;
			}
			motionX *= f4;
			motionY *= f4;
			motionZ *= f4;
			motionY -= f6;
			setPosition(posX, posY, posZ);
			spawnParticles();
			doBlockCollisions();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("xTile", (short) xTile);
		nbttagcompound.setShort("yTile", (short) yTile);
		nbttagcompound.setShort("zTile", (short) zTile);
		nbttagcompound.setByte("inTile", (byte) inTile);
		nbttagcompound.setByte("inData", (byte) inData);
		nbttagcompound.setByte("shake", (byte) arrowShake);
		nbttagcompound.setByte("inGround", (byte) (inGround ? 1 : 0));
		nbttagcompound.setBoolean("player", doesArrowBelongToPlayer);
		nbttagcompound.setBoolean("silver", silverFired);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		xTile = nbttagcompound.getShort("xTile");
		yTile = nbttagcompound.getShort("yTile");
		zTile = nbttagcompound.getShort("zTile");
		inTile = nbttagcompound.getByte("inTile") & 0xff;
		inData = nbttagcompound.getByte("inData") & 0xff;
		arrowShake = nbttagcompound.getByte("shake") & 0xff;
		inGround = nbttagcompound.getByte("inGround") == 1;
		doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
		silverFired = nbttagcompound.getBoolean("silver");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (worldObj.isRemote) {
			return;
		}
		if (inGround && doesArrowBelongToPlayer && arrowShake <= 0) {
			EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, getDartItem());
			entityitem.delayBeforeCanPickup = 0;
			worldObj.spawnEntityInWorld(entityitem);
			setDead();
		}
	}

	@Override
	public float getShadowSize() {
		return 0.0F;
	}

	protected abstract ItemStack getDartItem();

	protected abstract int getDamage();

	protected abstract void onHitEntity(Entity hitEntity);

	protected abstract void spawnParticles();

	protected abstract float getSpeedReduction();

	protected void inGroundEvent() {
	}
}

package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.potion.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.List;
import java.util.Random;

public abstract class LKEntitySpear extends Entity {
	private boolean doesArrowBelongToPlayer;
	public int arrowShake;
	private Entity shootingEntity;
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int inData;
	private boolean inGround;
	private int ticksInGround;
	private int ticksInAir;
	private int spearDamage;
	private boolean fished;
	private final Random rand = new Random();

	protected LKEntitySpear(World world) {
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

	protected LKEntitySpear(World world, double d, double d1, double d2, int damage) {
		super(world);
		spearDamage = damage;
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

	protected LKEntitySpear(World world, EntityLivingBase entityliving, float f, int damage) {
		super(world);
		spearDamage = damage;
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
		setArrowHeading(motionX, motionY, motionZ, f * 1.5F, 1.0F);
	}

	@Override
	protected void entityInit() {
	}

	private void setArrowHeading(double d, double d1, double d2, float f, float f1) {
		double d3 = d;
		double d11 = d1;
		double d21 = d2;
		float f2 = MathHelper.sqrt_double(d3 * d3 + d11 * d11 + d21 * d21);
		d3 /= f2;
		d11 /= f2;
		d21 /= f2;
		d3 += rand.nextGaussian() * 0.0074999998323619366D * f1;
		d11 += rand.nextGaussian() * 0.0074999998323619366D * f1;
		d21 += rand.nextGaussian() * 0.0074999998323619366D * f1;
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
				ticksInGround++;

				if (ticksInGround == 6000) {
					setDead();
				}
				if (spearDamage == 160) {
					setDead();
				}
			} else {
				inGround = false;
				motionX *= rand.nextFloat() * 0.2F;
				motionY *= rand.nextFloat() * 0.2F;
				motionZ *= rand.nextFloat() * 0.2F;
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
					int j1 = isPoisoned() ? 5 : 7;
					j1 += rand.nextInt(4);
					if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), j1)) {
						if (movingobjectposition.entityHit instanceof EntityLivingBase) {
							if (isPoisoned() && rand.nextInt(4) != 0) {
								((EntityLivingBase) movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, (rand.nextInt(4) + 3) * 20, 0));
							}
						}
						worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
						onCollideWithLiving(movingobjectposition.entityHit);
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
					worldObj.playSoundAtEntity(this, "random.drr", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
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
			float f6 = 0.05F;
			if (isInWater()) {
				for (int k1 = 0; k1 < 4; k1++) {
					float f7 = 0.25F;
					worldObj.spawnParticle("bubble", posX - motionX * f7, posY - motionY * f7, posZ - motionZ * f7, motionX, motionY, motionZ);
				}
				f4 = 0.8F;
				if (!worldObj.isRemote && shootingEntity != null && !isPoisoned() && !fished && rand.nextInt(16) == 0) {
					EntityItem fish = new EntityItem(worldObj, posX, posY, posZ, new ItemStack(Item.fishRaw));
					double d1 = shootingEntity.posX - posX;
					double d2 = shootingEntity.posY - posY;
					double d3 = shootingEntity.posZ - posZ;
					double d4 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
					fish.motionX = d1 * 0.1D;
					fish.motionY = d2 * 0.1D + MathHelper.sqrt_double(d4) * 0.08D;
					fish.motionZ = d3 * 0.1D;
					worldObj.spawnEntityInWorld(fish);
					shootingEntity.worldObj.spawnEntityInWorld(new EntityXPOrb(shootingEntity.worldObj, shootingEntity.posX, shootingEntity.posY + 0.5D, shootingEntity.posZ + 0.5D, rand.nextInt(3) + 1));
					fished = true;
				}
			}
			motionX *= f4;
			motionY *= f4;
			motionZ *= f4;
			motionY -= f6;
			setPosition(posX, posY, posZ);
			for (int i1 = 0; i1 < 4; i1++) {
				worldObj.spawnParticle("crit", posX + motionX * i1 / 4.0D, posY + motionY * i1 / 4.0D, posZ + motionZ * i1 / 4.0D, -motionX, -motionY + 0.20000000000000001D, -motionZ);
			}
			doBlockCollisions();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("Damage", (short) spearDamage);
		nbttagcompound.setShort("xTile", (short) xTile);
		nbttagcompound.setShort("yTile", (short) yTile);
		nbttagcompound.setShort("zTile", (short) zTile);
		nbttagcompound.setByte("inTile", (byte) inTile);
		nbttagcompound.setByte("inData", (byte) inData);
		nbttagcompound.setByte("shake", (byte) arrowShake);
		nbttagcompound.setByte("inGround", (byte) (inGround ? 1 : 0));
		nbttagcompound.setBoolean("player", doesArrowBelongToPlayer);
		nbttagcompound.setBoolean("fished", fished);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		spearDamage = nbttagcompound.getShort("Damage");
		xTile = nbttagcompound.getShort("xTile");
		yTile = nbttagcompound.getShort("yTile");
		zTile = nbttagcompound.getShort("zTile");
		inTile = nbttagcompound.getByte("inTile") & 0xff;
		inData = nbttagcompound.getByte("inData") & 0xff;
		arrowShake = nbttagcompound.getByte("shake") & 0xff;
		inGround = nbttagcompound.getByte("inGround") == 1;
		doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
		fished = nbttagcompound.getBoolean("fished");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (worldObj.isRemote) {
			return;
		}
		if (inGround && doesArrowBelongToPlayer && arrowShake <= 0 && spearDamage < 160) {
			EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, new ItemStack(isPoisoned() ? mod_LionKing.poisonedSpear : mod_LionKing.gemsbokSpear, 1, spearDamage + 1));
			entityitem.delayBeforeCanPickup = 0;
			worldObj.spawnEntityInWorld(entityitem);
			setDead();
		}
	}

	private void onCollideWithLiving(Entity entity) {
		if (worldObj.isRemote) {
			return;
		}
		if (doesArrowBelongToPlayer && arrowShake <= 0 && spearDamage < 160) {
			EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, new ItemStack(isPoisoned() ? mod_LionKing.poisonedSpear : mod_LionKing.gemsbokSpear, 1, spearDamage + 1));
			entityitem.delayBeforeCanPickup = 0;
			worldObj.spawnEntityInWorld(entityitem);
			setDead();
		}
	}

	protected abstract boolean isPoisoned();

	@Override
	public float getShadowSize() {
		return 0.0F;
	}
}

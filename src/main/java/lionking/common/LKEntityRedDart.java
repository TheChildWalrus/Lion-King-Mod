package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

public class LKEntityRedDart extends LKEntityDart {
	public LKEntityRedDart(World world) {
		super(world);
	}

	public LKEntityRedDart(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
	}

	public LKEntityRedDart(World world, EntityLivingBase entityliving, float f, boolean flag) {
		super(world, entityliving, f, flag);
	}

	@Override
	public ItemStack getDartItem() {
		return new ItemStack(mod_LionKing.dartRed);
	}

	@Override
	public int getDamage() {
		return 6;
	}

	@Override
	public void onHitEntity(Entity hitEntity) {
		if (shootingEntity != null) {
			hitEntity.addVelocity(-MathHelper.sin(shootingEntity.rotationYaw * 3.141593F / 180.0F) * 0.45F, 0.10000000000000001D, MathHelper.cos(shootingEntity.rotationYaw * 3.141593F / 180.0F) * 0.45F);
		}
		if (!hitEntity.isImmuneToFire()) {
			hitEntity.setFire(worldObj.rand.nextInt(silverFired ? 4 : 2) + 3);
		}
	}

	@Override
	public void spawnParticles() {
		worldObj.spawnParticle("flame", posX, posY, posZ, -motionX * 0.1, -motionY * 0.1, -motionZ * 0.1);
	}

	@Override
	public float getSpeedReduction() {
		return 0.02F;
	}
}

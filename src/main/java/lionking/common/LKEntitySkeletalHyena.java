package lionking.common;

import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

public class LKEntitySkeletalHyena extends LKEntityHyena {
	public LKEntitySkeletalHyena(World world) {
		super(world);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.6D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(21, (byte) 0);
	}

	public boolean isHeadless() {
		return dataWatcher.getWatchableObjectByte(21) == (byte) 1;
	}

	private void setHeadless(boolean flag) {
		dataWatcher.updateObject(21, (byte) (flag ? 1 : 0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Headless", isHeadless());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setHeadless(nbt.getBoolean("Headless"));
	}

	@Override
	public boolean onHyenaKilled(EntityPlayer entityplayer) {
		if (isHeadless()) {
			entityplayer.triggerAchievement(LKAchievementList.killHyena);
			int looting = EnchantmentHelper.getLootingModifier(entityplayer);
			if (!worldObj.isRemote) {
				int bones = getRNG().nextInt(3) + getRNG().nextInt(1 + looting);
				for (int i = 0; i < bones; i++) {
					dropItem(mod_LionKing.hyenaBone.itemID, 1);
				}

				int shards = getRNG().nextInt(2) + getRNG().nextInt(1 + looting);
				for (int i = 0; i < shards; i++) {
					dropItem(mod_LionKing.hyenaBoneShard.itemID, 1);
				}
			}
			return false;
		}
		int event = getRNG().nextInt(3);
		if (event == 0) {
			entityplayer.triggerAchievement(LKAchievementList.killHyena);
			int looting = EnchantmentHelper.getLootingModifier(entityplayer);
			if (!worldObj.isRemote) {
				int bones = getRNG().nextInt(3) + getRNG().nextInt(1 + looting);
				for (int i = 0; i < bones; i++) {
					dropItem(mod_LionKing.hyenaBone.itemID, 1);
				}

				int shards = getRNG().nextInt(2) + getRNG().nextInt(1 + looting);
				for (int i = 0; i < shards; i++) {
					dropItem(mod_LionKing.hyenaBoneShard.itemID, 1);
				}

				if (getRNG().nextInt(40) == 0) {
					entityDropItem(new ItemStack(mod_LionKing.hyenaHeadItem.itemID, 1, 3), 0.0F);
				}
			}
			return false;
		}
		if (event == 1) {
			LKEntitySkeletalHyenaHead head = new LKEntitySkeletalHyenaHead(worldObj);
			head.setLocationAndAngles(posX, posY + 0.5D, posZ, getRNG().nextFloat() * 360.0F, getRNG().nextFloat() * 360.0F);
			head.motionX = getRNG().nextGaussian() * 0.01D;
			head.motionY = getRNG().nextFloat() * 0.2D;
			head.motionZ = getRNG().nextGaussian() * 0.01D;
			if (!worldObj.isRemote) {
				worldObj.spawnEntityInWorld(head);
			}
			head.attackEntityFrom(DamageSource.causePlayerDamage(entityplayer), 100.0F);

			setHealth(15);
			setHeadless(true);
			entityplayer.triggerAchievement(LKAchievementList.behead);
			return true;
		}
		entityplayer.triggerAchievement(LKAchievementList.killHyena);
		int looting = EnchantmentHelper.getLootingModifier(entityplayer);
		if (!worldObj.isRemote) {
			int bones = getRNG().nextInt(3) + getRNG().nextInt(1 + looting);
			for (int i = 0; i < bones; i++) {
				dropItem(mod_LionKing.hyenaBone.itemID, 1);
			}

			int shards = getRNG().nextInt(2) + getRNG().nextInt(1 + looting);
			for (int i = 0; i < shards; i++) {
				dropItem(mod_LionKing.hyenaBoneShard.itemID, 1);
			}
		}
		setHeadless(true);

		LKEntitySkeletalHyenaHead head = new LKEntitySkeletalHyenaHead(worldObj);
		head.setLocationAndAngles(posX, posY + 0.5D, posZ, getRNG().nextFloat() * 360.0F, getRNG().nextFloat() * 360.0F);
		head.motionX = getRNG().nextGaussian() * 0.02D;
		head.motionY = getRNG().nextGaussian() * 0.02D;
		head.motionZ = getRNG().nextGaussian() * 0.02D;
		if (!worldObj.isRemote) {
			worldObj.spawnEntityInWorld(head);
		}
		entityplayer.triggerAchievement(LKAchievementList.behead);
		return false;
	}

	@Override
	public boolean canAttackZazus() {
		return false;
	}

	@Override
	public boolean damagedBySunlight() {
		return false;
	}

	@Override
	protected String getHurtSound() {
		return "mob.skeleton.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "mob.skeleton.death";
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
}

package lionking.common;

import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.List;

public class LKEntityHyena extends EntityMob {
	public LKEntityHyena(World world) {
		super(world);
		setSize(0.6F, 0.8F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.5D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, (byte) getRNG().nextInt(3));
	}

	public byte getHyenaType() {
		return dataWatcher.getWatchableObjectByte(20);
	}

	private void setHyenaType(byte b) {
		dataWatcher.updateObject(20, b);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("HyenaType", getHyenaType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setHyenaType(nbt.getByte("HyenaType"));
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (attackTime <= 0 && f < 1.7F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			attackEntityAsMob(entity);
		}
	}

	@Override
	public void onLivingUpdate() {
		if (damagedBySunlight() && worldObj.isDaytime() && !worldObj.isRemote) {
			float f = getBrightness(1.0F);
			if (f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && getRNG().nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
				attackEntityFrom(DamageSource.generic, getRNG().nextInt(2) + 1);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected void updateEntityActionState() {
		super.updateEntityActionState();
		if (canAttackZazus() && entityToAttack == null && !hasPath() && worldObj.rand.nextInt(300) == 0) {
			List list = worldObj.getEntitiesWithinAABB(LKEntityZazu.class, AxisAlignedBB.getAABBPool().getAABB(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
			if (!list.isEmpty()) {
				setTarget((Entity) list.get(getRNG().nextInt(list.size())));
			}
		}
	}

	@Override
	protected String getLivingSound() {
		return "mob.wolf.growl";
	}

	@Override
	protected String getHurtSound() {
		return "mob.wolf.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "mob.wolf.death";
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		if (damagesource.getEntity() instanceof EntityPlayer && onHyenaKilled((EntityPlayer) damagesource.getEntity())) {
			return;
		}
		super.onDeath(damagesource);
	}

	protected boolean onHyenaKilled(EntityPlayer entityplayer) {
		entityplayer.triggerAchievement(LKAchievementList.killHyena);
		int looting = EnchantmentHelper.getLootingModifier(entityplayer);
		if (!worldObj.isRemote) {
			int bones = getRNG().nextInt(3) + getRNG().nextInt(1 + looting);
			for (int i = 0; i < bones; i++) {
				dropItem(mod_LionKing.hyenaBone.itemID, 1);
			}

			if (getRNG().nextInt(40) == 0) {
				entityDropItem(new ItemStack(mod_LionKing.hyenaHeadItem.itemID, 1, getHyenaType()), 0.0F);
			}
		}
		return false;
	}

	protected boolean canAttackZazus() {
		return true;
	}

	protected boolean damagedBySunlight() {
		return true;
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target) {
		return new ItemStack(mod_LionKing.spawnEgg, 1, LKEntities.getEntityID(this));
	}
}

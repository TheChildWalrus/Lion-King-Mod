package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

public class LKEntityTermite extends EntityMob {
	private int timeSinceIgnited;
	private int lastActiveTime;

	public LKEntityTermite(World world) {
		super(world);
		setSize(0.4F, 0.4F);
		experienceValue = 3;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(9.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, (byte) -1);
	}

	@Override
	public void onUpdate() {
		lastActiveTime = timeSinceIgnited;
		if (worldObj.isRemote) {
			int i = getTermiteState();
			if (i > 0 && timeSinceIgnited == 0) {
				worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
			}
			timeSinceIgnited += i;
			if (timeSinceIgnited < 0) {
				timeSinceIgnited = 0;
			}
			if (timeSinceIgnited >= 20) {
				timeSinceIgnited = 20;
			}
		}
		super.onUpdate();
		if (entityToAttack == null && timeSinceIgnited > 0) {
			setTermiteState(-1);
			timeSinceIgnited--;
			if (timeSinceIgnited < 0) {
				timeSinceIgnited = 0;
			}
		}
	}

	@Override
	protected String getLivingSound() {
		return "mob.silverfish.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.silverfish.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.silverfish.kill";
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (worldObj.isRemote) {
			return;
		}
		int i = getTermiteState();
		if (f < (i <= 0 ? 3.0F : 7.0F)) {
			if (timeSinceIgnited == 0) {
				worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
			}
			setTermiteState(1);
			timeSinceIgnited++;
			if (timeSinceIgnited >= 20) {
				worldObj.createExplosion(this, posX, posY, posZ, 1.7F, worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
				setDead();
			}
			hasAttacked = true;
		} else {
			setTermiteState(-1);
			timeSinceIgnited--;
			if (timeSinceIgnited < 0) {
				timeSinceIgnited = 0;
			}
		}
	}

	public float setTermiteFlashTime(float f) {
		return (lastActiveTime + (timeSinceIgnited - lastActiveTime) * f) / 28.0F;
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	private int getTermiteState() {
		return dataWatcher.getWatchableObjectByte(16);
	}

	private void setTermiteState(int i) {
		dataWatcher.updateObject(16, (byte) i);
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote && damagesource.getEntity() instanceof EntityPlayer) {
			dropItem(mod_LionKing.itemTermite.itemID, 1);
			setDead();
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target) {
		return new ItemStack(mod_LionKing.spawnEgg, 1, LKEntities.getEntityID(this));
	}
}

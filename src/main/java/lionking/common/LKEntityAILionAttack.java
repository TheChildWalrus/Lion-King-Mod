package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;

public class LKEntityAILionAttack extends EntityAIAttackOnCollide {
	private int privateSuperField;
	private final EntityCreature theEntity;
	private final double speed;
	private final boolean aBoolean;
	private int entityAttackTick;

	public LKEntityAILionAttack(EntityCreature entity, Class entityclass, double d, boolean flag) {
		super(entity, entityclass, d, flag);
		theEntity = entity;
		speed = d;
		aBoolean = flag;
	}

	@Override
	public boolean shouldExecute() {
		return ((LKAngerable) theEntity).isHostile() && super.shouldExecute();
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		privateSuperField = 0;
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = theEntity.getAttackTarget();
		theEntity.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);

		if ((aBoolean || theEntity.getEntitySenses().canSee(target)) && --privateSuperField <= 0) {
			privateSuperField = 4 + theEntity.getRNG().nextInt(7);
			theEntity.getNavigator().tryMoveToEntityLiving(target, speed);
		}

		entityAttackTick = Math.max(entityAttackTick - 1, 0);
		double d = theEntity.width * 1.6F * theEntity.width * 1.6F;

		if (theEntity.getDistanceSq(target.posX, target.boundingBox.minY, target.posZ) <= d) {
			if (entityAttackTick <= 0) {
				entityAttackTick = 20;
				theEntity.attackEntityAsMob(target);
			}
		}
	}
}

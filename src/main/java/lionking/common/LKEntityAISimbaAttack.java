package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;

public class LKEntityAISimbaAttack extends EntityAIAttackOnCollide {
	private int privateSuperField;
	private final LKEntitySimba theSimba;
	private final double speed;
	private final boolean aBoolean;
	private int entityAttackTick;

	public LKEntityAISimbaAttack(LKEntitySimba simba, double d, boolean flag) {
		super(simba, d, flag);
		theSimba = simba;
		speed = d;
		aBoolean = flag;
	}

	@Override
	public boolean shouldExecute() {
		return !theSimba.isSitting() && super.shouldExecute();
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		privateSuperField = 0;
	}

	@Override
	public boolean continueExecuting() {
		return !theSimba.isSitting() && super.continueExecuting();
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = theSimba.getAttackTarget();
		theSimba.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);

		if ((aBoolean || theSimba.getEntitySenses().canSee(target)) && --privateSuperField <= 0) {
			privateSuperField = 4 + theSimba.getRNG().nextInt(7);
			theSimba.getNavigator().tryMoveToEntityLiving(target, speed);
		}

		entityAttackTick = Math.max(entityAttackTick - 1, 0);
		double var1 = theSimba.width * 1.6F * theSimba.width * 1.6F;

		if (theSimba.getDistanceSq(target.posX, target.boundingBox.minY, target.posZ) <= var1) {
			if (entityAttackTick <= 0) {
				entityAttackTick = 20;
				theSimba.attackEntityAsMob(target);
			}
		}
	}
}

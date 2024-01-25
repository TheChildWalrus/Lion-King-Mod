package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.*;

public class LKEntityAISimbaAttackPlayerTarget extends EntityAITarget {
	private final LKEntitySimba theSimba;
	private EntityLivingBase attackTarget;
	private int ownerTicksExisted;

	public LKEntityAISimbaAttackPlayerTarget(LKEntitySimba simba) {
		super(simba, false);
		theSimba = simba;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (theSimba.isSitting()) {
			return false;
		}
		EntityPlayer entityplayer = theSimba.getOwner();
		if (entityplayer == null) {
			return false;
		}
		attackTarget = entityplayer.getLastAttacker();
		int i = entityplayer.getLastAttackerTime();
		return i != ownerTicksExisted && isSuitableTarget(attackTarget, false);
	}


	@Override
	public void startExecuting() {
		taskOwner.setAttackTarget(attackTarget);
		EntityPlayer entityplayer = theSimba.getOwner();
		if (entityplayer != null) {
			ownerTicksExisted = entityplayer.getLastAttackerTime();
		}
		super.startExecuting();
	}

	@Override
	public boolean continueExecuting() {
		return !theSimba.isSitting() && super.continueExecuting();
	}
}

package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.*;

public class LKEntityAISimbaAttackPlayerAttacker extends EntityAITarget {
	private final LKEntitySimba theSimba;
	private EntityLivingBase attackTarget;
	private int ownerRevengeTimer;

	public LKEntityAISimbaAttackPlayerAttacker(LKEntitySimba simba) {
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
		attackTarget = entityplayer.getAITarget();
		int i = entityplayer.func_142015_aE();
		return i != ownerRevengeTimer && isSuitableTarget(attackTarget, false);
	}

	@Override
	public void startExecuting() {
		taskOwner.setAttackTarget(attackTarget);
		EntityPlayer entityplayer = theSimba.getOwner();
		if (entityplayer != null) {
			ownerRevengeTimer = entityplayer.func_142015_aE();
		}
		super.startExecuting();
	}

	@Override
	public boolean continueExecuting() {
		return !theSimba.isSitting() && super.continueExecuting();
	}
}

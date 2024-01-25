package lionking.common;

import net.minecraft.entity.ai.*;

import net.minecraft.world.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LKEntityAIZazuMate extends EntityAIBase {
	private final World theWorld;
	private int layEggDelay;
	private final LKEntityZazu theZazu;
	private LKEntityZazu targetMate;
	private final double moveSpeed;

	public LKEntityAIZazuMate(LKEntityZazu zazu, double d) {
		theZazu = zazu;
		theWorld = zazu.worldObj;
		moveSpeed = d;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if (theZazu.isInLove()) {
			targetMate = getNearbyMate();
			return targetMate != null;
		}
		return false;
	}

	@Override
	public boolean continueExecuting() {
		return targetMate.isEntityAlive() && targetMate.isInLove() && layEggDelay < 60;
	}

	@Override
	public void resetTask() {
		targetMate = null;
		layEggDelay = 0;
	}

	@Override
	public void updateTask() {
		theZazu.getLookHelper().setLookPositionWithEntity(targetMate, 10.0F, theZazu.getVerticalFaceSpeed());
		theZazu.getNavigator().tryMoveToEntityLiving(targetMate, moveSpeed);
		++layEggDelay;

		if (layEggDelay == 60) {
			layEggs();
		}
	}

	private LKEntityZazu getNearbyMate() {
		float f = 8.0F;
		List list = theWorld.getEntitiesWithinAABB(LKEntityZazu.class, theZazu.boundingBox.expand(f, f, f));
		Iterator i = list.iterator();
		LKEntityZazu zazu;

		do {
			if (!i.hasNext()) {
				return null;
			}

			zazu = (LKEntityZazu) i.next();
		}
		while (!theZazu.canMateWith(zazu));

		return zazu;
	}

	private void layEggs() {
		theZazu.setGrowingAge(6000);
		targetMate.setGrowingAge(6000);
		theZazu.resetInLove();
		targetMate.resetInLove();

		Random rand = theZazu.getRNG();
		int eggs = rand.nextInt(3) + 1;
		for (int i = 0; i < eggs; i++) {
			theZazu.dropItem(mod_LionKing.zazuEgg.itemID, 1);
		}

		for (int i = 0; i < 7; i++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			theWorld.spawnParticle("heart", theZazu.posX + rand.nextFloat() * theZazu.width * 2.0F - theZazu.width, theZazu.posY + 0.5D + rand.nextFloat() * theZazu.height, theZazu.posZ + rand.nextFloat() * theZazu.width * 2.0F - theZazu.width, d, d1, d2);
		}
	}
}

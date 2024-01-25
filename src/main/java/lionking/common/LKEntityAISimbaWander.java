package lionking.common;

import net.minecraft.entity.ai.*;

import net.minecraft.util.*;

public class LKEntityAISimbaWander extends EntityAIBase {
	private final LKEntitySimba theSimba;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private final double speed;

	public LKEntityAISimbaWander(LKEntitySimba simba, double d) {
		theSimba = simba;
		speed = d;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (theSimba.isSitting()) {
			return false;
		}
		if (theSimba.getAge() >= 100 || theSimba.getRNG().nextInt(120) != 0) {
			return false;
		}
		Vec3 vec = RandomPositionGenerator.findRandomTarget(theSimba, 10, 7);

		if (vec == null) {
			return false;
		}
		xPosition = vec.xCoord;
		yPosition = vec.yCoord;
		zPosition = vec.zCoord;
		return true;
	}

	@Override
	public boolean continueExecuting() {
		return !theSimba.getNavigator().noPath() && !theSimba.isSitting();
	}

	@Override
	public void startExecuting() {
		theSimba.getNavigator().tryMoveToXYZ(xPosition, yPosition, zPosition, speed);
	}
}

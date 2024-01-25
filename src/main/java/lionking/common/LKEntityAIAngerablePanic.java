package lionking.common;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;

import net.minecraft.util.*;

public class LKEntityAIAngerablePanic extends EntityAIBase {
	private final EntityAnimal theAnimal;
	private final double speed;
	private double randPosX;
	private double randPosY;
	private double randPosZ;

	public LKEntityAIAngerablePanic(EntityAnimal animal, double d) {
		theAnimal = animal;
		speed = d;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (!theAnimal.isChild()) {
			return false;
		}
		if (theAnimal.getAITarget() == null) {
			return false;
		}
		Vec3 vec3 = RandomPositionGenerator.findRandomTarget(theAnimal, 5, 4);

		if (vec3 == null) {
			return false;
		}
		randPosX = vec3.xCoord;
		randPosY = vec3.yCoord;
		randPosZ = vec3.zCoord;
		return true;
	}

	@Override
	public void startExecuting() {
		theAnimal.getNavigator().tryMoveToXYZ(randPosX, randPosY, randPosZ, speed);
	}

	@Override
	public boolean continueExecuting() {
		return !theAnimal.getNavigator().noPath();
	}
}

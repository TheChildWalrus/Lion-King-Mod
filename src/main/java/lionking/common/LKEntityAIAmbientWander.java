package lionking.common;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;

import net.minecraft.util.*;

public class LKEntityAIAmbientWander extends EntityAIBase {
	private final EntityAmbientCreature entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private final double speed;

	public LKEntityAIAmbientWander(EntityAmbientCreature creature, double d) {
		entity = creature;
		speed = d;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (entity.getAge() >= 100 || entity.getRNG().nextInt(60) != 0) {
			return false;
		}
		Vec3 v = LKAmbientPositionGenerator.findRandomTarget(entity, 10, 7);

		if (v == null) {
			return false;
		}
		xPosition = v.xCoord;
		yPosition = v.yCoord;
		zPosition = v.zCoord;
		return true;
	}

	@Override
	public boolean continueExecuting() {
		return !entity.getNavigator().noPath();
	}

	@Override
	public void startExecuting() {
		entity.getNavigator().tryMoveToXYZ(xPosition, yPosition, zPosition, speed);
	}
}

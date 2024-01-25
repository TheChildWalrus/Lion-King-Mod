package lionking.common;

import net.minecraft.entity.*;

import net.minecraft.world.*;

public class LKEntityPoisonedSpear extends LKEntitySpear {
	public LKEntityPoisonedSpear(World world) {
		super(world);
	}

	public LKEntityPoisonedSpear(World world, double d, double d1, double d2, int damage) {
		super(world, d, d1, d2, damage);
	}

	public LKEntityPoisonedSpear(World world, EntityLivingBase entityliving, float f, int damage) {
		super(world, entityliving, f, damage);
	}

	@Override
	public boolean isPoisoned() {
		return true;
	}
}

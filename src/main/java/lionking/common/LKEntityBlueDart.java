package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

public class LKEntityBlueDart extends LKEntityDart {
	public LKEntityBlueDart(World world) {
		super(world);
	}

	public LKEntityBlueDart(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
	}

	public LKEntityBlueDart(World world, EntityLivingBase entityliving, float f, boolean flag) {
		super(world, entityliving, f, flag);
	}

	@Override
	public ItemStack getDartItem() {
		return new ItemStack(mod_LionKing.dartBlue);
	}

	@Override
	public int getDamage() {
		return 7;
	}

	@Override
	public void onHitEntity(Entity hitEntity) {
	}

	@Override
	public void spawnParticles() {
	}

	@Override
	public float getSpeedReduction() {
		return 0.05F;
	}
}

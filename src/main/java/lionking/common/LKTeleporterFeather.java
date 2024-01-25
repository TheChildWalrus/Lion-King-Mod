package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.nbt.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.Random;
import java.util.List;

public class LKTeleporterFeather extends Teleporter {
	private final Random random = new Random();
	private final List simbaData;
	private final WorldServer world;

	public LKTeleporterFeather(WorldServer worldserver, List list) {
		super(worldserver);
		world = worldserver;
		simbaData = list;
	}

	@Override
	public void placeInPortal(Entity entity, double d, double d1, double d2, float f) {
		int i = MathHelper.floor_double(entity.posX);
		int k = MathHelper.floor_double(entity.posZ);

		int i1 = i - 32 + random.nextInt(65);
		int k1 = k - 32 + random.nextInt(65);
		int j1 = world.getTopSolidOrLiquidBlock(i1, k1) + random.nextInt(12);

		entity.setLocationAndAngles(i1 + 0.5D, j1 + 1.0D, k1 + 0.5D, entity.rotationYaw, 0.0F);

		for (Object simbaDatum : simbaData) {
			LKEntitySimba simba = new LKEntitySimba(world);
			simba.readFromNBT((NBTTagCompound) simbaDatum);

			simba.setLocationAndAngles(i1 + 0.5D, j1 + 1.0D, k1 + 0.5D, entity.rotationYaw, 0.0F);
			simba.setVelocity(0.0D, 0.0D, 0.0D);

			world.spawnEntityInWorld(simba);
			simba.applyTeleportationEffects(entity);
		}
	}
}
package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.nbt.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.List;

public class LKTeleporterUpendi extends Teleporter {
	private final List simbaData;
	private final WorldServer worldObj;

	public LKTeleporterUpendi(WorldServer world, List list) {
		super(world);
		worldObj = world;
		simbaData = list;
	}

	@Override
	public void placeInPortal(Entity entity, double d, double d1, double d2, float f) {
		int i = MathHelper.floor_double(entity.posX);
		int k = MathHelper.floor_double(entity.posZ);
		int j = worldObj.getTopSolidOrLiquidBlock(i, k);
		entity.setLocationAndAngles(i + 0.5D, j + 1.0D, k + 0.5D, entity.rotationYaw, 0.0F);

		for (Object simbaDatum : simbaData) {
			LKEntitySimba simba = new LKEntitySimba(worldObj);
			simba.readFromNBT((NBTTagCompound) simbaDatum);

			simba.setLocationAndAngles(i + 0.5D, j + 1.0D, k + 0.5D, entity.rotationYaw, 0.0F);
			simba.setVelocity(0.0D, 0.0D, 0.0D);

			worldObj.spawnEntityInWorld(simba);
			simba.applyTeleportationEffects(entity);
		}
	}
}
package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenZazu extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		if (Block.blocksList[world.getBlockId(i, j, k)] instanceof LKBlockLeaves && world.isAirBlock(i, j + 1, k) && world.isAirBlock(i, j + 2, k)) {
			LKEntityZazu entityzazu = new LKEntityZazu(world);
			entityzazu.setLocationAndAngles(i, j + 1, k, 0.0F, 0.0F);
			world.spawnEntityInWorld(entityzazu);
			return true;
		}
		return false;
	}
}


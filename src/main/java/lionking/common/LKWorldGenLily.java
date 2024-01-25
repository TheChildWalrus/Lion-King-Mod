package lionking.common;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenLily extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 10; ++l) {
			int i1 = i + random.nextInt(8) - random.nextInt(8);
			int j1 = j + random.nextInt(4) - random.nextInt(4);
			int k1 = k + random.nextInt(8) - random.nextInt(8);

			if (world.isAirBlock(i1, j1, k1) && mod_LionKing.lily.canPlaceBlockAt(world, i1, j1, k1)) {
				world.setBlock(i1, j1, k1, mod_LionKing.lily.blockID, random.nextInt(3), 2);
			}
		}

		return true;
	}
}

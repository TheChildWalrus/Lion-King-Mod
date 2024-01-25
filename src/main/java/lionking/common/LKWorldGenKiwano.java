package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenKiwano extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 64; l++) {
			int i1 = i + random.nextInt(8) - random.nextInt(8);
			int j1 = j + random.nextInt(4) - random.nextInt(4);
			int k1 = k + random.nextInt(8) - random.nextInt(8);

			if (world.isAirBlock(i1, j1, k1) && world.getBlockId(i1, j1 - 1, k1) == Block.sand.blockID && mod_LionKing.kiwanoBlock.canPlaceBlockAt(world, i1, j1, k1)) {
				world.setBlock(i1, j1, k1, mod_LionKing.kiwanoBlock.blockID, 0, 2);
			}
		}

		return true;
	}
}

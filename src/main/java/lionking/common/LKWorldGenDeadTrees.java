package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenDeadTrees extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int l = random.nextInt(3) + 6;
		if (j + l >= 256) {
			return false;
		}

		if (world.getBlockId(i, j - 1, k) == Block.sand.blockID && world.isAirBlock(i, j, k)) {
			for (int j1 = j; j1 < j + l + 1; j1++) {
				world.setBlock(i, j1, k, mod_LionKing.prideWood2.blockID, 1, 2);
			}
			int l1 = l - 1 - random.nextInt(2);
			int l2 = l - 1 - random.nextInt(2);
			int l3 = l - 1 - random.nextInt(2);
			int l4 = l - 1 - random.nextInt(2);
			world.setBlock(i - 1, j + l1, k, mod_LionKing.prideWood2.blockID, 1, 2);
			world.setBlock(i + 1, j + l2, k, mod_LionKing.prideWood2.blockID, 1, 2);
			world.setBlock(i, j + l3, k - 1, mod_LionKing.prideWood2.blockID, 1, 2);
			world.setBlock(i, j + l4, k + 1, mod_LionKing.prideWood2.blockID, 1, 2);
			return true;
		}
		return false;
	}
}

package lionking.common;

import net.minecraft.block.material.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenMaize extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 20; ++l) {
			int i1 = i + random.nextInt(4) - random.nextInt(4);
			int k1 = k + random.nextInt(4) - random.nextInt(4);
			if (world.isAirBlock(i1, j, k1) && (world.getBlockMaterial(i1 - 1, j - 1, k1) == Material.water || world.getBlockMaterial(i1 + 1, j - 1, k1) == Material.water || world.getBlockMaterial(i1, j - 1, k1 - 1) == Material.water || world.getBlockMaterial(i1, j - 1, k1 + 1) == Material.water)) {
				int l1 = 2 + random.nextInt(random.nextInt(3) + 1);
				for (int l2 = 0; l2 < l1; ++l2) {
					int i2 = l2 == 0 ? 0 : 1;
					if (mod_LionKing.maize.canBlockStay(world, i1, j + l2, k1)) {
						world.setBlock(i1, j + l2, k1, mod_LionKing.maize.blockID, i2, 2);
					}
				}
			}
		}
		return true;
	}
}

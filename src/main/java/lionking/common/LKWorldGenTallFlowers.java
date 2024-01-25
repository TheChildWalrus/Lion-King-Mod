package lionking.common;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenTallFlowers extends WorldGenerator {
	private final int metadata;

	public LKWorldGenTallFlowers(boolean flag, int i) {
		super(flag);
		metadata = i;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 64; l++) {
			int i1 = i + random.nextInt(8) - random.nextInt(8);
			int j1 = j + random.nextInt(4) - random.nextInt(4);
			int k1 = k + random.nextInt(8) - random.nextInt(8);
			if (mod_LionKing.flowerBase.canBlockStay(world, i1, j1, k1) && world.isAirBlock(i1, j1, k1) && world.isAirBlock(i1, j1 + 1, k1)) {
				setBlockAndMetadata(world, i1, j1, k1, mod_LionKing.flowerBase.blockID, metadata);
				setBlockAndMetadata(world, i1, j1 + 1, k1, mod_LionKing.flowerTop.blockID, metadata);
			}
		}

		return true;
	}
}

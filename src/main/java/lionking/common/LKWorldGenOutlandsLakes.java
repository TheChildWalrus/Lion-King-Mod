package lionking.common;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenOutlandsLakes extends WorldGenerator {
	private final int blockIndex;

	public LKWorldGenOutlandsLakes() {
		blockIndex = Block.lavaStill.blockID;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int i6 = i;
		int k3 = k;
		int j2 = j;
		i6 -= 8;
		for (k3 -= 8; j2 > 0 && world.isAirBlock(i6, j2, k3); j2--) {
		}
		j2 -= 4;
		boolean[] aflag = new boolean[2048];
		int l = random.nextInt(4) + 4;
		for (int i1 = 0; i1 < l; i1++) {
			double d = random.nextDouble() * 6.0D + 3.0D;
			double d1 = random.nextDouble() * 4.0D + 2.0D;
			double d2 = random.nextDouble() * 6.0D + 3.0D;
			double d3 = random.nextDouble() * (16.0D - d - 2.0D) + 1.0D + d / 2.0D;
			double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
			double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;
			for (int l4 = 1; l4 < 15; l4++) {
				for (int i5 = 1; i5 < 15; i5++) {
					for (int j5 = 1; j5 < 7; j5++) {
						double d6 = (l4 - d3) / (d / 2.0D);
						double d7 = (j5 - d4) / (d1 / 2.0D);
						double d8 = (i5 - d5) / (d2 / 2.0D);
						double d9 = d6 * d6 + d7 * d7 + d8 * d8;
						if (d9 < 1.0D) {
							aflag[(l4 * 16 + i5) * 8 + j5] = true;
						}
					}

				}

			}

		}

		for (int j1 = 0; j1 < 16; j1++) {
			for (int k2 = 0; k2 < 16; k2++) {
				for (int l3 = 0; l3 < 8; l3++) {
					boolean flag = !aflag[(j1 * 16 + k2) * 8 + l3] && (j1 < 15 && aflag[((j1 + 1) * 16 + k2) * 8 + l3] || j1 > 0 && aflag[((j1 - 1) * 16 + k2) * 8 + l3] || k2 < 15 && aflag[(j1 * 16 + k2 + 1) * 8 + l3] || k2 > 0 && aflag[(j1 * 16 + k2 - 1) * 8 + l3] || l3 < 7 && aflag[(j1 * 16 + k2) * 8 + l3 + 1] || l3 > 0 && aflag[(j1 * 16 + k2) * 8 + l3 - 1]);
					if (!flag) {
						continue;
					}
					Material material = world.getBlockMaterial(i6 + j1, j2 + l3, k3 + k2);
					if (l3 >= 4 && material.isLiquid()) {
						return false;
					}
					if (l3 < 4 && !material.isSolid() && world.getBlockId(i6 + j1, j2 + l3, k3 + k2) != blockIndex) {
						return false;
					}
				}

			}

		}

		for (int k1 = 0; k1 < 16; k1++) {
			for (int l2 = 0; l2 < 16; l2++) {
				for (int i4 = 0; i4 < 8; i4++) {
					if (aflag[(k1 * 16 + l2) * 8 + i4]) {
						world.setBlock(i6 + k1, j2 + i4, k3 + l2, i4 < 4 ? blockIndex : 0, 0, 2);
					}
				}

			}

		}

		for (int l1 = 0; l1 < 16; l1++) {
			for (int i3 = 0; i3 < 16; i3++) {
				for (int j4 = 4; j4 < 8; j4++) {
					if (!aflag[(l1 * 16 + i3) * 8 + j4] || world.getBlockId(i6 + l1, j2 + j4 - 1, k3 + i3) != Block.sand.blockID || world.getSavedLightValue(EnumSkyBlock.Sky, i6 + l1, j2 + j4, k3 + i3) <= 0) {
						continue;
					}
					world.setBlock(i6 + l1, j2 + j4 - 1, k3 + i3, Block.sand.blockID, 0, 2);
				}

			}

		}

		if (Block.blocksList[blockIndex].blockMaterial == Material.lava) {
			for (int i2 = 0; i2 < 16; i2++) {
				for (int j3 = 0; j3 < 16; j3++) {
					for (int k4 = 0; k4 < 8; k4++) {
						boolean flag1 = !aflag[(i2 * 16 + j3) * 8 + k4] && (i2 < 15 && aflag[((i2 + 1) * 16 + j3) * 8 + k4] || i2 > 0 && aflag[((i2 - 1) * 16 + j3) * 8 + k4] || j3 < 15 && aflag[(i2 * 16 + j3 + 1) * 8 + k4] || j3 > 0 && aflag[(i2 * 16 + j3 - 1) * 8 + k4] || k4 < 7 && aflag[(i2 * 16 + j3) * 8 + k4 + 1] || k4 > 0 && aflag[(i2 * 16 + j3) * 8 + k4 - 1]);
						if (flag1 && (k4 < 4 || random.nextInt(2) != 0) && world.getBlockMaterial(i6 + i2, j2 + k4, k3 + j3).isSolid()) {
							world.setBlock(i6 + i2, j2 + k4, k3 + j3, mod_LionKing.pridestone.blockID, 1, 2);
						}
					}

				}

			}

		}
		return true;
	}
}

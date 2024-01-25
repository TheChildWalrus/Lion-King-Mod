package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenRainforestTrees extends WorldGenerator {
	private final boolean naturallyGenerated;

	public LKWorldGenRainforestTrees(boolean flag) {
		super(flag);
		naturallyGenerated = !flag;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int l = random.nextInt(5) + 9;
		if (j < 1 || j + l + 2 > 256) {
			return false;
		}
		if (world.getBlockId(i, j, k) != 0 && world.getBlockId(i, j, k) != mod_LionKing.forestSapling.blockID) {
			return false;
		}
		if (world.getBlockId(i, j - 1, k) != Block.grass.blockID && world.getBlockId(i, j - 1, k) != Block.dirt.blockID) {
			return false;
		}
		if (random.nextInt(5) == 0 && naturallyGenerated || random.nextInt(8) == 0 && !naturallyGenerated) {
			l += random.nextInt(7) + 3;
			return generateLargeTree(world, random, i, j, k, l);
		}
		return generateNormalTree(world, random, i, j, k, l);
	}

	private boolean generateNormalTree(World world, Random random, int i, int j, int k, int l) {
		int l1 = l - 4;
		if (l > 11 && l < 15) {
			l1--;
		}
		if (l > 14) {
			l1 -= 2;
		}
		for (int j1 = 1; j1 <= l; j1++) {
			if (!isBlockReplaceable(world, i, j + j1, k)) {
				return false;
			}
		}
		for (int i1 = -1; i1 < 2; i1++) {
			for (int k1 = -1; k1 < 2; k1++) {
				if (!isBlockReplaceable(world, i + i1, j + l1, k + k1)) {
					return false;
				}
				if (!isBlockReplaceable(world, i + i1, j + l, k + k1)) {
					return false;
				}
				if (!isBlockReplaceable(world, i + i1, j + l + 2, k + k1)) {
					return false;
				}
			}
		}
		for (int i1 = -2; i1 < 3; i1++) {
			for (int k1 = -2; k1 < 3; k1++) {
				if (!isBlockReplaceable(world, i + i1, j + l + 1, k + k1)) {
					return false;
				}
			}
		}
		for (int i2 = -1; i2 < 2; i2++) {
			for (int k2 = -1; k2 < 2; k2++) {
				setBlockAndMetadata(world, i + i2, j + l1, k + k2, mod_LionKing.forestLeaves.blockID, 0);
				setBlockAndMetadata(world, i + i2, j + l, k + k2, mod_LionKing.forestLeaves.blockID, 0);
				setBlockAndMetadata(world, i + i2, j + l + 2, k + k2, mod_LionKing.forestLeaves.blockID, 0);
				generateVines(world, random, i + i2, j + l1, k + k2);
				generateVines(world, random, i + i2, j + l, k + k2);
				generateVines(world, random, i + i2, j + l + 2, k + k2);
			}
		}
		for (int i3 = -2; i3 < 3; i3++) {
			for (int k3 = -2; k3 < 3; k3++) {
				setBlockAndMetadata(world, i + i3, j + l + 1, k + k3, mod_LionKing.forestLeaves.blockID, 0);
				generateVines(world, random, i + i3, j + l + 1, k + k3);
			}
		}
		for (int j1 = 0; j1 <= l + 1; j1++) {
			setBlockAndMetadata(world, i, j + j1, k, mod_LionKing.prideWood.blockID, 1);
		}
		world.setBlock(i, j - 1, k, Block.dirt.blockID, 0, 2);
		return true;
	}

	private boolean generateLargeTree(World world, Random random, int i, int j, int k, int l) {
		int l1 = l - 4;
		if (l > 11 && l < 15) {
			l1--;
		}
		if (l > 14 && l < 19) {
			l1 -= 2;
		}
		if (l > 18) {
			l1 -= 3;
		}
		for (int j1 = 1; j1 <= l; j1++) {
			if (!isBlockReplaceable(world, i, j + j1, k)) {
				return false;
			}
		}
		for (int i1 = -2; i1 < 3; i1++) {
			for (int k1 = -2; k1 < 3; k1++) {
				if (i1 == -2 && k1 == -2 || i1 == 2 && k1 == -2 || i1 == -2 && k1 == 2 || i1 == 2 && k1 == 2) {
				} else if (!isBlockReplaceable(world, i + i1, j + l + 2, k + k1)) {
					return false;
				}
			}
		}
		for (int i1 = -1; i1 < 2; i1++) {
			for (int k1 = -1; k1 < 2; k1++) {
				if (!isBlockReplaceable(world, i + i1, j + l, k + k1)) {
					return false;
				}
			}
		}
		for (int i1 = -3; i1 < 4; i1++) {
			for (int k1 = -3; k1 < 4; k1++) {
				if (!isBlockReplaceable(world, i + i1, j + l + 1, k + k1)) {
					return false;
				}
			}
		}
		for (int i1 = -2; i1 < 3; i1++) {
			for (int k1 = -2; k1 < 3; k1++) {
				if (!isBlockReplaceable(world, i + i1, j + l + 2, k + k1)) {
					return false;
				}
			}
		}
		for (int j1 = 0; j1 < 3; j1++) {
			int i1 = j1 == 2 ? 2 : 1;
			int j2 = j1 == 0 ? l1 - 1 : j1 == 1 ? l - 1 : l;
			if (!isBlockReplaceable(world, i - i1, j + j2, k) || !isBlockReplaceable(world, i + i1, j + j2, k) || !isBlockReplaceable(world, i, j + j2, k - i1) || !isBlockReplaceable(world, i, j + j2, k + i1)) {
				return false;
			}
		}
		for (int i1 = -2; i1 < 3; i1++) {
			for (int k1 = -2; k1 < 3; k1++) {
				if (i1 == -2 && k1 == -2 || i1 == 2 && k1 == -2 || i1 == -2 && k1 == 2 || i1 == 2 && k1 == 2) {
				} else {
					setBlockAndMetadata(world, i + i1, j + l1, k + k1, mod_LionKing.forestLeaves.blockID, 0);
					generateVines(world, random, i + i1, j + l1, k + k1);
				}
			}
		}
		for (int i2 = -1; i2 < 2; i2++) {
			for (int k2 = -1; k2 < 2; k2++) {
				setBlockAndMetadata(world, i + i2, j + l, k + k2, mod_LionKing.forestLeaves.blockID, 0);
				generateVines(world, random, i + i2, j + l, k + k2);
			}
		}
		for (int i3 = -3; i3 < 4; i3++) {
			for (int k3 = -3; k3 < 4; k3++) {
				setBlockAndMetadata(world, i + i3, j + l + 1, k + k3, mod_LionKing.forestLeaves.blockID, 0);
				generateVines(world, random, i + i3, j + l + 1, k + k3);
			}
		}
		for (int i2 = -2; i2 < 3; i2++) {
			for (int k2 = -2; k2 < 3; k2++) {
				setBlockAndMetadata(world, i + i2, j + l + 2, k + k2, mod_LionKing.forestLeaves.blockID, 0);
				generateVines(world, random, i + i2, j + l + 2, k + k2);
			}
		}
		for (int j1 = 0; j1 < 3; j1++) {
			int i1 = j1 == 2 ? 2 : 1;
			int j2 = j1 == 0 ? l1 - 1 : j1 == 1 ? l - 1 : l;
			setBlockAndMetadata(world, i - i1, j + j2, k, mod_LionKing.prideWood.blockID, 1);
			setBlockAndMetadata(world, i + i1, j + j2, k, mod_LionKing.prideWood.blockID, 1);
			setBlockAndMetadata(world, i, j + j2, k - i1, mod_LionKing.prideWood.blockID, 1);
			setBlockAndMetadata(world, i, j + j2, k + i1, mod_LionKing.prideWood.blockID, 1);
		}
		for (int j1 = 0; j1 <= l + 1; j1++) {
			setBlockAndMetadata(world, i, j + j1, k, mod_LionKing.prideWood.blockID, 1);
		}
		world.setBlock(i, j - 1, k, Block.dirt.blockID, 0, 2);
		return true;
	}

	private void generateVines(World world, Random random, int i, int j, int k) {
		if (random.nextInt(9) == 0 && world.isAirBlock(i - 1, j, k)) {
			placeVines(world, i - 1, j, k, 8);
		}
		if (random.nextInt(9) == 0 && world.isAirBlock(i + 1, j, k)) {
			placeVines(world, i + 1, j, k, 2);
		}
		if (random.nextInt(9) == 0 && world.isAirBlock(i, j, k - 1)) {
			placeVines(world, i, j, k - 1, 1);
		}
		if (random.nextInt(9) == 0 && world.isAirBlock(i, j, k + 1)) {
			placeVines(world, i, j, k + 1, 4);
		}
	}

	private void placeVines(World world, int i, int j, int k, int meta) {
		int j1 = j;
		world.setBlock(i, j1, k, Block.vine.blockID, meta, 3);
		int l = world.rand.nextInt(2) + 6;
		while (true) {
			--j1;
			if (world.getBlockId(i, j1, k) != 0 || l <= 0) {
				return;
			}
			world.setBlock(i, j1, k, Block.vine.blockID, meta, 3);
			--l;
		}
	}

	private boolean isBlockReplaceable(IBlockAccess world, int i, int j, int k) {
		if (world.isAirBlock(i, j, k)) {
			return true;
		}
		int l = world.getBlockId(i, j, k);
		return l == Block.vine.blockID || l == mod_LionKing.forestLeaves.blockID;
	}
}

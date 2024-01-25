package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenTrees extends WorldGenerator {
	private final boolean doBlockNotify;
	private boolean isLarge;

	public LKWorldGenTrees(boolean flag) {
		super(flag);
		doBlockNotify = flag;
	}

	public LKWorldGenTrees setLarge() {
		isLarge = true;
		return this;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		int l = random.nextInt(6);
		BiomeGenBase biomegenbase = world.getBiomeGenForCoords(i, k);
		int trunkWidth = 1;
		if (biomegenbase instanceof LKBiomeGenMountains && random.nextInt(3) != 0) {
			l += random.nextInt(6) + 5;
		}
		if (isLarge || !doBlockNotify && biomegenbase instanceof LKBiomeGenWoodedSavannah && random.nextInt(3) == 0) {
			l += random.nextInt(3) + 3;
			trunkWidth = 2;
		}
		boolean flag = true;

		if (j < 1 || j + l + 5 > 256) {
			return false;
		}

		if (!isBlockReplaceable(world, i, j, k) && world.getBlockId(i, j, k) != mod_LionKing.sapling.blockID) {
			return false;
		}

		if (j >= 256 - l - 5) {
			return false;
		}

		int j1 = world.getBlockId(i, j - 1, k);
		if (biomegenbase instanceof LKBiomeGenAridSavannah) {
			if (j1 != Block.sand.blockID && j1 != Block.dirt.blockID && j1 != Block.grass.blockID) {
				return false;
			}
		} else {
			if (j1 != Block.grass.blockID && j1 != Block.dirt.blockID) {
				return false;
			}
		}

		for (int j2 = 0; j2 < l + 1; j2++) {
			for (int i1 = i; i1 < i + trunkWidth; i1++) {
				for (int k1 = k; k1 < k + trunkWidth; k1++) {
					if (!isBlockReplaceable(world, i1, j + j2, k1) && world.getBlockId(i1, j + j2, k1) != mod_LionKing.sapling.blockID) {
						flag = false;
					}
				}
			}
		}

		for (int i2 = -3; i2 < 4; i2++) {
			for (int k2 = -3; k2 < 4; k2++) {
				if (!isBlockReplaceable(world, i + i2, j + l + 3, k + k2)) {
					flag = false;
				}
			}
		}

		for (int i2 = -2; i2 < 3; i2++) {
			for (int k2 = -2; k2 < 3; k2++) {
				if (!isBlockReplaceable(world, i + i2, j + l + 4, k + k2)) {
					flag = false;
				}
			}
		}

		if (!isBlockReplaceable(world, i, j + l + 1, k)
				|| !isBlockReplaceable(world, i - 1, j + l + 1, k)
				|| !isBlockReplaceable(world, i + 1, j + l + 1, k)
				|| !isBlockReplaceable(world, i, j + l + 1, k - 1)
				|| !isBlockReplaceable(world, i, j + l + 1, k + 1)
				|| !isBlockReplaceable(world, i, j + l + 2, k)
				|| !isBlockReplaceable(world, i - 2, j + l + 2, k)
				|| !isBlockReplaceable(world, i + 2, j + l + 2, k)
				|| !isBlockReplaceable(world, i, j + l + 2, k - 2)
				|| !isBlockReplaceable(world, i, j + l + 2, k + 2)
				|| !isBlockReplaceable(world, i, j + l + 2, k)
				|| !isBlockReplaceable(world, i - 2, j + l + 3, k)
				|| !isBlockReplaceable(world, i + 2, j + l + 3, k)
				|| !isBlockReplaceable(world, i, j + l + 3, k - 2)
				|| !isBlockReplaceable(world, i, j + l + 3, k + 2)) {
			flag = false;
		}

		if (!flag) {
			return false;
		}

		for (int i2 = -3; i2 < 4; i2++) {
			for (int k2 = -3; k2 < 4; k2++) {
				setBlockAndMetadata(world, i + i2, j + l + 3, k + k2, mod_LionKing.leaves.blockID, 0);
			}
		}

		world.setBlock(i - 3, j + l + 3, k - 3, 0, 0, 3);
		world.setBlock(i + 3, j + l + 3, k - 3, 0, 0, 3);
		world.setBlock(i - 3, j + l + 3, k + 3, 0, 0, 3);
		world.setBlock(i + 3, j + l + 3, k + 3, 0, 0, 3);

		for (int i2 = -2; i2 < 3; i2++) {
			for (int k2 = -2; k2 < 3; k2++) {
				setBlockAndMetadata(world, i + i2, j + l + 4, k + k2, mod_LionKing.leaves.blockID, 0);
			}
		}

		world.setBlock(i - 2, j + l + 4, k - 2, 0, 0, 3);
		world.setBlock(i + 2, j + l + 4, k - 2, 0, 0, 3);
		world.setBlock(i - 2, j + l + 4, k + 2, 0, 0, 3);
		world.setBlock(i + 2, j + l + 4, k + 2, 0, 0, 3);

		for (int j2 = 0; j2 < l + 1; j2++) {
			for (int i1 = i; i1 < i + trunkWidth; i1++) {
				for (int k1 = k; k1 < k + trunkWidth; k1++) {
					setBlockAndMetadata(world, i1, j + j2, k1, mod_LionKing.prideWood.blockID, 0);
				}
			}
		}

		setBlockAndMetadata(world, i, j + l + 1, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i - 1, j + l + 1, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i + 1, j + l + 1, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i, j + l + 1, k - 1, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i, j + l + 1, k + 1, mod_LionKing.prideWood.blockID, 0);

		setBlockAndMetadata(world, i, j + l + 2, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i - 2, j + l + 2, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i + 2, j + l + 2, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i, j + l + 2, k - 2, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i, j + l + 2, k + 2, mod_LionKing.prideWood.blockID, 0);

		setBlockAndMetadata(world, i, j + l + 3, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i - 2, j + l + 3, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i + 2, j + l + 3, k, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i, j + l + 3, k - 2, mod_LionKing.prideWood.blockID, 0);
		setBlockAndMetadata(world, i, j + l + 3, k + 2, mod_LionKing.prideWood.blockID, 0);

		world.setBlock(i, j - 1, k, Block.dirt.blockID, 0, 2);

		if (l > 4) {
			tryToBranchAt(world, random, i, j, k, l);
		}
		if (l > 7) {
			tryToBranchAt(world, random, i, j, k, l - 1);
		}
		if (random.nextInt(4) != 0) {
			for (int tries = 0; tries < (random.nextInt(3) == 0 ? 2 : random.nextInt(5) == 0 ? 3 : 1); tries++) {
				tryCanopyShift(world, random, i, j + l + 3, k, random.nextInt(3) == 0 ? 1 : 2, random.nextInt(4));
			}
		}
		return true;
	}

	private void tryCanopyShift(World world, Random random, int i, int j, int k, int length, int direction) {
		int length2 = length;
		int k1 = k;
		while (true) {
			int length1 = length2;
			if (direction == 0) {
				if (world.isAirBlock(i - 4, j, k1 - 3) && world.isAirBlock(i - 4, j, k1 - 2) && world.isAirBlock(i - 4, j, k1 - 1)
						&& world.isAirBlock(i - 4, j, k1)
						&& world.isAirBlock(i - 4, j, k1 + 1) && world.isAirBlock(i - 4, j, k1 + 2) && world.isAirBlock(i - 4, j, k1 + 3)
						&& world.isAirBlock(i - 4, j + 1, k1 - 3) && world.isAirBlock(i - 4, j + 1, k1 - 2) && world.isAirBlock(i - 4, j + 1, k1 - 1)
						&& world.isAirBlock(i - 4, j + 1, k1)
						&& world.isAirBlock(i - 4, j + 1, k1 + 1) && world.isAirBlock(i - 4, j + 1, k1 + 2) && world.isAirBlock(i - 4, j + 1, k1 + 3)
						&& world.isAirBlock(i - 3, j + 1, k1 - 3) && world.isAirBlock(i - 3, j + 1, k1 - 2) && world.isAirBlock(i - 3, j + 1, k1 - 1)
						&& world.isAirBlock(i - 3, j + 1, k1)
						&& world.isAirBlock(i - 3, j + 1, k1 + 1) && world.isAirBlock(i - 3, j + 1, k1 + 2) && world.isAirBlock(i - 3, j + 1, k1 + 3)) {
					setBlockAndMetadata(world, i - 3, j, k1 - 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i - 3, j, k1 + 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i - 2, j + 1, k1 - 2, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i - 2, j + 1, k1 + 2, mod_LionKing.leaves.blockID, 0);
					for (int i1 = -2; i1 < 3; i1++) {
						setBlockAndMetadata(world, i - 4, j, k1 + i1, mod_LionKing.leaves.blockID, 0);
					}
					for (int i1 = -1; i1 < 2; i1++) {
						setBlockAndMetadata(world, i - 3, j + 1, k1 + i1, mod_LionKing.leaves.blockID, 0);
					}
					setBlockAndMetadata(world, i - 3, j, k1, mod_LionKing.prideWood.blockID, 0);
				}
			}
			if (direction == 1) {
				if (world.isAirBlock(i + 4, j, k1 - 3) && world.isAirBlock(i + 4, j, k1 - 2) && world.isAirBlock(i + 4, j, k1 - 1)
						&& world.isAirBlock(i + 4, j, k1)
						&& world.isAirBlock(i + 4, j, k1 + 1) && world.isAirBlock(i + 4, j, k1 + 2) && world.isAirBlock(i + 4, j, k1 + 3)
						&& world.isAirBlock(i + 4, j + 1, k1 - 3) && world.isAirBlock(i + 4, j + 1, k1 - 2) && world.isAirBlock(i + 4, j + 1, k1 - 1)
						&& world.isAirBlock(i + 4, j + 1, k1)
						&& world.isAirBlock(i + 4, j + 1, k1 + 1) && world.isAirBlock(i + 4, j + 1, k1 + 2) && world.isAirBlock(i + 4, j + 1, k1 + 3)
						&& world.isAirBlock(i + 3, j + 1, k1 - 3) && world.isAirBlock(i + 3, j + 1, k1 - 2) && world.isAirBlock(i + 3, j + 1, k1 - 1)
						&& world.isAirBlock(i + 3, j + 1, k1)
						&& world.isAirBlock(i + 3, j + 1, k1 + 1) && world.isAirBlock(i + 3, j + 1, k1 + 2) && world.isAirBlock(i + 3, j + 1, k1 + 3)) {
					setBlockAndMetadata(world, i + 3, j, k1 - 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 3, j, k1 + 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 2, j + 1, k1 - 2, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 2, j + 1, k1 + 2, mod_LionKing.leaves.blockID, 0);
					for (int i1 = -2; i1 < 3; i1++) {
						setBlockAndMetadata(world, i + 4, j, k1 + i1, mod_LionKing.leaves.blockID, 0);
					}
					for (int i1 = -1; i1 < 2; i1++) {
						setBlockAndMetadata(world, i + 3, j + 1, k1 + i1, mod_LionKing.leaves.blockID, 0);
					}
					setBlockAndMetadata(world, i + 3, j, k1, mod_LionKing.prideWood.blockID, 0);
				}
			}
			if (direction == 2) {
				if (world.isAirBlock(i - 3, j, k1 - 4) && world.isAirBlock(i - 2, j, k1 - 4) && world.isAirBlock(i - 1, j, k1 - 4)
						&& world.isAirBlock(i, j, k1 - 4)
						&& world.isAirBlock(i + 1, j, k1 - 4) && world.isAirBlock(i + 2, j, k1 - 4) && world.isAirBlock(i + 3, j, k1 - 4)
						&& world.isAirBlock(i - 3, j + 1, k1 - 4) && world.isAirBlock(i - 2, j + 1, k1 - 4) && world.isAirBlock(i - 1, j + 1, k1 - 4)
						&& world.isAirBlock(i, j + 1, k1 - 4)
						&& world.isAirBlock(i + 1, j + 1, k1 - 4) && world.isAirBlock(i + 2, j + 1, k1 - 4) && world.isAirBlock(i + 3, j + 1, k1 - 4)
						&& world.isAirBlock(i - 3, j + 1, k1 - 3) && world.isAirBlock(i - 2, j + 1, k1 - 3) && world.isAirBlock(i - 1, j + 1, k1 - 3)
						&& world.isAirBlock(i, j + 1, k1 - 3)
						&& world.isAirBlock(i + 1, j + 1, k1 - 3) && world.isAirBlock(i + 2, j + 1, k1 - 3) && world.isAirBlock(i + 3, j + 1, k1 - 3)) {
					setBlockAndMetadata(world, i - 3, j, k1 - 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 3, j, k1 - 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i - 2, j + 1, k1 - 2, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 2, j + 1, k1 - 2, mod_LionKing.leaves.blockID, 0);
					for (int i1 = -2; i1 < 3; i1++) {
						setBlockAndMetadata(world, i + i1, j, k1 - 4, mod_LionKing.leaves.blockID, 0);
					}
					for (int i1 = -1; i1 < 2; i1++) {
						setBlockAndMetadata(world, i + i1, j + 1, k1 - 3, mod_LionKing.leaves.blockID, 0);
					}
					setBlockAndMetadata(world, i, j, k1 - 3, mod_LionKing.prideWood.blockID, 0);
				}
			}
			if (direction == 3) {
				if (world.isAirBlock(i - 3, j, k1 + 4) && world.isAirBlock(i - 2, j, k1 + 4) && world.isAirBlock(i - 1, j, k1 + 4)
						&& world.isAirBlock(i, j, k1 + 4)
						&& world.isAirBlock(i + 1, j, k1 + 4) && world.isAirBlock(i + 2, j, k1 + 4) && world.isAirBlock(i + 3, j, k1 + 4)
						&& world.isAirBlock(i - 3, j + 1, k1 + 4) && world.isAirBlock(i - 2, j + 1, k1 + 4) && world.isAirBlock(i - 1, j + 1, k1 + 4)
						&& world.isAirBlock(i, j + 1, k1 + 4)
						&& world.isAirBlock(i + 1, j + 1, k1 + 4) && world.isAirBlock(i + 2, j + 1, k1 + 4) && world.isAirBlock(i + 3, j + 1, k1 + 4)
						&& world.isAirBlock(i - 3, j + 1, k1 + 3) && world.isAirBlock(i - 2, j + 1, k1 + 3) && world.isAirBlock(i - 1, j + 1, k1 + 3)
						&& world.isAirBlock(i, j + 1, k1 + 3)
						&& world.isAirBlock(i + 1, j + 1, k1 + 3) && world.isAirBlock(i + 2, j + 1, k1 + 3) && world.isAirBlock(i + 3, j + 1, k1 + 3)) {
					setBlockAndMetadata(world, i - 3, j, k1 + 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 3, j, k1 + 3, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i - 2, j + 1, k1 + 2, mod_LionKing.leaves.blockID, 0);
					setBlockAndMetadata(world, i + 2, j + 1, k1 + 2, mod_LionKing.leaves.blockID, 0);
					for (int i1 = -2; i1 < 3; i1++) {
						setBlockAndMetadata(world, i + i1, j, k1 + 4, mod_LionKing.leaves.blockID, 0);
					}
					for (int i1 = -1; i1 < 2; i1++) {
						setBlockAndMetadata(world, i + i1, j + 1, k1 + 3, mod_LionKing.leaves.blockID, 0);
					}
					setBlockAndMetadata(world, i, j, k1 + 3, mod_LionKing.prideWood.blockID, 0);
				}
			}
			if (length1 > 1) {
				length1--;
				switch (direction) {
					case 0:
						tryCanopyShift(world, random, i - 1, j, k1, length1, direction);
						break;
					case 1:
						tryCanopyShift(world, random, i + 1, j, k1, length1, direction);
						break;
					case 2:
						tryCanopyShift(world, random, i, j, k1 - 1, length1, direction);
						break;
					case 3:
						length2 = length1;
						k1 += 1;
						continue;
				}
			}
			return;
		}
	}

	private void tryToBranchAt(World world, Random random, int i, int j, int k, int l) {
		int direction = random.nextInt(4);
		if (direction == 0) {
			if (world.isAirBlock(i - 1, j + l - 1, k) && world.isAirBlock(i - 1, j + l, k) && world.isAirBlock(i - 2, j + l, k) && world.isAirBlock(i - 3, j + l, k)
					&& world.isAirBlock(i - 4, j + l, k)
					&& world.isAirBlock(i - 1, j + l, k - 1) && world.isAirBlock(i - 2, j + l, k - 1) && world.isAirBlock(i - 3, j + l, k - 1)
					&& world.isAirBlock(i - 4, j + l, k - 1)
					&& world.isAirBlock(i - 1, j + l, k + 1) && world.isAirBlock(i - 2, j + l, k + 1) && world.isAirBlock(i - 3, j + l, k + 1)
					&& world.isAirBlock(i - 4, j + l, k + 1)
					&& world.isAirBlock(i - 2, j + l, k + 2) && world.isAirBlock(i - 3, j + l, k + 2)
					&& world.isAirBlock(i - 2, j + l, k - 2) && world.isAirBlock(i - 3, j + l, k - 2)
					&& world.isAirBlock(i - 2, j + l + 1, k + 1) && world.isAirBlock(i - 3, j + l + 1, k + 1)
					&& world.isAirBlock(i - 2, j + l + 1, k - 1) && world.isAirBlock(i - 3, j + l + 1, k - 1)
					&& world.isAirBlock(i - 2, j + l + 1, k) && world.isAirBlock(i - 3, j + l + 1, k)) {
				setBlockAndMetadata(world, i - 1, j + l - 1, k, mod_LionKing.prideWood.blockID, 0);
				for (int i1 = 1; i1 < 5; i1++) {
					for (int k1 = -1; k1 < 2; k1++) {
						setBlockAndMetadata(world, i - i1, j + l, k + k1, mod_LionKing.leaves.blockID, 0);
					}
				}
				setBlockAndMetadata(world, i - 2, j + l, k, mod_LionKing.prideWood.blockID, 0);
				setBlockAndMetadata(world, i - 3, j + l, k, mod_LionKing.prideWood.blockID, 0);

				setBlockAndMetadata(world, i - 2, j + l, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 3, j + l, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 2, j + l, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 3, j + l, k + 2, mod_LionKing.leaves.blockID, 0);

				setBlockAndMetadata(world, i - 2, j + l + 1, k - 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 3, j + l + 1, k - 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 2, j + l + 1, k + 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 3, j + l + 1, k + 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 2, j + l + 1, k, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 3, j + l + 1, k, mod_LionKing.leaves.blockID, 0);
			}
		}
		if (direction == 1) {
			if (world.isAirBlock(i + 1, j + l - 1, k) && world.isAirBlock(i + 1, j + l, k) && world.isAirBlock(i + 2, j + l, k) && world.isAirBlock(i + 3, j + l, k)
					&& world.isAirBlock(i + 4, j + l, k)
					&& world.isAirBlock(i + 1, j + l, k - 1) && world.isAirBlock(i + 2, j + l, k - 1) && world.isAirBlock(i + 3, j + l, k - 1)
					&& world.isAirBlock(i + 4, j + l, k - 1)
					&& world.isAirBlock(i + 1, j + l, k + 1) && world.isAirBlock(i + 2, j + l, k + 1) && world.isAirBlock(i + 3, j + l, k + 1)
					&& world.isAirBlock(i + 4, j + l, k + 1)
					&& world.isAirBlock(i + 2, j + l, k + 2) && world.isAirBlock(i + 3, j + l, k + 2)
					&& world.isAirBlock(i + 2, j + l, k - 2) && world.isAirBlock(i + 3, j + l, k - 2)
					&& world.isAirBlock(i + 2, j + l + 1, k + 1) && world.isAirBlock(i + 3, j + l + 1, k + 1)
					&& world.isAirBlock(i + 2, j + l + 1, k - 1) && world.isAirBlock(i + 3, j + l + 1, k - 1)
					&& world.isAirBlock(i + 2, j + l + 1, k) && world.isAirBlock(i + 3, j + l + 1, k)) {
				setBlockAndMetadata(world, i + 1, j + l - 1, k, mod_LionKing.prideWood.blockID, 0);
				for (int i1 = 1; i1 < 5; i1++) {
					for (int k1 = -1; k1 < 2; k1++) {
						setBlockAndMetadata(world, i + i1, j + l, k + k1, mod_LionKing.leaves.blockID, 0);
					}
				}
				setBlockAndMetadata(world, i + 2, j + l, k, mod_LionKing.prideWood.blockID, 0);
				setBlockAndMetadata(world, i + 3, j + l, k, mod_LionKing.prideWood.blockID, 0);

				setBlockAndMetadata(world, i + 2, j + l, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 3, j + l, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 3, j + l, k + 2, mod_LionKing.leaves.blockID, 0);

				setBlockAndMetadata(world, i + 2, j + l + 1, k - 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 3, j + l + 1, k - 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l + 1, k + 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 3, j + l + 1, k + 1, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l + 1, k, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 3, j + l + 1, k, mod_LionKing.leaves.blockID, 0);
			}
		}
		if (direction == 2) {
			if (world.isAirBlock(i, j + l - 1, k - 1) && world.isAirBlock(i, j + l, k - 1) && world.isAirBlock(i, j + l, k - 2) && world.isAirBlock(i, j + l, k - 3)
					&& world.isAirBlock(i, j + l, k - 4)
					&& world.isAirBlock(i - 1, j + l, k - 1) && world.isAirBlock(i - 1, j + l, k - 2) && world.isAirBlock(i - 1, j + l, k - 3)
					&& world.isAirBlock(i - 1, j + l, k - 4)
					&& world.isAirBlock(i + 1, j + l, k - 1) && world.isAirBlock(i + 1, j + l, k - 2) && world.isAirBlock(i + 1, j + l, k - 3)
					&& world.isAirBlock(i + 1, j + l, k - 4)
					&& world.isAirBlock(i + 2, j + l, k - 2) && world.isAirBlock(i + 2, j + l, k - 3)
					&& world.isAirBlock(i - 2, j + l, k - 2) && world.isAirBlock(i - 2, j + l, k - 3)
					&& world.isAirBlock(i + 1, j + l + 1, k - 2) && world.isAirBlock(i + 1, j + l + 1, k - 3)
					&& world.isAirBlock(i - 1, j + l + 1, k - 2) && world.isAirBlock(i - 1, j + l + 1, k - 3)
					&& world.isAirBlock(i, j + l + 1, k - 2) && world.isAirBlock(i, j + l + 1, k - 3)) {
				setBlockAndMetadata(world, i, j + l - 1, k - 1, mod_LionKing.prideWood.blockID, 0);
				for (int k1 = 1; k1 < 5; k1++) {
					for (int i1 = -1; i1 < 2; i1++) {
						setBlockAndMetadata(world, i + i1, j + l, k - k1, mod_LionKing.leaves.blockID, 0);
					}
				}
				setBlockAndMetadata(world, i, j + l, k - 2, mod_LionKing.prideWood.blockID, 0);
				setBlockAndMetadata(world, i, j + l, k - 3, mod_LionKing.prideWood.blockID, 0);

				setBlockAndMetadata(world, i - 2, j + l, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 2, j + l, k - 3, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l, k - 3, mod_LionKing.leaves.blockID, 0);

				setBlockAndMetadata(world, i - 1, j + l + 1, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 1, j + l + 1, k - 3, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 1, j + l + 1, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 1, j + l + 1, k - 3, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i, j + l + 1, k - 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i, j + l + 1, k - 3, mod_LionKing.leaves.blockID, 0);
			}
		}
		if (direction == 3) {
			if (world.isAirBlock(i, j + l - 1, k + 1) && world.isAirBlock(i, j + l, k + 1) && world.isAirBlock(i, j + l, k + 2) && world.isAirBlock(i, j + l, k + 3)
					&& world.isAirBlock(i, j + l, k + 4)
					&& world.isAirBlock(i - 1, j + l, k + 1) && world.isAirBlock(i - 1, j + l, k + 2) && world.isAirBlock(i - 1, j + l, k + 3)
					&& world.isAirBlock(i - 1, j + l, k + 4)
					&& world.isAirBlock(i + 1, j + l, k + 1) && world.isAirBlock(i + 1, j + l, k + 2) && world.isAirBlock(i + 1, j + l, k + 3)
					&& world.isAirBlock(i + 1, j + l, k + 4)
					&& world.isAirBlock(i + 2, j + l, k + 2) && world.isAirBlock(i + 2, j + l, k + 3)
					&& world.isAirBlock(i - 2, j + l, k + 2) && world.isAirBlock(i - 2, j + l, k + 3)
					&& world.isAirBlock(i + 1, j + l + 1, k + 2) && world.isAirBlock(i + 1, j + l + 1, k + 3)
					&& world.isAirBlock(i - 1, j + l + 1, k + 2) && world.isAirBlock(i - 1, j + l + 1, k + 3)
					&& world.isAirBlock(i, j + l + 1, k + 2) && world.isAirBlock(i, j + l + 1, k + 3)) {
				setBlockAndMetadata(world, i, j + l - 1, k + 1, mod_LionKing.prideWood.blockID, 0);
				for (int k1 = 1; k1 < 5; k1++) {
					for (int i1 = -1; i1 < 2; i1++) {
						setBlockAndMetadata(world, i + i1, j + l, k + k1, mod_LionKing.leaves.blockID, 0);
					}
				}
				setBlockAndMetadata(world, i, j + l, k + 2, mod_LionKing.prideWood.blockID, 0);
				setBlockAndMetadata(world, i, j + l, k + 3, mod_LionKing.prideWood.blockID, 0);

				setBlockAndMetadata(world, i - 2, j + l, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 2, j + l, k + 3, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 2, j + l, k + 3, mod_LionKing.leaves.blockID, 0);

				setBlockAndMetadata(world, i - 1, j + l + 1, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i - 1, j + l + 1, k + 3, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 1, j + l + 1, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i + 1, j + l + 1, k + 3, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i, j + l + 1, k + 2, mod_LionKing.leaves.blockID, 0);
				setBlockAndMetadata(world, i, j + l + 1, k + 3, mod_LionKing.leaves.blockID, 0);
			}
		}
	}

	private boolean isBlockReplaceable(IBlockAccess world, int i, int j, int k) {
		return world.isAirBlock(i, j, k) || world.getBlockId(i, j, k) == mod_LionKing.leaves.blockID;
	}
}

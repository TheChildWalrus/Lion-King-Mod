package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenLiquids extends WorldGenerator {
	private final int liquidBlockId;

	public LKWorldGenLiquids(int i) {
		liquidBlockId = i;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		if (world.getBlockId(i, j + 1, k) != mod_LionKing.pridestone.blockID) {
			return false;
		}
		if (world.getBlockId(i, j - 1, k) != mod_LionKing.pridestone.blockID) {
			return false;
		}
		if (world.getBlockId(i, j, k) != 0 && world.getBlockId(i, j, k) != mod_LionKing.pridestone.blockID) {
			return false;
		}
		int l = 0;
		if (world.getBlockId(i - 1, j, k) == mod_LionKing.pridestone.blockID) {
			l++;
		}
		if (world.getBlockId(i + 1, j, k) == mod_LionKing.pridestone.blockID) {
			l++;
		}
		if (world.getBlockId(i, j, k - 1) == mod_LionKing.pridestone.blockID) {
			l++;
		}
		if (world.getBlockId(i, j, k + 1) == mod_LionKing.pridestone.blockID) {
			l++;
		}
		int i1 = 0;
		if (world.isAirBlock(i - 1, j, k)) {
			i1++;
		}
		if (world.isAirBlock(i + 1, j, k)) {
			i1++;
		}
		if (world.isAirBlock(i, j, k - 1)) {
			i1++;
		}
		if (world.isAirBlock(i, j, k + 1)) {
			i1++;
		}
		if (l == 3 && i1 == 1) {
			world.setBlock(i, j, k, liquidBlockId, 0, 2);
			world.scheduledUpdatesAreImmediate = true;
			Block.blocksList[liquidBlockId].updateTick(world, i, j, k, random);
			world.scheduledUpdatesAreImmediate = false;
		}
		return true;
	}
}

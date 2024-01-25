package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKBiomeGenRiver extends LKPrideLandsBiome {
	public LKBiomeGenRiver(int i) {
		super(i);
		spawnableCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(LKEntityCrocodile.class, 3, 4, 4));
		lkDecorator.treesPerChunk = 0;
		lkDecorator.mangoPerChunk = 0;
		lkDecorator.grassPerChunk = 3;
		lkDecorator.whiteFlowersPerChunk = 5;
		lkDecorator.purpleFlowersPerChunk = 0;
		lkDecorator.blueFlowersPerChunk = 0;
		lkDecorator.logsPerChunk = 0;
		lkDecorator.maizePerChunk = 10;
		lkDecorator.zazuPerChunk = 0;
	}

	@Override
	public void decoratePrideLands(World world, Random random, int i, int j) {
		if (random.nextInt(10) == 0) {
			lkDecorator.treesPerChunk += 1 + random.nextInt(2);
		}

		super.decoratePrideLands(world, random, i, j);

		lkDecorator.treesPerChunk = 0;

		for (int k = 0; k < 3; k++) {
			int i1 = i + random.nextInt(16) + 8;
			int j1 = j + random.nextInt(16) + 8;
			new WorldGenSand(7, Block.sand.blockID).generate(world, random, i1, world.getTopSolidOrLiquidBlock(i1, j1), j1);
		}
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		return new LKWorldGenBananaTrees(false);
	}
}

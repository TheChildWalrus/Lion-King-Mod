package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;

import java.util.*;

public abstract class LKOutlandsBiome extends BiomeGenBase {
	public static BiomeGenBase outlands;
	public static BiomeGenBase outlandsMountains;
	public static BiomeGenBase outlandsRiver;

	protected LKOutlandsDecorator outlandsDecorator;

	protected LKOutlandsBiome(int i) {
		super(i);

		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();

		spawnableMonsterList.add(new SpawnListEntry(LKEntityOutlander.class, 40, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(LKEntityOutlandess.class, 40, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(LKEntityVulture.class, 30, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(LKEntitySkeletalHyena.class, 10, 4, 6));

		outlandsDecorator = new LKOutlandsDecorator(this);

		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sand.blockID;

		setDisableRain();
	}

	public void decorateOutlands(World world, Random random, int i, int j) {
		outlandsDecorator.decorate(world, random, i, j);
	}

	public WorldGenerator getOutlandsTreeGen(Random random) {
		return new LKWorldGenDeadTrees();
	}
}

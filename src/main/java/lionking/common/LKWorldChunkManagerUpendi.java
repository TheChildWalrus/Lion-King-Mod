package lionking.common;

import net.minecraft.world.*;
import net.minecraft.world.biome.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LKWorldChunkManagerUpendi extends WorldChunkManager {
	private final BiomeGenBase theBiome;
	private final float temperature;
	private final float rainfall;

	public LKWorldChunkManagerUpendi() {
		BiomeGenBase biome = LKBiomeGenUpendi.upendi;
		theBiome = biome;
		temperature = biome.temperature;
		rainfall = biome.rainfall;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int i, int j) {
		return theBiome;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int i, int j, int k, int l) {
		BiomeGenBase[] biomes1 = biomes;
		if (biomes1 == null || biomes1.length < k * l) {
			biomes1 = new BiomeGenBase[k * l];
		}

		Arrays.fill(biomes1, 0, k * l, theBiome);
		return biomes1;
	}

	@Override
	public float[] getTemperatures(float[] floats, int i, int j, int k, int l) {
		float[] floats1 = floats;
		if (floats1 == null || floats1.length < k * l) {
			floats1 = new float[k * l];
		}

		Arrays.fill(floats1, 0, k * l, temperature);
		return floats1;
	}

	@Override
	public float[] getRainfall(float[] floats, int i, int j, int k, int l) {
		float[] floats1 = floats;
		if (floats1 == null || floats1.length < k * l) {
			floats1 = new float[k * l];
		}

		Arrays.fill(floats1, 0, k * l, rainfall);
		return floats1;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomes, int i, int j, int k, int l) {
		BiomeGenBase[] biomes1 = biomes;
		if (biomes1 == null || biomes1.length < k * l) {
			biomes1 = new BiomeGenBase[k * l];
		}

		Arrays.fill(biomes1, 0, k * l, theBiome);
		return biomes1;
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] biomes, int i, int j, int k, int l, boolean flag) {
		return loadBlockGeneratorData(biomes, i, j, k, l);
	}

	@Override
	public ChunkPosition findBiomePosition(int i, int j, int k, List list, Random random) {
		return list.contains(theBiome) ? new ChunkPosition(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1)) : null;
	}

	@Override
	public boolean areBiomesViable(int i, int j, int k, List list) {
		return list.contains(theBiome);
	}
}

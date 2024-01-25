package lionking.common;

import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.layer.*;

import java.util.List;
import java.util.Random;

public class LKWorldChunkManagerOutlands extends WorldChunkManager {
	private final GenLayer genBiomes;
	private final GenLayer biomeIndexLayer;
	private final BiomeCache biomeCache;

	public LKWorldChunkManagerOutlands(long l, WorldType worldType) {
		biomeCache = new BiomeCache(this);
		GenLayer[] var4 = LKGenLayerWorld.createOutlands(l, worldType);
		genBiomes = var4[0];
		biomeIndexLayer = var4[1];
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		return biomeCache.getBiomeGenAt(par1, par2);
	}

	@Override
	public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
		float[] par1ArrayOfFloat1 = par1ArrayOfFloat;
		IntCache.resetIntCache();

		if (par1ArrayOfFloat1 == null || par1ArrayOfFloat1.length < par4 * par5) {
			par1ArrayOfFloat1 = new float[par4 * par5];
		}

		int[] var6 = biomeIndexLayer.getInts(par2, par3, par4, par5);

		for (int var7 = 0; var7 < par4 * par5; ++var7) {
			float var8 = BiomeGenBase.biomeList[var6[var7]].getIntRainfall() / 65536.0F;

			if (var8 > 1.0F) {
				var8 = 1.0F;
			}

			par1ArrayOfFloat1[var7] = var8;
		}

		return par1ArrayOfFloat1;
	}

	@Override
	public float getTemperatureAtHeight(float par1, int par2) {
		return par1;
	}

	@Override
	public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
		float[] par1ArrayOfFloat1 = par1ArrayOfFloat;
		IntCache.resetIntCache();

		if (par1ArrayOfFloat1 == null || par1ArrayOfFloat1.length < par4 * par5) {
			par1ArrayOfFloat1 = new float[par4 * par5];
		}

		int[] var6 = biomeIndexLayer.getInts(par2, par3, par4, par5);

		for (int var7 = 0; var7 < par4 * par5; ++var7) {
			float var8 = BiomeGenBase.biomeList[var6[var7]].getIntTemperature() / 65536.0F;

			if (var8 > 1.0F) {
				var8 = 1.0F;
			}

			par1ArrayOfFloat1[var7] = var8;
		}

		return par1ArrayOfFloat1;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		BiomeGenBase[] par1ArrayOfBiomeGenBase1 = par1ArrayOfBiomeGenBase;
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase1 == null || par1ArrayOfBiomeGenBase1.length < par4 * par5) {
			par1ArrayOfBiomeGenBase1 = new BiomeGenBase[par4 * par5];
		}

		int[] var6 = genBiomes.getInts(par2, par3, par4, par5);

		for (int var7 = 0; var7 < par4 * par5; ++var7) {
			par1ArrayOfBiomeGenBase1[var7] = BiomeGenBase.biomeList[var6[var7]];
		}

		return par1ArrayOfBiomeGenBase1;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		return getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6) {
		BiomeGenBase[] par1ArrayOfBiomeGenBase1 = par1ArrayOfBiomeGenBase;
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase1 == null || par1ArrayOfBiomeGenBase1.length < par4 * par5) {
			par1ArrayOfBiomeGenBase1 = new BiomeGenBase[par4 * par5];
		}

		if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0) {
			BiomeGenBase[] var9 = biomeCache.getCachedBiomes(par2, par3);
			System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase1, 0, 16 * 16);
		} else {
			int[] var7 = biomeIndexLayer.getInts(par2, par3, par4, par5);

			for (int var8 = 0; var8 < par4 * par5; ++var8) {
				par1ArrayOfBiomeGenBase1[var8] = BiomeGenBase.biomeList[var7[var8]];
			}

		}
		return par1ArrayOfBiomeGenBase1;
	}

	@Override
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
		int var5 = par1 - par3 >> 2;
		int var6 = par2 - par3 >> 2;
		int var7 = par1 + par3 >> 2;
		int var8 = par2 + par3 >> 2;
		int var9 = var7 - var5 + 1;
		int var10 = var8 - var6 + 1;
		int[] var11 = genBiomes.getInts(var5, var6, var9, var10);

		for (int var12 = 0; var12 < var9 * var10; ++var12) {
			BiomeGenBase var13 = BiomeGenBase.biomeList[var11[var12]];

			if (!par4List.contains(var13)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random) {
		int var6 = par1 - par3 >> 2;
		int var7 = par2 - par3 >> 2;
		int var8 = par1 + par3 >> 2;
		int var9 = par2 + par3 >> 2;
		int var10 = var8 - var6 + 1;
		int var11 = var9 - var7 + 1;
		int[] var12 = genBiomes.getInts(var6, var7, var10, var11);
		ChunkPosition var13 = null;
		int var14 = 0;

		for (int var15 = 0; var15 < var12.length; ++var15) {
			int var16 = var6 + var15 % var10 << 2;
			int var17 = var7 + var15 / var10 << 2;
			BiomeGenBase var18 = BiomeGenBase.biomeList[var12[var15]];

			if (par4List.contains(var18) && (var13 == null || par5Random.nextInt(var14 + 1) == 0)) {
				var13 = new ChunkPosition(var16, 0, var17);
				++var14;
			}
		}

		return var13;
	}

	@Override
	public void cleanupCache() {
		biomeCache.cleanupCache();
	}
}

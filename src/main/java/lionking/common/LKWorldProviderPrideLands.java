package lionking.common;

import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;

public class LKWorldProviderPrideLands extends WorldProvider {
	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new LKWorldChunkManagerPrideLands(worldObj.getSeed(), worldObj.getWorldInfo().getTerrainType());
		dimensionId = mod_LionKing.idPrideLands;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new LKChunkProviderPrideLands(worldObj, worldObj.getSeed());
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public String getWelcomeMessage() {
		return "Entering the Pride Lands";
	}

	@Override
	public String getDepartMessage() {
		return "Leaving the Pride Lands";
	}

	@Override
	public String getSaveFolder() {
		return "PrideLands";
	}

	@Override
	public String getDimensionName() {
		return "Pride Lands";
	}

	@Override
	public ChunkCoordinates getSpawnPoint() {
		return new ChunkCoordinates(LKLevelData.homePortalX, LKLevelData.homePortalY, LKLevelData.homePortalZ);
	}

	@Override
	public void setSpawnPoint(int i, int j, int k) {
		if (!(i == 8 && j == 64 && k == 8)) {
			LKLevelData.setHomePortalLocation(i, j, k);
		}
	}

	@Override
	public boolean shouldMapSpin(String entity, double x, double y, double z) {
		return false;
	}
}

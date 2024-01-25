package lionking.common;

import net.minecraft.block.*;

import net.minecraft.world.*;

import java.util.Random;

public class LKBlockOutshroom extends BlockMushroom {
	private final boolean glowing;

	public LKBlockOutshroom(int i, boolean flag) {
		super(i);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.625F, 0.75F);
		glowing = flag;
		if (glowing) {
			setLightValue(0.875F);
		}
		setCreativeTab(LKCreativeTabs.tabDeco);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		if (!glowing) {
			if (random.nextInt(50) == 0) {
				int i1 = i + random.nextInt(3) - 1;
				int j1 = j + random.nextInt(2) - random.nextInt(2);
				int k1 = k + random.nextInt(3) - 1;

				if (world.isAirBlock(i1, j1, k1) && canBlockStay(world, i1, j1, k1)) {
					random.nextInt(3);
					random.nextInt(3);

					if (world.isAirBlock(i1, j1, k1) && canBlockStay(world, i1, j1, k1)) {
						world.setBlock(i1, j1, k1, blockID);
					}
				}
			}
		}
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (glowing) {
			return world.isBlockOpaqueCube(i, j - 1, k);
		}
		return super.canBlockStay(world, i, j, k);
	}
}

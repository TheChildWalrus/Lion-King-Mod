package lionking.common;

import net.minecraft.block.*;
import net.minecraft.world.*;

public class LKBlockStairs extends BlockStairs {
	private final Block theBlock;

	public LKBlockStairs(int i, Block block, int j) {
		super(i, block, j);
		theBlock = block;
		setCreativeTab(LKCreativeTabs.tabBlock);
	}

	@Override
	public float getBlockHardness(World world, int i, int j, int k) {
		return theBlock.getBlockHardness(world, i, j, k);
	}
}
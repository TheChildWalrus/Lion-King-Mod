package lionking.common;

import net.minecraft.block.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public class LKBlockWall extends BlockWall {
	public LKBlockWall(int i) {
		super(i, mod_LionKing.pridestone);
		setCreativeTab(LKCreativeTabs.tabBlock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		if (j == 1) {
			return mod_LionKing.prideBrick.getIcon(i, 0);
		}
		if (j == 2) {
			return mod_LionKing.prideBrickMossy.getIcon(i, 0);
		}
		if (j == 3) {
			return mod_LionKing.pridestone.getIcon(i, 1);
		}
		if (j == 4) {
			return mod_LionKing.prideBrick.getIcon(i, 1);
		}
		return mod_LionKing.pridestone.getIcon(i, 0);
	}

	@Override
	public float getBlockHardness(World world, int i, int j, int k) {
		int l = world.getBlockMetadata(i, j, k);
		if (l == 3 || l == 4) {
			return blockHardness * 0.7F;
		}
		return blockHardness;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs tab, List list) {
		for (int j = 0; j <= 4; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}

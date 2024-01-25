package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

public class LKItemTallFlower extends LKItem {
	private final int flowerMetadata;

	public LKItemTallFlower(int i, int j) {
		super(i);
		flowerMetadata = j;
		setCreativeTab(LKCreativeTabs.tabDeco);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int l1 = l;
		int j1 = j;
		int k1 = k;
		int i2 = i;
		int i1 = world.getBlockId(i2, j1, k1);
		if (i1 == Block.snow.blockID) {
			l1 = 1;
		} else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID) {
			if (l1 == 0) {
				--j1;
			}
			if (l1 == 1) {
				++j1;
			}
			if (l1 == 2) {
				--k1;
			}
			if (l1 == 3) {
				++k1;
			}
			if (l1 == 4) {
				--i2;
			}
			if (l1 == 5) {
				++i2;
			}
		}
		if (!entityplayer.canPlayerEdit(i2, j1, k1, l1, itemstack)) {
			return false;
		}
		Block block = mod_LionKing.flowerBase;
		if (j1 < 255 && (world.getBlockId(i2, j1 - 1, k1) == Block.grass.blockID || world.getBlockId(i2, j1 - 1, k1) == Block.dirt.blockID || world.getBlockId(i2, j1 - 1, k1) == Block.tilledField.blockID) && isReplaceableBlock(world, i2, j1, k1) && isReplaceableBlock(world, i2, j1 + 1, k1)) {
			if (!world.isRemote) {
				world.setBlock(i2, j1, k1, mod_LionKing.flowerBase.blockID, flowerMetadata, 3);
				world.setBlock(i2, j1 + 1, k1, mod_LionKing.flowerTop.blockID, flowerMetadata, 3);
				world.playSoundEffect(i2 + 0.5F, j1 + 0.5F, k1 + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				itemstack.stackSize--;
				return true;
			}
		}
		return false;
	}

	private boolean isReplaceableBlock(IBlockAccess world, int i, int j, int k) {
		int l = world.getBlockId(i, j, k);
		return l == 0 || Block.blocksList[l].blockMaterial.isReplaceable();
	}
}
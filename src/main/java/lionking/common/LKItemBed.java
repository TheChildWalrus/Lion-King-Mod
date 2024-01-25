package lionking.common;

import net.minecraft.block.BlockBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LKItemBed extends LKItem {
	public LKItemBed(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabDeco);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int j1 = j;
		if (l == 1) {
			j1++;
			BlockBed block = (BlockBed) mod_LionKing.blockBed;
			int i1 = MathHelper.floor_double(entityplayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			byte byte0 = 0;
			byte byte1 = 0;

			if (i1 == 0) {
				byte1 = 1;
			}

			if (i1 == 1) {
				byte0 = -1;
			}

			if (i1 == 2) {
				byte1 = -1;
			}

			if (i1 == 3) {
				byte0 = 1;
			}

			if (entityplayer.canPlayerEdit(i, j1, k, i1, itemstack) && entityplayer.canPlayerEdit(i + byte0, j1, k + byte1, i1, itemstack)) {
				if (world.isAirBlock(i, j1, k) && world.isAirBlock(i + byte0, j1, k + byte1) && world.doesBlockHaveSolidTopSurface(i, j1 - 1, k) && world.doesBlockHaveSolidTopSurface(i + byte0, j1 - 1, k + byte1)) {
					world.setBlock(i, j1, k, block.blockID, i1, 3);

					if (world.getBlockId(i, j1, k) == block.blockID) {
						world.setBlock(i + byte0, j1, k + byte1, block.blockID, i1 + 8, 3);
					}

					--itemstack.stackSize;
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
}

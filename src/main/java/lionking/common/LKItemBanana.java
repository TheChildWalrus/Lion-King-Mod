package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

import net.minecraftforge.common.ForgeDirection;

public class LKItemBanana extends LKItemFood {
	public LKItemBanana(int i, int j, float f, boolean flag) {
		super(i, j, f, flag);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ) {
		int k1 = k;
		int i1 = i;
		int id = world.getBlockId(i1, j, k1);
		int meta = world.getBlockMetadata(i1, j, k1);

		if (id == mod_LionKing.prideWood2.blockID && BlockLog.limitToValidMetadata(meta) == 0) {
			if (side == 0 || side == 1) {
				return false;
			}
			if (side == 2) {
				k1--;
			}
			if (side == 3) {
				k1++;
			}
			if (side == 4) {
				i1--;
			}
			if (side == 5) {
				i1++;
			}

			if (world.isAirBlock(i1, j, k1)) {
				int bananaMetadata = ForgeDirection.getOrientation(side - 2).getOpposite().ordinal();
				world.setBlock(i1, j, k1, mod_LionKing.hangingBanana.blockID, bananaMetadata, 3);

				if (!entityplayer.capabilities.isCreativeMode) {
					itemstack.stackSize--;
				}
			}
			return true;
		}
		return false;
	}
}

package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKItemScarRug extends LKItem {
	private final int type;

	public LKItemScarRug(int i, int j) {
		super(i);
		type = j;
		setCreativeTab(LKCreativeTabs.tabQuest);
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
		} else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID && Block.blocksList[i1] != null && !Block.blocksList[i1].isBlockReplaceable(world, i2, j1, k1)) {
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
		if (world.doesBlockHaveSolidTopSurface(i2, j1 - 1, k1) || world.isBlockNormalCube(i2, j1 - 1, k1)) {
			if (!world.isRemote) {
				LKEntityScarRug rug = new LKEntityScarRug(world, type);
				rug.setLocationAndAngles((double) i2 + f, j1, (double) k1 + f2, entityplayer.rotationYaw % 360.0F + 180.0F, 0.0F);
				if (world.checkNoEntityCollision(rug.boundingBox) && world.getCollidingBoundingBoxes(rug, rug.boundingBox).isEmpty() && !world.isAnyLiquid(rug.boundingBox)) {
					world.spawnEntityInWorld(rug);
					world.playSoundAtEntity(rug, "lionking:lion", 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
					itemstack.stackSize--;
					return true;
				}
				rug.setDead();
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.uncommon;
	}
}

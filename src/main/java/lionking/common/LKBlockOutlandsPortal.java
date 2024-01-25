package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKBlockOutlandsPortal extends BlockBreakable {
	public LKBlockOutlandsPortal(int i) {
		super(i, "lionking:outlandsPortal", LKMaterialPortal.material, false);
	}

	public static boolean tryToCreatePortal(World world, int i, int j, int k) {
		int i3 = i;
		int k2 = k;
		int l = 0;
		int i1 = 0;
		if (world.getBlockId(i3 - 1, j, k2) == mod_LionKing.outlandsPortalFrame.blockID || world.getBlockId(i3 + 1, j, k2) == mod_LionKing.outlandsPortalFrame.blockID) {
			l = 1;
		}
		if (world.getBlockId(i3, j, k2 - 1) == mod_LionKing.outlandsPortalFrame.blockID || world.getBlockId(i3, j, k2 + 1) == mod_LionKing.outlandsPortalFrame.blockID) {
			i1 = 1;
		}
		if (l == i1) {
			return false;
		}
		if (world.getBlockId(i3 - l, j, k2 - i1) == 0) {
			i3 -= l;
			k2 -= i1;
		}
		for (int j1 = -1; j1 <= 2; j1++) {
			for (int l1 = -1; l1 <= 3; l1++) {
				boolean flag = j1 == -1 || j1 == 2 || l1 == -1 || l1 == 3;
				if ((j1 == -1 || j1 == 2) && (l1 == -1 || l1 == 3)) {
					continue;
				}
				int j2 = world.getBlockId(i3 + l * j1, j + l1, k2 + i1 * j1);
				if (flag) {
					if (j2 != mod_LionKing.outlandsPortalFrame.blockID) {
						return false;
					}
					continue;
				}
				if (j2 != 0) {
					return false;
				}
			}

		}

		for (int k1 = 0; k1 < 2; k1++) {
			for (int i2 = 0; i2 < 3; i2++) {
				world.setBlock(i3 + l * k1, j + i2, k2 + i1 * k1, mod_LionKing.outlandsPortal.blockID, 0, 2);
			}

		}

		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
		if (iblockaccess.getBlockId(i - 1, j, k) == blockID || iblockaccess.getBlockId(i + 1, j, k) == blockID) {
			float f = 0.5F;
			float f2 = 0.125F;
			setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
		} else {
			float f1 = 0.125F;
			float f3 = 0.5F;
			setBlockBounds(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		int i1 = 0;
		int j1 = 1;
		if (world.getBlockId(i - 1, j, k) == blockID || world.getBlockId(i + 1, j, k) == blockID) {
			i1 = 1;
			j1 = 0;
		}
		int k1;
		for (k1 = j; world.getBlockId(i, k1 - 1, k) == blockID; k1--) {
		}
		if (world.getBlockId(i, k1 - 1, k) != mod_LionKing.outlandsPortalFrame.blockID) {
			world.setBlockToAir(i, j, k);
			return;
		}
		int l1;
		for (l1 = 1; l1 < 4 && world.getBlockId(i, k1 + l1, k) == blockID; l1++) {
		}
		if (l1 != 3 || world.getBlockId(i, k1 + l1, k) != mod_LionKing.outlandsPortalFrame.blockID) {
			world.setBlockToAir(i, j, k);
			return;
		}
		boolean flag = world.getBlockId(i - 1, j, k) == blockID || world.getBlockId(i + 1, j, k) == blockID;
		boolean flag1 = world.getBlockId(i, j, k - 1) == blockID || world.getBlockId(i, j, k + 1) == blockID;
		if (flag && flag1) {
			world.setBlockToAir(i, j, k);
			return;
		}
		if ((world.getBlockId(i + i1, j, k + j1) != mod_LionKing.outlandsPortalFrame.blockID || world.getBlockId(i - i1, j, k - j1) != blockID) && (world.getBlockId(i - i1, j, k - j1) != mod_LionKing.outlandsPortalFrame.blockID || world.getBlockId(i + i1, j, k + j1) != blockID)) {
			world.setBlockToAir(i, j, k);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		if (iblockaccess.getBlockId(i, j, k) == blockID) {
			return false;
		}
		boolean flag = iblockaccess.getBlockId(i - 1, j, k) == blockID && iblockaccess.getBlockId(i - 2, j, k) != blockID;
		boolean flag1 = iblockaccess.getBlockId(i + 1, j, k) == blockID && iblockaccess.getBlockId(i + 2, j, k) != blockID;
		boolean flag2 = iblockaccess.getBlockId(i, j, k - 1) == blockID && iblockaccess.getBlockId(i, j, k - 2) != blockID;
		boolean flag3 = iblockaccess.getBlockId(i, j, k + 1) == blockID && iblockaccess.getBlockId(i, j, k + 2) != blockID;
		boolean flag4 = flag || flag1;
		boolean flag5 = flag2 || flag3;
		return flag4 && l == 4 || flag4 && l == 5 || flag5 && l == 2 || flag5 && l == 3;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entity;
			mod_LionKing.proxy.setInOutlandsPortal(entityplayer);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (random.nextInt(100) == 0) {
			world.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "portal.portal", 1.0F, random.nextFloat() * 0.4F + 0.8F);
		}
		for (int l = 0; l < 4; l++) {
			double d = i + random.nextFloat();
			double d1 = j + random.nextFloat();
			double d2 = k + random.nextFloat();
			int i1 = random.nextInt(2) * 2 - 1;
			double d3 = (random.nextFloat() - 0.5D) * 0.5D;
			double d4 = (random.nextFloat() - 0.5D) * 0.5D;
			double d5 = (random.nextFloat() - 0.5D) * 0.5D;
			if (world.getBlockId(i - 1, j, k) == blockID || world.getBlockId(i + 1, j, k) == blockID) {
				d2 = k + 0.5D + 0.25D * i1;
				d5 = random.nextFloat() * 2.0F * i1;
			} else {
				d = i + 0.5D + 0.25D * i1;
				d3 = random.nextFloat() * 2.0F * i1;
			}

			mod_LionKing.proxy.spawnParticle("outlandsPortal", d, d1, d2, d3, d4, d5);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int i, int j, int k) {
		return 0;
	}
}
package lionking.common;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.ForgeDirection;

public class LKBlockOutsand extends LKBlock {
	public LKBlockOutsand(int i) {
		super(i, Material.sand);
	}

	private static boolean canFallBelow(IBlockAccess world, int i, int j, int k) {
		int l = world.getBlockId(i, j, k);
		if (l == 0) {
			return true;
		}
		if (l == Block.fire.blockID) {
			return true;
		}
		Material material = Block.blocksList[l].blockMaterial;
		return material == Material.water || material == Material.lava;
	}

	@Override
	public boolean isFireSource(World world, int i, int j, int k, int metadata, ForgeDirection side) {
		return side == ForgeDirection.UP;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		tryToFall(world, i, j, k);
	}

	private void tryToFall(World world, int i, int j, int k) {
		int j2 = j;
		int i1 = j2;
		if (canFallBelow(world, i, i1 - 1, k) && i1 >= 0) {
			byte byte0 = 32;
			if (BlockSand.fallInstantly || !world.checkChunksExist(i - byte0, j2 - byte0, k - byte0, i + byte0, j2 + byte0, k + byte0)) {
				world.setBlockToAir(i, j2, k);
				for (; canFallBelow(world, i, j2 - 1, k) && j2 > 0; j2--) {
				}
				if (j2 > 0) {
					world.setBlock(i, j2, k, blockID, 0, 3);
				}
			} else if (!world.isRemote) {
				LKEntityOutsand entity = new LKEntityOutsand(world, i + 0.5F, j2 + 0.5F, k + 0.5F, blockID);
				world.spawnEntityInWorld(entity);
			}
		}
	}

	@Override
	public int tickRate(World world) {
		return 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		random.nextFloat();
		double d1 = j + random.nextFloat();
		double d2 = k + random.nextFloat();
		int i1 = random.nextInt(2) * 2 - 1;
		random.nextFloat();
		double d4 = (random.nextFloat() - 0.5D) * 0.5D;
		double d5 = (random.nextFloat() - 0.5D) * 0.5D;
		double d = i + 0.5D + 0.25D * i1;
		double d3 = random.nextFloat() * 2.0F * i1;
		world.spawnParticle("smoke", d, d1, d2, d3, d4, d5);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (entity instanceof EntityLivingBase && !entity.isImmuneToFire() && world.rand.nextBoolean()) {
			entity.attackEntityFrom(DamageSource.inFire, 2.0F);
		}
	}

	@Override
	public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
		if (entity instanceof EntityLivingBase && !entity.isImmuneToFire() && world.rand.nextBoolean()) {
			entity.attackEntityFrom(DamageSource.inFire, 2.0F);
		}
	}
}

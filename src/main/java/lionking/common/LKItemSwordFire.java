package lionking.common;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

public class LKItemSwordFire extends LKItemSword {
	public LKItemSwordFire(int i, EnumToolMaterial enumtoolmaterial) {
		super(i, enumtoolmaterial);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if (!entityliving.isImmuneToFire() && entityliving.getHealth() > 0) {
			entityliving.setFire(3 + itemRand.nextInt(3));
			for (int i = 0; i < 8; i++) {
				double d = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				entityliving.worldObj.spawnParticle("flame", entityliving.posX + ((double) (itemRand.nextFloat() * entityliving.width * 2.0F) - entityliving.width) * 0.75F, entityliving.posY + 0.25F + itemRand.nextFloat() * entityliving.height, entityliving.posZ + ((double) (itemRand.nextFloat() * entityliving.width * 2.0F) - entityliving.width) * 0.75F, d, d1, d2);
			}
			itemstack.damageItem(1, entityliving1);
			return true;
		}
		return super.hitEntity(itemstack, entityliving, entityliving1);
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
		if (entityplayer.canPlayerEdit(i2, j1, k1, l1, itemstack)) {
			if (world.isAirBlock(i2, j1, k1)) {
				world.playSoundEffect(i2 + 0.5D, j1 + 0.5D, k1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				world.setBlock(i2, j1, k1, Block.fire.blockID, 0, 3);
			}
			itemstack.damageItem(1, entityplayer);
			return true;
		}
		return false;
	}
}

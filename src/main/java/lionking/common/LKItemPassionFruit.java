package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.*;

import net.minecraft.world.*;

import net.minecraftforge.common.DimensionManager;

public class LKItemPassionFruit extends LKItemFood {
	public LKItemPassionFruit(int i) {
		super(i, 0, 0.0F, false);
	}

	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.stackSize--;
		mod_LionKing.proxy.playPortalFXForUpendi(world);
		if (!world.isRemote && entityplayer instanceof EntityPlayerMP) {
			byte dimension;
			if (entityplayer.dimension == mod_LionKing.idUpendi) {
				dimension = (byte) mod_LionKing.idPrideLands;
			} else {
				dimension = (byte) mod_LionKing.idUpendi;
			}
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entityplayer, dimension, new LKTeleporterUpendi(DimensionManager.getWorld(dimension), LKIngame.getSimbas(entityplayer)));
		}
		return itemstack;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.dimension == mod_LionKing.idUpendi || entityplayer.dimension == mod_LionKing.idPrideLands) {
			if (entityplayer.capabilities.isCreativeMode || entityplayer.getHealth() == entityplayer.getMaxHealth()) {
				entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
			}
		}
		return itemstack;
	}
}

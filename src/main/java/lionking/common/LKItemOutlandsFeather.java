package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.*;

import net.minecraft.world.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.DimensionManager;

public class LKItemOutlandsFeather extends LKItem {
	public LKItemOutlandsFeather(int i) {
		super(i);
		setMaxStackSize(16);
		setCreativeTab(LKCreativeTabs.tabQuest);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (LKLevelData.ziraStage > 9 && LKLevelData.ziraStage < 14) {
			return itemstack;
		}
		if (entityplayer.dimension != mod_LionKing.idPrideLands && entityplayer.dimension != mod_LionKing.idOutlands) {
			return itemstack;
		}
		world.playSoundAtEntity(entityplayer, "portal.portal", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		int dimension = mod_LionKing.idOutlands;
		if (entityplayer.dimension == mod_LionKing.idOutlands) {
			dimension = mod_LionKing.idPrideLands;
		} else if (entityplayer.dimension == mod_LionKing.idPrideLands) {
			dimension = mod_LionKing.idOutlands;
		}
		if (entityplayer instanceof EntityPlayerMP && world instanceof WorldServer) {
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entityplayer, dimension, new LKTeleporterFeather(DimensionManager.getWorld(dimension), LKIngame.getSimbas(entityplayer)));
		}
		itemstack.stackSize--;
		return itemstack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.uncommon;
	}
}

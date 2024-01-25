package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKItemTicket extends LKItem {
	@SideOnly(Side.CLIENT)
	private Icon crackerIcon;

	public LKItemTicket(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(LKCreativeTabs.tabQuest);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		return i == 1 ? crackerIcon : itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		super.registerIcons(iconregister);
		crackerIcon = iconregister.registerIcon("lionking:cracker");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + '.' + itemstack.getItemDamage();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (itemstack.getItemDamage() == 1) {
			itemstack.stackSize--;
			entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_LionKing.ticket));
			world.playSoundAtEntity(entityplayer, "lionking:pop", 1.0F, 1.0F);
		}
		return itemstack;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		if (itemstack.getItemDamage() == 0) {
			if (world.getBlockId(i, j, k) == mod_LionKing.lionPortalFrame.blockID && world.isAirBlock(i, j + 1, k)) {
				if (entityplayer.dimension == 0 || entityplayer.dimension == mod_LionKing.idPrideLands) {
					if (LKBlockPortal.tryToCreatePortal(world, i, j + 1, k)) {
						itemstack.stackSize--;
						return true;
					}
				}
			}
			if (world.getBlockId(i, j, k) == mod_LionKing.outlandsPortalFrame.blockID && world.isAirBlock(i, j + 1, k)) {
				if (entityplayer.dimension == mod_LionKing.idOutlands || entityplayer.dimension == mod_LionKing.idPrideLands) {
					if (LKBlockOutlandsPortal.tryToCreatePortal(world, i, j + 1, k)) {
						itemstack.stackSize--;
						return true;
					}
				}
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

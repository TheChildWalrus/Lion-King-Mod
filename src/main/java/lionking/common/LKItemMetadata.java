package lionking.common;

import net.minecraft.block.*;
import net.minecraft.item.*;

import net.minecraft.util.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKItemMetadata extends ItemBlock {
	public LKItemMetadata(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		return Block.blocksList[getBlockID()].getIcon(2, i);
	}

	@Override
	public int getMetadata(int i) {
		return i;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + '.' + itemstack.getItemDamage();
	}
}

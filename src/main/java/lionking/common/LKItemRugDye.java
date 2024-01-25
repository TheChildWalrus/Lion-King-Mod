package lionking.common;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;

import net.minecraft.util.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKItemRugDye extends LKItem {
	@SideOnly(Side.CLIENT)
	private Icon[] dyeIcons;
	private final String[] dyeTypes = new String[]{"white", "blue", "yellow", "red", "purple", "lightBlue", "green", "black", "violet", "pink", "lightGreen"};

	public LKItemRugDye(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(LKCreativeTabs.tabMaterials);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		int i1 = i;
		if (i1 >= dyeTypes.length) {
			i1 = 0;
		}
		return dyeIcons[i1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		dyeIcons = new Icon[dyeTypes.length];
		for (int i = 0; i < dyeTypes.length; i++) {
			dyeIcons[i] = iconregister.registerIcon("lionking:dye_" + dyeTypes[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + '.' + dyeTypes[itemstack.getItemDamage()];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list) {
		for (int j = 0; j <= 10; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}
package lionking.common;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;

import net.minecraft.util.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockWood2 extends LKBlockWood {
	@SideOnly(Side.CLIENT)
	private Icon[][] woodIcons;
	private final String[] woodNames = new String[]{"banana", "deadwood"};

	public LKBlockWood2(int i) {
		super(i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		int j2 = j;
		int j1 = j2 & 12;
		j2 &= 3;

		if (j2 >= woodNames.length) {
			j2 = 0;
		}

		if (j1 == 0 && (i == 0 || i == 1) || j1 == 4 && (i == 4 || i == 5) || j1 == 8 && (i == 2 || i == 3)) {
			return woodIcons[j2][0];
		}
		return woodIcons[j2][1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		woodIcons = new Icon[woodNames.length][2];
		for (int i = 0; i < woodNames.length; i++) {
			woodIcons[i][0] = iconregister.registerIcon("lionking:wood_" + woodNames[i] + "_top");
			woodIcons[i][1] = iconregister.registerIcon("lionking:wood_" + woodNames[i] + "_side");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs tab, List list) {
		for (int j = 0; j <= 1; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}

package lionking.common;

import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;

import net.minecraft.util.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockPlanks extends LKBlock {
	@SideOnly(Side.CLIENT)
	private Icon[] plankIcons;
	private final String[] plankTypes = new String[]{"acacia", "rainforest", "mango", "passion", "banana", "deadwood"};

	public LKBlockPlanks(int i) {
		super(i, Material.wood);
		setCreativeTab(LKCreativeTabs.tabBlock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		int j1 = j;
		if (j1 >= plankTypes.length) {
			j1 = 0;
		}
		return plankIcons[j1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		plankIcons = new Icon[plankTypes.length];
		for (int i = 0; i < plankTypes.length; i++) {
			plankIcons[i] = iconregister.registerIcon(getTextureName() + '_' + plankTypes[i]);
		}
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs tab, List list) {
		for (int j = 0; j <= 5; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}

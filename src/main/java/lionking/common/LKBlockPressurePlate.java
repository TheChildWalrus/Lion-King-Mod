package lionking.common;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockPressurePlate extends BlockPressurePlate {
	public LKBlockPressurePlate(int i, String s, EnumMobType type, Material material) {
		super(i, s, material, type);
		setCreativeTab(LKCreativeTabs.tabMisc);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		return mod_LionKing.pridestone.getIcon(i, 0);
	}
}

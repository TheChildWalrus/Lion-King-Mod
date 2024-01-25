package lionking.common;

import net.minecraft.block.*;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKBlockButton extends BlockButtonStone {
	public LKBlockButton(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabMisc);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		return mod_LionKing.pridestone.getIcon(i, 0);
	}
}

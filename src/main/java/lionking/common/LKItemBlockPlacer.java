package lionking.common;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class LKItemBlockPlacer extends ItemReed {
	public LKItemBlockPlacer(int i, Block block) {
		super(i, block);
		setCreativeTab(LKCreativeTabs.tabDeco);
	}
}

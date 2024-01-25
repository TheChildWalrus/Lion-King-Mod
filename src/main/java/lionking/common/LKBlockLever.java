package lionking.common;

import net.minecraft.block.*;

public class LKBlockLever extends BlockLever {
	public LKBlockLever(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabMisc);
	}

	@Override
	public int getRenderType() {
		return mod_LionKing.proxy.getLeverRenderID();
	}
}

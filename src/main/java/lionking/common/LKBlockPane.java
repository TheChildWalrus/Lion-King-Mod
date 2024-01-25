package lionking.common;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

public class LKBlockPane extends BlockPane {
	public LKBlockPane(int i, String s, String s1, Material material, boolean flag) {
		super(i, s, s1, material, flag);
		setCreativeTab(LKCreativeTabs.tabDeco);
	}
}

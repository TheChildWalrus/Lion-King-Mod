package lionking.common;

import net.minecraft.item.*;

public class LKItemSword extends ItemSword {
	public LKItemSword(int i, EnumToolMaterial enumtoolmaterial) {
		super(i, enumtoolmaterial);
		setCreativeTab(LKCreativeTabs.tabCombat);
	}
}

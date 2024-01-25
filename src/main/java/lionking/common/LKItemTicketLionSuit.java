package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

public class LKItemTicketLionSuit extends LKItemArmor {
	public LKItemTicketLionSuit(int i, int j) {
		super(i, mod_LionKing.armorSuit, 0, j);
		setMaxDamage(0);
		setMaxStackSize(1);
		setCreativeTab(LKCreativeTabs.tabMisc);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		String s = armorType == 2 ? "_2" : "_1";
		return "lionking:item/suit" + s + ".png";
	}
}

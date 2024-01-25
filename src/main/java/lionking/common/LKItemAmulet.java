package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

public class LKItemAmulet extends LKItemArmor {
	public LKItemAmulet(int i) {
		super(i, mod_LionKing.armorSuit, 0, 1);
		setMaxDamage(0);
		setMaxStackSize(1);
		setCreativeTab(LKCreativeTabs.tabQuest);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "lionking:item/amulet.png";
	}
}

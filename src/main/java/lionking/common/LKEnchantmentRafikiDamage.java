package lionking.common;

import net.minecraft.enchantment.*;
import net.minecraft.item.*;

public class LKEnchantmentRafikiDamage extends EnchantmentDamage {
	public LKEnchantmentRafikiDamage(int i, int j) {
		super(i, j, 0);
		setName("damage");
	}

	@Override
	public boolean canApply(ItemStack item) {
		return item.itemID == mod_LionKing.rafikiStick.itemID;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return canApply(stack);
	}
}

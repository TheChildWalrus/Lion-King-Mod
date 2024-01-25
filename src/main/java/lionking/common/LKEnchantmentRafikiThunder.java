package lionking.common;

import net.minecraft.enchantment.*;
import net.minecraft.item.*;

public class LKEnchantmentRafikiThunder extends Enchantment {
	public LKEnchantmentRafikiThunder(int i, int j) {
		super(i, j, EnumEnchantmentType.weapon);
		setName("rafiki.thunder");
	}

	@Override
	public int getMinEnchantability(int i) {
		return 5 + (i - 1) * 12;
	}

	@Override
	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	@Override
	public int getMaxLevel() {
		return 3;
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

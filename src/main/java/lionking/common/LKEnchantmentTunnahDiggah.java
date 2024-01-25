package lionking.common;

import net.minecraft.enchantment.*;
import net.minecraft.item.*;

public class LKEnchantmentTunnahDiggah extends Enchantment {
	public LKEnchantmentTunnahDiggah(int i, int j) {
		super(i, j, EnumEnchantmentType.digger);
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getMinEnchantability(int i) {
		return 5;
	}

	@Override
	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	@Override
	public String getName() {
		return "enchantment.td." + name;
	}

	@Override
	public boolean canApply(ItemStack item) {
		return item.itemID == mod_LionKing.tunnahDiggah.itemID;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return canApply(stack);
	}
}

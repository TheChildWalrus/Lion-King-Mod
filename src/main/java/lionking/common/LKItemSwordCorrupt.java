package lionking.common;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class LKItemSwordCorrupt extends LKItemSword {
	public LKItemSwordCorrupt(int i, EnumToolMaterial enumtoolmaterial) {
		super(i, enumtoolmaterial);
	}

	@Override
	public float getStrVsBlock(ItemStack itemstack, Block block, int meta) {
		int currentDamage = itemstack.getItemDamage();
		if (currentDamage < 0) {
			currentDamage = 0;
		}

		float f = 0.15F + (float) (getMaxDamage() - currentDamage) / getMaxDamage() * 0.85F;
		f *= super.getStrVsBlock(itemstack, block, meta);

		return Math.max(f, 1.5F);

	}
}

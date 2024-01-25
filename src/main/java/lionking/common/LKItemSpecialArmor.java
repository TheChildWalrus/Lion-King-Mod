package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

public class LKItemSpecialArmor extends LKItemArmor {
	public LKItemSpecialArmor(int i, EnumArmorMaterial material, int j, int k) {
		super(i, material, 0, j);
		setMaxDamage(k);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "lionking:item/special.png";
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
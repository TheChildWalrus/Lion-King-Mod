package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

import java.util.Locale;

public class LKItemArmor extends ItemArmor {
	public LKItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
		setCreativeTab(LKCreativeTabs.tabCombat);
	}

	@Override
	@SuppressWarnings("deprecation")
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		String s = getArmorMaterial().name().substring(3).toLowerCase(Locale.ROOT);
		String s1 = armorType == 2 ? "_2" : "_1";
		return "lionking:item/" + s + s1 + ".png";
	}
}

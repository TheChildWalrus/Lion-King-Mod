package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKItemOutlandsHelm extends LKItemArmor {
	public LKItemOutlandsHelm(int i) {
		super(i, mod_LionKing.armorOutlandsHelm, 0, 0);
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
		return "lionking:item/special.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.uncommon;
	}
}

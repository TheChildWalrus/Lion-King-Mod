package lionking.common;

import net.minecraft.item.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKItemQuestBlock extends ItemBlock {
	public LKItemQuestBlock(int i) {
		super(i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.uncommon;
	}
}

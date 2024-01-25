package lionking.common;

import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKSlotQuiver extends Slot {
	public LKSlotQuiver(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		Item i = itemstack.getItem();
		return i == mod_LionKing.dartBlue || i == mod_LionKing.dartYellow || i == mod_LionKing.dartRed || i == mod_LionKing.dartBlack || i == mod_LionKing.dartPink;
	}
}

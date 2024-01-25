package lionking.common;

import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKSlotItemInfo extends Slot {
	public LKSlotItemInfo(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return LKItemInfo.getItemInfo(itemstack) != null;
	}
}

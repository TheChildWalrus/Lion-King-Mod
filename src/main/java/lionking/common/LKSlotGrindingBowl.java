package lionking.common;

import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKSlotGrindingBowl extends Slot {
	public LKSlotGrindingBowl(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return false;
	}
}

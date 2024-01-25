package lionking.common;

import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKSlotBugBait extends Slot {
	public LKSlotBugBait(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		Item item = itemstack.getItem();
		if (item instanceof ItemFood) {
			ItemFood food = (ItemFood) item;
			return !food.isWolfsFavoriteMeat() && food != Item.fishRaw && food != Item.fishCooked && food.getSaturationModifier() > 0.0F;
		}
		return false;
	}
}

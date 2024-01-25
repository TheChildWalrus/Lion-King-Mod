package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKContainerSimba extends Container {
	private final LKInventorySimba simbaInventory;

	public LKContainerSimba(EntityPlayer player, LKEntitySimba simba) {
		simbaInventory = simba.inventory;
		for (int l = 0; l < 9; l++) {
			addSlotToContainer(new Slot(simbaInventory, l, 8 + l * 18, 31));
		}

		for (int k = 0; k < 3; k++) {
			for (int j1 = 0; j1 < 9; j1++) {
				addSlotToContainer(new Slot(player.inventory, j1 + k * 9 + 9, 8 + j1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; l++) {
			addSlotToContainer(new Slot(player.inventory, l, 8 + l * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return simbaInventory.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i < 9) {
				if (!mergeItemStack(itemstack1, 9, 45, true)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 0, 9, false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(entityplayer, itemstack1);
		}
		return itemstack;
	}
}

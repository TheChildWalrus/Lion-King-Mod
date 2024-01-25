package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKSlotBugTrap extends Slot {
	public LKSlotBugTrap(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return false;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
		if (itemstack.itemID == mod_LionKing.bug.itemID) {
			entityplayer.triggerAchievement(LKAchievementList.bugTrap);
		}
		super.onPickupFromSlot(entityplayer, itemstack);
	}
}

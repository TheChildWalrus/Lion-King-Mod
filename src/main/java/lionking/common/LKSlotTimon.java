package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class LKSlotTimon extends Slot {
	public int cost;
	private final LKEntityTimon theTimon;

	public LKSlotTimon(IInventory iinventory, int i, int j, int k, LKEntityTimon timon, int l) {
		super(iinventory, i, j, k);
		theTimon = timon;
		cost = l;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return false;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
		for (int i = 0; i < cost; i++) {
			entityplayer.inventory.consumeInventoryItem(mod_LionKing.bug.itemID);
		}
		theTimon.setEaten();
		entityplayer.triggerAchievement(LKAchievementList.hakunaMatata);
		super.onPickupFromSlot(entityplayer, itemstack);
	}
}

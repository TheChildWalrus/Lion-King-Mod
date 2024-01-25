package lionking.common;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

public class LKItemTermite extends LKItem {
	public LKItemTermite(int i) {
		super(i);
		setMaxStackSize(16);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			world.spawnEntityInWorld(new LKEntityThrownTermite(world, entityplayer));
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!entityplayer.capabilities.isCreativeMode) {
				itemstack.stackSize--;
			}
		}
		return itemstack;
	}
}

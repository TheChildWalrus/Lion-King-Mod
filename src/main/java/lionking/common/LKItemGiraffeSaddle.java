package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class LKItemGiraffeSaddle extends LKItem {
	public LKItemGiraffeSaddle(int i) {
		super(i);
		setMaxStackSize(1);
		setCreativeTab(LKCreativeTabs.tabMisc);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityplayer, EntityLivingBase entityliving) {
		if (entityliving instanceof LKEntityGiraffe) {
			LKEntityGiraffe giraffe = (LKEntityGiraffe) entityliving;

			if (!giraffe.getSaddled() && !giraffe.isChild()) {
				giraffe.setSaddled(true);
				itemstack.stackSize--;
			}

			return true;
		}
		return false;
	}
}

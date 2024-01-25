package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class LKEntityAIAngerableAttackableTarget extends EntityAINearestAttackableTarget {
	public LKEntityAIAngerableAttackableTarget(EntityCreature entity, Class entityclass, int i, boolean flag) {
		super(entity, entityclass, i, flag);
	}

	@Override
	public boolean shouldExecute() {
		if (((LKAngerable) taskOwner).isHostile()) {
			if (taskOwner instanceof LKEntityOutlander && !((LKEntityOutlander) taskOwner).inMound) {
				if (super.shouldExecute()) {
					if (taskOwner.getAttackTarget() instanceof EntityPlayer) {
						EntityPlayer entityplayer = (EntityPlayer) taskOwner.getAttackTarget();
						ItemStack helmet = entityplayer.inventory.armorItemInSlot(3);
						return helmet == null || helmet.itemID != mod_LionKing.outlandsHelm.itemID;
					}
					return true;
				}
				return false;
			}
			return super.shouldExecute();
		}
		return false;
	}
}

package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.packet.*;

import net.minecraft.stats.*;

import cpw.mods.fml.common.network.*;
import net.minecraftforge.common.DimensionManager;

import java.nio.ByteBuffer;


public class LKPacketHandlerServer implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if ("lk.simbaSit".equals(packet.channel)) {
			Entity entity = mod_LionKing.proxy.getEntityFromID(ByteBuffer.wrap(packet.data).getInt(0), DimensionManager.getWorld(packet.data[4]));
			if (entity instanceof LKEntitySimba) {
				LKEntitySimba simba = (LKEntitySimba) entity;
				simba.setSitting(!simba.isSitting());
			}
		} else if ("lk.damageItem".equals(packet.channel)) {
			Entity entity = mod_LionKing.proxy.getEntityFromID(ByteBuffer.wrap(packet.data).getInt(0), DimensionManager.getWorld(packet.data[4]));
			if (entity instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entity;
				int type = packet.data[5];
				if (type == (byte) 0) {
					ItemStack boots = entityplayer.inventory.armorItemInSlot(0);
					if (boots != null && boots.getItem() == mod_LionKing.zebraBoots) {
						boots.damageItem(1, entityplayer);
						if (boots.getItemDamage() == boots.getMaxDamage()) {
							LKIngame.sendBreakItemPacket(entityplayer, 0);
							entityplayer.inventory.setInventorySlotContents(36, null);
							entityplayer.addStat(StatList.objectBreakStats[mod_LionKing.zebraBoots.itemID], 1);
						}
					}
				} else if (type == (byte) 1) {
					ItemStack body = entityplayer.inventory.armorItemInSlot(2);
					if (body != null && body.getItem() == mod_LionKing.wings) {
						body.damageItem(1, entityplayer);
						if (body.getItemDamage() == body.getMaxDamage()) {
							LKIngame.sendBreakItemPacket(entityplayer, 1);
							entityplayer.inventory.setInventorySlotContents(38, null);
							entityplayer.addStat(StatList.objectBreakStats[mod_LionKing.wings.itemID], 1);
						}
					}
				}
			}
		} else if ("lk.sendQCheck".equals(packet.channel)) {
			LKQuestBase quest = LKQuestBase.allQuests[packet.data[0]];
			quest.setChecked(true);
		}
	}
}

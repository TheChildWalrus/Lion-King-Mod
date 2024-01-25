package lionking.common;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;

import net.minecraft.tileentity.*;
import net.minecraft.world.*;

import java.util.List;

import cpw.mods.fml.common.network.*;

import lionking.client.*;

public class LKCommonProxy implements IGuiHandler {
	public static int GUI_ID_BOWL;
	public static int GUI_ID_SIMBA = 1;
	public static int GUI_ID_TIMON = 2;
	public static int GUI_ID_QUIVER = 3;
	public static int GUI_ID_QUESTS = 4;
	public static int GUI_ID_TRAP = 6;
	public static int GUI_ID_DRUM = 7;

	public void onPreload() {
	}

	public void onLoad() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer entityplayer, World world, int i, int j, int k) {
		if (ID == GUI_ID_BOWL) {
			TileEntity bowl = world.getBlockTileEntity(i, j, k);
			if (bowl instanceof LKTileEntityGrindingBowl) {
				return new LKContainerGrindingBowl(entityplayer, (LKTileEntityGrindingBowl) bowl);
			}
		}
		if (ID == GUI_ID_SIMBA) {
			Entity simba = getEntityFromID(i, world);
			if (simba instanceof LKEntitySimba) {
				return new LKContainerSimba(entityplayer, (LKEntitySimba) simba);
			}
		}
		if (ID == GUI_ID_TIMON) {
			Entity timon = getEntityFromID(i, world);
			if (timon instanceof LKEntityTimon) {
				return new LKContainerTimon(entityplayer, (LKEntityTimon) timon);
			}
		}
		if (ID == GUI_ID_QUIVER) {
			LKInventoryQuiver inv = LKItemDartQuiver.getQuiverInventory(i, world);
			return new LKContainerQuiver(entityplayer, inv);
		}
		if (ID == GUI_ID_QUESTS) {
			return new LKContainerItemInfo(entityplayer);
		}
		if (ID == GUI_ID_TRAP) {
			TileEntity trap = world.getBlockTileEntity(i, j, k);
			if (trap instanceof LKTileEntityBugTrap) {
				return new LKContainerBugTrap(entityplayer, (LKTileEntityBugTrap) trap);
			}
		}
		if (ID == GUI_ID_DRUM) {
			TileEntity drum = world.getBlockTileEntity(i, j, k);
			if (drum instanceof LKTileEntityDrum) {
				return new LKContainerDrum(entityplayer, world, (LKTileEntityDrum) drum);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer entityplayer, World world, int i, int j, int k) {
		if (ID == GUI_ID_BOWL) {
			TileEntity bowl = world.getBlockTileEntity(i, j, k);
			if (bowl instanceof LKTileEntityGrindingBowl) {
				return new LKGuiGrindingBowl(entityplayer, (LKTileEntityGrindingBowl) bowl);
			}
		}
		if (ID == GUI_ID_SIMBA) {
			Entity simba = getEntityFromID(i, world);
			if (simba instanceof LKEntitySimba) {
				return new LKGuiSimba(entityplayer, (LKEntitySimba) simba);
			}
		}
		if (ID == GUI_ID_TIMON) {
			Entity timon = getEntityFromID(i, world);
			if (timon instanceof LKEntityTimon) {
				return new LKGuiTimon(entityplayer, (LKEntityTimon) timon);
			}
		}
		if (ID == GUI_ID_QUIVER) {
			LKInventoryQuiver inv = LKItemDartQuiver.getQuiverInventory(i, world);
			return new LKGuiQuiver(entityplayer, inv);
		}
		if (ID == GUI_ID_QUESTS) {
			return new LKGuiQuests(entityplayer);
		}
		int GUI_ID_ACHIEVEMENTS = 5;
		if (ID == GUI_ID_ACHIEVEMENTS) {
			return new LKGuiAchievements(Minecraft.getMinecraft().statFileWriter, i);
		}
		if (ID == GUI_ID_TRAP) {
			TileEntity trap = world.getBlockTileEntity(i, j, k);
			if (trap instanceof LKTileEntityBugTrap) {
				return new LKGuiBugTrap(entityplayer, (LKTileEntityBugTrap) trap);
			}
		}
		if (ID == GUI_ID_DRUM) {
			TileEntity drum = world.getBlockTileEntity(i, j, k);
			if (drum instanceof LKTileEntityDrum) {
				return new LKGuiDrum(entityplayer, world, (LKTileEntityDrum) drum);
			}
		}
		return null;
	}

	public Entity getEntityFromID(int ID, World world) {
		List entities = world.loadedEntityList;
		if (!entities.isEmpty()) {
			for (Object obj : entities) {
				Entity entity = (Entity) obj;
				if (entity != null && entity.entityId == ID) {
					return entity;
				}
			}
		}
		return null;
	}

	public int getGrindingBowlRenderID() {
		return 0;
	}

	public int getPillarRenderID() {
		return 0;
	}

	public int getStarAltarRenderID() {
		return 0;
	}

	public int getVaseRenderID() {
		return 0;
	}

	public int getBugTrapRenderID() {
		return 0;
	}

	public int getAridGrassRenderID() {
		return 0;
	}

	public int getKiwanoBlockRenderID() {
		return 0;
	}

	public int getKiwanoStemRenderID() {
		return 0;
	}

	public int getLeverRenderID() {
		return 0;
	}

	public int getLilyRenderID() {
		return 0;
	}

	public int getRugRenderID() {
		return 0;
	}

	public void setInPrideLandsPortal(EntityPlayer entityplayer) {
		if (!LKTickHandlerServer.playersInPortals.containsKey(entityplayer)) {
			LKTickHandlerServer.playersInPortals.put(entityplayer, 0);
		}
	}

	public void setInOutlandsPortal(EntityPlayer entityplayer) {
		if (!LKTickHandlerServer.playersInOutPortals.containsKey(entityplayer)) {
			LKTickHandlerServer.playersInOutPortals.put(entityplayer, 0);
		}
	}

	public void playPortalFXForUpendi(World world) {
	}

	public void spawnParticle(String type, double d, double d1, double d2, double d3, double d4, double d5) {
	}

	public EntityPlayer getSPPlayer() {
		return null;
	}
}

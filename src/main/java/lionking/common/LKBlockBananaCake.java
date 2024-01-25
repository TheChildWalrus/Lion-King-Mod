package lionking.common;

import net.minecraft.block.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockBananaCake extends BlockCake {
	@SideOnly(Side.CLIENT)
	private Icon iconBottom;
	@SideOnly(Side.CLIENT)
	private Icon iconTop;
	@SideOnly(Side.CLIENT)
	private Icon iconSide;
	@SideOnly(Side.CLIENT)
	private Icon iconEaten;

	public LKBlockBananaCake(int i) {
		super(i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		if (i == 0) {
			return iconBottom;
		}
		if (i == 1) {
			return iconTop;
		}
		if (j > 0 && i == 4) {
			return iconEaten;
		}
		return iconSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		iconBottom = iconregister.registerIcon(getTextureName() + "_bottom");
		iconTop = iconregister.registerIcon(getTextureName() + "_top");
		iconSide = iconregister.registerIcon(getTextureName() + "_side");
		iconEaten = iconregister.registerIcon(getTextureName() + "_eaten");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int i, int j, int k) {
		return mod_LionKing.bananaCake.itemID;
	}
}

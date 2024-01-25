package lionking.common;

import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockPridestone extends LKBlock {
	@SideOnly(Side.CLIENT)
	private Icon corruptIcon;

	public LKBlockPridestone(int i) {
		super(i, Material.rock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		return j == 1 ? corruptIcon : super.getIcon(i, j);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		super.registerIcons(iconregister);
		corruptIcon = iconregister.registerIcon("lionking:pridestone_corrupt");
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	public float getBlockHardness(World world, int i, int j, int k) {
		if (world == null) {
			return super.getBlockHardness(null, i, j, k);
		}
		int l = world.getBlockMetadata(i, j, k);
		if (l == 1) {
			return super.getBlockHardness(world, i, j, k) * 0.7F;
		}
		return super.getBlockHardness(world, i, j, k);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs tab, List list) {
		for (int j = 0; j < 2; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}

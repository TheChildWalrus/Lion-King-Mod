package lionking.common;

import net.minecraft.block.material.*;
import net.minecraft.entity.player.*;

import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockOutlandsAltar extends LKBlock {
	@SideOnly(Side.CLIENT)
	private Icon[] altarIcons;

	public LKBlockOutlandsAltar(int i) {
		super(i, Material.iron);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
		setLightValue(0.75F);
		setCreativeTab(null);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		return altarIcons[i == 1 ? 1 : 0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		altarIcons = new Icon[2];
		altarIcons[0] = iconregister.registerIcon("lionking:poolFocus_side");
		altarIcons[1] = iconregister.registerIcon("lionking:poolFocus_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		random.nextFloat();
		double d1 = j + random.nextFloat();
		double d2 = k + random.nextFloat();
		int i1 = random.nextInt(2) * 2 - 1;
		random.nextFloat();
		double d4 = (random.nextFloat() - 0.5D) * 0.5D;
		double d5 = (random.nextFloat() - 0.5D) * 0.5D;
		double d = i + 0.5D + 0.25D * i1;
		double d3 = random.nextFloat() * 2.0F * i1;
		mod_LionKing.proxy.spawnParticle("outlandsPortal", d, d1, d2, d3, d4, d5);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getMobilityFlag() {
		return 2;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2) {
		return true;
	}
}
package lionking.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class LKBlockYam extends BlockCrops {
	@SideOnly(Side.CLIENT)
	private Icon[] yamIcons;

	public LKBlockYam(int i) {
		super(i);
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlockMetadata(i, j, k) == 8) {
			return (world.getBlockId(i, j - 1, k) == Block.dirt.blockID || world.getBlockId(i, j - 1, k) == Block.grass.blockID) && (world.getFullBlockLightValue(i, j, k) >= 8 || world.canBlockSeeTheSky(i, j, k));
		}
		return super.canBlockStay(world, i, j, k);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		if (LKIngame.isLKWorld(world.provider.dimensionId)) {
			super.updateTick(world, i, j, k, random);
		}
	}

	@Override
	public void fertilize(World world, int i, int j, int k) {
		if (LKIngame.isLKWorld(world.provider.dimensionId)) {
			super.fertilize(world, i, j, k);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		int j1 = j;
		if (j1 < 7) {
			if (j1 == 6) {
				j1 = 5;
			}
			return yamIcons[j1 >> 1];
		}
		return yamIcons[3];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		yamIcons = new Icon[4];
		for (int i = 0; i < 4; i++) {
			yamIcons[i] = iconregister.registerIcon(getTextureName() + '_' + i);
		}
	}

	@Override
	protected int getSeedItem() {
		return mod_LionKing.yam.itemID;
	}

	@Override
	protected int getCropItem() {
		return mod_LionKing.yam.itemID;
	}

	@Override
	public ArrayList getBlockDropped(World world, int i, int j, int k, int metadata, int fortune) {
		if (metadata == 8) {
			ArrayList drops = new ArrayList();
			if (world.rand.nextInt(3) > 0) {
				drops.add(new ItemStack(mod_LionKing.yam));
			}
			return drops;
		}
		return super.getBlockDropped(world, i, j, k, metadata, fortune);
	}
}

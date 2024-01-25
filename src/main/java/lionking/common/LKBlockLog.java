package lionking.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.Random;

public class LKBlockLog extends LKBlock {
	public LKBlockLog(int i) {
		super(i, Material.wood);
		setCreativeTab(null);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		return mod_LionKing.prideWood.getIcon(i, 8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
	}

	@Override
	public int idDropped(int i, Random random, int j) {
		return mod_LionKing.prideWood.blockID;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {
		if (!world.isRemote && world.rand.nextBoolean()) {
			LKEntityBug bug = new LKEntityBug(world);
			bug.setLocationAndAngles((double) i + 0.5D, j, (double) k + 0.5D, world.rand.nextFloat() * 360.0F, 0.0F);
			world.spawnEntityInWorld(bug);
		}
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int i, int j, int k) {
		return mod_LionKing.prideWood.blockID;
	}
}

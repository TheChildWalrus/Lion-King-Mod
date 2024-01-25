package lionking.common;

import net.minecraft.block.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKItemMountedShooter extends LKItem {
	@SideOnly(Side.CLIENT)
	private Icon[] shooterIcons;
	private final String[] shooterTypes = new String[]{"wood", "silver"};

	public LKItemMountedShooter(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabCombat);
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int j1 = j;
		int k1 = k;
		int i1 = i;
		if (l == 0 || !world.getBlockMaterial(i1, j1, k1).isSolid()) {
			return false;
		}

		if (l == 1) {
			j1++;
		}
		if (l == 2) {
			k1--;
		}
		if (l == 3) {
			k1++;
		}
		if (l == 4) {
			i1--;
		}
		if (l == 5) {
			i1++;
		}

		if (!entityplayer.canPlayerEdit(i1, j1, k1, l, itemstack) || !mod_LionKing.mountedShooter.canPlaceBlockAt(world, i1, j1, k1)) {
			return false;
		}

		Block block = mod_LionKing.mountedShooter;
		int rotation = MathHelper.floor_double(entityplayer.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
		world.setBlock(i1, j1, k1, block.blockID, rotation, 3);
		world.playSoundEffect(i1 + 0.5F, j1 + 0.5F, k1 + 0.5F, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

		TileEntity tileentity = world.getBlockTileEntity(i1, j1, k1);
		if (tileentity instanceof LKTileEntityMountedShooter) {
			((LKTileEntityMountedShooter) tileentity).setShooterType(itemstack.getItemDamage());
		}

		itemstack.stackSize--;
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		int i1 = i;
		if (i1 >= shooterTypes.length) {
			i1 = 0;
		}
		return shooterIcons[i1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		shooterIcons = new Icon[shooterTypes.length];
		for (int i = 0; i < shooterTypes.length; i++) {
			shooterIcons[i] = iconregister.registerIcon("lionking:mountedShooter_" + shooterTypes[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + '.' + itemstack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list) {
		for (int j = 0; j < 2; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}

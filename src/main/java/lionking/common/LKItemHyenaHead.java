package lionking.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class LKItemHyenaHead extends LKItem {
	@SideOnly(Side.CLIENT)
	private Icon[] headIcons;

	public LKItemHyenaHead(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabDeco);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		int i1 = i;
		if (i1 >= 4) {
			i1 = 0;
		}
		return headIcons[i1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		headIcons = new Icon[4];
		for (int i = 0; i < 4; i++) {
			headIcons[i] = iconregister.registerIcon("lionking:hyenaHead_" + i);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + '.' + itemstack.getItemDamage();
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

		if (!entityplayer.canPlayerEdit(i1, j1, k1, l, itemstack) || !mod_LionKing.hyenaHead.canPlaceBlockAt(world, i1, j1, k1)) {
			return false;
		}

		world.setBlock(i1, j1, k1, mod_LionKing.hyenaHead.blockID, l, 3);

		int rotation = 0;
		if (l == 1) {
			rotation = MathHelper.floor_double(entityplayer.rotationYaw * 16.0F / 360.0F + 0.5D) & 15;
		}

		TileEntity tileentity = world.getBlockTileEntity(i1, j1, k1);
		if (tileentity instanceof LKTileEntityHyenaHead) {
			((LKTileEntityHyenaHead) tileentity).setHyenaType(itemstack.getItemDamage());
			((LKTileEntityHyenaHead) tileentity).setRotation(rotation);
		}

		itemstack.stackSize--;
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list) {
		for (int j = 0; j < 4; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}
}

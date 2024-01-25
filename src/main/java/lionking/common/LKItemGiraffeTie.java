package lionking.common;

import net.minecraft.creativetab.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.util.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKItemGiraffeTie extends LKItem {
	@SideOnly(Side.CLIENT)
	private Icon[] tieIcons;
	private final String[] tieTypes = new String[]{"base", "white", "blue", "yellow", "red", "purple", "green", "black"};

	public LKItemGiraffeTie(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		int i1 = i;
		if (i1 >= tieTypes.length) {
			i1 = 0;
		}
		return tieIcons[i1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconregister) {
		tieIcons = new Icon[tieTypes.length];
		for (int i = 0; i < tieTypes.length; i++) {
			tieIcons[i] = iconregister.registerIcon("lionking:tie_" + tieTypes[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + '.' + itemstack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list) {
		for (int j = 0; j < 8; j++) {
			list.add(new ItemStack(i, 1, j));
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityplayer, EntityLivingBase entityliving) {
		if (entityliving instanceof LKEntityGiraffe) {
			LKEntityGiraffe giraffe = (LKEntityGiraffe) entityliving;

			if (giraffe.getTie() == -1 && !giraffe.isChild() && giraffe.getSaddled()) {
				giraffe.setTie(itemstack.getItemDamage());
				itemstack.stackSize--;
			}

			return true;
		}
		return false;
	}
}

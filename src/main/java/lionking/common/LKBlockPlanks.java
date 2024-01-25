package lionking.common;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.*;
import net.minecraft.network.packet.*;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.*;
import net.minecraft.server.*;
import net.minecraft.server.management.*;

import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.storage.*;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;

public class LKBlockPlanks extends LKBlock
{
	@SideOnly(Side.CLIENT)
	private Icon[] plankIcons;
	private String[] plankTypes = {"acacia", "rainforest", "mango", "passion", "banana", "deadwood"};
	
	public LKBlockPlanks(int i)
	{
		super(i, Material.wood);
		setCreativeTab(LKCreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int j)
    {
		if (j >= plankTypes.length)
		{
			j = 0;
		}
		return plankIcons[j];
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
        plankIcons = new Icon[plankTypes.length];
		for (int i = 0; i < plankTypes.length; i++)
		{
			plankIcons[i] = iconregister.registerIcon(getTextureName() + "_" + plankTypes[i]);
		}
    }
	
	@Override
    public int damageDropped(int i)
    {
        return i;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int i, CreativeTabs tab, List list)
    {
		for (int j = 0; j <= 5; j++)
		{
			list.add(new ItemStack(i, 1, j));
		}
    }
}

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

public class LKEnchantmentTunnahDiggah extends Enchantment
{
	public LKEnchantmentTunnahDiggah(int i, int j)
	{
		super(i, j, EnumEnchantmentType.digger);
	}

	@Override
    public int getMaxLevel()
    {
        return 1;
    }

	@Override
    public int getMinEnchantability(int i)
    {
        return 5;
    }

	@Override
    public int getMaxEnchantability(int i)
    {
        return super.getMinEnchantability(i) + 50;
    }

	@Override
    public String getName()
    {
        return "enchantment.td." + name;
    }

	@Override
    public boolean canApply(ItemStack item) 
    {
        return item.itemID == mod_LionKing.tunnahDiggah.itemID;
    }
	
	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return canApply(stack);
    }
}

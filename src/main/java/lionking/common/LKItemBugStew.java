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

public class LKItemBugStew extends LKItemFood
{
    public LKItemBugStew(int i)
    {
        super(i, 8, 0.5F, false);
		setContainerItem(Item.bowlEmpty);
		setMaxStackSize(1);
    }

	@Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        super.onEaten(itemstack, world, entityplayer);
		if (!world.isRemote && world.rand.nextFloat() < 0.4F)
		{
			entityplayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 160 + world.rand.nextInt(100), 0));
			if (world.rand.nextFloat() < 0.3F)
			{
				entityplayer.addPotionEffect(new PotionEffect(Potion.poison.id, 40 + world.rand.nextInt(40), 0));
			}
		}
		return new ItemStack(Item.bowlEmpty);
    }
}
package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;

import net.minecraft.world.*;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class LKItemSpear extends LKItem {
	private final boolean poisoned;

	public LKItemSpear(int i, boolean flag) {
		super(i);
		maxStackSize = 1;
		setMaxDamage(160);
		poisoned = flag;
		setCreativeTab(LKCreativeTabs.tabCombat);
		setFull3D();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		LKEntitySpear spear = poisoned ? new LKEntityPoisonedSpear(world, entityplayer, 2.0F, itemstack.getItemDamage()) : new LKEntityGemsbokSpear(world, entityplayer, 2.0F, itemstack.getItemDamage());
		world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.25F);
		entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
		if (!world.isRemote) {
			world.spawnEntityInWorld(spear);
		}
		return itemstack;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase user) {
		itemstack.damageItem(1, user);
		if (poisoned && itemRand.nextInt(3) != 0) {
			hitEntity.addPotionEffect(new PotionEffect(Potion.poison.id, (itemRand.nextInt(3) + 1) * 20, 0));
		}
		return true;
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", poisoned ? 3.0D : 4.0D, 0));
		return multimap;
	}
}

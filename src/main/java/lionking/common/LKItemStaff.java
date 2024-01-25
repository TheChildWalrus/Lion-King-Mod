package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;

import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class LKItemStaff extends LKItem {
	public LKItemStaff(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabTools);
		setFull3D();
		setMaxStackSize(1);
		setMaxDamage(600);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase usingEntity) {
		itemstack.damageItem(1, usingEntity);
		return true;
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 5.0D, 0));
		return multimap;
	}
}

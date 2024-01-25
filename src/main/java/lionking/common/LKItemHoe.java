package lionking.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class LKItemHoe extends ItemHoe
{
    public LKItemHoe(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
		setCreativeTab(LKCreativeTabs.tabTools);
    }
}

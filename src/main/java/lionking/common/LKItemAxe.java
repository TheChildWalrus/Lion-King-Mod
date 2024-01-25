package lionking.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;

public class LKItemAxe extends ItemAxe
{
    public LKItemAxe(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
		setCreativeTab(LKCreativeTabs.tabTools);
    }
}

package lionking.common;

import net.minecraft.item.ItemFood;

public class LKItemFood extends ItemFood
{
    public LKItemFood(int i, int j, float f, boolean flag)
    {
        super(i, j, f, flag);
		setCreativeTab(LKCreativeTabs.tabFood);
    }
}

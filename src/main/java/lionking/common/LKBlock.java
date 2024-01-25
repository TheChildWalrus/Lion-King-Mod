package lionking.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class LKBlock extends Block
{
    public LKBlock(int i, Material material)
    {
        super(i, material);
		setCreativeTab(LKCreativeTabs.tabBlock);
    }
}

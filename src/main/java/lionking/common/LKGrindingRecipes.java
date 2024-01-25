package lionking.common;

import net.minecraft.block.*;
import net.minecraft.item.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LKGrindingRecipes {
	private static final LKGrindingRecipes grindingBase = new LKGrindingRecipes();
	private final Map grindingList = new HashMap();
	private final Map metaGrindingList = new HashMap();

	private LKGrindingRecipes() {
		addGrinding(mod_LionKing.hyenaBone.itemID, new ItemStack(mod_LionKing.hyenaBoneShard));
		addGrinding(mod_LionKing.mango.itemID, new ItemStack(mod_LionKing.mangoDust));
		addGrinding(mod_LionKing.itemTermite.itemID, new ItemStack(mod_LionKing.termiteDust));
		addGrinding(mod_LionKing.horn.itemID, new ItemStack(mod_LionKing.hornGround));
		addGrinding(mod_LionKing.whiteFlower.blockID, new ItemStack(mod_LionKing.rugDye, 1, 0));
		addGrinding(mod_LionKing.featherBlue.itemID, new ItemStack(mod_LionKing.rugDye, 1, 1));
		addGrinding(mod_LionKing.featherYellow.itemID, new ItemStack(mod_LionKing.rugDye, 1, 2));
		addGrinding(mod_LionKing.featherRed.itemID, new ItemStack(mod_LionKing.rugDye, 1, 3));
		addGrinding(mod_LionKing.purpleFlower.itemID, new ItemStack(mod_LionKing.rugDye, 1, 4));
		addGrinding(mod_LionKing.blueFlower.blockID, new ItemStack(mod_LionKing.rugDye, 1, 5));
		addGrinding(mod_LionKing.leaves.blockID, new ItemStack(mod_LionKing.rugDye, 1, 6));
		addGrinding(mod_LionKing.forestLeaves.blockID, new ItemStack(mod_LionKing.rugDye, 1, 6));
		addGrinding(mod_LionKing.mangoLeaves.blockID, new ItemStack(mod_LionKing.rugDye, 1, 6));
		addGrinding(mod_LionKing.bananaLeaves.blockID, new ItemStack(mod_LionKing.rugDye, 1, 6));
		addGrinding(mod_LionKing.redFlower.itemID, new ItemStack(mod_LionKing.rugDye, 1, 3));
		addGrinding(mod_LionKing.featherBlack.itemID, new ItemStack(mod_LionKing.rugDye, 1, 7));
		addGrinding(mod_LionKing.nukaShard.itemID, new ItemStack(mod_LionKing.poison));
		addGrinding(mod_LionKing.lily.blockID, 0, new ItemStack(mod_LionKing.rugDye, 1, 0));
		addGrinding(mod_LionKing.lily.blockID, 1, new ItemStack(mod_LionKing.rugDye, 1, 8));
		addGrinding(mod_LionKing.lily.blockID, 2, new ItemStack(mod_LionKing.rugDye, 1, 3));
		addGrinding(mod_LionKing.pridestone.blockID, new ItemStack(Block.sand));
		addGrinding(mod_LionKing.featherPink.itemID, new ItemStack(mod_LionKing.rugDye, 1, 9));
		addGrinding(mod_LionKing.passionLeaves.blockID, new ItemStack(mod_LionKing.rugDye, 1, 10));

		for (int i = 0; i <= 2; i++) {
			addGrinding(mod_LionKing.pridePillar.blockID, i, new ItemStack(mod_LionKing.pridePillar, 1, i + 1));
		}

		for (int i = 4; i <= 6; i++) {
			addGrinding(mod_LionKing.pridePillar.blockID, i, new ItemStack(mod_LionKing.pridePillar, 1, i + 1));
		}
	}

	public static LKGrindingRecipes grinding() {
		return grindingBase;
	}

	private void addGrinding(int i, ItemStack itemstack) {
		grindingList.put(i, itemstack);
	}

	private void addGrinding(int i, int j, ItemStack itemstack) {
		metaGrindingList.put(Arrays.asList(i, j), itemstack);
	}

	public ItemStack getGrindingResult(ItemStack itemstack) {
		if (itemstack == null) {
			return null;
		}
		ItemStack stack = (ItemStack) metaGrindingList.get(Arrays.asList(itemstack.itemID, itemstack.getItemDamage()));
		if (stack != null) {
			return stack;
		}
		return (ItemStack) grindingList.get(itemstack.itemID);
	}
}

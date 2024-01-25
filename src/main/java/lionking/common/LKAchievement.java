package lionking.common;

import net.minecraft.block.*;
import net.minecraft.item.*;

import net.minecraft.stats.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LKAchievement extends Achievement {
	public final int displayColumn;
	public final int displayRow;
	private final String achievementDescription;
	public String lkAchievementTitle;
	private IStatStringFormat statStringFormatter;
	private boolean isSpecial;

	public LKAchievement(int i, String s, int j, int k, Item item, LKAchievement achievement) {
		this(i, s, j, k, new ItemStack(item), achievement);
	}

	public LKAchievement(int i, String s, int j, int k, Block block, LKAchievement achievement) {
		this(i, s, j, k, new ItemStack(block), achievement);
	}

	public LKAchievement(int i, String s, int j, int k, ItemStack itemstack, LKAchievement achievement) {
		super(0x19941994 + i, s, j, k, itemstack, achievement);
		achievementDescription = s;
		displayColumn = j;
		displayRow = k;
		if (j < LKAchievementList.minDisplayColumn) {
			LKAchievementList.minDisplayColumn = j;
		}
		if (k < LKAchievementList.minDisplayRow) {
			LKAchievementList.minDisplayRow = k;
		}
		if (j > LKAchievementList.maxDisplayColumn) {
			LKAchievementList.maxDisplayColumn = j;
		}
		if (k > LKAchievementList.maxDisplayRow) {
			LKAchievementList.maxDisplayRow = k;
		}
	}

	@Override
	public LKAchievement setIndependent() {
		isIndependent = true;
		return this;
	}

	@Override
	public LKAchievement setSpecial() {
		isSpecial = true;
		return this;
	}

	@Override
	public LKAchievement registerAchievement() {
		super.registerAchievement();
		AchievementList.achievementList.remove(this);
		return this;
	}

	@Override
	public boolean isAchievement() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return lkAchievementTitle;
	}

	@Override
	public String getDescription() {
		if (statStringFormatter != null) {
			return statStringFormatter.formatString(achievementDescription);
		}
		return achievementDescription;
	}

	@Override
	public LKAchievement setStatStringFormatter(IStatStringFormat istatstringformat) {
		statStringFormatter = istatstringformat;
		return this;
	}

	@Override
	public boolean getSpecial() {
		return isSpecial;
	}

	@Override
	public StatBase registerStat() {
		return registerAchievement();
	}

	@Override
	public StatBase initIndependentStat() {
		return setIndependent();
	}

	@Override
	public String toString() {
		return lkAchievementTitle;
	}
}

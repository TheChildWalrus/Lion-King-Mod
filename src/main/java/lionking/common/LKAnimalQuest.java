package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;

import java.util.Random;

public class LKAnimalQuest {
	private static final String[] numbers = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five", "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine", "thirty", "thirty-one", "thirty-two", "thirty-three", "thirty-four", "thirty-five", "thirty-six", "thirty-seven", "thirty-eight", "thirty-nine", "forty", "forty-one", "forty-two", "forty-three", "forty-four", "forty-five", "forty-six", "forty-seven", "forty-eight", "forty-nine", "fifty", "fifty-one", "fifty-two", "fifty-three", "fifty-four", "fifty-five", "fifty-six", "fifty-seven", "fifty-eight", "fifty-nine", "sixty", "sixty-one", "sixty-two", "sixty-three", "sixty-four"};
	private static final String[] questStart =
			new String[]{
					"I'm getting quite hungry. If you can bring me #, there might be a reward for you!",
					"Bring me # to eat and I'll give you something useful!",
					"If you get me # to eat, I have a little something for you in return.",
					"I feel so hungry. Could you find # for me?",
					"Would you be able to find me # to eat? I'll give you something in return!",
					"Do you have # to spare? I'm starving here!"
			};
	private static final String[] questEnd =
			new String[]{
					"That was just what I needed. Here you go.",
					"Mmm! Delicious!",
					"Thank you! And here's your reward.",
					"Thank you for that. Here's the reward I promised!"
			};
	private static final Random rand = new Random();

	private DataWatcher dataWatcher;

	public static String startQuest(String animal, ItemStack itemstack) {
		String s = "§e<" + animal + "> §f" + questStart[rand.nextInt(questStart.length)];
		return replace(s, "#", numbers[itemstack.stackSize] + ' ' + itemstack.getItem().getItemDisplayName(itemstack));
	}

	public static String endQuest(String animal) {
		return "§e<" + animal + "> §f" + questEnd[rand.nextInt(questEnd.length)];
	}

	private static String replace(String text, String pattern, String replace) {
		int s = 0;
		int e;
		StringBuilder newText = new StringBuilder();

		while ((e = text.indexOf(pattern, s)) >= 0) {
			newText.append(text, s, e);
			newText.append(replace);
			s = e + pattern.length();
		}

		newText.append(text.substring(s));
		return newText.toString();
	}

	public void init(DataWatcher datawatcher) {
		dataWatcher = datawatcher;
		datawatcher.addObject(20, 0);
		datawatcher.addObject(21, 0);
		datawatcher.addObject(22, 0);
		datawatcher.addObject(23, (byte) 0);
	}

	public void setQuest(ItemStack itemstack) {
		dataWatcher.updateObject(20, itemstack.itemID);
		dataWatcher.updateObject(21, itemstack.stackSize);
		dataWatcher.updateObject(22, itemstack.getItemDamage());
		setHasQuest(true);
	}

	public boolean isRequiredItem(ItemStack itemstack) {
		return itemstack.itemID == dataWatcher.getWatchableObjectInt(20) && itemstack.stackSize >= dataWatcher.getWatchableObjectInt(21) && itemstack.getItemDamage() == dataWatcher.getWatchableObjectInt(22);
	}

	public ItemStack getRequiredItem() {
		return new ItemStack(dataWatcher.getWatchableObjectInt(20), dataWatcher.getWatchableObjectInt(21), dataWatcher.getWatchableObjectInt(22));
	}

	public boolean hasQuest() {
		return dataWatcher.getWatchableObjectByte(23) == (byte) 1;
	}

	public void setHasQuest(boolean flag) {
		dataWatcher.updateObject(23, (byte) (flag ? 1 : 0));
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("QuestID", dataWatcher.getWatchableObjectInt(20));
		nbt.setInteger("QuestAmount", dataWatcher.getWatchableObjectInt(21));
		nbt.setInteger("QuestDamage", dataWatcher.getWatchableObjectInt(22));
		nbt.setBoolean("HasQuest", hasQuest());
	}

	public void readFromNBT(NBTTagCompound nbt) {
		dataWatcher.updateObject(20, nbt.getInteger("QuestID"));
		dataWatcher.updateObject(21, nbt.getInteger("QuestAmount"));
		dataWatcher.updateObject(22, nbt.getInteger("QuestDamage"));
		dataWatcher.updateObject(23, (byte) (nbt.getBoolean("HasQuest") ? 1 : 0));
	}
}

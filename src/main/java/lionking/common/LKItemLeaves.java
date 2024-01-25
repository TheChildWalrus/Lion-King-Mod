package lionking.common;

import net.minecraft.item.*;

public class LKItemLeaves extends ItemBlock {
	public LKItemLeaves(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int i) {
		return i | 4;
	}
}

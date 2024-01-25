package lionking.common;

import net.minecraft.world.*;

public class LKEntityOutlandess extends LKEntityOutlander {
	public LKEntityOutlandess(World world) {
		super(world);
		setSize(1.2F, 1.3F);
	}

	@Override
	public boolean isHostile() {
		return !inMound || LKLevelData.outlandersHostile == 1 || angerLevel > 0;
	}
}

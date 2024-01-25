package lionking.common;

import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class LKWorldGenTimonAndPumbaa extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		LKEntityPumbaa pumbaa = new LKEntityPumbaa(world);
		pumbaa.setLocationAndAngles(i, j, k, 0.0F, 0.0F);
		world.spawnEntityInWorld(pumbaa);

		LKEntityTimon timon = new LKEntityTimon(world);
		timon.setLocationAndAngles(i, j, k, 0.0F, 0.0F);
		world.spawnEntityInWorld(timon);

		return true;
	}
}

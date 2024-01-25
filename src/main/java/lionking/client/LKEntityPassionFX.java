package lionking.client;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

import java.awt.*;

public class LKEntityPassionFX extends EntityFX {
	public LKEntityPassionFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, 0, 0, 0);
		int i = Color.HSBtoRGB(world.rand.nextFloat(), 1F, 1F);
		Color c = Color.decode(Integer.valueOf(String.valueOf(i), 16).toString());
		particleRed = (float) c.getRed() / 255F;
		particleGreen = (float) c.getGreen() / 255F;
		particleBlue = (float) c.getBlue() / 255F;
		particleMaxAge = 32;
		motionX = d3;
		motionY = d4;
		motionZ = d5;
	}

	@Override
	public int getBrightnessForRender(float f) {
		float f1 = ((float) particleAge + f) / (float) particleMaxAge;

		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f1 > 1.0F) {
			f1 = 1.0F;
		}

		int i = super.getBrightnessForRender(f);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f1 * 15.0F * 16.0F);

		if (j > 240) {
			j = 240;
		}

		return k | k << 16;
	}

	@Override
	public float getBrightness(float f) {
		float f1 = ((float) particleAge + f) / (float) particleMaxAge;

		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f1 > 1.0F) {
			f1 = 1.0F;
		}

		float f2 = super.getBrightness(f);
		return f2 * f1 + (1.0F - f1);
	}
}

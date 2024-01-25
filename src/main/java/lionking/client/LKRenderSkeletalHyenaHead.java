package lionking.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LKRenderSkeletalHyenaHead extends LKRenderLiving {
	private static ResourceLocation texture = new ResourceLocation("lionking:mob/hyena_skeleton.png");

	public LKRenderSkeletalHyenaHead() {
		super(new LKModelHyenaHead(true));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

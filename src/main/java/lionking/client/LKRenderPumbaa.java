package lionking.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LKRenderPumbaa extends LKRenderLiving {
	private final ResourceLocation texture = new ResourceLocation("lionking:mob/pumbaa.png");

	public LKRenderPumbaa() {
		super(new LKModelPumbaa(), "Pumbaa");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

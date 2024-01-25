package lionking.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LKRenderBug extends LKRenderLiving {
	private final ResourceLocation texture = new ResourceLocation("lionking:mob/bug.png");

	public LKRenderBug() {
		super(new LKModelTermite(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

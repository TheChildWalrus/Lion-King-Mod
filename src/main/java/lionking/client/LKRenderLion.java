package lionking.client;

import lionking.common.LKEntityLioness;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LKRenderLion extends LKRenderLiving {
	private ResourceLocation textureLion = new ResourceLocation("lionking:mob/lion.png");
	private ResourceLocation textureLioness = new ResourceLocation("lionking:mob/lioness.png");

	public LKRenderLion() {
		super(new LKModelLion());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return entity instanceof LKEntityLioness ? textureLioness : textureLion;
	}
}

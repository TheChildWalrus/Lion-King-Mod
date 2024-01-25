package lionking.client;

import lionking.common.LKEntityHyena;
import lionking.common.LKEntitySkeletalHyena;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class LKRenderHyena extends LKRenderLiving {
	public static final ResourceLocation textureHyenaSkeleton = new ResourceLocation("lionking:mob/hyena_skeleton.png");
	private static final HashMap textures = new HashMap();

	public LKRenderHyena() {
		super(new LKModelHyena());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (entity instanceof LKEntitySkeletalHyena) {
			return textureHyenaSkeleton;
		}
		int i = ((LKEntityHyena) entity).getHyenaType();
		if (textures.get(i) == null) {
			textures.put(i, new ResourceLocation("lionking:mob/hyena_" + i + ".png"));
		}
		return (ResourceLocation) textures.get(i);
	}
}

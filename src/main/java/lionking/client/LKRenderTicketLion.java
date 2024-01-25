package lionking.client;

import lionking.common.LKIngame;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LKRenderTicketLion extends LKRenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("lionking:mob/ticketlion.png");
	private static final ResourceLocation textureChristmas = new ResourceLocation("lionking:mob/ticketlion_christmas.png");
	
    public LKRenderTicketLion()
    {
        super(new LKModelLion(), "Ticket Lion");
    }
	
	@Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return LKIngame.isChristmas() ? textureChristmas : texture;
    }
}

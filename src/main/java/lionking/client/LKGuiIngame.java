package lionking.client;

import lionking.common.LKEntityZira;
import lionking.common.mod_LionKing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LKGuiIngame {
	private static final ResourceLocation flatulenceTexture = new ResourceLocation("lionking:gui/flatulence.png");

	public static void drawBossHealth(Minecraft mc, EntityLivingBase boss) {
		if (boss == null || !boss.isEntityAlive()) {
			return;
		}

		mc.getTextureManager().bindTexture(LKClientProxy.iconsTexture);
		boolean isZira = boss instanceof LKEntityZira;
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int i = scaledresolution.getScaledWidth();
		int j = i / 2 - 91;
		int k = (int) (boss.getHealth() / boss.getMaxHealth() * (float) 183);

		drawTexturedModalRect(j, 12, 0, isZira ? 16 : 0, 182, 5);

		if (k > 0) {
			drawTexturedModalRect(j, 12, 0, isZira ? 21 : 5, k, 5);
		}

		String s = isZira ? "Zira" : "Scar";
		mc.fontRenderer.drawStringWithShadow(s, i / 2 - mc.fontRenderer.getStringWidth(s) / 2, 2, isZira ? 0xF23C00 : 0xAED110);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderPortalOverlay(float f, Minecraft mc, boolean isPrideLands) {
		if (mc.currentScreen != null) {
			return;
		}
		if (mc.gameSettings.keyBindPlayerList.pressed) {
			return;
		}

		if (f < 1.0F) {
			f *= f;
			f *= f;
			f = f * 0.8F + 0.2F;
		}

		ScaledResolution resolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int i = resolution.getScaledWidth();
		int j = resolution.getScaledHeight();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
		mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		Icon texture = isPrideLands ? mod_LionKing.lionPortal.getBlockTextureFromSide(0) : mod_LionKing.outlandsPortal.getBlockTextureFromSide(0);
		float f1 = texture.getMinU();
		float f2 = texture.getMinV();
		float f3 = texture.getMaxU();
		float f4 = texture.getMaxV();
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, (double) j, -90.0D, (double) f1, (double) f4);
		tessellator.addVertexWithUV((double) i, (double) j, -90.0D, (double) f3, (double) f4);
		tessellator.addVertexWithUV((double) i, 0.0D, -90.0D, (double) f3, (double) f2);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, (double) f1, (double) f2);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderFlatulenceOverlay(float f, Minecraft mc) {
		if (mc.currentScreen != null) {
			return;
		}
		if (mc.gameSettings.keyBindPlayerList.pressed) {
			return;
		}

		ScaledResolution resolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int i = resolution.getScaledWidth();
		int j = resolution.getScaledHeight();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
		mc.getTextureManager().bindTexture(flatulenceTexture);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, (double) j, -90.0D, (double) 0.0F, (double) 256.0F);
		tessellator.addVertexWithUV((double) i, (double) j, -90.0D, (double) 0.0F, (double) 256.0F);
		tessellator.addVertexWithUV((double) i, 0.0D, -90.0D, (double) 0.0F, (double) 256.0F);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, (double) 0.0F, (double) 256.0F);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + height), (double) 0.0F, (double) ((float) (u + 0) * f), (double) ((float) (v + height) * f1));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + height), (double) 0.0F, (double) ((float) (u + width) * f), (double) ((float) (v + height) * f1));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + 0), (double) 0.0F, (double) ((float) (u + width) * f), (double) ((float) (v + 0) * f1));
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) 0.0F, (double) ((float) (u + 0) * f), (double) ((float) (v + 0) * f1));
		tessellator.draw();
	}
}

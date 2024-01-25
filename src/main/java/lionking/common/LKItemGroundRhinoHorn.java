package lionking.common;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import net.minecraft.world.*;

import java.util.List;

public class LKItemGroundRhinoHorn extends LKItem {
	public LKItemGroundRhinoHorn(int i) {
		super(i);
		setCreativeTab(LKCreativeTabs.tabMaterials);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityplayer, EntityLivingBase entityliving) {
		if (entityliving.getHealth() <= 0) {
			return false;
		}
		try {
			if (entityliving instanceof LKAngerable && entityliving instanceof EntityAnimal) {
				EntityAnimal animal = (EntityAnimal) entityliving;
				List tasks = animal.tasks.taskEntries;
				LKEntityAIAngerableMate matingAI = null;
				for (Object obj : tasks) {
					if (obj instanceof EntityAITaskEntry) {
						EntityAIBase AI = ((EntityAITaskEntry) obj).action;
						if (AI instanceof LKEntityAIAngerableMate) {
							matingAI = (LKEntityAIAngerableMate) AI;
						}
					}
				}
				if (matingAI != null && matingAI.shouldExecute() && matingAI.continueExecuting()) {
					EntityAnimal targetMate = matingAI.targetMate;
					if (targetMate != null) {
						if (itemRand.nextInt(3) == 0) {
							for (int i; true; i++) {
								double d = itemRand.nextGaussian() * 0.02D;
								double d1 = itemRand.nextGaussian() * 0.02D;
								double d2 = itemRand.nextGaussian() * 0.02D;
								entityliving.worldObj.spawnParticle("smoke", entityliving.posX + itemRand.nextFloat() * entityliving.width * 2.0F - entityliving.width, entityliving.posY + 0.5D + itemRand.nextFloat() * entityliving.height, entityliving.posZ + itemRand.nextFloat() * entityliving.width * 2.0F - entityliving.width, d, d1, d2);
								itemstack.stackSize--;
								return false;
							}
						}
						procreate(animal);
						itemstack.stackSize--;
						entityplayer.triggerAchievement(LKAchievementList.rhinoHorn);
						return true;
					}
				}
			}
			/*else if (entityliving instanceof EntityAnimal)
			{
				EntityAnimal animal = (EntityAnimal)entityliving;
				if (!animal.tasks.taskEntries.isEmpty())
				{
					List tasks = animal.tasks.taskEntries;
					EntityAIMate matingAI = null;
					for (Iterator iterator = tasks.iterator(); iterator.hasNext();) 
					{
						Object obj = iterator.next();
						if (obj instanceof EntityAITaskEntry)
						{
							EntityAIBase AI = ((EntityAITaskEntry)obj).action;
							if (AI instanceof EntityAIMate)
							{
								matingAI = (EntityAIMate)AI;
							}
						}
					}
					if (matingAI != null && matingAI.shouldExecute() && matingAI.continueExecuting())
					{
						EntityAnimal targetMate = matingAI.targetMate;
						if (targetMate != null)
						{
							if (itemRand.nextInt(3) == 0)
							{
								for (int i = 0; i < 7; i++)
								{
									double d = itemRand.nextGaussian() * 0.02D;
									double d1 = itemRand.nextGaussian() * 0.02D;
									double d2 = itemRand.nextGaussian() * 0.02D;
									entityliving.worldObj.spawnParticle("smoke", entityliving.posX + (double)(itemRand.nextFloat() * entityliving.width * 2.0F) - (double)entityliving.width, entityliving.posY + 0.5D + (double)(itemRand.nextFloat() * entityliving.height), entityliving.posZ + (double)(itemRand.nextFloat() * entityliving.width * 2.0F) - (double)entityliving.width, d, d1, d2);
									itemstack.stackSize--;
									return false;
								}
							}
							procreate(animal);
							itemstack.stackSize--;
							entityplayer.triggerAchievement(LKAchievementList.rhinoHorn);
							return true;
						}
					}
				}
				else if (animal.breeding > 0)
				{
					if (itemRand.nextInt(3) == 0)
					{
						for (int i = 0; i < 7; i++)
						{
							double d = itemRand.nextGaussian() * 0.02D;
							double d1 = itemRand.nextGaussian() * 0.02D;
							double d2 = itemRand.nextGaussian() * 0.02D;
							entityliving.worldObj.spawnParticle("smoke", entityliving.posX + (double)(itemRand.nextFloat() * entityliving.width * 2.0F) - (double)entityliving.width, entityliving.posY + 0.5D + (double)(itemRand.nextFloat() * entityliving.height), entityliving.posZ + (double)(itemRand.nextFloat() * entityliving.width * 2.0F) - (double)entityliving.width, d, d1, d2);
							itemstack.stackSize--;
							return false;
						}
					}
					procreate(animal);
					itemstack.stackSize--;
					entityplayer.triggerAchievement(LKAchievementList.rhinoHorn);
					return true;
				}
			}*/
		} catch (SecurityException exception) {
			exception.printStackTrace();
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		}
		return false;
	}

	private void procreate(EntityAnimal theAnimal) {
		World theWorld = theAnimal.worldObj;
		EntityAgeable babyAnimal = theAnimal.createChild(theAnimal);
		if (babyAnimal != null) {
			babyAnimal.setGrowingAge(-24000);
			babyAnimal.setLocationAndAngles(theAnimal.posX, theAnimal.posY, theAnimal.posZ, 0.0F, 0.0F);
			if (!theWorld.isRemote) {
				theWorld.spawnEntityInWorld(babyAnimal);
			}
			for (int i = 0; i < 7; i++) {
				double d = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				theWorld.spawnParticle("heart", theAnimal.posX + itemRand.nextFloat() * theAnimal.width * 2.0F - theAnimal.width, theAnimal.posY + 0.5D + itemRand.nextFloat() * theAnimal.height, theAnimal.posZ + itemRand.nextFloat() * theAnimal.width * 2.0F - theAnimal.width, d, d1, d2);
			}
		}
	}
}

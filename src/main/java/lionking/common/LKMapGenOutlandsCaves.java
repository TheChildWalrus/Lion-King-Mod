package lionking.common;

import net.minecraft.block.*;

import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.*;

import java.util.Random;

public class LKMapGenOutlandsCaves extends MapGenBase {

	private void generateLargeCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10) {
		generateCaveNode(par1, par3, par4, par5ArrayOfByte, par6, par8, par10, 1.0F + rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}

	private void generateCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17) {
		double par171 = par17;
		float par121 = par12;
		while (true) {
			int par161 = par16;
			int par151 = par15;
			double par61 = par6;
			double par81 = par8;
			double par101 = par10;
			float par141 = par14;
			float par131 = par13;
			double var19 = par3 * 16 + 8;
			double var21 = par4 * 16 + 8;
			float var23 = 0.0F;
			float var24 = 0.0F;
			Random var25 = new Random(par1);

			if (par161 <= 0) {
				int var26 = range * 16 - 16;
				par161 = var26 - var25.nextInt(var26 / 4);
			}

			boolean var54 = false;

			if (par151 == -1) {
				par151 = par161 / 2;
				var54 = true;
			}

			int var27 = var25.nextInt(par161 / 2) + par161 / 4;

			for (boolean var28 = var25.nextInt(6) == 0; par151 < par161; ++par151) {
				double var29 = 1.5D + MathHelper.sin(par151 * (float) Math.PI / par161) * par121 * 1.0F;
				double var31 = var29 * par171;
				float var33 = MathHelper.cos(par141);
				float var34 = MathHelper.sin(par141);
				par61 += MathHelper.cos(par131) * var33;
				par81 += var34;
				par101 += MathHelper.sin(par131) * var33;

				if (var28) {
					par141 *= 0.92F;
				} else {
					par141 *= 0.7F;
				}

				par141 += var24 * 0.1F;
				par131 += var23 * 0.1F;
				var24 *= 0.9F;
				var23 *= 0.75F;
				var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
				var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;

				if (!var54 && par151 == var27 && par121 > 1.0F && par161 > 0) {
					generateCaveNode(var25.nextLong(), par3, par4, par5ArrayOfByte, par61, par81, par101, var25.nextFloat() * 0.5F + 0.5F, par131 - (float) Math.PI / 2.0F, par141 / 3.0F, par151, par161, 1.0D);
					par171 = 1.0D;
					par121 = var25.nextFloat() * 0.5F + 0.5F;
					var25.nextLong();
					continue;
				}

				if (var54 || var25.nextInt(4) != 0) {
					double var35 = par61 - var19;
					double var37 = par101 - var21;
					double var39 = par161 - par151;
					double var41 = par121 + 2.0F + 16.0F;

					if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
						return;
					}

					if (par61 >= var19 - 16.0D - var29 * 2.0D && par101 >= var21 - 16.0D - var29 * 2.0D && par61 <= var19 + 16.0D + var29 * 2.0D && par101 <= var21 + 16.0D + var29 * 2.0D) {
						int var55 = MathHelper.floor_double(par61 - var29) - par3 * 16 - 1;
						int var36 = MathHelper.floor_double(par61 + var29) - par3 * 16 + 1;
						int var57 = MathHelper.floor_double(par81 - var31) - 1;
						int var38 = MathHelper.floor_double(par81 + var31) + 1;
						int var56 = MathHelper.floor_double(par101 - var29) - par4 * 16 - 1;
						int var40 = MathHelper.floor_double(par101 + var29) - par4 * 16 + 1;

						if (var55 < 0) {
							var55 = 0;
						}

						if (var36 > 16) {
							var36 = 16;
						}

						if (var57 < 1) {
							var57 = 1;
						}

						if (var38 > 120) {
							var38 = 120;
						}

						if (var56 < 0) {
							var56 = 0;
						}

						if (var40 > 16) {
							var40 = 16;
						}

						boolean var58 = false;
						int var42;
						int var45;

						for (var42 = var55; !var58 && var42 < var36; ++var42) {
							for (int var43 = var56; !var58 && var43 < var40; ++var43) {
								for (int var44 = var38 + 1; !var58 && var44 >= var57 - 1; --var44) {
									var45 = (var42 * 16 + var43) * 128 + var44;

									if (var44 < 128) {
										if (par5ArrayOfByte[var45] == Block.waterMoving.blockID || par5ArrayOfByte[var45] == Block.waterStill.blockID) {
											var58 = true;
										}

										if (var44 != var57 - 1 && var42 != var55 && var42 != var36 - 1 && var43 != var56 && var43 != var40 - 1) {
											var44 = var57;
										}
									}
								}
							}
						}

						if (!var58) {
							for (var42 = var55; var42 < var36; ++var42) {
								double var59 = ((var42 + par3 * 16) + 0.5D - par61) / var29;

								for (var45 = var56; var45 < var40; ++var45) {
									double var46 = ((var45 + par4 * 16) + 0.5D - par101) / var29;
									int var48 = (var42 * 16 + var45) * 128 + var38;
									boolean var49 = false;

									if (var59 * var59 + var46 * var46 < 1.0D) {
										for (int var50 = var38 - 1; var50 >= var57; --var50) {
											double var51 = (var50 + 0.5D - par81) / var31;

											if (var51 > -0.7D && var59 * var59 + var51 * var51 + var46 * var46 < 1.0D) {
												byte var53 = par5ArrayOfByte[var48];

												if (var53 == Block.sand.blockID) {
													var49 = true;
												}

												if (var53 == (byte) mod_LionKing.pridestone.blockID || var53 == Block.sand.blockID || var53 == Block.sandStone.blockID) {
													if (var50 < 10) {
														par5ArrayOfByte[var48] = (byte) Block.lavaMoving.blockID;
													} else {
														par5ArrayOfByte[var48] = 0;

														if (var49 && par5ArrayOfByte[var48 - 1] == Block.sand.blockID) {
															par5ArrayOfByte[var48 - 1] = worldObj.getBiomeGenForCoords(var42 + par3 * 16, var45 + par4 * 16).topBlock;
														}
													}
												}
											}

											--var48;
										}
									}
								}
							}

							if (var54) {
								break;
							}
						}
					}
				}
			}
			return;
		}
	}

	@Override
	protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
		int var7 = rand.nextInt(rand.nextInt(rand.nextInt(40) + 1) + 1);

		if (rand.nextInt(15) != 0) {
			var7 = 0;
		}

		for (int var8 = 0; var8 < var7; ++var8) {
			double var9 = par2 * 16 + rand.nextInt(16);
			double var11 = rand.nextInt(rand.nextInt(120) + 8);
			double var13 = par3 * 16 + rand.nextInt(16);
			int var15 = 1;

			if (rand.nextInt(4) == 0) {
				generateLargeCaveNode(rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13);
				var15 += rand.nextInt(4);
			}

			for (int var16 = 0; var16 < var15; ++var16) {
				float var17 = rand.nextFloat() * (float) Math.PI * 2.0F;
				float var18 = (rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float var19 = rand.nextFloat() * 2.0F + rand.nextFloat();

				if (rand.nextInt(10) == 0) {
					var19 *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
				}

				generateCaveNode(rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
			}
		}
	}
}
package lionking.common;

public class LKBiomeGenOutlands extends LKOutlandsBiome {
	public LKBiomeGenOutlands(int i) {
		super(i);
		outlandsDecorator.treesPerChunk = 20;
		outlandsDecorator.deadBushPerChunk = 3;
	}
}

package lionking.common;

import net.minecraft.world.*;

public class LKEntityLioness extends LKEntityLionBase {
	public LKEntityLioness(World world) {
		super(world);
		setSize(1.2F, 1.3F);
	}

	@Override
	public LKCharacterSpeech getCharacterSpeech() {
		return LKCharacterSpeech.LIONESS;
	}

	@Override
	public LKCharacterSpeech getChildCharacterSpeech() {
		return LKCharacterSpeech.LIONESS_CUB;
	}

	@Override
	public String getAnimalName() {
		return "Lioness";
	}
}

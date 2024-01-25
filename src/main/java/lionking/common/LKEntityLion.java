package lionking.common;

import net.minecraft.world.*;

public class LKEntityLion extends LKEntityLionBase {
	public LKEntityLion(World world) {
		super(world);
		setSize(1.3F, 1.6F);
	}

	@Override
	public LKCharacterSpeech getCharacterSpeech() {
		return LKCharacterSpeech.LION;
	}

	@Override
	public LKCharacterSpeech getChildCharacterSpeech() {
		return LKCharacterSpeech.LION_CUB;
	}

	@Override
	public String getAnimalName() {
		return "Lion";
	}
}

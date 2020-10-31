package io.github.haykam821.instantfertilization;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Main {
	private static final String MOD_ID = "instantfertilization";

	private static final Identifier INSTANT_FERTILIZATION_BLACKLIST_ID = new Identifier(MOD_ID, "instant_fertilization_blacklist");
	public static final Tag<Block> INSTANT_FERTILIZATION_BLACKLIST = TagRegistry.block(INSTANT_FERTILIZATION_BLACKLIST_ID);
}

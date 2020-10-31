package io.github.haykam821.instantfertilization.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.instantfertilization.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin {
	@Shadow
	private SaplingGenerator generator;

	@Inject(method = "generate", at = @At("HEAD"), cancellable = true)
	public void generate(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
		if (!state.isIn(Main.INSTANT_FERTILIZATION_BLACKLIST)) {
			this.generator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
			ci.cancel();
		}
	}
}

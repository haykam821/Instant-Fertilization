package io.github.haykam821.instantfertilization.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.instantfertilization.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.StemBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(StemBlock.class)
public abstract class StemBlockMixin {
	@Inject(method = "grow", at = @At("HEAD"), cancellable = true)
	private void growToMaximum(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo ci) {
		if (!state.isIn(Main.INSTANT_FERTILIZATION_BLACKLIST)) {
			BlockState newState = state.with(StemBlock.AGE, 7);
			world.setBlockState(pos, newState);
			newState.randomTick(world, pos, random);

			ci.cancel();
		}
	}
}

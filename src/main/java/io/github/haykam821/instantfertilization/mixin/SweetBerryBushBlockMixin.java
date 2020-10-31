package io.github.haykam821.instantfertilization.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.instantfertilization.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(SweetBerryBushBlock.class)
public class SweetBerryBushBlockMixin {
	@Inject(method = "grow", at = @At("HEAD"), cancellable = true)
	private void growToMaximum(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo ci) {
		if (!state.isIn(Main.INSTANT_FERTILIZATION_BLACKLIST)) {
			world.setBlockState(pos, state.with(SweetBerryBushBlock.AGE, 3));
			ci.cancel();
		}
	}
}

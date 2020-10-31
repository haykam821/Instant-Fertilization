package io.github.haykam821.instantfertilization.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.instantfertilization.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(value = { SaplingBlock.class, FungusBlock.class, MushroomPlantBlock.class })
public class SaplingLikeBlockMixin {
	@Inject(method = "canGrow", at = @At("HEAD"), cancellable = true)
	private void allowInstantGrowing(World world, Random random, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> ci) {
		if (!state.isIn(Main.INSTANT_FERTILIZATION_BLACKLIST)) {
			ci.setReturnValue(true);
		}
	}
}

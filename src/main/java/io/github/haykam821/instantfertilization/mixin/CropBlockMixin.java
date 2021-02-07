package io.github.haykam821.instantfertilization.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.haykam821.instantfertilization.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
	@Shadow
	public abstract int getMaxAge();

	@Shadow
	public abstract int getAge(BlockState state);

	@Shadow
	public abstract int getGrowthAmount(World world);

	@Redirect(method = "applyGrowth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/CropBlock;getGrowthAmount(Lnet/minecraft/world/World;)I"))
	private int increaseGrowthAmount(CropBlock block, World passedWorld, World world, BlockPos pos, BlockState state) {
		if (!state.isIn(Main.INSTANT_FERTILIZATION_BLACKLIST)) {
			return this.getMaxAge() - this.getAge(state);
		}
		return this.getGrowthAmount(passedWorld);
	}
}

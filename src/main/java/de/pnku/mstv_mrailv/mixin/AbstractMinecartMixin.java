package de.pnku.mstv_mrailv.mixin;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mrailv.init.MrailvTags.ALL_POWERED_RAIL;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

    @Redirect(method = "getRedstoneDirection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 0))
    protected boolean redirectedMoveAlongTrack(BlockState blockState, Block block) {
        return blockState.is(ALL_POWERED_RAIL);
    }
}

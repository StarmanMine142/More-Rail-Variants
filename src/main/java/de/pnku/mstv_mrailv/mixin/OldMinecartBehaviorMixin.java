package de.pnku.mstv_mrailv.mixin;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartBehavior;
import net.minecraft.world.entity.vehicle.OldMinecartBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mrailv.init.MrailvTags.ACTIVATOR_RAIL_VARIANTS;
import static de.pnku.mstv_mrailv.init.MrailvTags.ALL_POWERED_RAIL;

@Mixin(OldMinecartBehavior.class)
public abstract class OldMinecartBehaviorMixin extends MinecartBehavior {
    protected OldMinecartBehaviorMixin(AbstractMinecart abstractMinecart) {
        super(abstractMinecart);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectedMoveAlongTrackStateIs(BlockState blockState, Block block) {
        return blockState.is(ACTIVATOR_RAIL_VARIANTS) || blockState.is(block);
    }

    @Redirect(method = "moveAlongTrack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectedCalculateHaltTrackSpeedStateIs(BlockState blockState, Block block) {
        return blockState.is(ALL_POWERED_RAIL) || blockState.is(block);
    }
}

package de.pnku.mstv_mrailv.mixin;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartBehavior;
import net.minecraft.world.entity.vehicle.NewMinecartBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mrailv.init.MrailvTags.*;

@Mixin(NewMinecartBehavior.class)
public abstract class NewMinecartBehaviorMixin extends MinecartBehavior {
    protected NewMinecartBehaviorMixin(AbstractMinecart abstractMinecart) {
        super(abstractMinecart);
    }

    @Redirect(method = "moveAlongTrack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectedMoveAlongTrackStateIs(BlockState blockState, Block block) {
        return blockState.is(ACTIVATOR_RAIL_VARIANTS) || blockState.is(block);
    }

    @Redirect(method = "calculateHaltTrackSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectedCalculateHaltTrackSpeedStateIs(BlockState blockState, Block block) {
        return blockState.is(ALL_POWERED_RAIL) || blockState.is(block);
    }

    @Redirect(method = "calculateBoostTrackSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectedCalculateBoostTrackSpeedStateIs(BlockState blockState, Block block) {
        return blockState.is(ALL_POWERED_RAIL) || blockState.is(block);
    }
}

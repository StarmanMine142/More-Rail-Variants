package de.pnku.mstv_mrailv.init.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.jetbrains.annotations.NotNull;

public record IsModLoadedPredicate(String modIdString) implements LootItemCondition {
    public static final MapCodec<IsModLoadedPredicate> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(Codec.STRING.fieldOf("mod_id")
                    .forGetter(IsModLoadedPredicate::modIdString)).apply(instance, IsModLoadedPredicate::new));
    @Override
    public @NotNull LootItemConditionType getType() {
        return MrailvLootConditions.IS_MOD_LOADED;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return FabricLoader.getInstance().isModLoaded(modIdString());
    }
}

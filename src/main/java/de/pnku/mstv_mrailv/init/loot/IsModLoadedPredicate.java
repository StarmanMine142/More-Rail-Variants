package de.pnku.mstv_mrailv.init.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.jetbrains.annotations.NotNull;

public record IsModLoadedPredicate(String modIdString) implements LootItemCondition {
    public static LootItemCondition.Builder isModLoaded(String modIdString) {
        return () -> new IsModLoadedPredicate(modIdString);
    }

    @Override
    public @NotNull LootItemConditionType getType() {
        return MrailvLootConditions.IS_MOD_LOADED;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return FabricLoader.getInstance().isModLoaded(modIdString());
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<IsModLoadedPredicate> {
        public void serialize(JsonObject jsonObject, IsModLoadedPredicate isModLoaded, @NotNull JsonSerializationContext context) {
            jsonObject.addProperty("tag", isModLoaded.toString());
        }

        public @NotNull IsModLoadedPredicate deserialize(@NotNull JsonObject jsonObject, @NotNull JsonDeserializationContext context) {
            String modId = GsonHelper.getAsString(jsonObject, "mod_id");
            return new IsModLoadedPredicate(modId);
        }
    }
}

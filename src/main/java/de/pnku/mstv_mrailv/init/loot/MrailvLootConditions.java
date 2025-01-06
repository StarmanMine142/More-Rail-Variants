package de.pnku.mstv_mrailv.init.loot;

import de.pnku.mstv_mrailv.MoreRailVariants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class MrailvLootConditions {

    public static final LootItemConditionType IS_MOD_LOADED = new LootItemConditionType(new IsModLoadedPredicate.Serializer());

    public static void registerMrailvLootConditions() {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, MoreRailVariants.asId("is_mod_loaded"), IS_MOD_LOADED);
    }
}

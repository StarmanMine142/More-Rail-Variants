package de.pnku.mstv_mrailv.init;

import de.pnku.mstv_mrailv.MoreRailVariants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MrailvTags {
        public static final TagKey<Block> ALL_POWERED_RAIL = TagKey.create(Registries.BLOCK, MoreRailVariants.asId("all_powered_rail"));
        public static final TagKey<Block> POWERED_RAIL_VARIANTS = TagKey.create(Registries.BLOCK, MoreRailVariants.asId("powered_rail_variants"));
        public static final TagKey<Block> ACTIVATOR_RAIL_VARIANTS = TagKey.create(Registries.BLOCK, MoreRailVariants.asId("activator_rail_variants"));
        public static final TagKey<Item> RAIL_REDSTONE_TORCHES = TagKey.create(Registries.ITEM, MoreRailVariants.asId("rail_redstone_torches"));
}

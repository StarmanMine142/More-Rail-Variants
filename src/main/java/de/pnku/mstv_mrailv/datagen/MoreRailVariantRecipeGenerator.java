package de.pnku.mstv_mrailv.datagen;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mrailv.MoreRailVariants;
import de.pnku.mstv_mrailv.init.MrailvBlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import static de.pnku.mstv_mrailv.init.MrailvBlockInit.*;
import static de.pnku.mstv_mrailv.init.MrailvTags.RAIL_REDSTONE_TORCHES;
import static de.pnku.mstv_mtv.init.MtvBlockInit.more_torch_blocks;
import static net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions.allModsLoaded;
import static org.apache.commons.lang3.math.IEEE754rUtils.min;

public class MoreRailVariantRecipeGenerator extends FabricRecipeProvider {
    public MoreRailVariantRecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final List<Item> more_redstone_torches = new ArrayList<>();


    // Fragile (only works if list building order is the same)
    public Map<Item, Item> more_redstone_rail_torches(){
        Map<Item, Item> more_redstone_rail_torch_items = new HashMap<>();
        for (Block torchBlock : more_torch_blocks) {
            if (torchBlock.getDescriptionId().contains("redstone_torch")) {
                more_redstone_torches.add(torchBlock.asItem());
            }
        }
        for (int i = 0; i < min(more_redstone_torches.size(), more_activator_rail_items.size()); i++) {
            more_redstone_rail_torch_items.put(more_activator_rail_items.get(i), more_redstone_torches.get(i*2));
        }
        //LOGGER.info("rs_t_list: " + more_redstone_torches.size() + " a_r_list: " + more_activator_rail_items.size() + " ar_rst_map: " + more_redstone_rail_torch_items.size());
        return more_redstone_rail_torch_items;
    }



    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        Map<Item, Item> more_redstone_rail_torch_items = more_redstone_rail_torches();
        for (Item railItem : more_rail_items) {
            String woodType = more_rail_wood_types.get(railItem);
            Item stickVariant = MoreStickVariantItem.getStickItem(woodType);
            //LOGGER.info(railItem + " -> (from: map size = " + more_rail_wood_types.size() + ") " + woodType + " -> " + stickVariant);
            if (more_activator_rail_items.contains(railItem)) {
                Item baseRailItemA = more_simple_rail_items.get(more_activator_rail_items.indexOf(railItem));
                ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, railItem, 6)
                        .define('#', RAIL_REDSTONE_TORCHES)
                        .define('S', stickVariant)
                        .define('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("XSX")
                        .pattern("X#X")
                        .pattern("XSX")
                        .group("activator_rail")
                        .unlockedBy("has_rail", has(baseRailItemA))
                        .save(withConditions(recipeOutput, ResourceConditions.not(allModsLoaded("quad-mstv-mtv"))));

                LOGGER.info(railItem + " -> Map: " + more_redstone_rail_torch_items.get(railItem));

                ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, railItem, 6)
                        .define('#', more_redstone_rail_torch_items.get(railItem))
                        .define('S', stickVariant)
                        .define('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("XSX")
                        .pattern("X#X")
                        .pattern("XSX")
                        .group("activator_rail")
                        .unlockedBy("has_rail", has(baseRailItemA))
                        .save(withConditions(recipeOutput, ResourceConditions.allModsLoaded("quad-mstv-mtv")), RecipeBuilder.getDefaultRecipeId(railItem).withSuffix("_from_mstv_torch_variants"));
            } else if (more_detector_rail_items.contains(railItem)) {
                Item baseRailItemD = more_simple_rail_items.get(more_detector_rail_items.indexOf(railItem));
                ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, railItem, 6)
                        .define('R', Items.REDSTONE)
                        .define('S', stickVariant)
                        .define('#', Blocks.STONE_PRESSURE_PLATE)
                        .define('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("XSX")
                        .pattern("X#X")
                        .pattern("XRX")
                        .group("detector_rail")
                        .unlockedBy("has_rail", has(baseRailItemD))
                        .save(recipeOutput);
            } else if (more_powered_rail_items.contains(railItem)) {
                Item baseRailItemP = more_simple_rail_items.get(more_powered_rail_items.indexOf(railItem));
                ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, railItem, 6)
                        .define('R', Items.REDSTONE)
                        .define('S', stickVariant)
                        .define('X', ConventionalItemTags.GOLD_INGOTS)
                        .pattern("X X")
                        .pattern("XSX")
                        .pattern("XRX")
                        .group("powered_rail")
                        .unlockedBy("has_rail", has(baseRailItemP))
                        .save(recipeOutput);
            } if (more_simple_rail_items.contains(railItem)) {
                ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, railItem, 16)
                        .define('S', stickVariant)
                        .define('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("X X")
                        .pattern("XSX")
                        .pattern("X X")
                        .group("rail")
                        .unlockedBy("has_stick", has(stickVariant))
                        .save(recipeOutput);
            }
        }
    }
}

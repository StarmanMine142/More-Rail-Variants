package de.pnku.mstv_mrailv.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.*;

import static de.pnku.mstv_mrailv.MoreRailVariants.asId;

public class MrailvBlockInit {

    public static final List<Block> more_rail_blocks = new ArrayList<>();
    public static final Map<Block, String> more_rail_names = new HashMap<>();
    public static final List<Block> more_simple_rail_blocks = new ArrayList<>();
    public static final List<Block> more_activator_rail_blocks = new ArrayList<>();
    public static final List<Block> more_detector_rail_blocks = new ArrayList<>();
    public static final List<Block> more_powered_rail_blocks = new ArrayList<>();
    public static final List<Item> more_rail_items = new ArrayList<>();
    public static final Map<Item, String> more_rail_wood_types = new HashMap<>();
    public static final List<Item> more_simple_rail_items = new ArrayList<>();
    public static final List<Item> more_activator_rail_items = new ArrayList<>();
    public static final List<Item> more_detector_rail_items = new ArrayList<>();
    public static final List<Item> more_powered_rail_items = new ArrayList<>();


    private static Block registerRailBlock(String woodType) {return registerRailBlock(woodType, "");}

    private static Block registerRailBlock(String woodType, String railType) {
        BlockBehaviour.Properties railBlockProperties;
        Block railBlock;
        String railName = railNameByTypes(woodType, railType);

        switch (railType) {
            case "activator" -> {
                railBlockProperties = setProperties(woodType, Blocks.ACTIVATOR_RAIL, railName);
                railBlock = new PoweredRailBlock(railBlockProperties);
                more_activator_rail_blocks.add(railBlock);
            }
            case "detector" -> {
                railBlockProperties = setProperties(woodType, Blocks.DETECTOR_RAIL, railName);
                railBlock = new DetectorRailBlock(railBlockProperties);
                more_detector_rail_blocks.add(railBlock);
            }
            case "powered" -> {
                railBlockProperties = setProperties(woodType, Blocks.POWERED_RAIL, railName);
                railBlock = new PoweredRailBlock(railBlockProperties);
                more_powered_rail_blocks.add(railBlock);
            }
            default -> {
                railBlockProperties = setProperties(woodType, Blocks.RAIL, railName);
                railBlock = new RailBlock(railBlockProperties);
                more_simple_rail_blocks.add(railBlock);
            }

        }
        more_rail_blocks.add(railBlock);
        more_rail_names.put(railBlock, railName);
        return Registry.register(BuiltInRegistries.BLOCK, asId(railName), railBlock);
    }

    private static BlockBehaviour.Properties setProperties(String woodType, Block baseRailBlock, String railName){
        BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(baseRailBlock);
        if (woodType.equals("crimson")||woodType.equals("warped")) {
            blockProperties.sound(SoundType.NETHER_WOOD);
        }
        blockProperties.setId(ResourceKey.create(Registries.BLOCK, asId(railName)));
        return blockProperties;
    }
    private static Item.Properties setProperties(String woodType, Block railItemBlock){
        Item.Properties itemProperties = new Item.Properties();
        if (woodType.equals("crimson")||woodType.equals("warped")) {
            itemProperties.fireResistant();
        }
        itemProperties.setId(ResourceKey.create(Registries.ITEM, BuiltInRegistries.BLOCK.getKey(railItemBlock))).useBlockDescriptionPrefix();
        return itemProperties;
    }

    private static Item registerRailItem(String woodType, Block railItemBlock) {return registerRailItem(woodType, railItemBlock, "");}

    private static Item registerRailItem(String woodType, Block railItemBlock, String railType) {
        Item railItem;
        Item baseRailItem;
            switch (railType){
                case "activator" -> {
                    baseRailItem = Items.ACTIVATOR_RAIL;
                    railItem = new BlockItem(railItemBlock, setProperties(woodType, railItemBlock));
                    more_activator_rail_items.add(railItem);
                }
                case "detector" -> {
                    baseRailItem = Items.DETECTOR_RAIL;
                    railItem = new BlockItem(railItemBlock, setProperties(woodType, railItemBlock));
                    more_detector_rail_items.add(railItem);
                }
                case "powered" -> {
                    baseRailItem = Items.POWERED_RAIL;
                    railItem = new BlockItem(railItemBlock, setProperties(woodType, railItemBlock));
                    more_powered_rail_items.add(railItem);
                }
                default -> {
                    baseRailItem = Items.RAIL;
                    railItem = new BlockItem(railItemBlock, setProperties(woodType, railItemBlock));
                    more_simple_rail_items.add(railItem);
                }
            }
        more_rail_items.add(railItem);
        more_rail_wood_types.put(railItem, woodType);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> entries.addAfter(baseRailItem, railItem));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> entries.addAfter(baseRailItem, railItem));
        return Registry.register(BuiltInRegistries.ITEM, asId(railNameByTypes(woodType, railType)), railItem);
    }

    public static String railNameByTypes(String woodType, String railType) {
        String railName;
        String railNameModifier;
        if (!railType.isEmpty()) {railNameModifier = "_" + railType + "_";}
        else {railNameModifier = "_";}
        railName = woodType + railNameModifier + "rail";
        return railName;
    }
    public static void registerRail() {}
    
    // Simple Rail Blocks + Simple Rail Items (Reverse Order)
    public static final Block WARPED_RAIL = registerRailBlock("warped");
    public static final Item WARPED_RAIL_I = registerRailItem("warped", WARPED_RAIL);

    public static final Block CRIMSON_RAIL = registerRailBlock("crimson");
    public static final Item CRIMSON_RAIL_I = registerRailItem("crimson", CRIMSON_RAIL);

    public static final Block BAMBOO_RAIL = registerRailBlock("bamboo");
    public static final Item BAMBOO_RAIL_I = registerRailItem("bamboo", BAMBOO_RAIL);

    public static final Block CHERRY_RAIL = registerRailBlock("cherry");
    public static final Item CHERRY_RAIL_I = registerRailItem("cherry", CHERRY_RAIL);

    public static final Block MANGROVE_RAIL = registerRailBlock("mangrove");
    public static final Item MANGROVE_RAIL_I = registerRailItem("mangrove", MANGROVE_RAIL);

    public static final Block DARK_OAK_RAIL = registerRailBlock("dark_oak");
    public static final Item DARK_OAK_RAIL_I = registerRailItem("dark_oak", DARK_OAK_RAIL);

    public static final Block PALE_OAK_RAIL = registerRailBlock("pale_oak");
    public static final Item PALE_OAK_RAIL_I = registerRailItem("pale_oak", PALE_OAK_RAIL);

    public static final Block ACACIA_RAIL = registerRailBlock("acacia");
    public static final Item ACACIA_RAIL_I = registerRailItem("acacia", ACACIA_RAIL);

    public static final Block JUNGLE_RAIL = registerRailBlock("jungle");
    public static final Item JUNGLE_RAIL_I = registerRailItem("jungle", JUNGLE_RAIL);

    public static final Block BIRCH_RAIL = registerRailBlock("birch");
    public static final Item BIRCH_RAIL_I = registerRailItem("birch", BIRCH_RAIL);

    public static final Block SPRUCE_RAIL = registerRailBlock("spruce");
    public static final Item SPRUCE_RAIL_I = registerRailItem("spruce", SPRUCE_RAIL);


    // Activator Rail Blocks + Activator Rail Items (Reverse Order)
    public static final Block WARPED_ACTIVATOR_RAIL = registerRailBlock("warped", "activator");
    public static final Item WARPED_ACTIVATOR_RAIL_I = registerRailItem("warped", WARPED_ACTIVATOR_RAIL, "activator");

    public static final Block CRIMSON_ACTIVATOR_RAIL = registerRailBlock("crimson", "activator");
    public static final Item CRIMSON_ACTIVATOR_RAIL_I = registerRailItem("crimson", CRIMSON_ACTIVATOR_RAIL, "activator");

    public static final Block BAMBOO_ACTIVATOR_RAIL = registerRailBlock("bamboo", "activator");
    public static final Item BAMBOO_ACTIVATOR_RAIL_I = registerRailItem("bamboo", BAMBOO_ACTIVATOR_RAIL, "activator");

    public static final Block CHERRY_ACTIVATOR_RAIL = registerRailBlock("cherry", "activator");
    public static final Item CHERRY_ACTIVATOR_RAIL_I = registerRailItem("cherry", CHERRY_ACTIVATOR_RAIL, "activator");

    public static final Block MANGROVE_ACTIVATOR_RAIL = registerRailBlock("mangrove", "activator");
    public static final Item MANGROVE_ACTIVATOR_RAIL_I = registerRailItem("mangrove", MANGROVE_ACTIVATOR_RAIL, "activator");

    public static final Block DARK_OAK_ACTIVATOR_RAIL = registerRailBlock("dark_oak", "activator");
    public static final Item DARK_OAK_ACTIVATOR_RAIL_I = registerRailItem("dark_oak", DARK_OAK_ACTIVATOR_RAIL, "activator");

    public static final Block PALE_OAK_ACTIVATOR_RAIL = registerRailBlock("pale_oak", "activator");
    public static final Item PALE_OAK_ACTIVATOR_RAIL_I = registerRailItem("pale_oak", PALE_OAK_ACTIVATOR_RAIL, "activator");

    public static final Block ACACIA_ACTIVATOR_RAIL = registerRailBlock("acacia", "activator");
    public static final Item ACACIA_ACTIVATOR_RAIL_I = registerRailItem("acacia", ACACIA_ACTIVATOR_RAIL, "activator");

    public static final Block JUNGLE_ACTIVATOR_RAIL = registerRailBlock("jungle", "activator");
    public static final Item JUNGLE_ACTIVATOR_RAIL_I = registerRailItem("jungle", JUNGLE_ACTIVATOR_RAIL, "activator");

    public static final Block BIRCH_ACTIVATOR_RAIL = registerRailBlock("birch", "activator");
    public static final Item BIRCH_ACTIVATOR_RAIL_I = registerRailItem("birch", BIRCH_ACTIVATOR_RAIL, "activator");

    public static final Block SPRUCE_ACTIVATOR_RAIL = registerRailBlock("spruce", "activator");
    public static final Item SPRUCE_ACTIVATOR_RAIL_I = registerRailItem("spruce", SPRUCE_ACTIVATOR_RAIL, "activator");


    // Detector Rail Blocks + Detector Rail Items (Reverse Order)
    public static final Block WARPED_DETECTOR_RAIL = registerRailBlock("warped", "detector");
    public static final Item WARPED_DETECTOR_RAIL_I = registerRailItem("warped", WARPED_DETECTOR_RAIL, "detector");

    public static final Block CRIMSON_DETECTOR_RAIL = registerRailBlock("crimson", "detector");
    public static final Item CRIMSON_DETECTOR_RAIL_I = registerRailItem("crimson", CRIMSON_DETECTOR_RAIL, "detector");

    public static final Block BAMBOO_DETECTOR_RAIL = registerRailBlock("bamboo", "detector");
    public static final Item BAMBOO_DETECTOR_RAIL_I = registerRailItem("bamboo", BAMBOO_DETECTOR_RAIL, "detector");

    public static final Block CHERRY_DETECTOR_RAIL = registerRailBlock("cherry", "detector");
    public static final Item CHERRY_DETECTOR_RAIL_I = registerRailItem("cherry", CHERRY_DETECTOR_RAIL, "detector");

    public static final Block MANGROVE_DETECTOR_RAIL = registerRailBlock("mangrove", "detector");
    public static final Item MANGROVE_DETECTOR_RAIL_I = registerRailItem("mangrove", MANGROVE_DETECTOR_RAIL, "detector");

    public static final Block DARK_OAK_DETECTOR_RAIL = registerRailBlock("dark_oak", "detector");
    public static final Item DARK_OAK_DETECTOR_RAIL_I = registerRailItem("dark_oak", DARK_OAK_DETECTOR_RAIL, "detector");

    public static final Block PALE_OAK_DETECTOR_RAIL = registerRailBlock("pale_oak", "detector");
    public static final Item PALE_OAK_DETECTOR_RAIL_I = registerRailItem("pale_oak", PALE_OAK_DETECTOR_RAIL, "detector");

    public static final Block ACACIA_DETECTOR_RAIL = registerRailBlock("acacia", "detector");
    public static final Item ACACIA_DETECTOR_RAIL_I = registerRailItem("acacia", ACACIA_DETECTOR_RAIL, "detector");

    public static final Block JUNGLE_DETECTOR_RAIL = registerRailBlock("jungle", "detector");
    public static final Item JUNGLE_DETECTOR_RAIL_I = registerRailItem("jungle", JUNGLE_DETECTOR_RAIL, "detector");

    public static final Block BIRCH_DETECTOR_RAIL = registerRailBlock("birch", "detector");
    public static final Item BIRCH_DETECTOR_RAIL_I = registerRailItem("birch", BIRCH_DETECTOR_RAIL, "detector");

    public static final Block SPRUCE_DETECTOR_RAIL = registerRailBlock("spruce", "detector");
    public static final Item SPRUCE_DETECTOR_RAIL_I = registerRailItem("spruce", SPRUCE_DETECTOR_RAIL, "detector");


    // Powered Rail Blocks + Powered Rail Items (Reverse Order)
    public static final Block WARPED_POWERED_RAIL = registerRailBlock("warped", "powered");
    public static final Item WARPED_POWERED_RAIL_I = registerRailItem("warped", WARPED_POWERED_RAIL, "powered");

    public static final Block CRIMSON_POWERED_RAIL = registerRailBlock("crimson", "powered");
    public static final Item CRIMSON_POWERED_RAIL_I = registerRailItem("crimson", CRIMSON_POWERED_RAIL, "powered");

    public static final Block BAMBOO_POWERED_RAIL = registerRailBlock("bamboo", "powered");
    public static final Item BAMBOO_POWERED_RAIL_I = registerRailItem("bamboo", BAMBOO_POWERED_RAIL, "powered");

    public static final Block CHERRY_POWERED_RAIL = registerRailBlock("cherry", "powered");
    public static final Item CHERRY_POWERED_RAIL_I = registerRailItem("cherry", CHERRY_POWERED_RAIL, "powered");

    public static final Block MANGROVE_POWERED_RAIL = registerRailBlock("mangrove", "powered");
    public static final Item MANGROVE_POWERED_RAIL_I = registerRailItem("mangrove", MANGROVE_POWERED_RAIL, "powered");

    public static final Block DARK_OAK_POWERED_RAIL = registerRailBlock("dark_oak", "powered");
    public static final Item DARK_OAK_POWERED_RAIL_I = registerRailItem("dark_oak", DARK_OAK_POWERED_RAIL, "powered");

    public static final Block PALE_OAK_POWERED_RAIL = registerRailBlock("pale_oak", "powered");
    public static final Item PALE_OAK_POWERED_RAIL_I = registerRailItem("pale_oak", PALE_OAK_POWERED_RAIL, "powered");

    public static final Block ACACIA_POWERED_RAIL = registerRailBlock("acacia", "powered");
    public static final Item ACACIA_POWERED_RAIL_I = registerRailItem("acacia", ACACIA_POWERED_RAIL, "powered");

    public static final Block JUNGLE_POWERED_RAIL = registerRailBlock("jungle", "powered");
    public static final Item JUNGLE_POWERED_RAIL_I = registerRailItem("jungle", JUNGLE_POWERED_RAIL, "powered");

    public static final Block BIRCH_POWERED_RAIL = registerRailBlock("birch", "powered");
    public static final Item BIRCH_POWERED_RAIL_I = registerRailItem("birch", BIRCH_POWERED_RAIL, "powered");

    public static final Block SPRUCE_POWERED_RAIL = registerRailBlock("spruce", "powered");
    public static final Item SPRUCE_POWERED_RAIL_I = registerRailItem("spruce", SPRUCE_POWERED_RAIL, "powered");


}
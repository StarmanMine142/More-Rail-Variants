package de.pnku.mstv_mrailv.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
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


    private static Block registerRailBlock(String woodType, Block railBlock) {
        more_simple_rail_blocks.add(railBlock);
        return registerRailBlock(woodType, railBlock, "");
    }

    private static Block registerRailBlock(String woodType, Block railBlock, String railType) {
        more_rail_blocks.add(railBlock);
        String railName = railNameByTypes(woodType, railType);
        more_rail_names.put(railBlock, railName);
        if (!railType.isEmpty()) {
            switch (railType) {
                case "activator" -> more_activator_rail_blocks.add(railBlock);
                case "detector" -> more_detector_rail_blocks.add(railBlock);
                case "powered" -> more_powered_rail_blocks.add(railBlock);
            }
        }
        return Registry.register(BuiltInRegistries.BLOCK, asId(railName), railBlock);
    }

    private static Item registerRailItem(String woodType, Item railItem) {
        more_simple_rail_items.add(railItem);
        return registerRailItem(woodType, railItem, "");
    }

    private static Item registerRailItem(String woodType, Item railItem, String railType) {
        more_rail_items.add(railItem);
        more_rail_wood_types.put(railItem, woodType);
        Item baseRailItem;
            switch (railType){
                case "activator" -> {baseRailItem = Items.ACTIVATOR_RAIL; more_activator_rail_items.add(railItem);}
                case "detector" -> {baseRailItem = Items.DETECTOR_RAIL; more_detector_rail_items.add(railItem);}
                case "powered" -> {baseRailItem = Items.POWERED_RAIL; more_powered_rail_items.add(railItem);}
                default -> baseRailItem = Items.RAIL;
            }
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
    public static final Block WARPED_RAIL = registerRailBlock("warped", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL).sound(SoundType.NETHER_WOOD)));
    public static final Item WARPED_RAIL_I = registerRailItem("warped", new BlockItem(WARPED_RAIL, new Item.Properties().fireResistant()));

    public static final Block CRIMSON_RAIL = registerRailBlock("crimson", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL).sound(SoundType.NETHER_WOOD)));
    public static final Item CRIMSON_RAIL_I = registerRailItem("crimson", new BlockItem(CRIMSON_RAIL, new Item.Properties().fireResistant()));

    public static final Block BAMBOO_RAIL = registerRailBlock("bamboo", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item BAMBOO_RAIL_I = registerRailItem("bamboo", new BlockItem(BAMBOO_RAIL, new Item.Properties()));

    public static final Block CHERRY_RAIL = registerRailBlock("cherry", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item CHERRY_RAIL_I = registerRailItem("cherry", new BlockItem(CHERRY_RAIL, new Item.Properties()));

    public static final Block MANGROVE_RAIL = registerRailBlock("mangrove", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item MANGROVE_RAIL_I = registerRailItem("mangrove", new BlockItem(MANGROVE_RAIL, new Item.Properties()));

    public static final Block DARK_OAK_RAIL = registerRailBlock("dark_oak", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item DARK_OAK_RAIL_I = registerRailItem("dark_oak", new BlockItem(DARK_OAK_RAIL, new Item.Properties()));

    public static final Block ACACIA_RAIL = registerRailBlock("acacia", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item ACACIA_RAIL_I = registerRailItem("acacia", new BlockItem(ACACIA_RAIL, new Item.Properties()));

    public static final Block JUNGLE_RAIL = registerRailBlock("jungle", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item JUNGLE_RAIL_I = registerRailItem("jungle", new BlockItem(JUNGLE_RAIL, new Item.Properties()));

    public static final Block BIRCH_RAIL = registerRailBlock("birch", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item BIRCH_RAIL_I = registerRailItem("birch", new BlockItem(BIRCH_RAIL, new Item.Properties()));

    public static final Block SPRUCE_RAIL = registerRailBlock("spruce", new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Item SPRUCE_RAIL_I = registerRailItem("spruce", new BlockItem(SPRUCE_RAIL, new Item.Properties()));


    // Activator Rail Blocks + Activator Rail Items (Reverse Order)
    public static final Block WARPED_ACTIVATOR_RAIL = registerRailBlock("warped", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL).sound(SoundType.NETHER_WOOD)), "activator");
    public static final Item WARPED_ACTIVATOR_RAIL_I = registerRailItem("warped", new BlockItem(WARPED_ACTIVATOR_RAIL, new Item.Properties().fireResistant()), "activator");

    public static final Block CRIMSON_ACTIVATOR_RAIL = registerRailBlock("crimson", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL).sound(SoundType.NETHER_WOOD)), "activator");
    public static final Item CRIMSON_ACTIVATOR_RAIL_I = registerRailItem("crimson", new BlockItem(CRIMSON_ACTIVATOR_RAIL, new Item.Properties().fireResistant()), "activator");

    public static final Block BAMBOO_ACTIVATOR_RAIL = registerRailBlock("bamboo", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item BAMBOO_ACTIVATOR_RAIL_I = registerRailItem("bamboo", new BlockItem(BAMBOO_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block CHERRY_ACTIVATOR_RAIL = registerRailBlock("cherry", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item CHERRY_ACTIVATOR_RAIL_I = registerRailItem("cherry", new BlockItem(CHERRY_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block MANGROVE_ACTIVATOR_RAIL = registerRailBlock("mangrove", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item MANGROVE_ACTIVATOR_RAIL_I = registerRailItem("mangrove", new BlockItem(MANGROVE_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block DARK_OAK_ACTIVATOR_RAIL = registerRailBlock("dark_oak", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item DARK_OAK_ACTIVATOR_RAIL_I = registerRailItem("dark_oak", new BlockItem(DARK_OAK_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block ACACIA_ACTIVATOR_RAIL = registerRailBlock("acacia", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item ACACIA_ACTIVATOR_RAIL_I = registerRailItem("acacia", new BlockItem(ACACIA_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block JUNGLE_ACTIVATOR_RAIL = registerRailBlock("jungle", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item JUNGLE_ACTIVATOR_RAIL_I = registerRailItem("jungle", new BlockItem(JUNGLE_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block BIRCH_ACTIVATOR_RAIL = registerRailBlock("birch", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item BIRCH_ACTIVATOR_RAIL_I = registerRailItem("birch", new BlockItem(BIRCH_ACTIVATOR_RAIL, new Item.Properties()), "activator");

    public static final Block SPRUCE_ACTIVATOR_RAIL = registerRailBlock("spruce", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)), "activator");
    public static final Item SPRUCE_ACTIVATOR_RAIL_I = registerRailItem("spruce", new BlockItem(SPRUCE_ACTIVATOR_RAIL, new Item.Properties()), "activator");


    // Detector Rail Blocks + Detector Rail Items (Reverse Order)
    public static final Block WARPED_DETECTOR_RAIL = registerRailBlock("warped", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL).sound(SoundType.NETHER_WOOD)), "detector");
    public static final Item WARPED_DETECTOR_RAIL_I = registerRailItem("warped", new BlockItem(WARPED_DETECTOR_RAIL, new Item.Properties().fireResistant()), "detector");

    public static final Block CRIMSON_DETECTOR_RAIL = registerRailBlock("crimson", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL).sound(SoundType.NETHER_WOOD)), "detector");
    public static final Item CRIMSON_DETECTOR_RAIL_I = registerRailItem("crimson", new BlockItem(CRIMSON_DETECTOR_RAIL, new Item.Properties().fireResistant()), "detector");

    public static final Block BAMBOO_DETECTOR_RAIL = registerRailBlock("bamboo", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item BAMBOO_DETECTOR_RAIL_I = registerRailItem("bamboo", new BlockItem(BAMBOO_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block CHERRY_DETECTOR_RAIL = registerRailBlock("cherry", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item CHERRY_DETECTOR_RAIL_I = registerRailItem("cherry", new BlockItem(CHERRY_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block MANGROVE_DETECTOR_RAIL = registerRailBlock("mangrove", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item MANGROVE_DETECTOR_RAIL_I = registerRailItem("mangrove", new BlockItem(MANGROVE_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block DARK_OAK_DETECTOR_RAIL = registerRailBlock("dark_oak", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item DARK_OAK_DETECTOR_RAIL_I = registerRailItem("dark_oak", new BlockItem(DARK_OAK_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block ACACIA_DETECTOR_RAIL = registerRailBlock("acacia", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item ACACIA_DETECTOR_RAIL_I = registerRailItem("acacia", new BlockItem(ACACIA_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block JUNGLE_DETECTOR_RAIL = registerRailBlock("jungle", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item JUNGLE_DETECTOR_RAIL_I = registerRailItem("jungle", new BlockItem(JUNGLE_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block BIRCH_DETECTOR_RAIL = registerRailBlock("birch", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item BIRCH_DETECTOR_RAIL_I = registerRailItem("birch", new BlockItem(BIRCH_DETECTOR_RAIL, new Item.Properties()), "detector");

    public static final Block SPRUCE_DETECTOR_RAIL = registerRailBlock("spruce", new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)), "detector");
    public static final Item SPRUCE_DETECTOR_RAIL_I = registerRailItem("spruce", new BlockItem(SPRUCE_DETECTOR_RAIL, new Item.Properties()), "detector");


    // Powered Rail Blocks + Powered Rail Items (Reverse Order)
    public static final Block WARPED_POWERED_RAIL = registerRailBlock("warped", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL).sound(SoundType.NETHER_WOOD)), "powered");
    public static final Item WARPED_POWERED_RAIL_I = registerRailItem("warped", new BlockItem(WARPED_POWERED_RAIL, new Item.Properties().fireResistant()), "powered");

    public static final Block CRIMSON_POWERED_RAIL = registerRailBlock("crimson", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL).sound(SoundType.NETHER_WOOD)), "powered");
    public static final Item CRIMSON_POWERED_RAIL_I = registerRailItem("crimson", new BlockItem(CRIMSON_POWERED_RAIL, new Item.Properties().fireResistant()), "powered");

    public static final Block BAMBOO_POWERED_RAIL = registerRailBlock("bamboo", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item BAMBOO_POWERED_RAIL_I = registerRailItem("bamboo", new BlockItem(BAMBOO_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block CHERRY_POWERED_RAIL = registerRailBlock("cherry", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item CHERRY_POWERED_RAIL_I = registerRailItem("cherry", new BlockItem(CHERRY_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block MANGROVE_POWERED_RAIL = registerRailBlock("mangrove", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item MANGROVE_POWERED_RAIL_I = registerRailItem("mangrove", new BlockItem(MANGROVE_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block DARK_OAK_POWERED_RAIL = registerRailBlock("dark_oak", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item DARK_OAK_POWERED_RAIL_I = registerRailItem("dark_oak", new BlockItem(DARK_OAK_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block ACACIA_POWERED_RAIL = registerRailBlock("acacia", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item ACACIA_POWERED_RAIL_I = registerRailItem("acacia", new BlockItem(ACACIA_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block JUNGLE_POWERED_RAIL = registerRailBlock("jungle", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item JUNGLE_POWERED_RAIL_I = registerRailItem("jungle", new BlockItem(JUNGLE_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block BIRCH_POWERED_RAIL = registerRailBlock("birch", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item BIRCH_POWERED_RAIL_I = registerRailItem("birch", new BlockItem(BIRCH_POWERED_RAIL, new Item.Properties()), "powered");

    public static final Block SPRUCE_POWERED_RAIL = registerRailBlock("spruce", new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)), "powered");
    public static final Item SPRUCE_POWERED_RAIL_I = registerRailItem("spruce", new BlockItem(SPRUCE_POWERED_RAIL, new Item.Properties()), "powered");


}
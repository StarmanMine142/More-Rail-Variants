package de.pnku.mstv_mrailv.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.text.WordUtils;

import java.util.concurrent.CompletableFuture;

import static de.pnku.mstv_mrailv.init.MrailvBlockInit.*;

public class MoreRailVariantLangGenerator extends FabricLanguageProvider {
    public MoreRailVariantLangGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        for (Block railBlock : more_rail_blocks) {
            String railPath = more_rail_names.get(railBlock);
            String railName = WordUtils.capitalizeFully(railPath.replace("_", " "));
            translationBuilder.add(railBlock, railName);
        }
        translationBuilder.add(Blocks.RAIL, "Oak Rail");
        translationBuilder.add(Blocks.ACTIVATOR_RAIL, "Oak Activator Rail");
        translationBuilder.add(Blocks.DETECTOR_RAIL, "Oak Detector Rail");
        translationBuilder.add(Blocks.POWERED_RAIL, "Oak Powered Rail");
    }
}

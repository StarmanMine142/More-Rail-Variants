package de.pnku.mstv_mrailv;

import de.pnku.mstv_mrailv.datagen.MoreRailVariantLootTableGenerator;
import de.pnku.mstv_mrailv.datagen.MoreRailVariantRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoreRailVariantDatagen implements DataGeneratorEntrypoint {

    public static FabricDataGenerator.Pack pack;

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        pack = generator.createPack();
        pack.addProvider(MoreRailVariantRecipeGenerator::new);
        pack.addProvider(MoreRailVariantLootTableGenerator::new);
        //pack.addProvider(MoreRailVariantLangGenerator::new);
    }

}

package de.pnku.mstv_mrailv;

import de.pnku.mstv_mrailv.init.MrailvBlockInit;
import de.pnku.mstv_mrailv.init.loot.MrailvLootConditions;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MoreRailVariants implements ModInitializer {

	public static final String MOD_ID = "quad-mstv-mrailv";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	
	@Override
	public void onInitialize() {
		MrailvBlockInit.registerRail();
		MrailvLootConditions.registerMrailvLootConditions();
	}

	public static ResourceLocation asId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

}

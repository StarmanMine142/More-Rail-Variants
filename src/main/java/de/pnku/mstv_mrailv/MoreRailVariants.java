package de.pnku.mstv_mrailv;

import de.pnku.mstv_mrailv.init.MrailvBlockInit;
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
	}

	public static ResourceLocation asId(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

}

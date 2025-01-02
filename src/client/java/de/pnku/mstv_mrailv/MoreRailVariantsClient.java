package de.pnku.mstv_mrailv;

import de.pnku.mstv_mrailv.init.MrailvBlockInit;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;


public class MoreRailVariantsClient implements ClientModInitializer {

	
	@Override
	public void onInitializeClient() {
		for (Block torchBlock : MrailvBlockInit.more_rail_blocks) {
			BlockRenderLayerMap.INSTANCE.putBlock(torchBlock, RenderType.cutout());
		}
	}

}

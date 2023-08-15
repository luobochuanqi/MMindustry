package mc.mdt;

import mc.mdt.blockEntityRenderer.ConveyorBeltBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

/**
 * @author luobochuanqi
 */
public class MMindustryClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		// renderer
		BlockEntityRendererFactories.register(MMindustry.WOOD_CONVEYOR_BELT_BLOCK_ENTITY, ConveyorBeltBlockEntityRenderer::new);
	}
}
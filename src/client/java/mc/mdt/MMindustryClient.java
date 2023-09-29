package mc.mdt;

import mc.mdt.blockEntityRenderer.ConveyorBeltBlockEntityRenderer;
import mc.mdt.common.init.MDTBlockEntitys;
import mc.mdt.common.init.MDTEntitys;
import mc.mdt.entityRenderer.DuoTurretEntityModel;
import mc.mdt.entityRenderer.DuoTurretEntityRenderer;
import mc.mdt.screen.ConveyorBeltScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

/**
 * @author luobochuanqi
 */
public class MMindustryClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier(MMindustry.MOD_ID, "duo"), "main");

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        // blockentity renderer
        BlockEntityRendererFactories.register(MDTBlockEntitys.WOOD_CONVEYOR_BELT_BLOCK_ENTITY, ConveyorBeltBlockEntityRenderer::new);

        // screen
        HandledScreens.register(MMindustry.CONVEYOR_BELT_SCREEN_HANDLER_TYPE, ConveyorBeltScreen::new);

        // entity renderer
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.INSTANCE.register(MDTEntitys.TURRET_DUO_ENTITY, (context) -> {
            return new DuoTurretEntityRenderer(context);
        });

        EntityRendererRegistry.INSTANCE.register(MDTEntitys.BULLET_ENTITY, (context) -> {
//            return new ProjectileEntityRenderer<BulletEntity>(context);
            return new FlyingItemEntityRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, DuoTurretEntityModel::createBodyLayer);
    }
}
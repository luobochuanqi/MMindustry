package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.luobochuanqi.mindustry.common.Type.DrillBlock.DrillBlockScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScreenRegister {
    public static class ModBusEventHandler {
        @SubscribeEvent
        public static void onClientSetupEvent(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ScreenManager.register(ContainerRegister.drill_block_container.get(), DrillBlockScreen::new);
            });
        }
    }
}

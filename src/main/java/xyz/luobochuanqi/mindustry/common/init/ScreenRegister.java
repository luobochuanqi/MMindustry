package xyz.luobochuanqi.mindustry.common.init;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.luobochuanqi.mindustry.client.GUI.OneEItemSlotScreen;
import xyz.luobochuanqi.mindustry.client.GUI.OneItemSlotScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScreenRegister {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ScreenManager.register(ContainerRegister.one_item_slot_container.get(), OneItemSlotScreen::new);
            ScreenManager.register(ContainerRegister.one_eitem_slot_container.get(), OneEItemSlotScreen::new);
        });
    }
}

package xyz.luobochuanqi.mindustry.common.util;

import com.google.gson.JsonElement;
import net.minecraft.client.renderer.model.ItemModelGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.world.Item.Ores;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Utils.ModID)
public class DataGenHandler {
    @SubscribeEvent
    public static void register(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeClient()) {
            //block/item models, blockstates, language files...
            event.getGenerator().addProvider(new ModLanguageProvider(event.getGenerator(), Utils.ModID, "zh_cn"));
        }
        if (event.includeServer()) {
            //recipes,advancements,tags...
        }
        if (event.includeReports()){
            //world
        }
    }

    public static class ModLanguageProvider extends LanguageProvider {
        public ModLanguageProvider(DataGenerator gen, String modid, String locale) {
            super(gen, modid, locale);
        }

        @Override
        protected void addTranslations() {
            for (Ores.OreType myOre : Ores.OreType.values()) {
                try {
                    add("item.mindustry." + myOre.toString(), pro2json(myOre.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public String pro2json(String name) throws IOException {
            InputStream inputStream = this.getClass().getResourceAsStream("bundle_zh_CN.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty("item." + name + ".name");
        }
    }

    public class ModItemModelProvider extends ItemModelProvider {
        public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {

        }

        @Override
        protected void generateAll(DirectoryCache cache) {
            super.generateAll(cache);
        }
    }
}

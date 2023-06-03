package xyz.luobochuanqi.mindustry.common.util;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.luobochuanqi.mindustry.Utils;
import xyz.luobochuanqi.mindustry.common.init.BlockRegister;
import xyz.luobochuanqi.mindustry.common.world.Block.OreBlocks;
import xyz.luobochuanqi.mindustry.common.world.Item.Ores;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Utils.ModID)
public class DataGenHandler {
    @SubscribeEvent
    public static void register(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeClient()) {
            //block/item models, blockstates, language files...
            //create en_us.json
//            event.getGenerator().addProvider(new ModLanguageProviderUS(event.getGenerator(), Utils.ModID, "en_us"));
            //create zh_cn.json
//            event.getGenerator().addProvider(new ModLanguageProviderCN(event.getGenerator(), Utils.ModID, "zh_cn"));
            //gen models.json
//            event.getGenerator().addProvider(new ModOreItemModelProvider(event.getGenerator(), Utils.ModID, event.getExistingFileHelper()));
            //gen blockstate
            event.getGenerator().addProvider(new ModOreBlockStateProvider(event.getGenerator(), Utils.ModID, event.getExistingFileHelper()));
//            event.getGenerator().addProvider(new ModItemModelProvider(event.getGenerator(), Utils.ModID, event.getExistingFileHelper(), ItemRegister.ITEMS));
        }
        if (event.includeServer()) {
            //recipes,advancements,tags...
        }
        if (event.includeReports()) {
            //world
        }
    }

    public static class ModLanguageProviderUS extends LanguageProvider {
        public ModLanguageProviderUS(DataGenerator gen, String modid, String locale) {
            super(gen, modid, locale);
        }

        @Override
        protected void addTranslations() {
            for (OreBlocks.OreBlockType myOre : OreBlocks.OreBlockType.values()) {
                add("block.mindustry." + myOre.toString(), underline2hump(myOre.toString()));
            }
        }

        public static String underline2hump(String str) {
            String regex = "_(.)";
            Matcher matcher = Pattern.compile(regex).matcher(str);
            while (matcher.find()) {
                String target = matcher.group(1);
                str = str.replaceAll("_" + target, " " + target.toUpperCase());
            }
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
            return str;
        }
    }

    public static class ModLanguageProviderCN extends LanguageProvider {
        public ModLanguageProviderCN(DataGenerator gen, String modid, String locale) {
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
            Properties pro = new Properties();
            FileInputStream in = new FileInputStream("zh_CN.properties");
            pro.load(in);
            in.close();
            return pro.getProperty("item." + name + ".name");
//            InputStream inputStream = this.getClass().getResourceAsStream("zh_CN.properties");
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            return properties.getProperty("item." + name + ".name");
        }
    }

    public static class ModOreItemModelProvider extends ItemModelProvider {
        public final ResourceLocation GENERATED = new ResourceLocation("item/generated");
        public final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

        public ModOreItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            for (Ores.OreType myOre : Ores.OreType.values()) {
                generatedItem(myOre.toString());
            }
        }

        protected final ItemModelBuilder generatedItem(String name) {
            return withExistingParent(name, GENERATED)
                    .texture("layer0", modLoc("item/" + name));
        }
    }

    public static class ModOreBlockStateProvider extends BlockStateProvider {
        private DeferredRegister<? extends Block> deferredRegister = BlockRegister.BLOCKS;
        private final Set<Block> skipBlocks = new HashSet<>();

        public ModOreBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
            super(gen, modid, exFileHelper);
            this.deferredRegister = deferredRegister;
        }

        @Override
        protected void registerStatesAndModels() {
            Set<Block> blocks = getBlocks();
            blocks.removeAll(skipBlocks);

            registerBlock(blocks);
        }

        protected void skipBlock(Block... blocks) {
            Collections.addAll(skipBlocks, blocks);
        }

        protected void skipBlock(Collection<Block> blocks) {
            skipBlocks.addAll(blocks);
        }

        protected Set<Block> getBlocks() {
            return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
        }

        protected void registerBlock(Set<Block> blocks) {
            blocks.forEach(this::simpleBlock);
        }
    }

    //gen all
    public static class ModItemModelProvider extends ItemModelProvider {
        public final ResourceLocation GENERATED = new ResourceLocation("item/generated");
        public final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");
        protected final DeferredRegister<? extends Item> deferredRegister;
        protected Set<Item> skipItems = new HashSet<>();

        public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<? extends Item> deferredRegister) {
            super(generator, modid, existingFileHelper);
            this.deferredRegister = deferredRegister;
        }

        private static String name(Item item) {
            return ForgeRegistries.ITEMS.getKey(item).getPath();
        }

        @Override
        protected void registerModels() {
            Set<Item> items = getItems();

            items.removeAll(skipItems);
            registerItemBlock(items.stream()
                    .filter(item -> item instanceof BlockItem)
                    .map(item -> (BlockItem) item)
                    .collect(Collectors.toSet()));

            items.removeAll(skipItems);
            registerItem(items);
        }

        protected void skipItems(Item... items) {
            Collections.addAll(skipItems, items);
        }

        protected void skipItems(Collection<? extends Item> items) {
            skipItems.addAll(items);
        }

        protected Set<Item> getItems() {
            return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
        }

        protected void registerItemBlock(Set<BlockItem> blockItems) {
            blockItems.forEach(blockItem -> withExistingParent(name(blockItem),
                    modLoc("block/" + name(blockItem))));
            skipItems(blockItems);
        }

        protected void registerItem(Set<Item> items) {
            items.forEach(this::generatedItem);
            items.stream()
                    .filter(item -> item instanceof TieredItem)
                    .forEach(this::handheldItem);
        }

        protected final ItemModelBuilder generatedItem(String name) {
            return withExistingParent(name, GENERATED)
                    .texture("layer0", modLoc("item/" + name));
        }

        protected final ItemModelBuilder generatedItem(Item item) {
            return generatedItem(name(item));
        }

        protected final ItemModelBuilder handheldItem(String name) {
            return withExistingParent(name, HANDHELD)
                    .texture("layer0", modLoc("item/" + name));
        }

        protected final ItemModelBuilder handheldItem(Item item) {
            return handheldItem(name(item));
        }
    }

}

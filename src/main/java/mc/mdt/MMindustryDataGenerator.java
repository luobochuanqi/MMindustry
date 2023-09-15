package mc.mdt;

import mc.mdt.common.init.MDTBlocks;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class MMindustryDataGenerator implements DataGeneratorEntrypoint {

	private static class ModEnglishLangProvider extends FabricLanguageProvider {
		private ModEnglishLangProvider(FabricDataOutput dataGenerator) {
			super(dataGenerator, "en_us");
		}

		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(MMindustry.MMDT_ITEM_GROUP_REGISTRY_KEY, "MMindustry Items");

			// Load an existing language file.
//			try {
//				Path existingFilePath = dataGenerator.getModContainer().findPath("assets/mmindustry/lang/en_us.existing.json").get();
//				translationBuilder.add(existingFilePath);
//			} catch (Exception e) {
//				throw new RuntimeException("Failed to add existing language file!", e);
//			}
		}
	}

	private static class ModModelGenerator extends FabricModelProvider {
		private ModModelGenerator(FabricDataOutput output) {
			super(output);
		}

		// .put(VariantSettings.MODEL, new Identifier())
		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(MDTBlocks.WOOD_CONVEYOR_BELT_BLOCK)
					.with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST), BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(MMindustry.MOD_ID, "block/conveyor_0")).put(VariantSettings.Y, VariantSettings.Rotation.R0))
					.with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH), BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(MMindustry.MOD_ID, "block/conveyor_0")).put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST), BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(MMindustry.MOD_ID, "block/conveyor_0")).put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(MMindustry.MOD_ID, "block/conveyor_0")).put(VariantSettings.Y, VariantSettings.Rotation.R270))
			);
//			blockStateModelGenerator.registerSimpleCubeAll(MMindustry.OOD_CONVEYOR_BELT_BLOCK);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(MDTBlocks.WOOD_CONVEYOR_BELT_BLOCK.asItem(), Models.GENERATED);
		}
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModEnglishLangProvider::new);
		pack.addProvider(ModModelGenerator::new);
	}
}

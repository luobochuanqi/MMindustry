package mc.mdt;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

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

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModEnglishLangProvider::new);
	}
}

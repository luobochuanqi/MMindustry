
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.yzy1.free.iitbag.mindustry.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class MindustryModTabs {
	public static CreativeModeTab TAB_MDT_TAB;

	public static void load() {
		TAB_MDT_TAB = new CreativeModeTab("tabmdt_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(MindustryModItems.COPPER.get());
			}

			@Override
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}


package top.yzy1.free.iitbag.mindustry.item;

import top.yzy1.free.iitbag.mindustry.init.MindustryModTabs;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class SiliconItem extends Item {
	public SiliconItem() {
		super(new Item.Properties().tab(MindustryModTabs.TAB_MDT_TAB).stacksTo(64).rarity(Rarity.COMMON));
	}
}

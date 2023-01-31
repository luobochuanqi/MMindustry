
package top.yzy1.free.iitbag.mindustry.item;

import top.yzy1.free.iitbag.mindustry.init.MindustryModTabs;
import top.yzy1.free.iitbag.mindustry.init.MindustryModFluids;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

public class CryofluidItem extends BucketItem {
	public CryofluidItem() {
		super(MindustryModFluids.CRYOFLUID,
				new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.COMMON).tab(MindustryModTabs.TAB_MDT_TAB));
	}
}

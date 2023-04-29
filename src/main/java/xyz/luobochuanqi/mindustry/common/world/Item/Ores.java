package xyz.luobochuanqi.mindustry.common.world.Item;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import xyz.luobochuanqi.mindustry.client.modTab.ModTab;

public class Ores extends Item {
    protected OreType oreType;

    public Ores(OreType type) {
        super(new Item.Properties().tab(ModTab.modItemGroup).stacksTo(1000));
        this.oreType = type;
    }

    public OreType getType() {
        return this.oreType;
    }

    public enum OreType implements IStringSerializable {
        copper("copper"),
        lead("lead"),
        plastanium("plastanium"),
        phase_fabric("phase_fabric"),
        surge_alloy("surge_alloy"),
        spore_pod("spore_pod"),
        blast_compound("blast_compound"),
        pyratite("pyratite"),
        metaglass("metaglass"),
        graphite("graphite"),
        sand("sand"),
        coal("coal"),
        titanium("titanium"),
        thorium("thorium"),
        scrap("scrap"),
        silicon("silicon");


        private final String name;

        OreType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}

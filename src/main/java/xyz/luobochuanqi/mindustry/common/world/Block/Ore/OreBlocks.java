package xyz.luobochuanqi.mindustry.common.world.Block.Ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class OreBlocks extends Block {
    protected OreBlockType oreType;

    public OreBlocks(OreBlockType type) {
        super(Properties.of(Material.STONE).strength(5));
        this.oreType = type;
    }

    public OreBlockType getType() {
        return this.oreType;
    }

    public enum OreBlockType implements IStringSerializable {
        copper("copper_ore"),
        lead("lead_ore"),
        coal("coal_ore"),
        titanium("titanium_ore"),
        thorium("thorium_ore"),
        scrap("scrap_ore");


        private final String name;
        OreBlockType(String name) {
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

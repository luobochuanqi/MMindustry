package xyz.luobochuanqi.mindustry.common.world.Block.Ore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.IStringSerializable;
import net.minecraft.block.BedBlock;

import java.util.List;

public class OresBlock extends Block {
    protected OreBlockType oreType;

    public OresBlock(OreBlockType type) {
        super(Properties.of(Material.STONE));
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

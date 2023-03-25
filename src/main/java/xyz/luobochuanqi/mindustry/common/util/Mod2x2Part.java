package xyz.luobochuanqi.mindustry.common.util;

import net.minecraft.util.IStringSerializable;

public enum Mod2x2Part implements IStringSerializable {
    START("start"),
    FRONT("front"),
    CORNER("corner"),
    RIGHT("right");

    private final String name;

    private Mod2x2Part(String pName) {
        this.name = pName;
    }

    public static Mod2x2Part valueOf(int ordinal) {
        return values()[ordinal];
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}

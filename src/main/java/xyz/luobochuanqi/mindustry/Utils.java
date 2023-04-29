package xyz.luobochuanqi.mindustry;

import net.minecraft.state.EnumProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

public class Utils {
    public static final String ModID = "mindustry";
    //    public static final String ModV = "0.1.1";

    public static final String ITEM = "item."+ModID+".";

    public static final String DESC = "desc."+ModID+".";
    public static final String DESC_INFO = DESC+"info.";
    public static final String DESC_FLAVOUR = DESC+"flavour.";

    public static final Logger LOGGER = LogManager.getLogger();
    public static final EnumProperty<Mod2x2Part> Mod2x2PART = EnumProperty.create("2x2part", Mod2x2Part.class);
}

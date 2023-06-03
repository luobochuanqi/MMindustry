package xyz.luobochuanqi.mindustry;

import com.google.common.collect.ImmutableList;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.luobochuanqi.mindustry.common.util.Mod2x2Part;

import java.util.Collection;
import java.util.Optional;

public class Utils {
    public static final String ModID = "mindustry";
    //    public static final String ModV = "0.1.1";

    public static final String ITEM = "item."+ModID+".";

    public static final String DESC = "desc."+ModID+".";
    public static final String DESC_INFO = DESC+"info.";
    public static final String DESC_FLAVOUR = DESC+"flavour.";

    public static final Logger LOGGER = LogManager.getLogger();

    public static class PropertyBoolInverted extends Property<Boolean> {
        private static final ImmutableList<Boolean> ALLOWED_VALUES = ImmutableList.of(false, true);

        protected PropertyBoolInverted(String name)
        {
            super(name, Boolean.class);
        }

        @Override
        public Collection<Boolean> getPossibleValues()
        {
            return ALLOWED_VALUES;
        }

        @Override
        public Optional<Boolean> getValue(String value)
        {
            return Optional.of(Boolean.parseBoolean(value));
        }

        public static PropertyBoolInverted create(String name)
        {
            return new PropertyBoolInverted(name);
        }

        @Override
        public String getName(Boolean value)
        {
            return value.toString();
        }
    }

    public static final EnumProperty<Mod2x2Part> Mod2x2PART = EnumProperty.create("2x2part", Mod2x2Part.class);
    public static final PropertyBoolInverted MULTIBLOCKSLAVE = PropertyBoolInverted.create("multiblockslave");
}

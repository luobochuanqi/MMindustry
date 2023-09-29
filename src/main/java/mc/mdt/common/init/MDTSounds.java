package mc.mdt.common.init;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class MDTSounds implements AutoRegistryContainer<SoundEvent> {

    public static SoundEvent SHOOT = SoundEvent.of(new Identifier("mmindustry:shoot"));
    public static SoundEvent BOOM = SoundEvent.of(new Identifier("mmindustry:boom"));

    @Override
    public Registry<SoundEvent> getRegistry() {
        return Registries.SOUND_EVENT;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<SoundEvent> getTargetFieldType() {
        return (Class<SoundEvent>) (Object) SoundEvent.class;
    }
}

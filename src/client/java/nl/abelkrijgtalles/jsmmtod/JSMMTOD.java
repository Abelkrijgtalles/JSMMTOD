package nl.abelkrijgtalles.jsmmtod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.Identifier;
import nl.abelkrijgtalles.jsmmtod.config.JSMMTODConfig;
import nl.abelkrijgtalles.jsmmtod.mixin.DebugScreenEntriesAccessor;

public class JSMMTOD implements ClientModInitializer {
    public static final String MOD_ID = "jsmmtod";

    @Override
    public void onInitializeClient() {
        AutoConfig.register(JSMMTODConfig.class, Toml4jConfigSerializer::new);

        DebugScreenEntriesAccessor.invokeRegister(Identifier.fromNamespaceAndPath(MOD_ID, "time_of_day"), new DebugEntryTimeOfDay());
    }
}

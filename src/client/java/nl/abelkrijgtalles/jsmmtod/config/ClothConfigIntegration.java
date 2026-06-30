package nl.abelkrijgtalles.jsmmtod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

public class ClothConfigIntegration {
    public static void register() {
        AutoConfig.register(JSMMTODConfig.class, Toml4jConfigSerializer::new);
    }

    public static JSMMTODConfig getConfig() {
        return AutoConfig.getConfigHolder(JSMMTODConfig.class).getConfig();
    }
}

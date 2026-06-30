package nl.abelkrijgtalles.jsmmtod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import nl.abelkrijgtalles.jsmmtod.config.ClothConfigIntegration;
import nl.abelkrijgtalles.jsmmtod.mixin.DebugScreenEntriesAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSMMTOD implements ClientModInitializer {
    public static final String MOD_ID = "jsmmtod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        if (configAvailable()) {
            ClothConfigIntegration.register();
        } else {
            LOGGER.warn("Cloth config or Mod menu isn't found. This means JSMMTOD will default to showing both ticks and time, with a 24 hour clock.");
        }

        DebugScreenEntriesAccessor.invokeRegister(Identifier.fromNamespaceAndPath(MOD_ID, "time_of_day"), new DebugEntryTimeOfDay());
    }

    public static boolean configAvailable() {
        return FabricLoader.getInstance().isModLoaded("cloth-config") && FabricLoader.getInstance().isModLoaded("modmenu");
    }
}

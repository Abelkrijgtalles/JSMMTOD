package nl.abelkrijgtalles.jsmmtod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import nl.abelkrijgtalles.jsmmtod.config.ClothConfigIntegration;
import nl.abelkrijgtalles.jsmmtod.mixin.DebugScreenEntriesAccessor;

public class JSMMTOD implements ClientModInitializer {
    public static final String MOD_ID = "jsmmtod";

    @Override
    public void onInitializeClient() {
        if (configAvailable()) {
            ClothConfigIntegration.register();
        }

        DebugScreenEntriesAccessor.invokeRegister(Identifier.fromNamespaceAndPath(MOD_ID, "time_of_day"), new DebugEntryTimeOfDay());
    }

    public static boolean configAvailable() {
        return FabricLoader.getInstance().isModLoaded("cloth-config") && FabricLoader.getInstance().isModLoaded("modmenu");
    }
}

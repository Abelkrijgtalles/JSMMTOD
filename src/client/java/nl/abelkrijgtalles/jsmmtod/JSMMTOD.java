package nl.abelkrijgtalles.jsmmtod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.Identifier;
import nl.abelkrijgtalles.jsmmtod.mixin.DebugScreenEntriesAccessor;

public class JSMMTOD implements ClientModInitializer {
    public static final String MOD_ID = "jsmmtod";

    @Override
    public void onInitializeClient() {
        DebugScreenEntriesAccessor.invokeRegister(Identifier.fromNamespaceAndPath(MOD_ID, "time_of_day"), new DebugEntryTimeOfDay());
    }
}

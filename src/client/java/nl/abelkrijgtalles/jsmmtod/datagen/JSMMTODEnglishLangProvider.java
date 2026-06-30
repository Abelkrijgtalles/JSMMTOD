package nl.abelkrijgtalles.jsmmtod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class JSMMTODEnglishLangProvider extends FabricLanguageProvider {
    private final static String AUTO_CONFIG_PREFIX = "text.autoconfig.jsmmtod.";
    protected JSMMTODEnglishLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(AUTO_CONFIG_PREFIX + "title", "Options for JUST SHOW ME MY TIME OF DAY!");
        translationBuilder.add(AUTO_CONFIG_PREFIX + "option.whatToShow", "Show ticks or time?");
        translationBuilder.add(AUTO_CONFIG_PREFIX + "option.twentyFourHourClock", "Use 24 hour clock?");
        translationBuilder.add(AUTO_CONFIG_PREFIX + "option.twentyFourHourClock.@Tooltip", "This won't do anything if you have show ticks selected.");
    }
}

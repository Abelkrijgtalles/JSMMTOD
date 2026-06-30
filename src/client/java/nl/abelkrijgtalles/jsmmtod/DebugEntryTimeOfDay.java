package nl.abelkrijgtalles.jsmmtod;

import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import nl.abelkrijgtalles.jsmmtod.config.ClothConfigIntegration;
import nl.abelkrijgtalles.jsmmtod.config.JSMMTODConfig;
import nl.abelkrijgtalles.jsmmtod.config.ShowOptions;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DebugEntryTimeOfDay implements DebugScreenEntry {
    private final static String TIME_OF_DAY = "Time of day: ";

    @Override
    public void display(@NonNull DebugScreenDisplayer displayer, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        if (serverOrClientLevel != null) {
            long timeOfDay = serverOrClientLevel.getDefaultClockTime() % 24000;
            long minutes = timeOfDay % 1000 * 60 / 1000;
            long hours = (Math.floorDiv(timeOfDay, 1000) + 6) % 24;
            String timeString = "%02d:%02d".formatted(hours, minutes);

            if (JSMMTOD.configAvailable()) {
                JSMMTODConfig config = ClothConfigIntegration.getConfig();
                if (config.getWhatToShow() == ShowOptions.SHOW_TICKS) {
                    displayer.addLine(TIME_OF_DAY + timeOfDay);
                    return;
                }

                if (!config.isTwentyFourHourClock()) {
                    LocalTime time = LocalTime.parse("%02d:%02d".formatted(hours, minutes));
                    timeString = time.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
                }
                if (config.getWhatToShow() == ShowOptions.SHOW_TIME) {
                    displayer.addLine(TIME_OF_DAY + timeString);
                    return;
                }
            }

            displayer.addLine(TIME_OF_DAY + "%d (%s)".formatted(timeOfDay, timeString));
        }
    }
}

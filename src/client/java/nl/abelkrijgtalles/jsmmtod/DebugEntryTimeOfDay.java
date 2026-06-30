package nl.abelkrijgtalles.jsmmtod;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import nl.abelkrijgtalles.jsmmtod.config.JSMMTODConfig;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DebugEntryTimeOfDay implements DebugScreenEntry {
    @Override
    public void display(@NonNull DebugScreenDisplayer displayer, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        if (serverOrClientLevel != null) {
            long timeOfDay = serverOrClientLevel.getDefaultClockTime() % 24000;
            long hours = (Math.floorDiv(timeOfDay, 1000) + 6) % 24;
            long minutes = timeOfDay % 1000 * 60 / 1000;
            displayer.addLine("Time of day: %d (%02d:%02d)".formatted(timeOfDay, hours, minutes));
        }
        Minecraft.getInstance().setScreenAndShow(AutoConfigClient.getConfigScreen(JSMMTODConfig.class, Minecraft.getInstance().gui.screen()).get());
    }
}

package nl.abelkrijgtalles.jsmmtod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name="jsmmtod")
public class JSMMTODConfig implements ConfigData {
    ShowOptions whatToShow = ShowOptions.SHOW_BOTH;
    @ConfigEntry.Gui.Tooltip
    boolean twentyFourHourClock = true;

    public ShowOptions getWhatToShow() {
        return whatToShow;
    }

    public boolean isTwentyFourHourClock() {
        return twentyFourHourClock;
    }
}

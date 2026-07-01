package nl.abelkrijgtalles.jsmmtod.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.util.NullScreenFactory;
import me.shedaniel.autoconfig.AutoConfigClient;
import nl.abelkrijgtalles.jsmmtod.JSMMTOD;

public class JSMMTODModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (JSMMTOD.configAvailable()) {
            return parent -> AutoConfigClient.getConfigScreen(JSMMTODConfig.class, parent).get();
        } else {
            return new NullScreenFactory<>();
        }
    }
}

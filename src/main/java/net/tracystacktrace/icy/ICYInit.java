package net.tracystacktrace.icy;

import com.fox2code.foxloader.config.ConfigIO;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.loader.ModContainer;
import net.tracystacktrace.icy.client.ICYRenderer;

public class ICYInit extends Mod {

    public static ICYRenderer RENDERER;
    public static ICYConfig CONFIG = new ICYConfig();
    private static ModContainer INTERNAL_CONTAINER;

    @Override
    public void onInit() {
        INTERNAL_CONTAINER = this.getModContainer();
        this.setConfigObject(CONFIG);
        RENDERER = new ICYRenderer();
    }

    public static void forceSaveConfig() {
        ConfigIO.writeConfiguration(INTERNAL_CONTAINER, CONFIG);
    }
}

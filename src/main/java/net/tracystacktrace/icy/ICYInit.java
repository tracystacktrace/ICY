package net.tracystacktrace.icy;

import com.fox2code.foxloader.loader.Mod;
import net.tracystacktrace.icy.client.ICYRenderer;

public class ICYInit extends Mod {

    public static ICYRenderer RENDERER;
    public static ICYConfig CONFIG = new ICYConfig();

    @Override
    public void onInit() {
        this.setConfigObject(CONFIG);
        RENDERER = new ICYRenderer();
    }
}

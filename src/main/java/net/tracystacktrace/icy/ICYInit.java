package net.tracystacktrace.icy;

import com.fox2code.foxloader.config.ConfigIO;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.loader.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.tracystacktrace.icy.client.ICYRenderer;
import net.tracystacktrace.icy.client.ICYResolver;
import net.tracystacktrace.icy.resolver.active.BonsaiPlanterResolver;
import net.tracystacktrace.icy.resolver.passive.GrowableResolver;
import net.tracystacktrace.icy.resolver.passive.MagmaResolver;
import org.lwjgl.input.Keyboard;

public class ICYInit extends Mod {

    public static ICYRenderer RENDERER;
    public static ICYConfig CONFIG = new ICYConfig();
    private static ModContainer INTERNAL_CONTAINER;

    @Override
    public void onInit() {
        INTERNAL_CONTAINER = this.getModContainer();
        this.setConfigObject(CONFIG);
        RENDERER = new ICYRenderer();

        /* init passive resolvers */
        ICYResolver.addPassiveResolver(new GrowableResolver());
        ICYResolver.addPassiveResolver(new MagmaResolver());

        /* init active resolvers */
        ICYResolver.addActiveResolver(new BonsaiPlanterResolver());
    }

    public static void forceSaveConfig() {
        ConfigIO.writeConfiguration(INTERNAL_CONTAINER, CONFIG);
    }

    public static int getLargestString(FontRenderer fontRenderer, String[] strings) {
        if (strings == null || strings.length == 0) {
            return 0;
        }

        int result = 0;
        for (String s : strings) {
            result = Math.max(result, fontRenderer.getStringWidth(s));
        }
        return result;
    }

    public static boolean enableActiveCache() {
        return Minecraft.getInstance().currentScreen == null && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
    }
}

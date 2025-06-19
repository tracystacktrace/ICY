package net.tracystacktrace.icy;

import com.fox2code.foxloader.config.ConfigIO;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.loader.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.tracystacktrace.icy.client.ICYRenderer;
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
    }

    public static int getLargestString(FontRenderer fontRenderer, String[] strings) {
        int result = 0;
        for (String s : strings) {
            result = Math.max(result, fontRenderer.getStringWidth(s));
        }
        return result;
    }

    public static void forceSaveConfig() {
        ConfigIO.writeConfiguration(INTERNAL_CONTAINER, CONFIG);
    }

    public static boolean enableActiveCache() {
        return Minecraft.getInstance().currentScreen == null && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
    }
}

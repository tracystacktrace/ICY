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

    public static boolean isValidInputDigit(char eventChar, int eventKey) {
        return Character.isDigit(eventChar) ||
                eventKey == 14 || eventKey == 203 || eventKey == 205; //left, right and backspace
    }

    public static boolean isValidInputHEX(char c, int eventKey) {
        return (c >= '0' && c <= '9') ||
                (c >= 'a' && c <= 'f') ||
                (c >= 'A' && c <= 'F') ||
                eventKey == 14 || eventKey == 203 || eventKey == 205; //left, right and backspace
    }

    public static boolean withinUnsignedByte(short value, char input) {
        if (Character.isDigit(input))
            return Short.parseShort(String.valueOf(value) + input) < 256;
        return true;
    }

    public static String fixColorHex(String damaged) {
        if (damaged.length() == 8) {
            return damaged;
        }

        if (damaged.length() > 8) {
            return damaged.substring(0, 8);
        }

        return damaged + "0".repeat(8 - damaged.length());
    }

    public static short safeStringToShort(final String s) {
        if (s == null || s.isEmpty()) return 0;
        return (short) Math.max(0, Math.min(255, Integer.parseInt(s)));
    }

    public static boolean enableActiveCache() {
        return Minecraft.getInstance().currentScreen == null && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
    }
}

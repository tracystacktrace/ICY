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

        StringBuilder builder = new StringBuilder();
        builder.append(damaged);
        for (int i = 0; i < 8 - damaged.length(); i++) {
            builder.append('0');
        }
        return builder.toString();
    }

    public static short safeStringToShort(final String s) {
        if (s == null || s.isEmpty()) return 0;
        return (short) Math.max(0, Math.min(255, Integer.parseInt(s)));
    }
}

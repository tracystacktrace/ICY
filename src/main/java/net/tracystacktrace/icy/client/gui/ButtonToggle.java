package net.tracystacktrace.icy.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.common.util.i18n.StringTranslate;

public class ButtonToggle extends GuiButton {

    protected final String original;
    protected boolean value = false;

    public ButtonToggle(int _id, int x, int y, int w, int h, String text) {
        super(_id, x, y, w, h, text);
        this.original = text;
    }

    public void toggleValue() {
        this.setValue(!this.value);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean b) {
        this.value = b;
        final String answer = StringTranslate.getInstance().translateKey(this.value ? "icy.config.yes" : "icy.config.no");
        this.displayString = StringTranslate.getInstance().translateKeyFormat(this.original, answer);
    }
}

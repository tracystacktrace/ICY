package net.tracystacktrace.icy.client.gui.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlider;

public class GuiSliderCompact extends GuiSlider {

    protected final IUpdateSliders parentScreen;

    public GuiSliderCompact(int _id, int x, int y, String text, float value, IUpdateSliders parentScreen) {
        super(_id, x, y, text, value);
        this.width = 100;
        this.parentScreen = parentScreen;
    }

    @Override
    protected void mouseDragged(Minecraft minecraft, float x, float y) {
        super.mouseDragged(minecraft, x, y);
        if (this.visible && this.dragging) {
            this.parentScreen.onUpdateFromSliders();
        }
    }

}

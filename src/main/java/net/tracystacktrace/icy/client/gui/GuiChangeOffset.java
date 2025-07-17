package net.tracystacktrace.icy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.common.block.Blocks;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.hellogui.func.FloatNormalization;
import net.tracystacktrace.hellogui.menu.GuiMultipleSliders;
import net.tracystacktrace.icy.ICYInit;
import org.jetbrains.annotations.Nullable;

public class GuiChangeOffset extends GuiMultipleSliders {

    protected final short[] privateTempCache = new short[2];

    public GuiChangeOffset(@Nullable GuiScreen parentScreen) {
        super(
                parentScreen,
                "icy.config.title.offset",
                new String[]{
                        Translation.quickTranslate("icy.config.offset.x"),
                        Translation.quickTranslate("icy.config.offset.y")
                },
                new float[]{
                        ICYInit.CONFIG.offset_x,
                        ICYInit.CONFIG.offset_y
                },
                new FloatNormalization() {
                    @Override
                    public float normalize(float f) {
                        if (f <= 0) return 0.0f;
                        if (f >= 255) return 1.0f;
                        return f / 255.0f;
                    }

                    @Override
                    public float denormalize(float f) {
                        if (f <= 0.0f) return 0;
                        if (f >= 1.0f) return 255;
                        return Math.round(f * 255.0f);
                    }
                },
                v -> {
                    ICYInit.CONFIG.offset_x = (short) v[0];
                    ICYInit.CONFIG.offset_y = (short) v[1];
                    ICYInit.forceSaveConfig();
                }
        );
    }

    @Override
    public void onGuiClosed() {
        ICYInit.RENDERER.getCache().clear();
    }

    @Override
    public void drawScreen(float mouseX, float mouseY, float deltaTicks) {
        super.drawScreen(mouseX, mouseY, deltaTicks);

        // render plaque
        this.privateTempCache[0] = ICYInit.CONFIG.offset_x;
        this.privateTempCache[1] = ICYInit.CONFIG.offset_y;
        ICYInit.CONFIG.offset_x = (short) this.value[0];
        ICYInit.CONFIG.offset_y = (short) this.value[1];
        ICYInit.RENDERER.getCache().buildBlockCache(Minecraft.getInstance().fontRenderer, Blocks.GRASS, 0, 0, 0, 0);
        ICYInit.RENDERER.renderItemPlaque(fontRenderer);
        ICYInit.CONFIG.offset_x = this.privateTempCache[0];
        ICYInit.CONFIG.offset_y = this.privateTempCache[1];
    }
}

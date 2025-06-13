package net.tracystacktrace.icy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.util.i18n.StringTranslate;
import net.tracystacktrace.icy.ICYInit;

public class GuiSelectLocation extends GuiScreen {

    public GuiSelectLocation(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
        ICYInit.RENDERER.buildCache(Minecraft.getInstance().fontRenderer, Blocks.GRASS, 0, 0, 0, 0);
    }

    @Override
    public void onGuiClosed() {
        ICYInit.RENDERER.clearCache();
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if(guiButton.enabled) {
            ICYInit.CONFIG.location = (byte) guiButton.id;
            ICYInit.forceSaveConfig();
            ICYInit.RENDERER.buildCache(Minecraft.getInstance().fontRenderer, Blocks.GRASS, 0, 0, 0, 0);
        }
    }

    @Override
    public void initGui() {

        final int s_width = this.width / 3;
        final int s_hegiht = this.height / 2;

        final StringTranslate translate = StringTranslate.getInstance();
        this.controlList.add(new GuiButton(0, 0, 0, s_width, s_hegiht, translate.translateKey("icy.location.topleft")));
        this.controlList.add(new GuiButton(1, s_width, 0, s_width, this.height / 3, translate.translateKey("icy.location.top")));
        this.controlList.add(new GuiButton(2, s_width * 2, 0, s_width, s_hegiht, translate.translateKey("icy.location.topright")));

        this.controlList.add(new GuiButton(3, 0, s_hegiht, s_width, s_hegiht, translate.translateKey("icy.location.bottomleft")));
        this.controlList.add(new GuiButton(4, s_width, this.height * 2 / 3, s_width, this.height / 3, translate.translateKey("icy.location.bottom")));
        this.controlList.add(new GuiButton(5, s_width * 2, s_hegiht, s_width, s_hegiht, translate.translateKey("icy.location.bottomright")));

        //this.controlList.add(new GuiButton(6, s_width, this.height / 3, s_width, this.height / 3, translate.translateKey("icy.location.center")));

        for(GuiElement element : this.controlList) {
            if(element instanceof GuiButton button) {
                button.renderButtonBg = false;
            }
        }

    }

    @Override
    public void drawScreen(float mouseX, float mouseY, float deltaTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, deltaTicks);
        ICYInit.RENDERER.renderItemPlaque(fontRenderer);
    }
}

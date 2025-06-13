package net.tracystacktrace.icy.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.tracystacktrace.icy.ICYInit;
import net.tracystacktrace.icy.client.gui.ButtonToggle;

public class GuiConfigHome extends GuiScreen {

    //todo complete all config values
    @Override
    public void initGui() {
        this.controlList.clear();

        final int offsetX = this.width / 2 - 100;
        final int offsetY = this.height / 2 - 71;


        this.controlList.add(new ButtonToggle(0, offsetX, offsetY + 22, 200, 20, "icy.config.enable"));
        this.controlList.add(new ButtonToggle(1, offsetX, offsetY + 22 + 25, 200, 20, "icy.config.harvest"));

        ((ButtonToggle)this.controlList.get(0)).setValue(ICYInit.CONFIG.enable);
        ((ButtonToggle)this.controlList.get(1)).setValue(ICYInit.CONFIG.showBlockHarvestability);
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if(guiButton.enabled) {
            if(guiButton.id == 0) {
                ((ButtonToggle)guiButton).toggleValue();
                ICYInit.CONFIG.enable = ((ButtonToggle)guiButton).getValue();
                ICYInit.forceSaveConfig();
                return;
            }

            if(guiButton.id == 1) {
                ((ButtonToggle)guiButton).toggleValue();
                ICYInit.CONFIG.showBlockHarvestability = ((ButtonToggle)guiButton).getValue();
                ICYInit.forceSaveConfig();
                return;
            }
        }
    }

    @Override
    public void drawScreen(float mouseX, float mouseY, float deltaTicks) {
        this.drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, deltaTicks);
    }
}

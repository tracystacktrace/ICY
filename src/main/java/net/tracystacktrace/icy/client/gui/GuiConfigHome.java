package net.tracystacktrace.icy.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.hellogui.element.ButtonToggle;
import net.tracystacktrace.hellogui.menu.GuiChangeARGB;
import net.tracystacktrace.icy.ICYInit;

public class GuiConfigHome extends GuiScreen {

    private final String title = Translation.quickTranslate("icy.config.title");

    //todo complete all config values
    @Override
    public void initGui() {
        this.controlList.clear();

        final int offsetX = this.width / 2 - 100;
        final int offsetY = this.height / 2 - 71;

        this.controlList.add(new ButtonToggle(0, offsetX, offsetY + 22, 200, 20, "icy.config.enable"));
        this.controlList.add(new ButtonToggle(1, offsetX, offsetY + 22 + 25, 200, 20, "icy.config.harvest"));
        this.controlList.add(new ButtonToggle(2, offsetX, offsetY + 22 + 50, 110, 20, "icy.config.gradient"));
        this.controlList.add(new GuiButton(3, offsetX + 115, offsetY + 22 + 50, 40, 20, "§fA§cR§aG§9B"));
        this.controlList.add(new GuiButton(4, offsetX + 160, offsetY + 22 + 50, 40, 20, "§fA§cR§aG§9B"));

        this.controlList.add(new GuiButton(5, offsetX, offsetY + 22 + 75, 200, 20, Translation.quickTranslate("icy.config.location")));
        this.controlList.add(new GuiButton(6, offsetX, offsetY + 22 + 100, 200, 20, Translation.quickTranslate("icy.config.exit")));

        ((ButtonToggle) this.controlList.get(0)).toggleValue(ICYInit.CONFIG.enable);
        ((ButtonToggle) this.controlList.get(1)).toggleValue(ICYInit.CONFIG.showBlockHarvestability);
        ((ButtonToggle) this.controlList.get(2)).toggleValue(ICYInit.CONFIG.gradientColor);

        this.updateColorState();
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 0) {
                ((ButtonToggle) guiButton).toggleValue();
                ICYInit.CONFIG.enable = ((ButtonToggle) guiButton).getValue();
                ICYInit.forceSaveConfig();
                return;
            }

            if (guiButton.id == 5) {
                this.mc.displayGuiScreen(new GuiSelectLocation(this));
                return;
            }

            if (guiButton.id == 3) {
                if (ICYInit.CONFIG.gradientColor) {
                    this.mc.displayGuiScreen(new GuiChangeARGB(
                            this,
                            Translation.quickTranslate("icy.config.title.gradient.start"),
                            ICYInit.CONFIG.startPlaqueGradient,
                            color -> {
                                ICYInit.CONFIG.startPlaqueGradient = color;
                                ICYInit.forceSaveConfig();
                            }
                    ));
                } else {
                    this.mc.displayGuiScreen(new GuiChangeARGB(
                            this,
                            Translation.quickTranslate("icy.config.title.static"),
                            ICYInit.CONFIG.staticPlaqueColor,
                            color -> {
                                ICYInit.CONFIG.staticPlaqueColor = color;
                                ICYInit.forceSaveConfig();
                            }
                    ));
                }
                return;
            }

            if (guiButton.id == 4) {
                this.mc.displayGuiScreen(new GuiChangeARGB(
                        this,
                        Translation.quickTranslate("icy.config.title.gradient.end"),
                        ICYInit.CONFIG.endPlaqueGradient,
                        color -> {
                            ICYInit.CONFIG.endPlaqueGradient = color;
                            ICYInit.forceSaveConfig();
                        }
                ));
                return;
            }

            if (guiButton.id == 1) {
                ((ButtonToggle) guiButton).toggleValue();
                ICYInit.CONFIG.showBlockHarvestability = ((ButtonToggle) guiButton).getValue();
                ICYInit.forceSaveConfig();
                return;
            }

            if (guiButton.id == 2) {
                ((ButtonToggle) guiButton).toggleValue();
                ICYInit.CONFIG.gradientColor = ((ButtonToggle) guiButton).getValue();
                this.updateColorState();
                ICYInit.forceSaveConfig();
                return;
            }

            if (guiButton.id == 6) {
                this.mc.displayGuiScreen(null);
                return;
            }
        }
    }

    @Override
    public void drawScreen(float mouseX, float mouseY, float deltaTicks) {
        this.drawDefaultBackground();

        final int offsetY = this.height / 2 - 71;
        this.drawCenteredString(fontRenderer, title, this.width / 2, offsetY, 0xFFFFFFFF);

        super.drawScreen(mouseX, mouseY, deltaTicks);
    }

    private void updateColorState() {
        ((GuiButton) this.controlList.get(3)).width = ICYInit.CONFIG.gradientColor ? 40 : 85;
        ((GuiButton) this.controlList.get(4)).enabled = ICYInit.CONFIG.gradientColor;
        ((GuiButton) this.controlList.get(4)).visible = ICYInit.CONFIG.gradientColor;
    }
}

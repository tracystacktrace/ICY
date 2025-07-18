package net.tracystacktrace.icy.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.hellogui.element.ButtonToggle;
import net.tracystacktrace.hellogui.menu.GuiChangeARGB;
import net.tracystacktrace.icy.ICYInit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiConfigHome extends GuiScreen {
    private final String title = Translation.quickTranslate("icy.config.title");

    public GuiConfigHome(@Nullable GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        this.controlList.clear();

        final int offsetX = this.width / 2 - 100;
        final int offsetY = this.height / 2 - (142) / 2;

        this.controlList.add(new ButtonToggle(0, offsetX + 65, offsetY + 22, 70, 20, "icy.config.enable"));
        this.controlList.add(new ButtonToggle(1, offsetX, offsetY + 22 + 25, 200, 20, "icy.config.harvest"));
        this.controlList.add(new ButtonToggle(2, offsetX, offsetY + 22 + 50, 110, 20, "icy.config.gradient"));
        this.controlList.add(new GuiButton(3, offsetX + 115, offsetY + 22 + 50, 40, 20, "§fA§cR§aG§9B"));
        this.controlList.add(new GuiButton(4, offsetX + 160, offsetY + 22 + 50, 40, 20, "§fA§cR§aG§9B"));

        this.controlList.add(new GuiButton(5, offsetX, offsetY + 22 + 75, 200, 20, Translation.quickTranslate("icy.config.location")));
        this.controlList.add(new GuiButton(6, offsetX, offsetY + 22 + 100, 200, 20, Translation.quickTranslate("icy.config.exit")));

        this.controlList.add(new GuiButton(7, offsetX + 140, offsetY + 22, 60, 20, "§bX ↔ §r| §dY ↕"));
        this.controlList.add(new ButtonToggle(8, offsetX, offsetY + 22, 60, 20, "icy.config.showid"));

        ((ButtonToggle) this.controlList.get(0)).toggleValue(ICYInit.CONFIG.enable);
        ((ButtonToggle) this.controlList.get(1)).toggleValue(ICYInit.CONFIG.showBlockHarvestability);
        ((ButtonToggle) this.controlList.get(2)).toggleValue(ICYInit.CONFIG.gradientColor);
        ((ButtonToggle) this.controlList.get(8)).toggleValue(ICYInit.CONFIG.showIDandMetadata);

        this.updateColorState();
    }

    @Override
    protected void actionPerformed(@NotNull GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                //toggle icy
                case 0: {
                    ((ButtonToggle) button).toggleValue();
                    ICYInit.CONFIG.enable = ((ButtonToggle) button).getValue();
                    ICYInit.forceSaveConfig();
                    return;
                }
                //toggle harvestability
                case 1: {
                    ((ButtonToggle) button).toggleValue();
                    ICYInit.CONFIG.showBlockHarvestability = ((ButtonToggle) button).getValue();
                    ICYInit.forceSaveConfig();
                    return;
                }
                //toggle color
                case 2: {
                    ((ButtonToggle) button).toggleValue();
                    ICYInit.CONFIG.gradientColor = ((ButtonToggle) button).getValue();
                    this.updateColorState();
                    ICYInit.forceSaveConfig();
                    return;
                }

                //first color/static color
                case 3: {
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
                //second color
                case 4: {
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
                //location
                case 5: {
                    this.mc.displayGuiScreen(new GuiSelectLocation(this));
                    return;
                }
                //exit
                case 6: {
                    this.mc.displayGuiScreen(null);
                    return;
                }
                //x/y offset
                case 7: {
                    this.mc.displayGuiScreen(new GuiChangeOffset(this));
                    return;
                }

                //id/meta
                case 8: {
                    ((ButtonToggle) button).toggleValue();
                    ICYInit.CONFIG.showIDandMetadata = ((ButtonToggle) button).getValue();
                    ICYInit.forceSaveConfig();
                    return;
                }
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

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        ICYInit.RENDERER.getCache().clear();
    }

    private void updateColorState() {
        ((GuiButton) this.controlList.get(3)).width = ICYInit.CONFIG.gradientColor ? 40 : 85;
        ((GuiButton) this.controlList.get(4)).enabled = ICYInit.CONFIG.gradientColor;
        ((GuiButton) this.controlList.get(4)).visible = ICYInit.CONFIG.gradientColor;
    }
}

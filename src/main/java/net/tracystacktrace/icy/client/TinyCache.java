package net.tracystacktrace.icy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.common.block.Block;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.icy.ICYInit;

public class TinyCache {

    protected String[] strings;
    protected ItemStack itemStack;
    protected int x;
    protected int y;
    protected int block;
    protected int meta;
    protected int largestStrWidth;
    protected boolean harvestable;
    protected byte renderLocation = -1;

    /**
     * Clears the cache
     */
    public void clear() {
        this.block = 0;
        this.meta = 0;
        this.strings = null;
        this.itemStack = null;
        this.largestStrWidth = 0;
        this.x = 0;
        this.y = 0;
        this.renderLocation = -1;
        this.harvestable = false;
    }

    public void buildCache(FontRenderer fontRenderer, Block block, int meta, int x, int y, int z) {
        this.block = block.blockID;
        this.meta = meta;
        this.renderLocation = ICYInit.CONFIG.location;

        this.itemStack = ICYResolver.getDisplayItemStack(block, meta);
        this.strings = ICYResolver.bakeLines(this.itemStack, meta, x, y, z);
        this.largestStrWidth = ICYInit.getLargestString(fontRenderer, this.strings);
        this.harvestable = Minecraft.getInstance().currentScreen != null || Minecraft.getInstance().thePlayer.canHarvestBlock(block);

        final ScaledResolution scaledResolution = ScaledResolution.instance;
        this.x = this.getXLocation(scaledResolution.getScaledWidth(), this.getPlaqueWidth());
        this.y = this.getYLocation(scaledResolution.getScaledHeight(), this.getPlaqueHeight());
    }

    private int getXLocation(int screenWidth, int plaqueWidth) {
        return switch (ICYInit.CONFIG.location) {
            case 0, 3 -> 5;
            case 1, 4 -> (screenWidth - plaqueWidth) / 2;
            case 2, 5 -> screenWidth - plaqueWidth - 5;
            default -> 0;
        };
    }

    private int getYLocation(int screenHeight, int plaqueHeight) {
        return switch (ICYInit.CONFIG.location) {
            case 0, 1, 2 -> 5;
            case 3, 5 -> screenHeight - 5 - plaqueHeight;
            case 4 -> screenHeight - plaqueHeight - 45;
            default -> 0;
        };
    }

    public int getPlaqueWidth() {
        return 28 + 5 + this.largestStrWidth;
    }

    public int getPlaqueHeight() {
        return 4 + this.strings.length * 12;
    }
}

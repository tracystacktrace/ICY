package net.tracystacktrace.icy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.common.block.Block;
import net.minecraft.common.entity.Entity;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.icy.ICYInit;

public class TinyCache {

    protected byte code = 0; //1 - for block, 2 - for entity

    protected String[] strings;
    protected ItemStack itemStack;
    protected int x;
    protected int y;
    protected int block;
    protected int meta;
    protected int largestStrWidth;
    protected boolean harvestable;
    protected byte renderLocation = -1;

    protected boolean hasAnyActiveString;
    protected String[] activeCacheStrings;
    protected int largestActiveString;

    protected int entityID = -1;

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
        this.hasAnyActiveString = false;
        this.activeCacheStrings = null;
        this.largestActiveString = 0;
        this.entityID = -1;
        this.code = 0;
    }

    public void buildBlockCache(FontRenderer fontRenderer, Block block, int meta, int x, int y, int z) {
        this.block = block.blockID;
        this.meta = meta;
        this.renderLocation = ICYInit.CONFIG.location;

        this.itemStack = ICYResolver.getDisplayItemStack(block, meta);
        this.strings = ICYResolver.bakeLines(this.itemStack, block, meta, x, y, z);
        this.largestStrWidth = ICYInit.getLargestString(fontRenderer, this.strings);
        this.harvestable = (!ICYInit.isScreenEmpty()) || Minecraft.getInstance().thePlayer.canHarvestBlock(block);

        this.x = this.getRealX();
        this.y = this.getRealY();
        this.code = 1;
    }

    public void buildEntityCache(FontRenderer fontRenderer, Entity entity) {
        this.renderLocation = ICYInit.CONFIG.location;

        String firstLine = entity.getEntityName();
        if(ICYInit.CONFIG.showIDandMetadata) {
            firstLine += " \u00A7r(" + entity.entityId + ")";
        }
        this.itemStack = ICYResolver.getEntitySkullPossible(entity);
        this.strings = new String[] {firstLine, "\u00A79\u00A7oReIndev"};
        this.largestStrWidth = ICYInit.getLargestString(fontRenderer, this.strings);

        this.x = this.getRealX();
        this.y = this.getRealY();
        this.code = 2;
    }

    public void buildActiveBlockCache(FontRenderer fontRenderer, Block block, int meta, int x, int y, int z) {
        this.activeCacheStrings = ICYResolver.bakeActiveLines(this.itemStack, block, meta, x, y, z);
        this.largestActiveString = (this.activeCacheStrings != null) ? ICYInit.getLargestString(fontRenderer, activeCacheStrings) : 0;
        this.hasAnyActiveString = this.activeCacheStrings != null;
    }

    public void dumpActiveCache() {
        this.activeCacheStrings = null;
        this.largestActiveString = 0;
        this.hasAnyActiveString = false;
    }

    private int getXLocation(int screenWidth, int plaqueWidth) {
        return switch (ICYInit.CONFIG.location) {
            case 0, 3 -> ICYInit.CONFIG.offset_x;
            case 1, 4 -> (screenWidth - plaqueWidth) / 2;
            case 2, 5 -> screenWidth - plaqueWidth - ICYInit.CONFIG.offset_x;
            default -> 0;
        };
    }

    private int getYLocation(int screenHeight, int plaqueHeight) {
        return switch (ICYInit.CONFIG.location) {
            case 0, 1, 2 -> ICYInit.CONFIG.offset_y;
            case 3, 5 -> screenHeight - plaqueHeight - ICYInit.CONFIG.offset_y;
            case 4 -> screenHeight - plaqueHeight - 40 - ICYInit.CONFIG.offset_y;
            default -> 0;
        };
    }

    private int getRealX() {
        return this.getXLocation(ScaledResolution.instance.getScaledWidth(), this.getPlaqueWidth());
    }

    private int getRealY() {
        return this.getYLocation(ScaledResolution.instance.getScaledHeight(), this.getPlaqueHeight());
    }

    public int getPlaqueWidth() {
        return 28 + 5 + Math.max(this.largestStrWidth, this.largestActiveString);
    }

    public int getPlaqueHeight() {
        return 4 + (this.strings.length + ((this.activeCacheStrings != null) ? this.activeCacheStrings.length : 0)) * 12;
    }

    public int getX() {
        if (this.hasAnyActiveString) {
            return this.getRealX();
        }
        return this.x;
    }

    public int getY() {
        if (this.hasAnyActiveString) {
            return this.getRealY();
        }
        return this.y;
    }

}

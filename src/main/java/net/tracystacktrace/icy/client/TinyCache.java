package net.tracystacktrace.icy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.common.block.Block;
import net.minecraft.common.entity.Entity;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.GameFeatures;
import net.tracystacktrace.icy.ICYInit;
import org.jetbrains.annotations.NotNull;

public class TinyCache {
    public static final byte SHOWING_NONE = 0;
    public static final byte SHOWING_BLOCK = 1;
    public static final byte SHOWING_ENTITY = 2;

    protected byte typeShowing = SHOWING_NONE; //1 - for block, 2 - for entity

    protected String[] displayStrings;
    protected int largestDisplayString;

    protected ItemStack displayItemStack;
    protected int block;
    protected int meta;
    protected boolean harvestable;

    protected int entityID = -1;

    protected int x;
    protected int y;
    protected byte renderLocation = -1;

    protected boolean hasAnyActiveString;
    protected String[] activeCacheStrings;
    protected int largestActiveString;


    /**
     * Clears the cache
     */
    public void clear() {
        this.block = 0;
        this.meta = 0;
        this.displayStrings = null;
        this.displayItemStack = null;
        this.largestDisplayString = 0;
        this.x = 0;
        this.y = 0;
        this.renderLocation = -1;
        this.harvestable = false;
        this.hasAnyActiveString = false;
        this.activeCacheStrings = null;
        this.largestActiveString = 0;
        this.entityID = -1;
        this.typeShowing = TinyCache.SHOWING_NONE;
    }

    public void buildBlockCache(
            @NotNull FontRenderer fontRenderer,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        this.block = block.blockID;
        this.meta = meta;
        this.renderLocation = ICYInit.CONFIG.location;

        this.displayItemStack = ICYResolver.getDisplayItemStack(block, meta);
        this.displayStrings = ICYResolver.bakeLines(this.displayItemStack, block, meta, x, y, z);
        this.largestDisplayString = ICYInit.getLargestString(fontRenderer, this.displayStrings);
        this.harvestable = (!GameFeatures.isScreenEmpty()) || Minecraft.getInstance().thePlayer.canHarvestBlock(block);

        this.x = this.getRealX();
        this.y = this.getRealY();
        this.typeShowing = TinyCache.SHOWING_BLOCK;
    }

    public void buildEntityCache(
            @NotNull FontRenderer fontRenderer,
            @NotNull Entity entity
    ) {
        this.renderLocation = ICYInit.CONFIG.location;

        String firstLine = ICYResolver.resolveEntityPrefix(entity) + String.join(" ", entity.getEntityName().split("(?<!^)(?=[A-Z])"));
        if (ICYInit.CONFIG.showIDandMetadata) {
            firstLine += " \u00A7r(" + entity.entityId + ")";
        }
        this.displayItemStack = ICYResolver.getEntitySkullPossible(entity);
        this.displayStrings = new String[]{firstLine, "\u00A79\u00A7oReIndev"};
        this.largestDisplayString = ICYInit.getLargestString(fontRenderer, this.displayStrings);

        this.x = this.getRealX();
        this.y = this.getRealY();
        this.typeShowing = TinyCache.SHOWING_ENTITY;
    }

    public void buildActiveBlockCache(
            @NotNull FontRenderer fontRenderer,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        this.activeCacheStrings = ICYResolver.bakeActiveLines(this.displayItemStack, block, meta, x, y, z);
        this.largestActiveString = (this.activeCacheStrings != null) ? ICYInit.getLargestString(fontRenderer, activeCacheStrings) : 0;
        this.hasAnyActiveString = this.activeCacheStrings != null;
    }

    public void clearActive() {
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
        return (this.displayItemStack != null ? 28 : 3) + 5 + Math.max(this.largestDisplayString, this.largestActiveString);
    }

    public int getPlaqueHeight() {
        return 4 + (this.displayStrings.length + ((this.activeCacheStrings != null) ? this.activeCacheStrings.length : 0)) * 12;
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

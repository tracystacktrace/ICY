package net.tracystacktrace.icy.client;

import com.indigo3d.util.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.world.RenderHelper;
import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.util.physics.MovingObjectPosition;
import net.minecraft.common.world.EnumMovingObjectType;
import net.minecraft.common.world.World;
import net.tracystacktrace.icy.ICYInit;
import org.lwjgl.opengl.GL11;

public class ICYRenderer extends Gui {

    protected final RenderItem renderItem = new RenderItem();

    //cache
    protected String[] cacheString;
    protected ItemStack cacheItemStack;
    protected int cacheX;
    protected int cacheY;
    protected int cacheBlock;
    protected int cacheMeta;
    protected int cacheLargestString;
    protected boolean cacheHarvestable;
    protected byte cacheLocation = -1;

    protected void clearCache() {
        this.cacheBlock = 0;
        this.cacheMeta = 0;
        this.cacheString = null;
        this.cacheItemStack = null;
        this.cacheLargestString = 0;
        this.cacheX = 0;
        this.cacheY = 0;
        this.cacheLocation = -1;
        this.cacheHarvestable = false;
    }

    protected void buildCache(FontRenderer fontRenderer, Block block, int meta, int x, int y, int z) {
        this.cacheBlock = block.blockID;
        this.cacheMeta = meta;
        this.cacheLocation = ICYInit.CONFIG.location;

        this.cacheItemStack = ICYResolver.getDisplayItemStack(block, meta);
        this.cacheString = ICYResolver.bakeLines(this.cacheItemStack, meta, x, y, z);
        this.cacheLargestString = this.getLargestString(fontRenderer, this.cacheString);
        this.cacheHarvestable = Minecraft.getInstance().thePlayer.canHarvestBlock(block);

        final ScaledResolution scaledResolution = ScaledResolution.instance;
        this.cacheX = this.getXLocation(scaledResolution.getScaledWidth(), this.getPlaqueWidth());
        this.cacheY = this.getYLocation(scaledResolution.getScaledHeight(), this.getPlaqueHeight());
    }

    public void renderICY(FontRenderer fontRenderer) {
        final MovingObjectPosition omo = Minecraft.getInstance().objectMouseOver;
        if (omo == null) {
            this.clearCache();
            return;
        }


        if (omo.typeOfHit == EnumMovingObjectType.TILE) {
            final Block block = getBlock(omo);

            if (block == null) {
                this.clearCache();
                return;
            }

            final int meta = getMetadata(omo);

            if (block.blockID != this.cacheBlock || meta != this.cacheMeta || this.cacheLocation != ICYInit.CONFIG.location) {
                this.buildCache(fontRenderer, block, meta, omo.blockX, omo.blockY, omo.blockZ);
            }

            this.renderItemPlaque(fontRenderer);
        }

    }

    protected void renderItemPlaque(FontRenderer fontRenderer) {
        final int realY = this.cacheY + ((Minecraft.getInstance().hotbarTickCounter != 0 && this.cacheLocation == 4) ? -25 : 0);

        this.drawGradientRect(
                this.cacheX, realY,
                this.cacheX + this.getPlaqueWidth(),
                realY + this.getPlaqueHeight(),
                ICYInit.CONFIG.startPlaqueGradient,
                ICYInit.CONFIG.endPlaqueGradient
        );

        for(int i = 0; i < this.cacheString.length; i++) {
            fontRenderer.drawStringWithShadow(cacheString[i], this.cacheX + 28, realY + 4 + 12 * i, 0xFFFFFFFF);
        }

        GL11.glPushMatrix();
        RenderHelper.enableStandardItemLighting();
        RenderSystem.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableLighting();
        RenderSystem.enableDepthTest();

        renderItem.drawItemIntoGui(
                fontRenderer,
                Minecraft.getInstance().renderEngine,
                this.cacheItemStack, this.cacheItemStack.getIconIndex(),
                this.cacheX + 5, realY + 5
        );

        RenderSystem.disableDepthTest();

        if(ICYInit.CONFIG.showBlockHarvestability && this.cacheHarvestable) {
            ItemStack debug =
                    new ItemStack(Items.IRON_PICKAXE);
            GL11.glScaled(0.5f, 0.5f, 0.0f);
            GL11.glTranslatef(0.0F, 0.0F, 15.0F);
            renderItem.drawItemIntoGui(
                    fontRenderer,
                    Minecraft.getInstance().renderEngine,
                    debug, debug.getIconIndex(),
                    (this.cacheX + 5) * 2, (realY + 5) * 2
            );
        }

        RenderSystem.enableLighting();
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }

    private Block getBlock(MovingObjectPosition omo) {
        final World world = Minecraft.getInstance().theWorld;
        int id = world.getBlockId(omo.blockX, omo.blockY, omo.blockZ);
        if (id == 0) {
            return null;
        }
        return Blocks.BLOCKS_LIST[id];
    }

    private int getMetadata(MovingObjectPosition omo) {
        final World world = Minecraft.getInstance().theWorld;
        return world.getBlockMetadata(omo.blockX, omo.blockY, omo.blockZ);
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

    private int getPlaqueWidth() {
        return 28 + 5 + this.cacheLargestString;
    }

    private int getPlaqueHeight() {
        return 4 + this.cacheString.length * 12;
    }

    private int getLargestString(FontRenderer fontRenderer, String[] strings) {
        int result = 0;
        for(String s : strings) {
            result = Math.max(result, fontRenderer.getStringWidth(s));
        }
        return result;
    }
}

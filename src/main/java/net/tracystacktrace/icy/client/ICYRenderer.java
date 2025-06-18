package net.tracystacktrace.icy.client;

import com.indigo3d.util.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.world.RenderHelper;
import net.minecraft.common.block.Block;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.util.physics.MovingObjectPosition;
import net.minecraft.common.world.EnumMovingObjectType;
import net.tracystacktrace.icy.ICYInit;
import org.lwjgl.opengl.GL11;

public class ICYRenderer extends Gui {

    protected final RenderItem renderItem = new RenderItem();
    protected final ItemStack harvestIconItem = new ItemStack(Items.IRON_PICKAXE);
    protected final TinyCache cache = new TinyCache();

    public TinyCache getCache() {
        return this.cache;
    }

    public void renderICY(FontRenderer fontRenderer) {
        final MovingObjectPosition omo = Minecraft.getInstance().objectMouseOver;
        if (omo == null) {
            cache.clear();
            return;
        }

        //render tile info
        if (omo.typeOfHit == EnumMovingObjectType.TILE) {
            final Block block = WorldHelper.getBlock(omo);

            if (block == null) {
                cache.clear();
                return;
            }

            final int meta = WorldHelper.getMetadata(omo);

            if (block.blockID != cache.block || meta != cache.meta || cache.renderLocation != ICYInit.CONFIG.location) {
                cache.buildCache(fontRenderer, block, meta, omo.blockX, omo.blockY, omo.blockZ);
            }

            this.renderItemPlaque(fontRenderer);
        }

    }

    public void renderItemPlaque(FontRenderer fontRenderer) {
        final int realY = cache.y + (this.shouldMoveUp() ? -25 : 0);

        this.drawGradientRect(
                cache.x, realY,
                cache.x + cache.getPlaqueWidth(),
                realY + cache.getPlaqueHeight(),
                ICYInit.CONFIG.gradientColor ? ICYInit.CONFIG.startPlaqueGradient : ICYInit.CONFIG.staticPlaqueColor,
                ICYInit.CONFIG.gradientColor ? ICYInit.CONFIG.endPlaqueGradient : ICYInit.CONFIG.staticPlaqueColor
        );

        for (int i = 0; i < cache.strings.length; i++) {
            fontRenderer.drawStringWithShadow(cache.strings[i], cache.x + 28, realY + 4 + 12 * i, 0xFFFFFFFF);
        }

        GL11.glPushMatrix();
        RenderHelper.enableStandardItemLighting();
        RenderSystem.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableLighting();
        RenderSystem.enableDepthTest();

        renderItem.drawItemIntoGui(
                fontRenderer,
                Minecraft.getInstance().renderEngine,
                cache.itemStack, cache.itemStack.getIconIndex(),
                cache.x + 5, realY + 5
        );

        RenderSystem.disableDepthTest();

        if (ICYInit.CONFIG.showBlockHarvestability && cache.harvestable) {
            GL11.glScaled(0.5f, 0.5f, 0.0f);
            GL11.glTranslatef(0.0F, 0.0F, 15.0F);
            renderItem.drawItemIntoGui(
                    fontRenderer,
                    Minecraft.getInstance().renderEngine,
                    this.harvestIconItem, this.harvestIconItem.getIconIndex(),
                    (cache.x + 5) * 2, (realY + 5) * 2
            );
        }

        RenderSystem.enableLighting();
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }

    private boolean shouldMoveUp() {
        if (Minecraft.getInstance().currentScreen != null)
            return false;
        return cache.renderLocation == 4 && Minecraft.getInstance().thePlayer.getHeldItem() != null && Minecraft.getInstance().hotbarTickCounter != 0;
    }
}

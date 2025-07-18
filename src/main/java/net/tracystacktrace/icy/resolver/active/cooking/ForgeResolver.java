package net.tracystacktrace.icy.resolver.active.cooking;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntityBlastFurnace;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.client.WorldHelper;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ForgeResolver implements IResolver {
    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.FORGE_IDLE.blockID || block.blockID == Blocks.FORGE_ACTIVE.blockID;
    }

    @Override
    public String @Nullable [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        final TileEntityBlastFurnace forge = WorldHelper.getTileEntity(x, y, z);

        if (forge == null) {
            return null;
        }

        final String[] collector = new String[4];
        byte cindex = 0;

        if (forge.currentItemBurnTime > 0) {
            final int progress_percent = Math.round((float) forge.getCookProgressScaled(100));
            collector[cindex++] = String.format(Translation.quickTranslate("icy.burn.status"), progress_percent, forge.forgeBurnTime);
        }

        final String fuelInfo = InventoryScanner.nameItemStack(forge.getStackInSlot(1));
        if (fuelInfo != null) {
            collector[cindex++] = Translation.quickTranslate("icy.fuel", fuelInfo);
        }

        final String inputInfo = InventoryScanner.nameItemStack(forge.getStackInSlot(0));
        if (inputInfo != null) {
            collector[cindex++] = Translation.quickTranslate("icy.input", inputInfo);
        }

        final String outputInfo = InventoryScanner.nameItemStack(forge.getStackInSlot(2));
        if (outputInfo != null) {
            collector[cindex++] = Translation.quickTranslate("icy.output", outputInfo);
        }

        return cindex == 0 ? null : Arrays.copyOf(collector, cindex);
    }
}

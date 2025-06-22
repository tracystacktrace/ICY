package net.tracystacktrace.icy.resolver.active.cooking;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntityIncinerator;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.client.WorldHelper;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class IncineratorResolver implements IResolver {
    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.INCINERATOR.blockID;
    }

    @Override
    public String @Nullable [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        final TileEntityIncinerator incinerator = WorldHelper.getTileEntity(x, y, z);

        final String[] collector = new String[4];
        byte cindex = 0;

        if(incinerator.currentBurnTime > 0) {
            final int progress_percent = Math.round((float) (100 * incinerator.cookTime) / incinerator.currentBurnTime);
            collector[cindex++] = String.format(Translation.quickTranslate("icy.incinerator.status"), progress_percent, incinerator.burnTime);
        }

        final String fuelInfo = InventoryScanner.nameItemStack(incinerator.getStackInSlot(18));
        if(fuelInfo != null) {
            collector[cindex++] = Translation.quickTranslate("icy.fuel", fuelInfo);
        }

        final String inputInfo = InventoryScanner.collectItemStack(incinerator, '3', 0, 9);
        if(inputInfo != null) {
            collector[cindex++] = Translation.quickTranslate("icy.input", inputInfo);
        }

        final String outputInfo = InventoryScanner.collectItemStack(incinerator, 'c', 9, 18);
        if(outputInfo != null) {
            collector[cindex++] = Translation.quickTranslate("icy.output", outputInfo);
        }

        return cindex == 0 ? null : Arrays.copyOf(collector, cindex);
    }
}

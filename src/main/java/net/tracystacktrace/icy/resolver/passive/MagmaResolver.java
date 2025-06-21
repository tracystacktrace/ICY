package net.tracystacktrace.icy.resolver.passive;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

public class MagmaResolver implements IResolver {

    @Override
    public boolean passes(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    ) {
        return block.blockID == Blocks.MAGMA.blockID ||
                block.blockID == Blocks.SULPHUROUS_MAGMA.blockID ||
                block.blockID == Blocks.BLOOD_MAGMA.blockID ||
                block.blockID == Blocks.CHROMAGMA.blockID;
    }

    @Override
    public String @NotNull [] bake(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    ) {
        return new String[]{
                Translation.quickTranslate("icy.hint.magma")
        };
    }

}

package net.tracystacktrace.icy.resolver.passive;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

public class YummyBlockResolver implements IResolver {
    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.CAKE.blockID || block.blockID == Blocks.BLUEBERRY_PIE.blockID;
    }

    @Override
    public String @NotNull [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        final int total = block.blockID == Blocks.CAKE.blockID ? 7 : 4;
        return new String[]{
                Translation.quickTranslate("icy.hint.pieces", total - meta)
        };
    }
}

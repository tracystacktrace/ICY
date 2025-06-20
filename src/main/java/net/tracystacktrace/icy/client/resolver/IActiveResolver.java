package net.tracystacktrace.icy.client.resolver;

import net.minecraft.common.block.Block;
import net.minecraft.common.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IActiveResolver {

    boolean passes(
            @NotNull ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    );

    @NotNull String[] bake(
            @NotNull ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    );

}

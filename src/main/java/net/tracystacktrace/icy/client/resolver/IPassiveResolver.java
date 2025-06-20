package net.tracystacktrace.icy.client.resolver;

import net.minecraft.common.block.Block;
import net.minecraft.common.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IPassiveResolver {

    boolean passes(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    );

    String @NotNull [] bake(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    );

}

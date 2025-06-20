package net.tracystacktrace.icy.client.resolver;

import net.minecraft.common.block.Block;
import net.minecraft.common.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IActiveResolver {

    boolean passes(@NotNull ItemStack stack, Block block, int meta, int x, int y, int z);

    @NotNull String[] bake(@NotNull ItemStack stack, Block block, int meta, int x, int y, int z);
}

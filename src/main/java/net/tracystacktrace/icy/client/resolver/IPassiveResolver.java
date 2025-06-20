package net.tracystacktrace.icy.client.resolver;

import net.minecraft.common.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IPassiveResolver {

    boolean passes(@NotNull ItemStack stack, int meta, int x, int y, int z);

    String @NotNull [] bake(@NotNull ItemStack stack, int meta, int x, int y, int z);

}

package net.tracystacktrace.icy.resolver.passive;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.children.BlockCarvedPumpkin;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.mixins.AccessorBlockCarvedPumpkin;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

public class CarvingResolver implements IResolver {
    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return (block instanceof BlockCarvedPumpkin);
    }

    @Override
    public String @NotNull [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        final String carving_style = ((AccessorBlockCarvedPumpkin) block).icy$getCarvingStyle();
        return new String[]{
                Translation.quickTranslate("icy.hint.carving", "carving." + carving_style)
        };
    }
}

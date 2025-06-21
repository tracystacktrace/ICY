package net.tracystacktrace.icy.resolver.passive;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

public class PistonPowerResolver implements IResolver {
    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.PISTON_BASE.blockID ||
                block.blockID == Blocks.PISTON_STICKY_BASE.blockID ||
                block.blockID == Blocks.PISTON_EXTENSION.blockID;
    }

    @Override
    public String @NotNull [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        final boolean powered = (block.blockID == Blocks.PISTON_EXTENSION.blockID) || (meta > 5);
        return new String[]{
                Translation.quickTranslate(powered ? "icy.hint.gear.active" : "icy.hint.gear.inactive")
        };
    }
}

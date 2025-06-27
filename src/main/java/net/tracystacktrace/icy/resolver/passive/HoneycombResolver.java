package net.tracystacktrace.icy.resolver.passive;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HoneycombResolver implements IResolver {
    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.HONEYCOMB_BLOCK.blockID;
    }

    @Override
    public String @Nullable [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        if(meta == 2 || meta == 3) {
            return new String[]{
                    Translation.quickTranslate(meta == 2 ? "icy.hint.honeycomb.filled" : "icy.hint.honeycomb.empty")
            };
        }
        return null;
    }
}

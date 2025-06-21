package net.tracystacktrace.icy.resolver.passive;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

public class DungeonChestResolver implements IResolver {

    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.DUNGEON_CHEST_INACTIVE.blockID ||
                block.blockID == Blocks.DUNGEON_CHEST_ACTIVE.blockID;
    }

    @Override
    public String @NotNull [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return new String[]{
                Translation.quickTranslate((block.blockID == Blocks.DUNGEON_CHEST_ACTIVE.blockID) ? "icy.hint.dungeon_chest.active" : "icy.hint.dungeon_chest.inactive")
        };
    }

}

package net.tracystacktrace.icy.resolver.active;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntityBonsaiPlanter;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.icy.client.WorldHelper;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BonsaiPlanterResolver implements IResolver {

    @Override
    public boolean passes(
            @NotNull ItemStack displayStack,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        return block.blockID == Blocks.BONSAI_PLANTER.blockID;
    }

    @Override
    public String @NotNull [] bake(
            @NotNull ItemStack displayStack,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        final List<String> data = new ArrayList<>();
        final TileEntityBonsaiPlanter planter = WorldHelper.getTileEntity(x, y, z);

        if (!planter.hasTree()) {
            data.add("Empty");
        } else {
            data.add("Tree type: " + planter.getTreeType());
        }

        return data.toArray(new String[0]);
    }

}

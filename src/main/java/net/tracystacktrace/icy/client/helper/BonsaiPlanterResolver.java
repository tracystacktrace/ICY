package net.tracystacktrace.icy.client.helper;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.tileentity.TileEntityBonsaiPlanter;
import net.tracystacktrace.icy.client.WorldHelper;

import java.util.ArrayList;
import java.util.List;

public final class BonsaiPlanterResolver {

    public static List<String> resolve(Block block, int meta, int x, int y, int z) {
        final List<String> data = new ArrayList<>();
        final TileEntityBonsaiPlanter planter = WorldHelper.getTileEntity(x, y, z);

        if(!planter.hasTree()) {
            data.add("Empty");
        } else {
            data.add("Tree type: " + planter.getTreeType());
        }

        return data;
    }
}

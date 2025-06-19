package net.tracystacktrace.icy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntity;
import net.minecraft.common.util.physics.MovingObjectPosition;
import net.minecraft.common.world.World;

public final class WorldHelper {

    public static Block getBlock(MovingObjectPosition omo) {
        final World world = Minecraft.getInstance().theWorld;
        int id = world.getBlockId(omo.blockX, omo.blockY, omo.blockZ);
        if (id == 0) {
            return null;
        }
        return Blocks.BLOCKS_LIST[id];
    }

    public static int getMetadata(MovingObjectPosition omo) {
        final World world = Minecraft.getInstance().theWorld;
        return world.getBlockMetadata(omo.blockX, omo.blockY, omo.blockZ);
    }

    public static <T extends TileEntity> T getTileEntity(int x, int y, int z) {
        return (T) Minecraft.getInstance().theWorld.getBlockTileEntity(x, y, z);
    }
}

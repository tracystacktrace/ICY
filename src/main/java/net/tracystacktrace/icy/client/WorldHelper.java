package net.tracystacktrace.icy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntity;
import net.minecraft.common.util.physics.MovingObjectPosition;
import net.minecraft.common.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WorldHelper {

    public static @Nullable Block getBlock(@NotNull MovingObjectPosition omo) {
        final World world = Minecraft.getInstance().theWorld;
        final int id = world.getBlockId(omo.blockX, omo.blockY, omo.blockZ);
        if (id <= 0 /* including zero */ || id > Blocks.BLOCKS_LIST.length) {
            return null;
        }
        return Blocks.BLOCKS_LIST[id];
    }

    public static int getMetadata(@NotNull MovingObjectPosition omo) {
        final World world = Minecraft.getInstance().theWorld;
        return world.getBlockMetadata(omo.blockX, omo.blockY, omo.blockZ);
    }

    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> @Nullable T getTileEntity(int x, int y, int z) {
        return (T) Minecraft.getInstance().theWorld.getBlockTileEntity(x, y, z);
    }

}

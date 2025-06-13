package net.tracystacktrace.icy.event;

import com.fox2code.foxevents.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.common.world.World;

import java.util.List;

/**
 * The event used to add additional description from other mods or callers.
 * <br>
 * Please use this event to manage descriptions, so unnecessary mixins could be avoided.
 *
 * @since 1.0
 */
public class IcyDescriptorEvent extends Event {
    private final int blockId;
    private final int blockMeta;
    private final int x;
    private final int y;
    private final int z;
    private final List<String> list;

    public IcyDescriptorEvent(List<String> list, int blockId, int blockMeta, int x, int y, int z) {
        this.list = list;
        this.blockId = blockId;
        this.blockMeta = blockMeta;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Return the X coordinate of the block
     *
     * @return the X coordinate of the block
     */
    public int getBlockX() {
        return this.x;
    }

    /**
     * Return the Y coordinate of the block
     *
     * @return the Y coordinate of the block
     */
    public int getBlockY() {
        return this.y;
    }

    /**
     * Return the Z coordinate of the block
     *
     * @return the Z coordinate of the block
     */
    public int getBlockZ() {
        return this.z;
    }

    /**
     * Return the metadata of the block
     *
     * @return the metadata of the block
     */
    public int getBlockMeta() {
        return this.blockMeta;
    }

    /**
     * Return the ID of the block
     *
     * @return the ID of the block
     */
    public int getBlockID() {
        return this.blockId;
    }

    /**
     * As ICY relies on client world, this method will return instance of {@link Minecraft#theWorld}
     *
     * @return the world instance
     */
    public World getWorld() {
        return Minecraft.getInstance().theWorld;
    }

    /**
     * Add description line to the ICY interface
     *
     * @param line string to add
     */
    public void addDescription(String line) {
        if (line != null && !line.isEmpty()) {
            this.list.add(line);
        }
    }
}

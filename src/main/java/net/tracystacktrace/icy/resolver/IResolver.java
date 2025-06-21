package net.tracystacktrace.icy.resolver;

import net.minecraft.common.block.Block;
import net.minecraft.common.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * This is the interface you should implement to your resolver class.
 * <br>
 * Use this one for both or either passive or active resolver implementations.
 * @since 1.0.2
 */
public interface IResolver {

    /**
     * Checks whenever the input block can be processed by this resolver.
     * <br>
     * For example, you have a block X that should be only processed by your resolver and receive some specific description.
     * There, you check if the hovered block has the same blockID as block X you created.
     * <br>
     * Please, return "true" to everything if the resolver is very flexible and usually output generic stuff.
     * @param displayStack The display {@link ItemStack}, basically what's being shown in UI
     * @param block The {@link Block} instance of hovered block
     * @param meta The metadata of hovered block
     * @param x The x coordinate of hovered block
     * @param y The y coordinate of hovered block
     * @param z The z coordinate of hovered block
     * @return True if the hovered block is suitable to be processed by this resolver.
     */
    boolean passes(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    );

    /**
     * Processes a hovered item and "bakes" an array of strings to be shown in the hint UI.
     * <br>
     * If the hovered block successfully goes through {@link IResolver#passes(ItemStack, Block, int, int, int, int)}, the ICY manager passes it to this method to receive baked strings.
     * @param displayStack The display {@link ItemStack}, basically what's being shown in UI
     * @param block The {@link Block} instance of hovered block
     * @param meta The metadata of hovered block
     * @param x The x coordinate of hovered block
     * @param y The y coordinate of hovered block
     * @param z The z coordinate of hovered block
     * @return An array of "baked" strings to show
     */
    String @NotNull [] bake(
            @NotNull final ItemStack displayStack,
            @NotNull final Block block,
            int meta, int x, int y, int z
    );

}

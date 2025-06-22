package net.tracystacktrace.icy.resolver.active.cooking;

import net.minecraft.common.entity.inventory.IInventory;
import net.minecraft.common.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class InventoryScanner {

    public static @Nullable String collectItemStack(
            @NotNull IInventory inventory,
            char prefix, int start, int end
    ) {
        if(start > end) {
            return null;
        }

        final List<String> strings = new ArrayList<>();
        for(int i = start; i < end; i++) {
            final ItemStack stack = inventory.getStackInSlot(i);

            if(stack != null) {
                strings.add("§" + prefix + stack.getDisplayName() + " x" + stack.stackSize);
            }
        }

        if(strings.isEmpty()) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();
        final int size = strings.size();
        for(int i = 0 ; i < size; i++) {
            builder.append(strings.get(i));
            if(i + 1 < size) {
                builder.append(" §7| ");
            }
        }

        return builder.isEmpty() ? null : builder.toString();
    }

    public static @Nullable String nameItemStack(@Nullable ItemStack itemStack) {
        if(itemStack == null) return null;
        return itemStack.getDisplayName() + " x" + itemStack.stackSize;
    }
}

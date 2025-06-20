package net.tracystacktrace.icy.client.resolver.impl;

import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.client.resolver.IPassiveResolver;
import org.jetbrains.annotations.NotNull;

public class GrowableResolver implements IPassiveResolver {

    @Override
    public boolean passes(@NotNull ItemStack stack, int meta, int x, int y, int z) {
        return isGrowable(stack.getItemID(), meta);
    }

    @Override
    public String @NotNull [] bake(@NotNull ItemStack stack, int meta, int x, int y, int z) {
        final int growth = (meta * 100) / getGrowthMax(stack.getItemID());
        return new String[] {
                Translation.quickTranslate("icy.growth", growth)
        };
    }

    private static boolean isGrowable(int id, int meta) {
        return id == Items.SEEDS.itemID ||
                id == Items.CARROT_SEEDS.itemID ||
                id == Items.POTATO.itemID ||
                id == Items.MINT_SEEDS.itemID ||
                (id == Items.CORN_KERNELS.itemID && meta != 9 && meta != 10) ||
                id == Items.PUMPKIN_SEEDS.itemID ||
                id == Items.WATERMELON_SEEDS.itemID;
    }

    private static byte getGrowthMax(int id) {
        if (id == Items.SEEDS.itemID) return 7;
        if (id == Items.CARROT_SEEDS.itemID) return 4;
        if (id == Items.POTATO.itemID) return 4;
        if (id == Items.MINT_SEEDS.itemID) return 3;
        if (id == Items.CORN_KERNELS.itemID) return 15;
        if (id == Items.PUMPKIN_SEEDS.itemID) return 7;
        if (id == Items.WATERMELON_SEEDS.itemID) return 7;
        return 1;
    }
}

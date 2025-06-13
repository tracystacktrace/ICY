package net.tracystacktrace.icy.client;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.util.ChatColors;
import net.minecraft.common.util.i18n.StringTranslate;

import java.util.ArrayList;
import java.util.List;

public class ICYResolver {

    public static String[] bakeLines(ItemStack stack, int blockMeta, int x, int y, int z) {
        List<String> result = new ArrayList<>();

        result.add(StringTranslate.getInstance().translateNamedKey(stack.getItemName()));

        //crops data
        if(isGrowable(stack.getItemID(), blockMeta)) {
            final int growth = (blockMeta * 100) / getGrowthMax(stack.getItemID());
            result.add(StringTranslate.getInstance().translateKeyFormat("icy.growth", growth));
        }

        result.add(getBlockSource(stack));

        return result.toArray(new String[0]);
    }

    private static String getBlockSource(ItemStack stack) {
        return ChatColors.BLUE + ChatColors.ITALIC + stack.getItem().getRegisteringMod().getModName();
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
        if(id == Items.SEEDS.itemID) return 7;
        if(id == Items.CARROT_SEEDS.itemID) return 4;
        if(id == Items.POTATO.itemID) return 4;
        if(id == Items.MINT_SEEDS.itemID) return 3;
        if(id == Items.CORN_KERNELS.itemID) return 15;
        if(id == Items.PUMPKIN_SEEDS.itemID) return 7;
        if(id == Items.WATERMELON_SEEDS.itemID) return 7;
        return 1;
    }

    public static ItemStack getDisplayItemStack(Block block, int meta) {
        final int id = block.blockID;

        if(id == Blocks.SIGN_POST.blockID || id == Blocks.WALL_SIGN.blockID) return new ItemStack(Items.SIGN);
        if(id == Blocks.SUGAR_CANE.blockID) return new ItemStack(Items.SUGAR_CANE);

        if(id == Blocks.LILYPAD.blockID) return new ItemStack(Items.LILYPAD);
        if(id == Blocks.WATERLILY.blockID) return new ItemStack(Items.WATERLILY);
        if(id == Blocks.BLUE_WATERLILY.blockID) return new ItemStack(Items.BLUE_WATERLILY);
        if(id == Blocks.YELLOW_WATERLILY.blockID) return new ItemStack(Items.YELLOW_WATERLILY);

        if(id == Blocks.WHEAT_CROPS.blockID) return new ItemStack(Items.SEEDS);
        if(id == Blocks.CARROT_CROPS.blockID) return new ItemStack(Items.CARROT_SEEDS);
        if(id == Blocks.POTATO_CROPS.blockID) return new ItemStack(Items.POTATO);
        if(id == Blocks.MINT_CROPS.blockID) return new ItemStack(Items.MINT_SEEDS);
        if(id == Blocks.CORN_CROPS.blockID) return new ItemStack(Items.CORN_KERNELS);
        if(id == Blocks.PUMPKIN_STEM.blockID) return new ItemStack(Items.PUMPKIN_SEEDS);
        if(id == Blocks.WATERMELON_STEM.blockID) return new ItemStack(Items.WATERMELON_SEEDS);


        if(id == Blocks.OAK_DOOR.blockID) return new ItemStack(Items.OAK_DOOR);
        if(id == Blocks.FIR_DOOR.blockID) return new ItemStack(Items.FIR_DOOR);
        if(id == Blocks.CHERRY_DOOR.blockID) return new ItemStack(Items.CHERRY_DOOR);
        if(id == Blocks.SPRUCE_DOOR.blockID) return new ItemStack(Items.SPRUCE_DOOR);
        if(id == Blocks.CRIMSON_DOOR.blockID) return new ItemStack(Items.CRIMSON_DOOR);
        if(id == Blocks.AZURE_DOOR.blockID) return new ItemStack(Items.AZURE_DOOR);
        if(id == Blocks.EBONY_DOOR.blockID) return new ItemStack(Items.EBONY_DOOR);
        if(id == Blocks.THISTLEWOOD_DOOR.blockID) return new ItemStack(Items.THISTLEWOOD_DOOR);
        if(id == Blocks.IRON_DOOR.blockID) return new ItemStack(Items.IRON_DOOR);

        if(id == Blocks.OAK_CHAIR.blockID) return new ItemStack(Items.OAK_CHAIR);
        if(id == Blocks.FIR_CHAIR.blockID) return new ItemStack(Items.FIR_CHAIR);
        if(id == Blocks.CHERRY_CHAIR.blockID) return new ItemStack(Items.CHERRY_CHAIR);
        if(id == Blocks.SPRUCE_CHAIR.blockID) return new ItemStack(Items.SPRUCE_CHAIR);
        if(id == Blocks.CRIMSON_CHAIR.blockID) return new ItemStack(Items.CRIMSON_CHAIR);
        if(id == Blocks.AZURE_CHAIR.blockID) return new ItemStack(Items.AZURE_CHAIR);
        if(id == Blocks.EBONY_CHAIR.blockID) return new ItemStack(Items.EBONY_CHAIR);
        if(id == Blocks.THISTLEWOOD_CHAIR.blockID) return new ItemStack(Items.THISTLEWOOD_CHAIR);
        if(id == Blocks.STONE_CHAIR.blockID) return new ItemStack(Items.STONE_CHAIR);
        if(id == Blocks.GLASS_CHAIR.blockID) return new ItemStack(Items.GLASS_CHAIR);

        if(id == Blocks.OAK_TABLE.blockID) return new ItemStack(Items.OAK_TABLE);
        if(id == Blocks.FIR_TABLE.blockID) return new ItemStack(Items.FIR_TABLE);
        if(id == Blocks.CHERRY_TABLE.blockID) return new ItemStack(Items.CHERRY_TABLE);
        if(id == Blocks.SPRUCE_TABLE.blockID) return new ItemStack(Items.SPRUCE_TABLE);
        if(id == Blocks.CRIMSON_TABLE.blockID) return new ItemStack(Items.CRIMSON_TABLE);
        if(id == Blocks.AZURE_TABLE.blockID) return new ItemStack(Items.AZURE_TABLE);
        if(id == Blocks.EBONY_TABLE.blockID) return new ItemStack(Items.EBONY_TABLE);
        if(id == Blocks.THISTLEWOOD_TABLE.blockID) return new ItemStack(Items.THISTLEWOOD_TABLE);
        if(id == Blocks.STONE_TABLE.blockID) return new ItemStack(Items.STONE_TABLE);
        if(id == Blocks.GLASS_TABLE.blockID) return new ItemStack(Items.GLASS_TABLE);

        return new ItemStack(block, 1, meta);
    }

}

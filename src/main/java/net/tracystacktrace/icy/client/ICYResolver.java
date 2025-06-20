package net.tracystacktrace.icy.client;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.children.BlockCarvedPumpkin;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.util.ChatColors;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.client.resolver.IActiveResolver;
import net.tracystacktrace.icy.client.resolver.IPassiveResolver;
import net.tracystacktrace.icy.event.IcyDescriptorEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ICYResolver {

    private static final List<IPassiveResolver> passiveResolvers = new ArrayList<>();
    private static final List<IActiveResolver> activeResolvers = new ArrayList<>();

    public static void addPassiveResolver(IPassiveResolver resolver) {
        passiveResolvers.add(resolver);
    }

    public static void addActiveResolver(IActiveResolver resolver) {
        activeResolvers.add(resolver);
    }

    public static String[] bakeLines(ItemStack stack, int blockMeta, int x, int y, int z) {
        final List<String> result = new ArrayList<>();

        //add name
        result.add(Translation.quickTranslate(stack.getItemName() + ".name"));

        for(IPassiveResolver resolver : passiveResolvers) {
            if(resolver.passes(stack, blockMeta, x, y, z)) {
                final String[] passResult = resolver.bake(stack, blockMeta, x, y, z);
                result.addAll(Arrays.asList(passResult));
            }
        }

        //process other mods' stuff
        new IcyDescriptorEvent(result, stack.getItemID(), blockMeta, x, y, z).callEvent();

        //add mod source
        result.add(getBlockSource(stack));

        return result.toArray(new String[0]);
    }

    public static String[] bakeQuichLines(ItemStack itemStack, Block block, int meta, int x, int y, int z) {
        final List<String> result = new ArrayList<>();

        for(IActiveResolver resolver : activeResolvers) {
            if(resolver.passes(itemStack, block, meta, x, y, z)) {
                result.addAll(Arrays.asList(resolver.bake(itemStack, block, meta, x, y, z)));
            }
        }

        return result.isEmpty() ? null : result.toArray(new String[0]);
    }

    private static String getBlockSource(ItemStack stack) {
        return ChatColors.BLUE + ChatColors.ITALIC + stack.getItem().getRegisteringMod().getModName();
    }

    public static ItemStack getDisplayItemStack(Block block, int meta) {
        final int id = block.blockID;

        if (id == Blocks.SIGN_POST.blockID || id == Blocks.WALL_SIGN.blockID) return new ItemStack(Items.SIGN);
        if (id == Blocks.SUGAR_CANE.blockID) return new ItemStack(Items.SUGAR_CANE);
        if (id == Blocks.BED.blockID) return new ItemStack(Items.BED);
        if (id == Blocks.STICKY_TORCH.blockID) return new ItemStack(Items.STICKY_TORCH);
        if (id == Blocks.PISTON_EXTENSION.blockID && (meta > 7)) return new ItemStack(Blocks.PISTON_STICKY_BASE);
        if (id == Blocks.PISTON_EXTENSION.blockID) return new ItemStack(Blocks.PISTON_BASE);

        if (id == Blocks.LILYPAD.blockID) return new ItemStack(Items.LILYPAD);
        if (id == Blocks.WATERLILY.blockID) return new ItemStack(Items.WATERLILY);
        if (id == Blocks.BLUE_WATERLILY.blockID) return new ItemStack(Items.BLUE_WATERLILY);
        if (id == Blocks.YELLOW_WATERLILY.blockID) return new ItemStack(Items.YELLOW_WATERLILY);

        if (id == Blocks.WHEAT_CROPS.blockID) return new ItemStack(Items.SEEDS);
        if (id == Blocks.CARROT_CROPS.blockID) return new ItemStack(Items.CARROT_SEEDS);
        if (id == Blocks.POTATO_CROPS.blockID) return new ItemStack(Items.POTATO);
        if (id == Blocks.MINT_CROPS.blockID) return new ItemStack(Items.MINT_SEEDS);
        if (id == Blocks.CORN_CROPS.blockID) return new ItemStack(Items.CORN_KERNELS);
        if (id == Blocks.PUMPKIN_STEM.blockID) return new ItemStack(Items.PUMPKIN_SEEDS);
        if (id == Blocks.WATERMELON_STEM.blockID) return new ItemStack(Items.WATERMELON_SEEDS);


        if (id == Blocks.OAK_DOOR.blockID) return new ItemStack(Items.OAK_DOOR);
        if (id == Blocks.FIR_DOOR.blockID) return new ItemStack(Items.FIR_DOOR);
        if (id == Blocks.CHERRY_DOOR.blockID) return new ItemStack(Items.CHERRY_DOOR);
        if (id == Blocks.SPRUCE_DOOR.blockID) return new ItemStack(Items.SPRUCE_DOOR);
        if (id == Blocks.CRIMSON_DOOR.blockID) return new ItemStack(Items.CRIMSON_DOOR);
        if (id == Blocks.AZURE_DOOR.blockID) return new ItemStack(Items.AZURE_DOOR);
        if (id == Blocks.EBONY_DOOR.blockID) return new ItemStack(Items.EBONY_DOOR);
        if (id == Blocks.THISTLEWOOD_DOOR.blockID) return new ItemStack(Items.THISTLEWOOD_DOOR);
        if (id == Blocks.IRON_DOOR.blockID) return new ItemStack(Items.IRON_DOOR);

        if (id == Blocks.OAK_CHAIR.blockID) return new ItemStack(Items.OAK_CHAIR);
        if (id == Blocks.FIR_CHAIR.blockID) return new ItemStack(Items.FIR_CHAIR);
        if (id == Blocks.CHERRY_CHAIR.blockID) return new ItemStack(Items.CHERRY_CHAIR);
        if (id == Blocks.SPRUCE_CHAIR.blockID) return new ItemStack(Items.SPRUCE_CHAIR);
        if (id == Blocks.CRIMSON_CHAIR.blockID) return new ItemStack(Items.CRIMSON_CHAIR);
        if (id == Blocks.AZURE_CHAIR.blockID) return new ItemStack(Items.AZURE_CHAIR);
        if (id == Blocks.EBONY_CHAIR.blockID) return new ItemStack(Items.EBONY_CHAIR);
        if (id == Blocks.THISTLEWOOD_CHAIR.blockID) return new ItemStack(Items.THISTLEWOOD_CHAIR);
        if (id == Blocks.STONE_CHAIR.blockID) return new ItemStack(Items.STONE_CHAIR);
        if (id == Blocks.GLASS_CHAIR.blockID) return new ItemStack(Items.GLASS_CHAIR);

        if (id == Blocks.OAK_TABLE.blockID) return new ItemStack(Items.OAK_TABLE);
        if (id == Blocks.FIR_TABLE.blockID) return new ItemStack(Items.FIR_TABLE);
        if (id == Blocks.CHERRY_TABLE.blockID) return new ItemStack(Items.CHERRY_TABLE);
        if (id == Blocks.SPRUCE_TABLE.blockID) return new ItemStack(Items.SPRUCE_TABLE);
        if (id == Blocks.CRIMSON_TABLE.blockID) return new ItemStack(Items.CRIMSON_TABLE);
        if (id == Blocks.AZURE_TABLE.blockID) return new ItemStack(Items.AZURE_TABLE);
        if (id == Blocks.EBONY_TABLE.blockID) return new ItemStack(Items.EBONY_TABLE);
        if (id == Blocks.THISTLEWOOD_TABLE.blockID) return new ItemStack(Items.THISTLEWOOD_TABLE);
        if (id == Blocks.STONE_TABLE.blockID) return new ItemStack(Items.STONE_TABLE);
        if (id == Blocks.GLASS_TABLE.blockID) return new ItemStack(Items.GLASS_TABLE);

        if (Blocks.BLOCKS_LIST[id] instanceof BlockCarvedPumpkin) meta = 0;

        if (id == Blocks.FURNACE_IDLE.blockID) meta = 0;
        if (id == Blocks.FURNACE_ACTIVE.blockID) meta = 0;
        if (id == Blocks.REFRIDGIFREEZER_IDLE.blockID) meta = 0;
        if (id == Blocks.REFRIDGIFREEZER_ACTIVE.blockID) meta = 0;
        if (id == Blocks.FORGE_IDLE.blockID) meta = 0;
        if (id == Blocks.FORGE_ACTIVE.blockID) meta = 0;
        if (id == Blocks.INCINERATOR.blockID) meta = 0;

        if (id == Blocks.DRAWER.blockID) meta = 0;
        if (id == Blocks.DIMENSIONAL_CHEST.blockID) meta = 0;
        if (id == Blocks.DUNGEON_CHEST_ACTIVE.blockID) meta = 0;
        if (id == Blocks.DUNGEON_CHEST_INACTIVE.blockID) meta = 0;
        if (id == Blocks.ARTIFICIAL_HIVE.blockID) meta = 0;
        if (id == Blocks.TOMBSTONE.blockID) meta = 0;
        if (id == Blocks.CUCURBOO_TOMBSTONE.blockID) meta = 0;

        if (id == Blocks.FOX_PLUSHIE.blockID) return new ItemStack(Items.FOX_PLUSHIE);
        if (id == Blocks.SNOW_FOX_PLUSHIE.blockID) return new ItemStack(Items.SNOW_FOX_PLUSHIE);
        if (id == Blocks.RED_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.RED_WYVERN_PLUSHIE);
        if (id == Blocks.GREEN_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.GREEN_WYVERN_PLUSHIE);
        if (id == Blocks.BLUE_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.BLUE_WYVERN_PLUSHIE);
        if (id == Blocks.BLOOD_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.BLOOD_WYVERN_PLUSHIE);
        if (id == Blocks.DEMON_PLUSHIE.blockID) return new ItemStack(Items.DEMON_PLUSHIE);
        if (id == Blocks.SHARK_PLUSHIE.blockID) return new ItemStack(Items.SHARK_PLUSHIE);

        if (id == Blocks.ZOMBIE_SKULL.blockID) return new ItemStack(Items.ZOMBIE_SKULL);
        if (id == Blocks.SKELETON_SKULL.blockID) return new ItemStack(Items.SKELETON_SKULL);
        if (id == Blocks.CREEPER_SKULL.blockID) return new ItemStack(Items.CREEPER_SKULL);
        if (id == Blocks.SPIDER_SKULL.blockID) return new ItemStack(Items.SPIDER_SKULL);
        if (id == Blocks.WITHER_SKELETON_SKULL.blockID) return new ItemStack(Items.WITHER_SKELETON_SKULL);
        if (id == Blocks.NETHER_ZOMBIE_SKULL.blockID) return new ItemStack(Items.NETHER_ZOMBIE_SKULL);
        if (id == Blocks.DEMON_SKULL.blockID) return new ItemStack(Items.DEMON_SKULL);
        if (id == Blocks.ZOMBIE_PIGMEN_SKULL.blockID) return new ItemStack(Items.ZOMBIE_PIGMEN_SKULL);
        if (id == Blocks.BLAZE_SKULL.blockID) return new ItemStack(Items.BLAZE_SKULL);
        if (id == Blocks.SCARIBOU_SKULL.blockID) return new ItemStack(Items.SCARIBOU_SKULL);

        return new ItemStack(block, 1, meta);
    }
}

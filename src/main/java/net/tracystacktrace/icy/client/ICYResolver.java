package net.tracystacktrace.icy.client;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.children.BlockCarvedPumpkin;
import net.minecraft.common.entity.Entity;
import net.minecraft.common.entity.EntityLiving;
import net.minecraft.common.entity.EntityWaterMob;
import net.minecraft.common.entity.animals.EntityAnimal;
import net.minecraft.common.entity.monsters.*;
import net.minecraft.common.entity.other.EntityHangingItemFrame;
import net.minecraft.common.entity.other.EntityHangingPainting;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.tracystacktrace.hellogui.GameFeatures;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.ICYInit;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class ICYResolver {
    private static final Set<IResolver> passiveResolvers = new HashSet<>();
    private static final Set<IResolver> activeResolvers = new HashSet<>();

    /**
     * Adds the resolver to ICY and marks it as "passive"
     *
     * @param resolver A resolver instance to be registered
     */
    public static void addPassiveResolver(@NotNull IResolver resolver) {
        passiveResolvers.add(resolver);
    }

    /**
     * Adds the resolver to ICY and marks it as "active"
     *
     * @param resolver A resolver instance to be registered
     */
    public static void addActiveResolver(@NotNull IResolver resolver) {
        activeResolvers.add(resolver);
    }

    public static @NotNull String @NotNull [] bakeLines(
            @NotNull ItemStack stack,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        final List<String> collector = new ArrayList<>(3);

        //fetch name
        collector.add(ICYInit.CONFIG.showIDandMetadata ?
                String.format("%s (%d:%d)", stack.getDisplayName(), stack.getItemID(), meta) :
                stack.getDisplayName()
        );

        //add custom lines/info when it's actually in-game
        //otherwise ignore in config gui
        if (GameFeatures.isScreenEmpty()) {
            for (IResolver resolver : passiveResolvers) {
                if (resolver.passes(stack, block, meta, x, y, z)) {
                    final String[] passResult = resolver.bake(stack, block, meta, x, y, z);
                    if (passResult != null) {
                        Collections.addAll(collector, passResult);
                    }
                }
            }
        }

        //add mod source
        collector.add(getBlockSource(stack));

        return collector.toArray(new String[0]);
    }

    public static @NotNull String @Nullable [] bakeActiveLines(
            @NotNull ItemStack itemStack,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        final List<String> collector = new ArrayList<>(4);

        for (IResolver resolver : activeResolvers) {
            if (resolver.passes(itemStack, block, meta, x, y, z)) {
                final String[] passResult = resolver.bake(itemStack, block, meta, x, y, z);
                if (passResult != null) {
                    Collections.addAll(collector, passResult);
                }
            }
        }

        return collector.isEmpty() ? null : collector.toArray(new String[0]);
    }

    @SuppressWarnings("UnnecessaryUnicodeEscape")
    private static @NotNull String getBlockSource(@NotNull ItemStack stack) {
        return "\u00A79\u00A7o" + stack.getItem().getRegisteringMod().getModName();
    }

    public static @NotNull ItemStack getDisplayItemStack(@NotNull Block block, int meta) {
        final int id = block.blockID;

        if (id == Blocks.SIGN_POST.blockID || id == Blocks.WALL_SIGN.blockID) return new ItemStack(Items.SIGN);
        if (id == Blocks.SUGAR_CANE.blockID) return new ItemStack(Items.SUGAR_CANE);
        if (id == Blocks.BED.blockID) return new ItemStack(Items.BED);
        if (id == Blocks.STICKY_TORCH.blockID) return new ItemStack(Items.STICKY_TORCH);
        if (id == Blocks.PISTON_EXTENSION.blockID && (meta > 7)) return new ItemStack(Blocks.PISTON_STICKY_BASE);
        if (id == Blocks.PISTON_EXTENSION.blockID) return new ItemStack(Blocks.PISTON_BASE);
        if (id == Blocks.GUNPOWDER_FUSE.blockID) return new ItemStack(Items.GUNPOWDER);

        if (id == Blocks.PRICKLY_PEAR.blockID) return new ItemStack(Items.PRICKLY_PEAR);
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

        if (id == Blocks.CAKE.blockID) return new ItemStack(Items.CAKE);
        if (id == Blocks.BLUEBERRY_PIE.blockID) return new ItemStack(Items.BLUEBERRY_PIE);


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
        if (id == Blocks.BASALT.blockID) meta = 0;

        if (id == Blocks.HONEYCOMB_BLOCK.blockID && meta != 0) meta = 1;

        if (id == Blocks.FOX_PLUSHIE.blockID) return new ItemStack(Items.FOX_PLUSHIE);
        if (id == Blocks.SNOW_FOX_PLUSHIE.blockID) return new ItemStack(Items.SNOW_FOX_PLUSHIE);
        if (id == Blocks.RED_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.RED_WYVERN_PLUSHIE);
        if (id == Blocks.GREEN_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.GREEN_WYVERN_PLUSHIE);
        if (id == Blocks.BLUE_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.BLUE_WYVERN_PLUSHIE);
        if (id == Blocks.BLOOD_WYVERN_PLUSHIE.blockID) return new ItemStack(Items.BLOOD_WYVERN_PLUSHIE);
        if (id == Blocks.DEMON_PLUSHIE.blockID) return new ItemStack(Items.DEMON_PLUSHIE);
        if (id == Blocks.SHARK_PLUSHIE.blockID) return new ItemStack(Items.SHARK_PLUSHIE);

        if (id == Blocks.ZOMBIE_SKULL.blockID || id == Blocks.ZOMBIE_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.ZOMBIE_SKULL);
        if (id == Blocks.SKELETON_SKULL.blockID || id == Blocks.SKELETON_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.SKELETON_SKULL);
        if (id == Blocks.CREEPER_SKULL.blockID || id == Blocks.CREEPER_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.CREEPER_SKULL);
        if (id == Blocks.SPIDER_SKULL.blockID || id == Blocks.SPIDER_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.SPIDER_SKULL);
        if (id == Blocks.WITHER_SKELETON_SKULL.blockID || id == Blocks.WITHER_SKELETON_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.WITHER_SKELETON_SKULL);
        if (id == Blocks.NETHER_ZOMBIE_SKULL.blockID || id == Blocks.NETHER_ZOMBIE_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.NETHER_ZOMBIE_SKULL);
        if (id == Blocks.DEMON_SKULL.blockID || id == Blocks.DEMON_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.DEMON_SKULL);
        if (id == Blocks.ZOMBIE_PIGMEN_SKULL.blockID || id == Blocks.ZOMBIE_PIGMEN_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.ZOMBIE_PIGMEN_SKULL);
        if (id == Blocks.BLAZE_SKULL.blockID || id == Blocks.BLAZE_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.BLAZE_SKULL);
        if (id == Blocks.SCARIBOU_SKULL.blockID || id == Blocks.SCARIBOU_SKULL_WALL_VARIANT.blockID)
            return new ItemStack(Items.SCARIBOU_SKULL);

        if (id == Blocks.PORTAL.blockID) {
            final ItemStack stack = new ItemStack(Blocks.PORTAL);
            stack.setItemName(Translation.quickTranslate("tile.nether_portal.name"));
            return stack;
        }

        return new ItemStack(block, 1, meta);
    }

    public static @Nullable ItemStack getEntitySkullPossible(@Nullable Entity entity) {
        if (entity instanceof EntityHangingItemFrame) return new ItemStack(Items.ITEM_FRAME);
        if (entity instanceof EntityHangingPainting) return new ItemStack(Items.PAINTING);
        if (entity instanceof EntityNetherZombie) return new ItemStack(Items.NETHER_ZOMBIE_SKULL);
        if (entity instanceof EntityZombie) return new ItemStack(Items.ZOMBIE_SKULL);
        if (entity instanceof EntityWitherSkeleton) return new ItemStack(Items.WITHER_SKELETON_SKULL);
        if (entity instanceof EntitySkeleton) return new ItemStack(Items.SKELETON_SKULL);
        if (entity instanceof EntityCreeper) return new ItemStack(Items.CREEPER_SKULL);
        if (entity instanceof EntitySpider) return new ItemStack(Items.SPIDER_SKULL);
        if (entity instanceof EntityDemon) return new ItemStack(Items.DEMON_SKULL);
        if (entity instanceof EntityZombiePigmen) return new ItemStack(Items.ZOMBIE_PIGMEN_SKULL);
        if (entity instanceof EntityBlaze) return new ItemStack(Items.BLAZE_SKULL);
        if (entity instanceof EntityScaribou) return new ItemStack(Items.SCARIBOU_SKULL);

        if (entity instanceof EntityLiving living) {
            final ItemStack lol = living.getSpawnEgg();
            if (lol != null) {
                return lol.copy();
            }
        }

        return null;
    }

    public static @NotNull String resolveEntityPrefix(@Nullable Entity entity) {
        if (entity != null) {
            if (EntityWaterMob.class.isAssignableFrom(entity.getClass())) return "\u00A7b";
            if (EntityAnimal.class.isAssignableFrom(entity.getClass())) return "\u00A7a";
            if (EntityMonster.class.isAssignableFrom(entity.getClass())) return "\u00A7c";
            if (entity instanceof EntityHangingPainting || entity instanceof EntityHangingItemFrame) return "\u00A7e";
        }
        return "\u00A7d";
    }
}

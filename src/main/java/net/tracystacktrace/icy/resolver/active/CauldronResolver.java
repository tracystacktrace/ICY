package net.tracystacktrace.icy.resolver.active;

import com.mojang.nbt.CompoundTag;
import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntityCauldron;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.recipe.brewing.EPotionType;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.caulviewer.PotionEjector;
import net.tracystacktrace.icy.caulviewer.SimulatedCauldron;
import net.tracystacktrace.icy.client.WorldHelper;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CauldronResolver implements IResolver {

    private final SimulatedCauldron simulatedCauldron = new SimulatedCauldron();

    @Override
    public boolean passes(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        return block.blockID == Blocks.CAULDRON.blockID;
    }

    @Override
    public String @NotNull [] bake(@NotNull ItemStack displayStack, @NotNull Block block, int meta, int x, int y, int z) {
        final TileEntityCauldron cauldron = WorldHelper.getTileEntity(x, y, z);

        final String totalVolumeState = cauldron.fluid == 0 ? Translation.quickTranslate("icy.cauldron.empty") : Translation.quickTranslate("icy.cauldron.fluid", Blocks.BLOCKS_LIST[cauldron.fluid].getBlockName() + ".name");

        if (!TileEntityCauldron.brewingHandlers.containsKey(cauldron.fluid)) {
            return new String[]{
                    totalVolumeState
            };
        }

        simulatedCauldron.copyFrom(cauldron);

        final List<String> parse = new ArrayList<>();
        parse.add(totalVolumeState);

        parse.add(Translation.quickTranslate("icy.cauldron.0.title"));
        parse.add(Translation.quickTranslate("icy.cauldron.potion_type", simulatedCauldron.potionType.name));
        parse.add(Translation.quickTranslate("icy.cauldron.progress", simulatedCauldron.recipeProgress));
        parse.add(Translation.quickTranslate("icy.cauldron.current_potion", simulatedCauldron.potion));
        if (simulatedCauldron.previousItem != 0) {
            parse.add(Translation.quickTranslate("icy.cauldron.prev_item", new ItemStack(simulatedCauldron.previousItem, 1, 0).getItemName() + ".name"));
        }


        //current potion type
        if (simulatedCauldron.recipeProgress > 1) {
            final ItemStack current = getCurrentPotion(simulatedCauldron);
            parse.add(Translation.quickTranslate("icy.cauldron.1.title"));
            parse.add(Translation.quickTranslate("icy.cauldron.1.expected", current.getDisplayName()));
            parse.addAll(Arrays.asList(PotionEjector.getPotionEffects(current)));
        }

        simulatedCauldron.clean();
        return parse.toArray(new String[0]);
    }


    private ItemStack getCurrentPotion(SimulatedCauldron cauldron) {
        final ItemStack currentPotion = new ItemStack(cauldron.potionType.item, 1, cauldron.potion);
        if (cauldron.potionType == EPotionType.ENHANCED) {
            currentPotion.setTagCompound(new CompoundTag());
            currentPotion.compoundTag.setBoolean("Fishy", true);
        }

        if (cauldron.potionType == EPotionType.CURSED) {
            currentPotion.setTagCompound(new CompoundTag());
            currentPotion.compoundTag.setInteger("Flavor", cauldron.potion);
        }

        if (cauldron.potionType == EPotionType.FLASH_FLASK) {
            currentPotion.setTagCompound(new CompoundTag());
            currentPotion.compoundTag.setInteger("flavor", cauldron.potion & 127);
        }

        return currentPotion;
    }
}

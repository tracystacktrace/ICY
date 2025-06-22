package net.tracystacktrace.icy.resolver.active;

import net.minecraft.common.block.Block;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.block.tileentity.TileEntityBonsaiPlanter;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.hellogui.Translation;
import net.tracystacktrace.icy.client.WorldHelper;
import net.tracystacktrace.icy.resolver.IResolver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BonsaiPlanterResolver implements IResolver {

    @Override
    public boolean passes(
            @NotNull ItemStack displayStack,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        return block.blockID == Blocks.BONSAI_PLANTER.blockID;
    }

    @Override
    public String @NotNull [] bake(
            @NotNull ItemStack displayStack,
            @NotNull Block block,
            int meta, int x, int y, int z
    ) {
        final TileEntityBonsaiPlanter planter = WorldHelper.getTileEntity(x, y, z);

        if (!planter.hasTree()) {
            return new String[]{
                    Translation.quickTranslate("icy.bonsai.empty")
            };
        }

        final List<String> compiled = new ArrayList<>();
        compiled.add(Translation.quickTranslate("icy.bonsai.tree", translateTreeType(planter.getTreeType())));

        String ageCompiled = Translation.quickTranslate("icy.bonsai.age", planter.getAge());
        if (planter.getAge() == TileEntityBonsaiPlanter.MAX_AGE) {
            ageCompiled += " §7| " + Translation.quickTranslate("icy.bonsai.adult");
        }
        compiled.add(ageCompiled);

        //fertilized
        if (meta == 1) {
            compiled.add(Translation.quickTranslate("icy.bonsai.fertilized"));
        }

        return compiled.toArray(new String[0]);
    }

    private static String translateTreeType(TileEntityBonsaiPlanter.BonsaiTree bonsaiTree) {
        return Translation.quickTranslate("icy.bonsai.type." + bonsaiTree.name().toLowerCase());
    }
}

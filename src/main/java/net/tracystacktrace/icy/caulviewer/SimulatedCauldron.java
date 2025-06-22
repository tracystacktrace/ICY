package net.tracystacktrace.icy.caulviewer;

import net.minecraft.common.block.tileentity.TileEntityCauldron;
import net.minecraft.common.recipe.brewing.EPotionType;

public class SimulatedCauldron {
    public int potion = 0;
    public boolean hasFluid = false;
    public int recipeProgress = 0;
    public int previousItem = 0;
    public EPotionType potionType;
    public int specificState = 0;
    public int fluid = 0;
    public int internalState = 0;

    public void copyFrom(TileEntityCauldron cauldron) {
        this.potion = cauldron.potion;
        this.hasFluid = cauldron.hasFluid;
        this.recipeProgress = cauldron.recipeProgress;
        this.previousItem = cauldron.previousItem;
        this.potionType = cauldron.potionType;
        this.specificState = cauldron.specificState;
        this.fluid = cauldron.fluid;
        this.internalState = cauldron.internalState;
    }

    public void clean() {
        this.potion = 0;
        this.recipeProgress = 0;
        this.previousItem = 0;
        this.hasFluid = false;
        this.specificState = 0;
        this.potionType = null;
        this.fluid = 0;
        this.internalState = 0;
    }

    public boolean nextMoveExplode() {
        return this.recipeProgress >= 7;
    }
}

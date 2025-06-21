package net.tracystacktrace.icy.mixins;

import net.minecraft.common.block.children.BlockCarvedPumpkin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockCarvedPumpkin.class)
public interface AccessorBlockCarvedPumpkin {
    @Accessor("carving")
    String icy$getCarvingStyle();
}

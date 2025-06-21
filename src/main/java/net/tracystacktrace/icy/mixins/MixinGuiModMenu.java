package net.tracystacktrace.icy.mixins;

import com.fox2code.foxloader.client.gui.GuiModMenu;
import com.fox2code.foxloader.client.gui.GuiModMenuContainer;
import net.minecraft.client.gui.GuiScreen;
import net.tracystacktrace.icy.client.gui.GuiConfigHome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = GuiModMenu.class, remap = false)
public class MixinGuiModMenu {

    @Shadow
    private GuiModMenuContainer modListContainer;

    @ModifyArg(method = "openModConfigScreen", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"), index = 0)
    private GuiScreen icy$openCustomConfigScreen(GuiScreen gui) {
        if (this.modListContainer.getSelectedModContainer().getModId().equals("icy")) {
            return new GuiConfigHome();
        }
        return gui;
    }

}

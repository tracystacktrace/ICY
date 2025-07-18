package net.tracystacktrace.icy.caulviewer;

import net.minecraft.common.effect.Effects;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.item.children.ItemPotion;
import net.tracystacktrace.hellogui.Translation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class PotionEjector {
    record EffectCompact(int id, int ticks, int level) {
        public String format() {
            final int totalSeconds = ticks / 20;
            final String name = Translation.quickTranslate("effect." + Effects.EFFECTS_LIST[id].effectName + ".name");

            return String.format(
                    Translation.quickTranslate("icy.cauldron.1.effect"),
                    this.level != 0 ? String.format("%s %d", name, this.level) : name,
                    String.format("%02d:%02d", totalSeconds / 60, totalSeconds % 60)
            );
        }
    }

    public static @NotNull String @NotNull [] getPotionEffects(@NotNull ItemStack potionStack) {
        final List<EffectCompact> effects = new ArrayList<>();
        final ItemPotion potionItem = (ItemPotion) Items.POTION;

        final int potionState = potionItem.getPotionState(potionStack);
        final boolean isEnhanced = Optional.ofNullable(potionStack.getTagCompound())
                .map(tag -> tag.getBoolean("Fishy"))
                .orElse(false);

        handleCursedPotion(potionStack, effects);
        handleRegularPotions(potionItem, potionState, isEnhanced, effects);

        return effects.stream()
                .map(EffectCompact::format)
                .toArray(String[]::new);
    }

    private static void handleCursedPotion(@NotNull ItemStack potionStack, @NotNull List<@NotNull EffectCompact> effects) {
        if (potionStack.getItemID() == Items.CURSED_POTION.itemID) {
            effects.add(new EffectCompact(Effects.CURSE.effectID, 600, 0));
        }
    }

    private static void handleRegularPotions(@NotNull ItemPotion potionItem, int potionState, boolean isEnhanced, @NotNull List<@NotNull EffectCompact> effects) {
        final int primaryEffect = potionItem.getPrimaryEffectFromDamage(potionState);
        final int secondaryEffect = potionItem.getSecondaryEffectFromDamage(potionState);

        if (secondaryEffect != 129) {
            handleNormalEffects(primaryEffect, secondaryEffect, isEnhanced, effects);
        } else {
            handleSpecialEffects(primaryEffect, effects);
        }
    }

    private static void handleNormalEffects(int primaryEffect, int secondaryEffect, boolean isEnhanced, @NotNull List<@NotNull EffectCompact> effects) {
        if (primaryEffect != secondaryEffect) {
            effects.add(new EffectCompact(secondaryEffect, isEnhanced ? 4800 : 1200, 0));
            effects.add(new EffectCompact(primaryEffect, 1200, 0));
        } else {
            effects.add(new EffectCompact(primaryEffect, isEnhanced ? 4800 : 1200, 1));
        }
    }

    private static void handleSpecialEffects(int primaryEffect, @NotNull List<@NotNull EffectCompact> effects) {
        final int amplifier = (primaryEffect == 10) ? 11 : 127;
        effects.add(new EffectCompact(primaryEffect, 9600, amplifier));
    }
}

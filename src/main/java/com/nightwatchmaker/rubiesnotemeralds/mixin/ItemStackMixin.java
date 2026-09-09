package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Recolour a held item's name only if that complete name is Ruby or ruby.
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getStyledHoverName", at = @At("RETURN"), cancellable = true, remap = false)
    private void rubiesnotemeralds$recolourRubyHoverName(CallbackInfoReturnable<Component> cir) {
        Component original = cir.getReturnValue();
        cir.setReturnValue(original.copy().withStyle(RubiesNotEmeralds.redIfExactRuby(original.getString(), original.getStyle())));
    }
}

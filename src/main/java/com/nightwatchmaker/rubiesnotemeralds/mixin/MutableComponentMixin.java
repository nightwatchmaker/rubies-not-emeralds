package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import net.minecraft.network.chat.MutableComponent;

import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Covers literal server Components that bypass the normal sequence factory.
 * Green changes are scoped to components whose text is being Ruby-renamed.
 */
@Mixin(MutableComponent.class)
public abstract class MutableComponentMixin {
    @Inject(method = "getVisualOrderText", at = @At("HEAD"), cancellable = true, remap = false)
    private void rubiesnotemeralds$replaceComponentDisplay(CallbackInfoReturnable<FormattedCharSequence> cir) {
        MutableComponent self = (MutableComponent) (Object) this;
        String original = self.getString();
        String replaced = RubiesNotEmeralds.replace(original);
        if (!replaced.equals(original)) {
            cir.setReturnValue(FormattedCharSequence.forward(replaced, RubiesNotEmeralds.redIfGreen(self.getStyle())));
        }
    }
}

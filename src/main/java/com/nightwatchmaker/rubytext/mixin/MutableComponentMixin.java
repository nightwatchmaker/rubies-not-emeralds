package com.nightwatchmaker.rubytext.mixin;

import com.nightwatchmaker.rubytext.RubyText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Server chat and other component-only UI paths can bypass the usual string
 * sequence factory. Flatten only matching components at this final display
 * boundary so literal server text such as "Emerald" also becomes "Ruby".
 * The red recolour is deliberately scoped to that same matching component.
 */
@Mixin(MutableComponent.class)
public abstract class MutableComponentMixin {
    @Inject(method = "getVisualOrderText", at = @At("HEAD"), cancellable = true, remap = false)
    private void rubytext$replaceComponentDisplay(CallbackInfoReturnable<FormattedCharSequence> cir) {
        MutableComponent self = (MutableComponent) (Object) this;
        String original = self.getString();
        String replaced = RubyText.replace(original);
        if (!replaced.equals(original)) {
            cir.setReturnValue(FormattedCharSequence.forward(replaced, rubytext$redIfGreen(self.getStyle())));
        }
    }

    private static Style rubytext$redIfGreen(Style style) {
        TextColor color = style.getColor();
        if (color == null) return style;
        if (color.getValue() == TextColor.GREEN.getValue()) return style.withColor(TextColor.RED);
        if (color.getValue() == TextColor.DARK_GREEN.getValue()) return style.withColor(TextColor.DARK_RED);
        return style;
    }
}

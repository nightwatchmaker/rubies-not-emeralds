package com.nightwatchmaker.rubytext.mixin;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Turns Minecraft's named green and dark-green formatting into bright red. */
@Mixin(Style.class)
public abstract class StyleMixin {
    @Inject(method = "withColor(Lnet/minecraft/network/chat/TextColor;)Lnet/minecraft/network/chat/Style;", at = @At("HEAD"), cancellable = true, remap = false)
    private void rubytext$replaceGreenOnCreation(TextColor color, CallbackInfoReturnable<Style> cir) {
        TextColor replacement = rubytext$replacementForGreen(color);
        if (replacement != null) {
            Style self = (Style) (Object) this;
            cir.setReturnValue(self.withColor(replacement));
        }
    }

    /**
     * Server components may deserialize a Style directly and never call
     * withColor. Recolour at read time too, which is the value the renderer
     * receives for both regular and bold text.
     */
    @Inject(method = "getColor", at = @At("RETURN"), cancellable = true, remap = false)
    private void rubytext$replaceGreenAtRender(CallbackInfoReturnable<TextColor> cir) {
        TextColor replacement = rubytext$replacementForGreen(cir.getReturnValue());
        if (replacement != null) {
            cir.setReturnValue(replacement);
        }
    }

    private static TextColor rubytext$replacementForGreen(TextColor color) {
        if (color == null) return null;
        if (color.getValue() == TextColor.GREEN.getValue()) return TextColor.RED;
        if (color.getValue() == TextColor.DARK_GREEN.getValue()) return TextColor.DARK_RED;
        return null;
    }
}

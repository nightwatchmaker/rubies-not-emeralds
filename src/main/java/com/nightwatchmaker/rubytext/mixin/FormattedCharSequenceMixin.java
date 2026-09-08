package com.nightwatchmaker.rubytext.mixin;

import com.nightwatchmaker.rubytext.RubyText;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Rewrites text at the final client rendering-sequence boundary. This covers
 * ordinary UI labels and text received from servers without altering their data.
 */
@Mixin(FormattedCharSequence.class)
public interface FormattedCharSequenceMixin {
    @Inject(method = "forward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubytext$forward(String text, Style style,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubyText.replace(text);
        if (!replaced.equals(text)) {
            cir.setReturnValue(FormattedCharSequence.forward(replaced, style));
        }
    }

    @Inject(method = "forward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;Lit/unimi/dsi/fastutil/ints/Int2IntFunction;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubytext$forwardWithMapper(String text, Style style, Int2IntFunction mapper,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubyText.replace(text);
        if (!replaced.equals(text)) {
            cir.setReturnValue(FormattedCharSequence.forward(replaced, style, mapper));
        }
    }

    @Inject(method = "backward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubytext$backward(String text, Style style,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubyText.replace(text);
        if (!replaced.equals(text)) {
            cir.setReturnValue(FormattedCharSequence.backward(replaced, style));
        }
    }

    @Inject(method = "backward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;Lit/unimi/dsi/fastutil/ints/Int2IntFunction;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubytext$backwardWithMapper(String text, Style style, Int2IntFunction mapper,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubyText.replace(text);
        if (!replaced.equals(text)) {
            cir.setReturnValue(FormattedCharSequence.backward(replaced, style, mapper));
        }
    }
}

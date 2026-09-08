package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Rewrites ordinary client and server-provided text at the render boundary. */
@Mixin(FormattedCharSequence.class)
public interface FormattedCharSequenceMixin {
    @Inject(method = "forward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubiesnotemeralds$forward(String text, Style style,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubiesNotEmeralds.replace(text);
        if (!replaced.equals(text)) cir.setReturnValue(FormattedCharSequence.forward(replaced, style));
    }

    @Inject(method = "forward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;Lit/unimi/dsi/fastutil/ints/Int2IntFunction;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubiesnotemeralds$forwardWithMapper(String text, Style style, Int2IntFunction mapper,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubiesNotEmeralds.replace(text);
        if (!replaced.equals(text)) cir.setReturnValue(FormattedCharSequence.forward(replaced, style, mapper));
    }

    @Inject(method = "backward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubiesnotemeralds$backward(String text, Style style,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubiesNotEmeralds.replace(text);
        if (!replaced.equals(text)) cir.setReturnValue(FormattedCharSequence.backward(replaced, style));
    }

    @Inject(method = "backward(Ljava/lang/String;Lnet/minecraft/network/chat/Style;Lit/unimi/dsi/fastutil/ints/Int2IntFunction;)Lnet/minecraft/util/FormattedCharSequence;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void rubiesnotemeralds$backwardWithMapper(String text, Style style, Int2IntFunction mapper,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        String replaced = RubiesNotEmeralds.replace(text);
        if (!replaced.equals(text)) cir.setReturnValue(FormattedCharSequence.backward(replaced, style, mapper));
    }
}

package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import java.util.Optional;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.PlainTextContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Replaces literal Component text before Minecraft measures and lays it out.
 * This keeps centered server messages centered after Emerald becomes Ruby.
 */
@Mixin(PlainTextContents.LiteralContents.class)
public abstract class PlainTextContentsMixin {
    @Inject(method = "visit(Lnet/minecraft/network/chat/FormattedText$ContentConsumer;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true, remap = false)
    private <T> void rubiesnotemeralds$replaceBeforePlainLayout(
            FormattedText.ContentConsumer<T> consumer, CallbackInfoReturnable<Optional<T>> cir) {
        PlainTextContents.LiteralContents self = (PlainTextContents.LiteralContents) (Object) this;
        String original = self.text();
        String replaced = RubiesNotEmeralds.replace(original);
        if (!replaced.equals(original)) cir.setReturnValue(consumer.accept(replaced));
    }

    @Inject(method = "visit(Lnet/minecraft/network/chat/FormattedText$StyledContentConsumer;Lnet/minecraft/network/chat/Style;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true, remap = false)
    private <T> void rubiesnotemeralds$replaceBeforeStyledLayout(
            FormattedText.StyledContentConsumer<T> consumer, Style style, CallbackInfoReturnable<Optional<T>> cir) {
        PlainTextContents.LiteralContents self = (PlainTextContents.LiteralContents) (Object) this;
        String original = self.text();
        String replaced = RubiesNotEmeralds.replace(original);
        if (!replaced.equals(original)) cir.setReturnValue(consumer.accept(style, replaced));
    }
}

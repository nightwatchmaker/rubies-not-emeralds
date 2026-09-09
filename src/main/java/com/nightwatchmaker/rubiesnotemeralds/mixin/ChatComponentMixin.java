package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.multiplayer.chat.GuiMessageSource;
import net.minecraft.client.multiplayer.chat.GuiMessageTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Keeps all client and server chat messages exactly as their original text. */
@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @Inject(method = "addMessage", at = @At("HEAD"), remap = false)
    private void rubiesnotemeralds$keepChatOriginal(Component message, MessageSignature signature,
            GuiMessageSource source, GuiMessageTag tag, CallbackInfo ci) {
        RubiesNotEmeralds.beginChatBuild();
    }

    @Inject(method = "addMessage", at = @At("RETURN"), remap = false)
    private void rubiesnotemeralds$finishKeepingChatOriginal(Component message, MessageSignature signature,
            GuiMessageSource source, GuiMessageTag tag, CallbackInfo ci) {
        RubiesNotEmeralds.endChatBuild();
    }
}

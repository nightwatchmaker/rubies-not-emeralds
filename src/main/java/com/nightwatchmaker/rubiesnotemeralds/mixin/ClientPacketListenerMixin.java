package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/** Converts Ruby aliases to real Emerald IDs immediately before sending a command. */
@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @ModifyVariable(method = "sendCommand", at = @At("HEAD"), argsOnly = true, remap = false)
    private String rubiesnotemeralds$sendVanillaCommand(String command) {
        return RubiesNotEmeralds.commandToVanilla(command);
    }
}

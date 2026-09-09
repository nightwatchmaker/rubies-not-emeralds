package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

/** Lets /give and tab completion accept Ruby aliases while vanilla receives Emerald IDs. */
@Mixin(CommandSuggestions.class)
public abstract class CommandSuggestionsMixin {
    @ModifyVariable(method = "updateCommandInfo", at = @At(value = "STORE"), ordinal = 0, remap = false)
    private String rubiesnotemeralds$parseRubyAliases(String input) {
        return RubiesNotEmeralds.commandToVanilla(input);
    }

    @Redirect(method = "updateCommandInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/EditBox;getCursorPosition()I"), remap = false)
    private int rubiesnotemeralds$adjustSuggestionCursor(EditBox input) {
        return RubiesNotEmeralds.commandCursorToVanilla(input.getValue(), input.getCursorPosition());
    }
}

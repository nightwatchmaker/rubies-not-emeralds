package com.nightwatchmaker.rubiesnotemeralds.mixin;

import com.nightwatchmaker.rubiesnotemeralds.RubiesNotEmeralds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * The client still knows the vanilla emerald item by its real registry ID.
 * Recolour its displayed name even when a server supplied a green custom name
 * that does not itself contain an Emerald word.
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getStyledHoverName", at = @At("RETURN"), cancellable = true, remap = false)
    private void rubiesnotemeralds$recolourRubyHoverName(CallbackInfoReturnable<Component> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (BuiltInRegistries.ITEM.getKey(self.getItem()).equals(Identifier.withDefaultNamespace("emerald"))) {
            Component original = cir.getReturnValue();
            cir.setReturnValue(original.copy().withStyle(RubiesNotEmeralds.redIfGreen(original.getStyle())));
        }
    }
}

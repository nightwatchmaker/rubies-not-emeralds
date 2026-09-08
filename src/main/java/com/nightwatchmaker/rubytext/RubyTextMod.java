package com.nightwatchmaker.rubytext;

import net.fabricmc.api.ClientModInitializer;

/** Client-only: this never changes server data or packets sent to a server. */
public final class RubyTextMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // The mixin is applied before UI text is laid out; no runtime setup needed.
    }
}

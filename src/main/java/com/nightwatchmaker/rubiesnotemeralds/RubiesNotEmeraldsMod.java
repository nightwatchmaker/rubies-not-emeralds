package com.nightwatchmaker.rubiesnotemeralds;

import net.fabricmc.api.ClientModInitializer;

/** Client-only: this never changes server data or packets sent to a server. */
public final class RubiesNotEmeraldsMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Mixins apply before client UI text is laid out; no runtime setup needed.
    }
}

package com.nightwatchmaker.rubiesnotemeralds;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

/** Shared display and alias policy for the client-side Ruby reskin. */
public final class RubiesNotEmeralds {
    private static final ThreadLocal<Integer> CHAT_BUILD_DEPTH = ThreadLocal.withInitial(() -> 0);

    private RubiesNotEmeralds() {}

    /** Plurals are replaced first so Emeralds becomes Rubies rather than Rubys. */
    public static String replace(String text) {
        if (isBuildingChat()) return text;
        return text
            .replace("Emeralds", "Rubies")
            .replace("emeralds", "rubies")
            .replace("Emerald", "Ruby")
            .replace("emerald", "ruby");
    }

    /** Maps Ruby aliases typed in a command back to vanilla's actual IDs. */
    public static String commandToVanilla(String command) {
        return command
            .replace("Rubies", "Emeralds")
            .replace("rubies", "emeralds")
            .replace("Ruby", "Emerald")
            .replace("ruby", "emerald");
    }

    /** Adjusts a suggestions cursor after Ruby aliases become longer vanilla IDs. */
    public static int commandCursorToVanilla(String command, int cursor) {
        return commandToVanilla(command.substring(0, cursor)).length();
    }

    /** Suppresses cosmetic replacement while Minecraft constructs a chat line. */
    public static void beginChatBuild() {
        CHAT_BUILD_DEPTH.set(CHAT_BUILD_DEPTH.get() + 1);
    }

    /** Balances beginChatBuild after Minecraft has constructed the chat line. */
    public static void endChatBuild() {
        int depth = CHAT_BUILD_DEPTH.get() - 1;
        if (depth <= 0) CHAT_BUILD_DEPTH.remove(); else CHAT_BUILD_DEPTH.set(depth);
    }

    private static boolean isBuildingChat() {
        return CHAT_BUILD_DEPTH.get() > 0;
    }

    /**
     * Recolours only a complete one-word Ruby/ruby text component. Compound
     * names such as "Ruby Block" and every other text stay untouched.
     */
    public static Style redIfExactRuby(String text, Style style) {
        if (!text.equals("Ruby") && !text.equals("ruby")) return style;
        TextColor color = style.getColor();
        if (color == null) return style;
        if (color.getValue() == TextColor.GREEN.getValue()) return style.withColor(TextColor.RED);
        if (color.getValue() == TextColor.DARK_GREEN.getValue()) return style.withColor(TextColor.DARK_RED);
        return style;
    }
}

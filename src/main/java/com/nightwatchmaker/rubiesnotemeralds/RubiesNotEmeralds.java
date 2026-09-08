package com.nightwatchmaker.rubiesnotemeralds;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

/** Shared display policy for the client-side Ruby reskin. */
public final class RubiesNotEmeralds {
    private RubiesNotEmeralds() {}

    /**
     * Plurals are replaced first so Emeralds becomes Rubies rather than Rubys.
     */
    public static String replace(String text) {
        return text
            .replace("Emeralds", "Rubies")
            .replace("emeralds", "rubies")
            .replace("Emerald", "Ruby")
            .replace("emerald", "ruby");
    }

    /** Maps vanilla green formatting to the corresponding ruby red tone. */
    public static Style redIfGreen(Style style) {
        TextColor color = style.getColor();
        if (color == null) return style;
        if (color.getValue() == TextColor.GREEN.getValue()) return style.withColor(TextColor.RED);
        if (color.getValue() == TextColor.DARK_GREEN.getValue()) return style.withColor(TextColor.DARK_RED);
        return style;
    }
}

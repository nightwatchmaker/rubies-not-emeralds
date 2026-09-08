package com.nightwatchmaker.rubiesnotemeralds;

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
}

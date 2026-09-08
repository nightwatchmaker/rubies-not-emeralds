package com.nightwatchmaker.rubytext;

/**
 * Central replacement policy. Plurals must be processed first so Emeralds
 * becomes Rubies rather than the grammatically-wrong Rubys.
 */
public final class RubyText {
    private RubyText() {}

    public static String replace(String text) {
        return text
            .replace("Emeralds", "Rubies")
            .replace("emeralds", "rubies")
            .replace("Emerald", "Ruby")
            .replace("emerald", "ruby");
    }
}

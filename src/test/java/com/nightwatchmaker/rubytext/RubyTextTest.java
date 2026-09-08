package com.nightwatchmaker.rubytext;

public final class RubyTextTest {
    private static void require(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + "; actual: " + actual);
        }
    }

    public static void main(String[] args) {
        require("Ruby", RubyText.replace("Emerald"));
        require("ruby", RubyText.replace("emerald"));
        require("Rubies", RubyText.replace("Emeralds"));
        require("rubies", RubyText.replace("emeralds"));
        require("Ruby ruby Rubies rubies", RubyText.replace("Emerald emerald Emeralds emeralds"));
        require("DIAMOND", RubyText.replace("DIAMOND"));
        System.out.println("Ruby text replacements: PASS");
    }
}

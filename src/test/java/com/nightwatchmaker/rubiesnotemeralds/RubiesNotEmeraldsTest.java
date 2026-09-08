package com.nightwatchmaker.rubiesnotemeralds;

public final class RubiesNotEmeraldsTest {
    private static void require(String expected, String actual) {
        if (!expected.equals(actual)) throw new AssertionError("Expected: " + expected + "; actual: " + actual);
    }

    public static void main(String[] args) {
        require("Ruby", RubiesNotEmeralds.replace("Emerald"));
        require("ruby", RubiesNotEmeralds.replace("emerald"));
        require("Rubies", RubiesNotEmeralds.replace("Emeralds"));
        require("rubies", RubiesNotEmeralds.replace("emeralds"));
        require("Ruby ruby Rubies rubies", RubiesNotEmeralds.replace("Emerald emerald Emeralds emeralds"));
        require("DIAMOND", RubiesNotEmeralds.replace("DIAMOND"));
        System.out.println("Rubies Not Emeralds replacements: PASS");
    }
}

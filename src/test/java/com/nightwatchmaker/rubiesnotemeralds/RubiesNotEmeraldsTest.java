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
        require("give @s emerald_block", RubiesNotEmeralds.commandToVanilla("give @s ruby_block"));
        require("give @s emerald", RubiesNotEmeralds.commandToVanilla("give @s ruby"));
        if (RubiesNotEmeralds.commandCursorToVanilla("give @s ruby_block", "give @s ruby_block".length()) != "give @s emerald_block".length()) {
            throw new AssertionError("Ruby command cursor was not mapped to the vanilla command length");
        }
        System.out.println("Rubies Not Emeralds replacements: PASS");
    }
}

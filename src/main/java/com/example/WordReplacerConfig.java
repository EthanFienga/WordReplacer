package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("wordreplacer")
public interface WordReplacerConfig extends Config
{
    // --- PAIR 1 ---
    @ConfigSection(
            name = "Word Swapper 1",
            description = "First replacement pair",
            position = 0,
            closedByDefault = false
    )
    String pair1 = "pair1";

    @ConfigItem(keyName = "word1", name = "Original word", description = "Original word for pair 1", position = 1, section = pair1)
    default String word1() { return "Shrimp"; }

    @ConfigItem(keyName = "word1IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair1)
    default boolean word1IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word1Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair1)
    default boolean word1Partial() { return true; }

    @ConfigItem(keyName = "word2", name = "New word", description = "Word Swapper for pair 1", position = 4, section = pair1)
    default String word2() { return "Prawn"; }

    @ConfigItem(keyName = "word2MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair1)
    default boolean word2MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word2MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair1)
    default boolean word2MimicCapitalisationPartial() { return true; }

    // --- PAIR 2 ---
    @ConfigSection(name = "Word Swapper 2", description = "Second replacement pair", position = 1, closedByDefault = true)
    String pair2 = "pair2";

    @ConfigItem(keyName = "word3", name = "Original word", description = "Original word for pair 2", position = 1, section = pair2)
    default String word3() { return ""; }

    @ConfigItem(keyName = "word3IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair2)
    default boolean word3IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word3Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair2)
    default boolean word3Partial() { return true; }

    @ConfigItem(keyName = "word4", name = "New word", description = "Word Swapper for pair 2", position = 4, section = pair2)
    default String word4() { return ""; }

    @ConfigItem(keyName = "word4MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair2)
    default boolean word4MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word4MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair2)
    default boolean word4MimicCapitalisationPartial() { return true; }

    // --- PAIR 3 ---
    @ConfigSection(name = "Word Swapper 3", description = "Third replacement pair", position = 2, closedByDefault = true)
    String pair3 = "pair3";

    @ConfigItem(keyName = "word5", name = "Original word", description = "Original word for pair 3", position = 1, section = pair3)
    default String word5() { return ""; }

    @ConfigItem(keyName = "word5IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair3)
    default boolean word5IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word5Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair3)
    default boolean word5Partial() { return true; }

    @ConfigItem(keyName = "word6", name = "New word", description = "Word Swapper for pair 3", position = 4, section = pair3)
    default String word6() { return ""; }

    @ConfigItem(keyName = "word6MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair3)
    default boolean word6MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word6MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair3)
    default boolean word6MimicCapitalisationPartial() { return true; }

    // --- PAIR 4 ---
    @ConfigSection(name = "Word Swapper 4", description = "Fourth replacement pair", position = 3, closedByDefault = true)
    String pair4 = "pair4";

    @ConfigItem(keyName = "word7", name = "Original word", description = "Original word for pair 4", position = 1, section = pair4)
    default String word7() { return ""; }

    @ConfigItem(keyName = "word7IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair4)
    default boolean word7IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word7Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair4)
    default boolean word7Partial() { return true; }

    @ConfigItem(keyName = "word8", name = "New word", description = "Word Swapper for pair 4", position = 4, section = pair4)
    default String word8() { return ""; }

    @ConfigItem(keyName = "word8MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair4)
    default boolean word8MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word8MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair4)
    default boolean word8MimicCapitalisationPartial() { return true; }

    // --- PAIR 5 ---
    @ConfigSection(name = "Word Swapper 5", description = "Fifth replacement pair", position = 4, closedByDefault = true)
    String pair5 = "pair5";

    @ConfigItem(keyName = "word9", name = "Original word", description = "Original word for pair 5", position = 1, section = pair5)
    default String word9() { return ""; }

    @ConfigItem(keyName = "word9IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair5)
    default boolean word9IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word9Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair5)
    default boolean word9Partial() { return true; }

    @ConfigItem(keyName = "word10", name = "New word", description = "Word Swapper for pair 5", position = 4, section = pair5)
    default String word10() { return ""; }

    @ConfigItem(keyName = "word10MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair5)
    default boolean word10MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word10MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair5)
    default boolean word10MimicCapitalisationPartial() { return true; }

    // --- PAIR 6 ---
    @ConfigSection(name = "Word Swapper 6", description = "Sixth replacement pair", position = 5, closedByDefault = true)
    String pair6 = "pair6";

    @ConfigItem(keyName = "word11", name = "Original word", description = "Original word for pair 6", position = 1, section = pair6)
    default String word11() { return ""; }

    @ConfigItem(keyName = "word11IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair6)
    default boolean word11IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word11Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair6)
    default boolean word11Partial() { return true; }

    @ConfigItem(keyName = "word12", name = "New word", description = "Word Swapper for pair 6", position = 4, section = pair6)
    default String word12() { return ""; }

    @ConfigItem(keyName = "word12MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair6)
    default boolean word12MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word12MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair6)
    default boolean word12MimicCapitalisationPartial() { return true; }

    // --- PAIR 7 ---
    @ConfigSection(name = "Word Swapper 7", description = "Seventh replacement pair", position = 6, closedByDefault = true)
    String pair7 = "pair7";

    @ConfigItem(keyName = "word13", name = "Original word", description = "Original word for pair 7", position = 1, section = pair7)
    default String word13() { return ""; }

    @ConfigItem(keyName = "word13IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair7)
    default boolean word13IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word13Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair7)
    default boolean word13Partial() { return true; }

    @ConfigItem(keyName = "word14", name = "New word", description = "Word Swapper for pair 7", position = 4, section = pair7)
    default String word14() { return ""; }

    @ConfigItem(keyName = "word14MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair7)
    default boolean word14MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word14MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair7)
    default boolean word14MimicCapitalisationPartial() { return true; }

    // --- PAIR 8 ---
    @ConfigSection(name = "Word Swapper 8", description = "Eighth replacement pair", position = 7, closedByDefault = true)
    String pair8 = "pair8";

    @ConfigItem(keyName = "word15", name = "Original word", description = "Original word for pair 8", position = 1, section = pair8)
    default String word15() { return ""; }

    @ConfigItem(keyName = "word15IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair8)
    default boolean word15IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word15Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair8)
    default boolean word15Partial() { return true; }

    @ConfigItem(keyName = "word16", name = "New word", description = "Word Swapper for pair 8", position = 4, section = pair8)
    default String word16() { return ""; }

    @ConfigItem(keyName = "word16MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair8)
    default boolean word16MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word16MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair8)
    default boolean word16MimicCapitalisationPartial() { return true; }

    // --- PAIR 9 ---
    @ConfigSection(name = "Word Swapper 9", description = "Ninth replacement pair", position = 8, closedByDefault = true)
    String pair9 = "pair9";

    @ConfigItem(keyName = "word17", name = "Original word", description = "Original word for pair 9", position = 1, section = pair9)
    default String word17() { return ""; }

    @ConfigItem(keyName = "word17IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair9)
    default boolean word17IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word17Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair9)
    default boolean word17Partial() { return true; }

    @ConfigItem(keyName = "word18", name = "New word", description = "Word Swapper for pair 9", position = 4, section = pair9)
    default String word18() { return ""; }

    @ConfigItem(keyName = "word18MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair9)
    default boolean word18MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word18MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair9)
    default boolean word18MimicCapitalisationPartial() { return true; }

    // --- PAIR 10 ---
    @ConfigSection(name = "Word Swapper 10", description = "Tenth replacement pair", position = 9, closedByDefault = true)
    String pair10 = "pair10";

    @ConfigItem(keyName = "word19", name = "Original word", description = "Original word for pair 10", position = 1, section = pair10)
    default String word19() { return ""; }

    @ConfigItem(keyName = "word19IgnoreCapitalisation", name = "Ignore capitalisation", description = "Replace regardless of case", position = 2, section = pair10)
    default boolean word19IgnoreCapitalisation() { return true; }

    @ConfigItem(keyName = "word19Partial", name = "Allow partial words", description = "Replace inside larger words", position = 3, section = pair10)
    default boolean word19Partial() { return true; }

    @ConfigItem(keyName = "word20", name = "New word", description = "Word Swapper for pair 10", position = 4, section = pair10)
    default String word20() { return ""; }

    @ConfigItem(keyName = "word20MimicCapitalisation", name = "Mimic capitalisation (full word)", description = "Copies capitalisation of the original word", position = 5, section = pair10)
    default boolean word20MimicCapitalisation() { return true; }

    @ConfigItem(keyName = "word20MimicCapitalisationPartial", name = "Mimic capitalisation (partial match)", description = "Copies capitalisation for partial matches", position = 6, section = pair10)
    default boolean word20MimicCapitalisationPartial() { return true; }
}

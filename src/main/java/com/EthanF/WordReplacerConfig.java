package com.EthanF;

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

    // PAIR 6–10 are identical in structure; omitted here for brevity but should match your original config
}

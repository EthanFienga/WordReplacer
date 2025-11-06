package com.example;

import com.google.inject.Provides;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Word Replacer",
        description = "Swaps configured words in item names, NPC dialogue, examine text, chat, and more",
        tags = {"replace", "swap", "words"}
)
public class WordReplacerPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private WordReplacerConfig config;

    private static class ReplacementPair
    {
        String original;
        boolean ignoreCapitalisation;
        boolean allowPartial;
        String replacement;
        boolean mimicCapitalisation;
        boolean mimicPartialCapitalisation;

        ReplacementPair(String original, boolean ignoreCapitalisation, boolean allowPartial,
                        String replacement, boolean mimicCapitalisation, boolean mimicPartialCapitalisation)
        {
            this.original = original;
            this.ignoreCapitalisation = ignoreCapitalisation;
            this.allowPartial = allowPartial;
            this.replacement = replacement;
            this.mimicCapitalisation = mimicCapitalisation;
            this.mimicPartialCapitalisation = mimicPartialCapitalisation;
        }
    }

    @Override
    protected void startUp()
    {
        log.info("Word Replacer started!");
    }

    @Override
    protected void shutDown()
    {
        log.info("Word Replacer stopped!");
    }

    @Subscribe
    public void onScriptPostFired(ScriptPostFired event)
    {
        List<ReplacementPair> pairs = getPairsFromConfig();
        if (pairs.isEmpty()) return;

        Widget[] roots = client.getWidgetRoots();
        if (roots != null)
        {
            for (Widget root : roots)
            {
                if (root != null)
                    processWidgetTree(root, pairs);
            }
        }

        handleWidget(WidgetInfo.DIALOG_NPC_TEXT, pairs);
        handleWidget(WidgetInfo.DIALOG_NPC_NAME, pairs);
        handleWidget(WidgetInfo.DIALOG_OPTION, pairs);
        handleWidget(WidgetInfo.DIALOG_PLAYER_TEXT, pairs);
        // Add other widgets here as before

        Widget chatboxMessages = client.getWidget(WidgetInfo.CHATBOX_MESSAGE_LINES);
        if (chatboxMessages != null)
        {
            Widget[] lines = chatboxMessages.getChildren();
            if (lines != null)
            {
                for (Widget line : lines)
                {
                    if (line != null && line.getText() != null && !line.getText().isEmpty())
                        line.setText(applyReplacements(line.getText(), pairs));
                }
            }
        }

        Widget chatboxReportText = client.getWidget(WidgetInfo.CHATBOX_REPORT_TEXT);
        if (chatboxReportText != null)
            chatboxReportText.setText(applyReplacements(chatboxReportText.getText(), pairs));

        client.getPlayers().forEach(player ->
        {
            if (player != null && player.getOverheadText() != null && !player.getOverheadText().isEmpty())
                player.setOverheadText(applyReplacements(player.getOverheadText(), pairs));
        });

        client.getNpcs().forEach(npc ->
        {
            if (npc != null && npc.getOverheadText() != null && !npc.getOverheadText().isEmpty())
                npc.setOverheadText(applyReplacements(npc.getOverheadText(), pairs));
        });
    }

    private void handleWidget(WidgetInfo widgetInfo, List<ReplacementPair> pairs)
    {
        Widget widget = client.getWidget(widgetInfo);
        if (widget != null && widget.getText() != null && !widget.getText().isEmpty())
            widget.setText(applyReplacements(widget.getText(), pairs));
    }

    private void processWidgetTree(Widget widget, List<ReplacementPair> pairs)
    {
        if (widget == null) return;

        String text = widget.getText();
        if (text != null && !text.isEmpty())
        {
            String replaced = applyReplacements(text, pairs);
            if (!replaced.equals(text))
                widget.setText(replaced);
        }

        Widget[] children = widget.getChildren();
        if (children != null)
        {
            for (Widget child : children)
                processWidgetTree(child, pairs);
        }
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event)
    {
        List<ReplacementPair> pairs = getPairsFromConfig();
        if (pairs.isEmpty()) return;

        MenuEntry entry = event.getMenuEntry();

        if (entry.getOption() != null && !entry.getOption().isEmpty())
            entry.setOption(applyReplacements(entry.getOption(), pairs));

        if (entry.getTarget() != null && !entry.getTarget().isEmpty())
            entry.setTarget(applyReplacements(entry.getTarget(), pairs));

        client.setMenuEntries(client.getMenuEntries());
    }

    private String applyReplacements(String text, List<ReplacementPair> pairs)
    {
        String result = text;
        for (ReplacementPair pair : pairs)
            result = swapWords(result, pair);
        return result;
    }

    private String swapWords(String text, ReplacementPair pair)
    {
        if (pair.original.isEmpty()) return text;

        String patternString = pair.ignoreCapitalisation ? "(?i)" + Pattern.quote(pair.original) : Pattern.quote(pair.original);
        if (!pair.allowPartial)
            patternString = "\\b" + patternString + "\\b";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            String matchedText = matcher.group();
            String replacement = pair.mimicCapitalisation ? mimicCapitalisation(matchedText, pair.replacement) : pair.replacement;
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String mimicCapitalisation(String original, String replacement)
    {
        if (original.isEmpty() || replacement.isEmpty())
            return replacement;

        if (original.equals(original.toUpperCase()))
            return replacement.toUpperCase();

        if (Character.isUpperCase(original.charAt(0)))
            return replacement.substring(0, 1).toUpperCase() + replacement.substring(1).toLowerCase();

        return replacement.toLowerCase();
    }

    private List<ReplacementPair> getPairsFromConfig()
    {
        List<ReplacementPair> pairs = new ArrayList<>();

        pairs.add(new ReplacementPair(config.word1(), config.word1IgnoreCapitalisation(), config.word1Partial(),
                config.word2(), config.word2MimicCapitalisation(), config.word2MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word3(), config.word3IgnoreCapitalisation(), config.word3Partial(),
                config.word4(), config.word4MimicCapitalisation(), config.word4MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word5(), config.word5IgnoreCapitalisation(), config.word5Partial(),
                config.word6(), config.word6MimicCapitalisation(), config.word6MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word7(), config.word7IgnoreCapitalisation(), config.word7Partial(),
                config.word8(), config.word8MimicCapitalisation(), config.word8MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word9(), config.word9IgnoreCapitalisation(), config.word9Partial(),
                config.word10(), config.word10MimicCapitalisation(), config.word10MimicCapitalisationPartial()));

        return pairs;
    }

    @Provides
    WordReplacerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(WordReplacerConfig.class);
    }
}

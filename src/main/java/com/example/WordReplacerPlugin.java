package com.example;

import com.google.inject.Provides;
import java.util.ArrayList;
import java.util.List;
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

        // Process all widget roots recursively
        Widget[] roots = client.getWidgetRoots();
        if (roots != null)
        {
            for (Widget root : roots)
            {
                if (root != null)
                    processWidgetTree(root, pairs);
            }
        }

        // Explicitly handle each widget individually
        handleWidget(WidgetInfo.DIALOG_NPC_TEXT, pairs);
        handleWidget(WidgetInfo.DIALOG_NPC_NAME, pairs);
        handleWidget(WidgetInfo.DIALOG_DOUBLE_SPRITE_SPRITE1, pairs);
        handleWidget(WidgetInfo.DIALOG_DOUBLE_SPRITE_SPRITE2, pairs);
        handleWidget(WidgetInfo.DIALOG_DOUBLE_SPRITE_TEXT, pairs);
        handleWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL, pairs);
        handleWidget(WidgetInfo.DIALOG_OPTION, pairs);
        handleWidget(WidgetInfo.DIALOG_OPTION_OPTIONS, pairs);
        handleWidget(WidgetInfo.DIALOG_PLAYER, pairs);
        handleWidget(WidgetInfo.DIALOG_PLAYER_TEXT, pairs);
        handleWidget(WidgetInfo.DIALOG_SPRITE, pairs);
        handleWidget(WidgetInfo.DIALOG_SPRITE_SPRITE, pairs);
        handleWidget(WidgetInfo.DIALOG_SPRITE_TEXT, pairs);
        handleWidget(WidgetInfo.BA_REWARD_TEXT, pairs);
        handleWidget(WidgetInfo.BA_ATK_ROLE_TEXT, pairs);
        handleWidget(WidgetInfo.BA_COLL_ROLE_TEXT, pairs);
        handleWidget(WidgetInfo.BA_DEF_ROLE_TEXT, pairs);
        handleWidget(WidgetInfo.BA_HEAL_ROLE_TEXT, pairs);
        handleWidget(WidgetInfo.CLUE_SCROLL_TEXT, pairs);
        handleWidget(WidgetInfo.COMBAT_SPELL_TEXT, pairs);
        handleWidget(WidgetInfo.COMBAT_DEFENSIVE_SPELL_TEXT, pairs);
        handleWidget(WidgetInfo.GENERIC_SCROLL_TEXT, pairs);
        handleWidget(WidgetInfo.IGNORE_LOADING_TEXT, pairs);
        handleWidget(WidgetInfo.DIARY_QUEST_WIDGET_TEXT, pairs);
        handleWidget(WidgetInfo.ACHIEVEMENT_DIARY_SCROLL_TEXT, pairs);
        handleWidget(WidgetInfo.FRIEND_LIST_LOADING_TEXT, pairs);
        handleWidget(WidgetInfo.GRAND_EXCHANGE_OFFER_TEXT, pairs);
        handleWidget(WidgetInfo.HEALTH_OVERLAY_BAR_TEXT, pairs);
        handleWidget(WidgetInfo.MINIMAP_PRAYER_ORB_TEXT, pairs);
        handleWidget(WidgetInfo.MINIMAP_RUN_ORB_TEXT, pairs);
        handleWidget(WidgetInfo.QUEST_COMPLETED_NAME_TEXT, pairs);
        handleWidget(WidgetInfo.SEED_VAULT_ITEM_TEXT, pairs);
        handleWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_CHATBOX_PARENT, pairs);
        handleWidget(WidgetInfo.RESIZABLE_VIEWPORT_CHATBOX_PARENT, pairs);

        // Chatbox messages: iterate all child lines
        Widget chatboxMessages = client.getWidget(WidgetInfo.CHATBOX_MESSAGE_LINES);
        if (chatboxMessages != null)
        {
            Widget[] lines = chatboxMessages.getChildren();
            if (lines != null)
            {
                for (Widget line : lines)
                {
                    if (line != null && line.getText() != null && !line.getText().isEmpty())
                    {
                        line.setText(applyReplacements(line.getText(), pairs));
                    }
                }
            }
        }

        // Chatbox report text
        Widget chatboxReportText = client.getWidget(WidgetInfo.CHATBOX_REPORT_TEXT);
        if (chatboxReportText != null)
            chatboxReportText.setText(applyReplacements(chatboxReportText.getText(), pairs));

        // --- OVERHEAD TEXT ---
        // Replace player overhead text
        client.getPlayers().forEach(player ->
        {
            if (player != null && player.getOverheadText() != null && !player.getOverheadText().isEmpty())
            {
                player.setOverheadText(applyReplacements(player.getOverheadText(), pairs));
            }
        });

        // Replace NPC overhead text
        client.getNpcs().forEach(npc ->
        {
            if (npc != null && npc.getOverheadText() != null && !npc.getOverheadText().isEmpty())
            {
                npc.setOverheadText(applyReplacements(npc.getOverheadText(), pairs));
            }
        });
    }


    private void handleWidget(WidgetInfo widgetInfo, List<ReplacementPair> pairs)
    {
        Widget widget = client.getWidget(widgetInfo);
        if (widget != null && widget.getText() != null && !widget.getText().isEmpty())
        {
            widget.setText(applyReplacements(widget.getText(), pairs));
        }
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

        String pattern = pair.ignoreCapitalisation ? "(?i)" + pair.original : pair.original;
        if (!pair.allowPartial)
            pattern = "\\b" + pattern + "\\b";

        return text.replaceAll(pattern, pair.replacement);
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
        pairs.add(new ReplacementPair(config.word11(), config.word11IgnoreCapitalisation(), config.word11Partial(),
                config.word12(), config.word12MimicCapitalisation(), config.word12MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word13(), config.word13IgnoreCapitalisation(), config.word13Partial(),
                config.word14(), config.word14MimicCapitalisation(), config.word14MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word15(), config.word15IgnoreCapitalisation(), config.word15Partial(),
                config.word16(), config.word16MimicCapitalisation(), config.word16MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word17(), config.word17IgnoreCapitalisation(), config.word17Partial(),
                config.word18(), config.word18MimicCapitalisation(), config.word18MimicCapitalisationPartial()));
        pairs.add(new ReplacementPair(config.word19(), config.word19IgnoreCapitalisation(), config.word19Partial(),
                config.word20(), config.word20MimicCapitalisation(), config.word20MimicCapitalisationPartial()));

        return pairs;
    }

    @Provides
    WordReplacerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(WordReplacerConfig.class);
    }
}

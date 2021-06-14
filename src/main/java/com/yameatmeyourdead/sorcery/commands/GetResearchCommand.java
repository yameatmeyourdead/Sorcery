package com.yameatmeyourdead.sorcery.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearch;
import com.yameatmeyourdead.sorcery.capabilities.Research;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GetResearchCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> getResearchCommand = Commands.literal("sorcery").then(Commands.literal("getResearch").executes(commandContext -> message(commandContext)));
        dispatcher.register(getResearchCommand);
    }

    private static int message(CommandContext<CommandSource> ctx) {
        Entity source = ctx.getSource().getEntity();
        if(source != null) {
            Research researchInterface = ctx.getSource().getEntity().getCapability(CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH).orElse(null);
            if(researchInterface == null) return 1;
            ctx.getSource().getServer().getPlayerList().broadcastMessage(new TranslationTextComponent("chat.type.announcement", ctx.getSource().getDisplayName(), new StringTextComponent(researchInterface.getResearch())), ChatType.CHAT, source.getUUID());
        }
        return 1;
    }
}

package com.yameatmeyourdead.sorcery.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearch;
import com.yameatmeyourdead.sorcery.capabilities.Research;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;

public class AddResearchCommand {
    // this is absolutely fucking massive holy shit
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> setResearchCommand = Commands.literal("sorcery").requires(commandSource -> commandSource.hasPermission(2))
            .then(Commands.literal("addResearch"))
            .then(Commands.argument("Research Name", StringArgumentType.word())).executes(commandContext -> message(commandContext))
            ;
        dispatcher.register(setResearchCommand);
    }

    private static int message(CommandContext<CommandSource> ctx) {
        Entity source = ctx.getSource().getEntity();
        if(source != null) {
            Research researchInterface = ctx.getSource().getEntity().getCapability(CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH).orElse(null);
            if(researchInterface == null) return 1;
            
            researchInterface.addResearch("");
        }
        return 1;
    }
}

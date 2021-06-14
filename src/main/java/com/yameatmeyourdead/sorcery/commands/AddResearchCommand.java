package com.yameatmeyourdead.sorcery.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearcher;
import com.yameatmeyourdead.sorcery.capabilities.Researcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TranslationTextComponent;

public class AddResearchCommand {
    // this will be absolutely fucking massive holy shit
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> setResearchCommand = Commands.literal("sorcery").requires(commandSource -> commandSource.hasPermission(2))
            .then(Commands.literal("addResearch")
                .then(Commands.argument("Research_Name", StringArgumentType.string())
                    .executes(commandContext -> message(commandContext))))
            ;
        dispatcher.register(setResearchCommand);
    }

    private static int message(CommandContext<CommandSource> ctx) {
        Entity source = ctx.getSource().getEntity();
        if(source != null) {
            Researcher researchInterface = source.getCapability(CapabilityPlayerResearcher.CAPABILITY_PLAYER_RESEARCHER).orElse(null);
            if(researchInterface == null) return 1;
            
            // TODO: FIX
            // if(!researchInterface.addResearch(StringArgumentType.getString(ctx, "Research_Name").toLowerCase())) {
            //     source.sendMessage(new TranslationTextComponent("You already have this knowledge."), source.getUUID());
            // }
        }
        return 1;
    }
}

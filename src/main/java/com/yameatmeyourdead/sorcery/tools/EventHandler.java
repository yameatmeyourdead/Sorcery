package com.yameatmeyourdead.sorcery.tools;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearch;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityProviderResearch;
import com.yameatmeyourdead.sorcery.capabilities.Research;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    // Attach Capabilities to relevant objects
    @SubscribeEvent
    public static void attachCapabilityToEntity(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof PlayerEntity) {
            Sorcery.LOGGER.debug("POOPMODE");
            event.addCapability(new ResourceLocation(Sorcery.MODID, "research_capability"), new CapabilityProviderResearch());
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        if(!event.isWasDeath()) return;
        // get old and new player
        PlayerEntity oldPlayer = event.getOriginal();
        PlayerEntity newPlayer = event.getPlayer();

        // get capability interfaces
        Research oldPlayerResearchInterface = oldPlayer.getCapability(CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH).orElse(null);
        if(oldPlayerResearchInterface == null) return;
        Research newPlayerResearchInterface = newPlayer.getCapability(CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH).orElse(null);
        if(newPlayerResearchInterface == null) return;

        // copy old research data to new player
        newPlayerResearchInterface.setData(oldPlayerResearchInterface.getProtectedResearch(), oldPlayerResearchInterface.getInsanity());
    }
}
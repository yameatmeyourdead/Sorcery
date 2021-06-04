package com.yameatmeyourdead.sorcery.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;


public class SigilRegistryEvent extends Event {
    private PlayerEntity player;

    public SigilRegistryEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer()
    {
        return player;
    }

    // TODO: Implement registry
}

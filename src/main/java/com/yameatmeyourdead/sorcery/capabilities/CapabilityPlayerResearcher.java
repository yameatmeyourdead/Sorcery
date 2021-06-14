package com.yameatmeyourdead.sorcery.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityPlayerResearcher {
    @CapabilityInject(Researcher.class)
    public static Capability<Researcher> CAPABILITY_PLAYER_RESEARCHER = null;
    
    public static void register() {
        CapabilityManager.INSTANCE.register(Researcher.class, new Researcher.ResearchStorage(), Researcher::createDefaultInstance);
    }
}

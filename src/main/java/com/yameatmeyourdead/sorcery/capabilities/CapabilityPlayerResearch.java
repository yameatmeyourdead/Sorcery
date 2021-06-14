package com.yameatmeyourdead.sorcery.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityPlayerResearch {
    @CapabilityInject(Research.class)
    public static Capability<Research> CAPABILITY_PLAYER_RESEARCH = null;
    
    public static void register() {
        CapabilityManager.INSTANCE.register(Research.class, new Research.ResearchStorage(), Research::createDefaultInstance);
    }
}

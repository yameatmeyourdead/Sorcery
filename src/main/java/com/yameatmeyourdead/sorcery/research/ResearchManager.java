package com.yameatmeyourdead.sorcery.research;

import java.lang.IllegalStateException;
import java.util.Map;

import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearcher;
import com.yameatmeyourdead.sorcery.capabilities.Researcher;

import net.minecraft.entity.player.PlayerEntity;

public class ResearchManager {
    private static Map<String, ResearchBase> allValidResearch = null;

    public static boolean completeResearch(PlayerEntity player, String name) {
        if(!allValidResearch.containsKey(name)) throw new IllegalStateException("Attempted to complete unregistered Research. Did you remember to call ResearchManager#registerResearch ?");
        Researcher researcherInterface = player.getCapability(CapabilityPlayerResearcher.CAPABILITY_PLAYER_RESEARCHER).orElse(null);
        if(researcherInterface.getProtectedResearch().contains(name.toLowerCase())) {return false;}
        researcherInterface.addResearch(name.toLowerCase());
        return true;
    }

    public static void registerResearch(String name, ResearchBase research) {
        if(allValidResearch.containsKey(name)) throw new IllegalStateException("Attempted to register Research with name already registered");
        allValidResearch.put(name, research);
    }

    public static Map<String, ResearchBase> getValidResearch() {
        return allValidResearch;
    }
}
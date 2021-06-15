package com.yameatmeyourdead.sorcery.research;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearcher;
import com.yameatmeyourdead.sorcery.capabilities.Researcher;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
 
public class ResearchManager {
    private static LinkedHashMap<String, ResearchBase> allValidResearch = new LinkedHashMap<>();
    public static LinkedHashMap<String, ResearchCategory> allValidResearchCategories = new LinkedHashMap<>();


    public static void registerResearchManager(DeferredRegister<ResearchBase> allResearchBase, DeferredRegister<ResearchCategory> allResearchCategory) {
        for(RegistryObject<ResearchBase> research : allResearchBase.getEntries()) {
            allValidResearch.put(research.get().getName(), research.get());
        }
        for(RegistryObject<ResearchCategory> researchCategory : allResearchCategory.getEntries()) {
            allValidResearchCategories.put(researchCategory.get().key, researchCategory.get());
        }
        for(ResearchBase research : allValidResearch.values()) {
            addResearchToResearchCategory(research);
        }
    }

    // complete specific research
    public static ActionResultType completeResearch(PlayerEntity player, ResearchBase research) throws IllegalStateException {
        if(player == null || research == null) return ActionResultType.PASS;
        String name = research.getName();
        if(!allValidResearch.containsKey(name)) throw new IllegalStateException("Attempted to complete unregistered Research.");
        Researcher researcherInterface = player.getCapability(CapabilityPlayerResearcher.CAPABILITY_PLAYER_RESEARCHER).orElse(null);
        if(researcherInterface.getProtectedResearch().contains(name.toLowerCase())) {return ActionResultType.FAIL;}
        researcherInterface.addResearch(name.toLowerCase());
        return ActionResultType.SUCCESS;
    }


    public static ResearchBase getResearch(String name) {
        if(allValidResearch.containsKey(name))
            return allValidResearch.get(name);
        return null;
    }

    public static ResearchCategory getResearchCategory(String key) {
        if(allValidResearchCategories.containsKey(key))
            return allValidResearchCategories.get(key);
        return null;
    }

    public static String getCategoryName(String key) {
        return "sorcery.research_category." + key;
    }

    public static void addResearchToResearchCategory(ResearchBase research) {
        ResearchCategory researchCategory = allValidResearchCategories.get(research.category);
        // check if category exists
        if(researchCategory == null) {
            Sorcery.LOGGER.fatal("[Sorcery] Research Category [" + research.category + "] does not exist and thus Research [" + research.getName() + "] could not be added to it.");
            return;
        }
        // check if research exists in category already
        if(researchCategory.research.containsKey(research.name)) {
            Sorcery.LOGGER.fatal("[Sorcery] Research [" + research.getName() + "] could not be added to Research Category [" + research.category + "] due to pre-existing research with same key.");
            return;
        }

        //add research to relevant researchCategory
        researchCategory.research.put(research.name, research);

        // update display bounds
        if(research.getCol() < researchCategory.minDisplayColumn)
            researchCategory.minDisplayColumn = research.getCol();
        if(research.getCol() > researchCategory.maxDisplayColumn)
            researchCategory.maxDisplayColumn = research.getCol();
        if(research.getRow() < researchCategory.minDisplayRow)
            researchCategory.minDisplayRow = research.getRow();
        if(research.getRow() > researchCategory.maxDisplayRow)
            researchCategory.maxDisplayRow = research.getRow();
    }

    public static List<ResearchBase> getAutoUnlockResearch() {
        List<ResearchBase> toReturn = new LinkedList<>();
        for(ResearchBase research : allValidResearch.values()) {
            if(research.isAutoUnlock())
                toReturn.add(0, research);
        }
        return toReturn;
    }

    public static Map<String, ResearchBase> getValidResearch() {
        return allValidResearch;
    }

    public static LinkedHashMap<String, ResearchCategory> getAllValidResearchCategories() {
        return allValidResearchCategories;
    }
}
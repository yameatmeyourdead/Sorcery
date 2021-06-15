package com.yameatmeyourdead.sorcery.research;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ResearchCategory extends ForgeRegistryEntry<ResearchCategory> {
    public String key;
    public int minDisplayColumn;
    public int minDisplayRow;
    public int maxDisplayColumn;
    public int maxDisplayRow;
    public ResourceLocation icon;
    public ResourceLocation background;
    public Map<String, ResearchBase> research = new HashMap<>();

    public ResearchCategory(String key, ResourceLocation icon, ResourceLocation background) {
        this.key = key;
        this.icon = icon;
        this.background = background;
    }
}
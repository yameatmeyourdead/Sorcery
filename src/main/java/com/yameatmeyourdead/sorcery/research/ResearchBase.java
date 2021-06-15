package com.yameatmeyourdead.sorcery.research;

import com.yameatmeyourdead.sorcery.pseudoitem.Sigil;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ResearchBase extends ForgeRegistryEntry<ResearchBase> {
    // instance variables that all researches should contain data on
    protected final String name;
    protected final String category;
    protected final int insanity;
    protected String[] prerequisites;
    protected String[] hiddenPreequisites;
    protected final ItemStack icon;
    protected final ResourceLocation icon_resource;
    private final int row;
    private final int col;
    private final int difficulty;
    private boolean isHidden = false;
    private boolean isAutoUnlock = false;
    private boolean isVirtual = false;
    private ItemStack[] itemTriggers;
    private String[] entityTriggers;
    private Sigil[] aspectTriggers;
    private ResearchPage[] pages = null;

    /**
     * Use for custom Icons
     * @param name name of research
     * @param category category this research is under
     * @param insanity how insane this research makes you
     * @param row row on category where this is placed
     * @param col col on category where this is placed
     * @param difficulty how complex minigame should be
     * @param icon_resource ResourceLocation of icon png
     */
    public ResearchBase(String name, String category, int insanity, int row, int col, int difficulty, ResourceLocation icon_resource) {
        this.name = name;
        this.category = category;
        this.insanity = insanity;
        this.row = row;
        this.col = col;
        this.difficulty = difficulty;
        this.icon = null;
        this.icon_resource = icon_resource;
    }

    /**
     * Use when you want an ItemStack as an Icon
     * @param name name of research
     * @param category category this research is under
     * @param insanity how insane this research makes you
     * @param row row on category where this is placed
     * @param col col on category where this is placed
     * @param difficulty how complex minigame should be
     * @param icon ItemStack to use as icon
     */
    public ResearchBase(String name, String category, int insanity, int row, int col, int difficulty, ItemStack icon) {
        this.name = name;
        this.category = category;
        this.insanity = insanity;
        this.row = row;
        this.col = col;
        this.difficulty = difficulty;
        this.icon = icon;
        this.icon_resource = null;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getInsanity() {
        return insanity;
    }

    public String[] getPrerequisites() {
        return prerequisites;
    }

    public ResearchBase setPrerequisites(String[] prerequisites) {
        this.prerequisites = prerequisites;
        return this;
    }

    public String[] getHiddenPreequisites() {
        return hiddenPreequisites;
    }

    public ResearchBase setHiddenPreequisites(String[] hiddenPreequisites) {
        this.hiddenPreequisites = hiddenPreequisites;
        return this;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public ResourceLocation getIcon_resource() {
        return icon_resource;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public ResearchBase setHidden() {
        this.isHidden = true;
        return this;
    }

    public boolean isAutoUnlock() {
        return isAutoUnlock;
    }

    public ResearchBase setAutoUnlock() {
        this.isAutoUnlock = true;
        return this;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public ResearchBase setVirtual() {
        this.isVirtual = true;
        return this;
    }

    public ItemStack[] getItemTriggers() {
        return itemTriggers;
    }

    public ResearchBase setItemTriggers(ItemStack[] itemTriggers) {
        this.itemTriggers = itemTriggers;
        return this;
    }

    public String[] getEntityTriggers() {
        return entityTriggers;
    }

    public ResearchBase setEntityTriggers(String[] entityTriggers) {
        this.entityTriggers = entityTriggers;
        return this;
    }

    public Sigil[] getAspectTriggers() {
        return aspectTriggers;
    }

    public ResearchBase setAspectTriggers(Sigil[] aspectTriggers) {
        this.aspectTriggers = aspectTriggers;
        return this;
    }

    public ResourceLocation getIconResource() {
        return icon_resource;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ResearchPage[] getPages() {
        return pages;
    }

    public void setPages(ResearchPage[] pages) {
        this.pages = pages;
    }
}

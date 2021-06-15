package com.yameatmeyourdead.sorcery.research;

import com.yameatmeyourdead.sorcery.pseudoitem.Sigil;
import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipe;
import com.yameatmeyourdead.sorcery.recipe.ISorceryRecipe;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

public class ResearchPage {
    public static enum PageType {
        TEXT,
        IMAGE,
        CRUCIBLE_CRAFTING,
        ARCANE_CIRCLE_CRAFTING,
        SORCERERS_TABLE_CRAFTING,
        CRAFTING,
        TRANSMUTATION_CRAFTING,
        SIGILS,
        SMELTING;
        private PageType() {}
    }

    public PageType type = PageType.TEXT;
    public String text = null;
    public String research = null;
    public ResourceLocation image = null;
    public Object recipe = null;
    public ItemStack[] recipeOutput = null;
    public Sigil[] sigils = null;

    public ResearchPage(String research, String text) {
        this.type = PageType.TEXT;
        this.research = research;
        this.text = text;
    }

    public ResearchPage(IRecipe<?> recipe) {
        this.type = PageType.CRAFTING;
        this.recipe = recipe;
        if(!(recipe instanceof ISorceryRecipe))
            this.recipeOutput = new ItemStack[]{recipe.getResultItem()};
        else
            this.recipeOutput = ((ISorceryRecipe) recipe).getResultItems();
    }

    public ResearchPage(SorcerersTableRecipe recipe) {
        this.type = PageType.SORCERERS_TABLE_CRAFTING;
        this.recipe = recipe;
        this.recipeOutput = recipe.getResultItems();
    }

    public ResearchPage(ArcaneCircleRecipe recipe) {
        this.type = PageType.ARCANE_CIRCLE_CRAFTING;
        this.recipe = recipe;
        this.recipeOutput = recipe.getResultItems();
    }

    public ResearchPage(Sigil[] sigils) {
        this.sigils = sigils;
    }

    public String getText() {
        return (this.text == null ? "" : this.text);
    }
}

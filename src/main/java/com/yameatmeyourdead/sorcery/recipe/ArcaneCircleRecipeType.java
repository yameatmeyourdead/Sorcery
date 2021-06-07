package com.yameatmeyourdead.sorcery.recipe;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.crafting.IRecipeType;

public class ArcaneCircleRecipeType  implements IRecipeType<ArcaneCircleRecipe>{
    
    @Override
    public String toString() {
        return Sorcery.MODID + ":arcane_circle_recipe";
    }
}

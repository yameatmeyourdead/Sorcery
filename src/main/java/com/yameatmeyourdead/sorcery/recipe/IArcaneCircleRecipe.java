package com.yameatmeyourdead.sorcery.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;

public interface IArcaneCircleRecipe extends IRecipe<IInventory> {
    public int getLevel();
    public float getInstability();
}

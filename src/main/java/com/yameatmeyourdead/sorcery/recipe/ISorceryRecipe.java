package com.yameatmeyourdead.sorcery.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface ISorceryRecipe extends IRecipe<IInventory> {
    ItemStack[] getResultItems();
}

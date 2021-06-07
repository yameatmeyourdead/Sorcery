package com.yameatmeyourdead.sorcery.recipe;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.crafting.IRecipeType;

public class SorcerersTableRecipeType implements IRecipeType<SorcerersTableRecipe> {
	
	@Override
	public String toString() {
		return Sorcery.MODID + ":sorcerers_table_recipe";
	}
}

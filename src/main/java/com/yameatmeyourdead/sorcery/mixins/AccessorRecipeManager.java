/**
 * This code was yoiked from Vazkii's Botania
 * Link to github -> https://github.com/Vazkii/Botania
 */
package com.yameatmeyourdead.sorcery.mixins;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(RecipeManager.class)
public interface AccessorRecipeManager {
	@Invoker("getRecipes")
	<C extends IInventory, T extends IRecipe<C>> Map<ResourceLocation, IRecipe<C>> sorcery_getRecipes(IRecipeType<T> type);
}
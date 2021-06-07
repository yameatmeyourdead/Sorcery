package com.yameatmeyourdead.sorcery.tools;

import java.util.Collection;
import java.util.stream.Collectors;

import com.yameatmeyourdead.sorcery.Sorcery;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

public class SorceryJEIPlugin implements IModPlugin {
    public static final ResourceLocation PLUGIN_ID = new ResourceLocation(Sorcery.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }
    
    @SuppressWarnings("resource")
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        registration.addRecipes(getRecipes(manager, null), SorcerersTableRecipeCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new SorcerersTableRecipeCategory(helper));
    }

    private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
        return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
    }
}

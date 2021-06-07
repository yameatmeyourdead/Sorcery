package com.yameatmeyourdead.sorcery.tools;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipe;
import com.yameatmeyourdead.sorcery.setup.Registration;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.config.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class SorcerersTableRecipeCategory implements IRecipeCategory<SorcerersTableRecipe> {

    public static ResourceLocation ID = new ResourceLocation(Sorcery.MODID, ".sorcerers_table_recipe_category");

    private final IDrawable bg;
    private final IDrawable icon;

    public SorcerersTableRecipeCategory(IGuiHelper helper) {
        this.bg = helper.createDrawable(Constants.RECIPE_GUI_VANILLA, 0, 114, 82, 54);
        this.icon = helper.createDrawableIngredient(new ItemStack(Registration.SIGNOMICON.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends SorcerersTableRecipe> getRecipeClass() {
        return SorcerersTableRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category." + Sorcery.MODID + ".sorcerers_table_recipe").getString();
    }

    @Override
    public IDrawable getBackground() {
        return bg;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(SorcerersTableRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SorcerersTableRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 0, 0);
        itemStackGroup.init(0, false, 60, 18);

        itemStackGroup.set(ingredients);
    }
}

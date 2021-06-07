package com.yameatmeyourdead.sorcery.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.setup.Registration;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SorcerersTableRecipe implements IRecipe<IInventory> {

    public static final Serializer SERIALIZER = new Serializer();

    private final Ingredient input;
    private final ItemStack output;
    private final ResourceLocation id;

    public SorcerersTableRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SorcerersTableRecipe> {
        Serializer() {
            this.setRegistryName(new ResourceLocation(Sorcery.MODID, "sorcerers_table"));
        }

        @Override
        public SorcerersTableRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            final JsonElement inputE1 = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input") : JSONUtils.getAsJsonObject(json, "input");
            final Ingredient input = Ingredient.fromJson(inputE1);
            final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

            return new SorcerersTableRecipe(recipeId, input, output);
        }

        @Override
        public SorcerersTableRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            final Ingredient input = Ingredient.fromNetwork(buffer);
            final ItemStack output = buffer.readItem();

            return new SorcerersTableRecipe(recipeId, input, output); 
        }

        @Override
        public void toNetwork(PacketBuffer buffer, SorcerersTableRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeItem(recipe.output);
        }
    }

    public boolean isValid(ItemStack input) {
        return this.input.test(input);
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        return this.input.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(IInventory p_77572_1_) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int d1, int d2) {
        return d1 == 3 && d2 == 3;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> i = NonNullList.create();
        i.add(input);
        return i;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return Registration.SORCERERS_TABLE_RECIPE;
    }

    
}

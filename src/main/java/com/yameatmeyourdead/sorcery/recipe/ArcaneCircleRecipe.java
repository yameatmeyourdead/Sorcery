package com.yameatmeyourdead.sorcery.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.setup.Registration;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ArcaneCircleRecipe implements IArcaneCircleRecipe, ISorceryRecipe {
    public static final Serializer SERIALIZER = new Serializer();
    
    private NonNullList<Ingredient> components = NonNullList.create();
    private NonNullList<ItemStack> outputs = NonNullList.create();
    private final ResourceLocation id;
    private int level;
    private final float instability;
    public ArcaneCircleRecipe(ResourceLocation id, NonNullList<Ingredient> components, NonNullList<ItemStack> outputs, int level, float instability) {
        this.id = id;
        this.components = components;
        this.outputs = outputs;
        this.level = level;
        this.instability = instability;
    }

    @Override
	public boolean matches(IInventory inv, World worldIn) {
		return false;
	}
	
	@Override
	public ItemStack assemble(IInventory inv) {
		return this.outputs.get(0).copy();
	}
	
    /**
     * Use {@link #getResultItems getResultItems()} instead <p>
     * @return Dirt Item Stack in case you use this >:( you naughty naughty
     */
    @Deprecated
	@Override
	public ItemStack getResultItem() {
		return new ItemStack(Items.DIRT);
	}

    public ItemStack[] getResultItems() {
        return (ItemStack[]) outputs.toArray();
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
		return Registration.ARCANE_CIRCLE_RECIPE;
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(Registration.SIGNOMICON.get());
	}
	
    public int getLevel() {
        return this.level;
    }

    public float getInstability() {
        return this.instability;
    }

    // TODO: FIX THIS 
    // This kinda expensive tho
    public boolean isValid(List<ItemStack> items) {
        List<Ingredient> missingIngredients = new ArrayList<>(components);
        for (ItemStack item : items) {
            missingIngredients.remove((Object) item);
        }

        return missingIngredients.size() == 0;
    }
	
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<ArcaneCircleRecipe> {

		Serializer() {
			this.setRegistryName(Sorcery.MODID, "arcane_circle_recipe");
		}

		@Override
		public ArcaneCircleRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            // parse ingredients
            JsonArray inputs = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> ingredients = NonNullList.create();
            for(JsonElement e : inputs) {
                ingredients.add(Ingredient.fromJson(e));
            }

            // parse outputs
            JsonArray output = JSONUtils.getAsJsonArray(json, "outputs");
            NonNullList<ItemStack> outputs = NonNullList.create();
            for(int i = 0; i < output.size(); i++)
            {
                ItemStack temp = ShapedRecipe.itemFromJson(output.get(i).getAsJsonObject());
                outputs.add(temp);
            }

            // parse level and instability
            final int level = JSONUtils.getAsInt(json, "level");
            final float instability = JSONUtils.getAsFloat(json, "instability");
			return new ArcaneCircleRecipe(recipeId, ingredients, outputs, level, instability);
		}

		@Override
		public ArcaneCircleRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> ingredients = NonNullList.create();
            int len = buffer.readVarInt();
            for (int i = 0; i < len; i++) {
                ingredients.add(Ingredient.fromNetwork(buffer));
            }
            len = 0;
            len = buffer.readVarInt();
            NonNullList<ItemStack> outputs = NonNullList.create();
            for (int i = 0; i < len; i++) {
                outputs.add(buffer.readItem());
            }
            final int level = buffer.readInt();
            final float instability = buffer.readFloat();
			return new ArcaneCircleRecipe(recipeId, ingredients, outputs, level, instability);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, ArcaneCircleRecipe recipe) {
            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient input : recipe.getIngredients()) {
                input.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem());
            buffer.writeInt(recipe.getLevel());
            buffer.writeFloat(recipe.getInstability());
			return;
		}
	}
}

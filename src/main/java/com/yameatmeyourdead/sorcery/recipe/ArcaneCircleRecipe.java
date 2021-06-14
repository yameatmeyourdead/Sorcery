package com.yameatmeyourdead.sorcery.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.setup.Registration;

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
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ArcaneCircleRecipe implements IArcaneCircleRecipe, ISorceryRecipe {
    public static final Serializer SERIALIZER = new Serializer();
    
    private NonNullList<Ingredient> components = NonNullList.create();
    private NonNullList<ItemStack> outputs = NonNullList.create();
    private final ResourceLocation id;
    private final ResourceLocation texture;
    private int level;
    private final float instability;
    public ArcaneCircleRecipe(ResourceLocation id, ResourceLocation texture, NonNullList<Ingredient> components, NonNullList<ItemStack> outputs, int level, float instability) {
        this.id = id;
        this.texture = texture;
        this.components = components;
        this.outputs = outputs;
        this.level = level;
        this.instability = instability;
    }

    @Override
	public boolean matches(IInventory inv, World worldIn) {
        // Copy the components array (we will be modifying)
        List<Ingredient> missingItems = new ArrayList<>(components);
        // was an item removed
        boolean removed = false;
        // loop through container's slots
        for (int i = 0; i < inv.getContainerSize(); i++) {
            removed = false;
            // get each item
            ItemStack input = inv.getItem(i);
            if(input.isEmpty()) continue; // don't get empty air slots
            for(int j = 0; j < missingItems.size(); j++) { // find the appropriate ingredient in missing items
                if(missingItems.get(j).test(input)) {
                    missingItems.remove(j); // remove it from further testing slight optimization still O(n^2)
                    removed = true;
                    break;
                }
            }
            // if item was in arcane circle but is not a part of recipe, recipe obv doesnt work
            if(!removed)
                return false;
        }

        return missingItems.isEmpty();
	}

    public boolean matches(ItemStack[] in) {
        // check if inputs match
        List<Ingredient> missingItems = new ArrayList<>(components);
        boolean removed = false;
        for(ItemStack item : in) {
            removed = false;
            for(int i = 0; i < missingItems.size(); i++) {
                if(missingItems.get(i).test(item)){
                    missingItems.remove(i);
                    removed = true;
                    break;
                }
            }
            if(!removed)
                return false;
        }

        return true;
    }
	
	@Override
	public ItemStack assemble(IInventory inv) {
		return this.outputs.get(0).copy();
	}
	
    public ResourceLocation getTexture() {
        return this.texture;
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
        ItemStack[] toReturn = new ItemStack[outputs.size()];
        for(int i = 0; i < outputs.size(); i++) {
            toReturn[i] = outputs.get(i);
        }
        return toReturn;
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
            final ResourceLocation texture = new ResourceLocation("sorcery", "textures/arcanecircles/" + JSONUtils.getAsString(json, "texture"));
            final int level = JSONUtils.getAsInt(json, "level");
            final float instability = JSONUtils.getAsFloat(json, "instability");
			return new ArcaneCircleRecipe(recipeId, texture, ingredients, outputs, level, instability);
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
            final ResourceLocation texture = new ResourceLocation("sorcery", "textures/arcanecircles/" + buffer.readUtf());
            final int level = buffer.readInt();
            final float instability = buffer.readFloat();
			return new ArcaneCircleRecipe(recipeId, texture, ingredients, outputs, level, instability);
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

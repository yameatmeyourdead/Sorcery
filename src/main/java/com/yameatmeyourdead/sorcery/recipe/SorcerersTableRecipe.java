package com.yameatmeyourdead.sorcery.recipe;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.setup.Registration;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SorcerersTableRecipe implements IRecipe<IInventory>, ISorceryRecipe {

	public static final Serializer SERIALIZER = new Serializer();

	private final Ingredient input;
	private final ItemStack output;
	private final Block block;
	private final ResourceLocation id;

	public SorcerersTableRecipe(ResourceLocation id, Ingredient input, ItemStack output, Block block) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.block = block;
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.input.test(inv.getItem(0));
	}
	
	@Override
	public ItemStack assemble(IInventory inv) {
		return this.output.copy();
	}
	
	@Deprecated
	@Override
	public ItemStack getResultItem() {
		return this.output;
	}

	@Override
	public ItemStack[] getResultItems() {
		return new ItemStack[]{this.output};
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
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(Registration.SIGNOMICON.get());
	}
	
	public boolean isValid(ItemStack input, Block block) {
		return this.input.test(input) && block == this.block;
	}
	
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<SorcerersTableRecipe> {

		Serializer() {
			this.setRegistryName(Sorcery.MODID, "sorcerers_table_recipe");
		}

		@Override
		public SorcerersTableRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input")
					: JSONUtils.getAsJsonObject(json, "input");
			final Ingredient input = Ingredient.fromJson(inputEl);

			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

			final Block block = ForgeRegistries.BLOCKS
					.getValue(new ResourceLocation(JSONUtils.getAsString(json, "block")));

			if (block == null) {
				throw new IllegalStateException("Block does not exist.");
			}

			return new SorcerersTableRecipe(recipeId, input, output, block);
		}

		@Override
		public SorcerersTableRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.fromNetwork(buffer);
			final ItemStack output = buffer.readItem();
			final Block block = ForgeRegistries.BLOCKS.getValue(buffer.readResourceLocation());
			
			if (block == null) {
				throw new IllegalStateException("Block does not exist.");
			}

			return new SorcerersTableRecipe(recipeId, input, output, block);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, SorcerersTableRecipe recipe) {
			recipe.input.toNetwork(buffer);
			buffer.writeItem(recipe.output);
			buffer.writeResourceLocation(recipe.block.getRegistryName());
		}
	}
}

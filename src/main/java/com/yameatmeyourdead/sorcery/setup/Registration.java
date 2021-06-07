package com.yameatmeyourdead.sorcery.setup;

import static com.yameatmeyourdead.sorcery.Sorcery.MODID;

import java.util.Collection;
import java.util.Map;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.blocks.*;
import com.yameatmeyourdead.sorcery.items.RunicInscriber;
import com.yameatmeyourdead.sorcery.items.Signomicon;
import com.yameatmeyourdead.sorcery.mixins.AccessorRecipeManager;
import com.yameatmeyourdead.sorcery.pseudoitem.Sigil;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipe;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipeType;

import org.spongepowered.asm.launch.platform.container.ContainerHandleModLauncher.Resource;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryBuilder;

@EventBusSubscriber(modid=Sorcery.MODID, bus = Bus.MOD)
public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    // private static final DeferredRegister<Block> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, MODID);
    
    private static final DeferredRegister<Sigil> SIGILS = DeferredRegister.create(Sigil.class, MODID);

    public static void init(final IEventBus bussy) {
        BLOCKS.register(bussy);
        ITEMS.register(bussy);
        TILES.register(bussy);
        CONTAINERS.register(bussy);
        ENTITIES.register(bussy);
        // DIMENSIONS.register(bussy);
        SIGILS.register(bussy);
    }

    // Register Blocks
    public static final RegistryObject<Crucible> CRUCIBLE = BLOCKS.register("crucible", Crucible::new);

    // Register Items
    public static final RegistryObject<Signomicon> SIGNOMICON = ITEMS.register("signomicon", Signomicon::new);
    public static final RegistryObject<RunicInscriber> RUNIC_INSCRIBER = ITEMS.register("runic_inscriber", RunicInscriber::new);

    // Register Tiles

    // Register Containers

    // Register Entities

    // Register Dimensions (changed)

    // Create Recipe Types
    public static final IRecipeType<SorcerersTableRecipe> SORCERERS_TABLE_RECIPE = new SorcerersTableRecipeType();

    // Register Sigils (wowee thats a lot)
    public static final RegistryObject<Sigil> AIR_SIGIL = SIGILS.register("test", () -> Sigil.AIR);

    // register recipes
    public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
        registerRecipe(event, SORCERERS_TABLE_RECIPE, SorcerersTableRecipe.SERIALIZER);
    }

    // helper method for registering recipes
    private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type, IRecipeSerializer<?> serializer) {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
        event.getRegistry().register(serializer);
    }
    
    // Access transform recipe manager recipe map and get recipes that match type
    public static Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> type, World world) {
        final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper.getPrivateValue(RecipeManager.class, world.getRecipeManager(), "recipes");
        return recipes.get(type);
    }

    // Create Block Items
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(Sorcery.ITEM_GROUP)).setRegistryName(block.getRegistryName()));
        });
    }

    // Create custom deferred registers here
    @SubscribeEvent
    public static void registerRegistries(final RegistryEvent.NewRegistry event) {
        new RegistryBuilder<Sigil>().setType(Sigil.class).setName(new ResourceLocation(MODID, "sigil_registry")).create();
    }

    // debug output on custom registry creation
    @SubscribeEvent
    public static void onSigilRegistry(final RegistryEvent.Register<Sigil> event) {
        event.getRegistry().getValues().forEach(element -> Sorcery.LOGGER.debug("Sucessfully Registered Sigil: " + element.getName()));
    }
}

package com.yameatmeyourdead.sorcery.setup;

import static com.yameatmeyourdead.sorcery.Sorcery.MODID;

import java.util.Map;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.blocks.*;
import com.yameatmeyourdead.sorcery.items.ArcaneDust;
import com.yameatmeyourdead.sorcery.items.RunicInscriber;
import com.yameatmeyourdead.sorcery.items.Signomicon;
import com.yameatmeyourdead.sorcery.pseudoitem.Sigil;
import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipeType;
import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipe;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipe;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipeType;
import com.yameatmeyourdead.sorcery.te.ArcaneCircleTileEntity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
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
    public static final RegistryObject<ArcaneCircle> ARCANE_CIRCLE = BLOCKS.register("arcane_circle", ArcaneCircle::new);
    public static final RegistryObject<SorcerersTable> SORCERERS_TABLE = BLOCKS.register("sorcerers_table", SorcerersTable::new);

    // Register Items
    public static final RegistryObject<Signomicon> SIGNOMICON = ITEMS.register("signomicon", Signomicon::new);
    public static final RegistryObject<RunicInscriber> RUNIC_INSCRIBER = ITEMS.register("runic_inscriber", RunicInscriber::new);
    public static final RegistryObject<ArcaneDust> ARCANE_DUST = ITEMS.register("arcane_dust", ArcaneDust::new);

    // Register Tiles
    public static final RegistryObject<TileEntityType<ArcaneCircleTileEntity>> ARCANE_CIRCLE_ENTITY_TYPE = TILES.register("arcane_circle", () -> TileEntityType.Builder.of(ArcaneCircleTileEntity::new, ARCANE_CIRCLE.get()).build(null));
    
    
    // Register Containers

    // Register Entities

    // Register Dimensions (changed)

    // Create Recipe Types
    // public static final IRecipeType<ExampleRecipe> EXAMPLE_RECIPE = new ExampleRecipeType();
    public static final IRecipeType<SorcerersTableRecipe> SORCERERS_TABLE_RECIPE = new SorcerersTableRecipeType();
    public static final IRecipeType<ArcaneCircleRecipe> ARCANE_CIRCLE_RECIPE = new ArcaneCircleRecipeType();

    // Register Sigils (wowee thats a lot)
    public static final RegistryObject<Sigil> AIR_SIGIL = SIGILS.register("test", () -> Sigil.AIR);

    // register recipes
    public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
        // registerRecipe(event, EXAMPLE_RECIPE, ExampleRecipe.SERIALIZER);
        registerRecipe(event, SORCERERS_TABLE_RECIPE, SorcerersTableRecipe.SERIALIZER);
        registerRecipe(event, ARCANE_CIRCLE_RECIPE, ArcaneCircleRecipe.SERIALIZER);
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
        
        // for(RegistryObject<Block> block : Registration.BLOCKS.getEntries()) {
        //     for(RegistryObject<Item> item : Registration.ITEMS.getEntries()) {
        //         if(item.get().getRegistryName() == block.get().getRegistryName()) continue;
        //     }
        // }
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

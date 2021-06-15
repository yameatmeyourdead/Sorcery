package com.yameatmeyourdead.sorcery.setup;

import static com.yameatmeyourdead.sorcery.Sorcery.MODID;

import java.util.Map;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.blocks.ArcaneCircle;
import com.yameatmeyourdead.sorcery.blocks.Crucible;
import com.yameatmeyourdead.sorcery.blocks.SorcerersTable;
import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearcher;
import com.yameatmeyourdead.sorcery.items.ArcaneDust;
import com.yameatmeyourdead.sorcery.items.Parchment;
import com.yameatmeyourdead.sorcery.items.ResearchNotes;
import com.yameatmeyourdead.sorcery.items.RollOfParchment;
import com.yameatmeyourdead.sorcery.items.RunicInscriber;
import com.yameatmeyourdead.sorcery.items.Signomicon;
import com.yameatmeyourdead.sorcery.pseudoitem.Sigil;
import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipe;
import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipeType;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipe;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipeType;
import com.yameatmeyourdead.sorcery.research.ResearchBase;
import com.yameatmeyourdead.sorcery.research.ResearchCategory;
import com.yameatmeyourdead.sorcery.research.ResearchManager;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
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
    private static final DeferredRegister<ResearchBase> ALL_VALID_RESEARCH = DeferredRegister.create(ResearchBase.class, MODID);
    private static final DeferredRegister<ResearchCategory> ALL_VALID_RESEARCH_CATEGORIES = DeferredRegister.create(ResearchCategory.class, MODID);

    /**
     * Register mod objects
     * @param bussy ModEventBus
     */
    public static void init(final IEventBus bussy) {
        BLOCKS.register(bussy);
        ITEMS.register(bussy);
        TILES.register(bussy);
        CONTAINERS.register(bussy);
        ENTITIES.register(bussy);
        // DIMENSIONS.register(bussy);
        SIGILS.register(bussy);
        ALL_VALID_RESEARCH.register(bussy);
        ALL_VALID_RESEARCH_CATEGORIES.register(bussy);
    }

    // Register Blocks
    public static final RegistryObject<Crucible> CRUCIBLE = BLOCKS.register("crucible", Crucible::new);
    public static final RegistryObject<ArcaneCircle> ARCANE_CIRCLE = BLOCKS.register("arcane_circle", ArcaneCircle::new);
    public static final RegistryObject<SorcerersTable> SORCERERS_TABLE = BLOCKS.register("sorcerers_table", SorcerersTable::new);



    // Register Items
    public static final RegistryObject<Signomicon> SIGNOMICON = ITEMS.register("signomicon", Signomicon::new);
    public static final RegistryObject<RunicInscriber> RUNIC_INSCRIBER = ITEMS.register("runic_inscriber", RunicInscriber::new);
    public static final RegistryObject<ArcaneDust> ARCANE_DUST = ITEMS.register("arcane_dust", ArcaneDust::new);
    public static final RegistryObject<RollOfParchment> PARCHMENT_ROLL = ITEMS.register("parchment_roll", RollOfParchment::new);
    public static final RegistryObject<Parchment> PARCHMENT = ITEMS.register("parchment", Parchment::new);
    public static final RegistryObject<ResearchNotes> RESEARCH_NOTES = ITEMS.register("research_notes", ResearchNotes::new);



    // Register Tiles
    public static final RegistryObject<TileEntityType<ArcaneCircleTileEntity>> ARCANE_CIRCLE_ENTITY_TYPE = TILES.register("arcane_circle", () -> TileEntityType.Builder.of(ArcaneCircleTileEntity::new, ARCANE_CIRCLE.get()).build(null));
    


    // Register Containers



    // Register Entities



    // Register Dimensions (changed)



    // Register Capabilities
    public static void registerCapabilities() {
        CapabilityPlayerResearcher.register();
    }



    // Register Sigils (wowee thats a lot)
    public static final RegistryObject<Sigil> AIR_SIGIL = SIGILS.register("air_sigil", () -> Sigil.AIR);



    // Register Researches
    public static void registerResearchManager() {
        ResearchManager.registerResearchManager(ALL_VALID_RESEARCH, ALL_VALID_RESEARCH_CATEGORIES);
    }
    public static final RegistryObject<ResearchBase> RESEARCH_INTRO = ALL_VALID_RESEARCH.register("intro_research", () -> new ResearchBase("intro_research", "sorcery", 0, 0, 0, 0, new ResourceLocation(MODID, "textures/items/signomicon.png")).setAutoUnlock());
    public static final RegistryObject<ResearchBase> RESEARCH_INTRO_RUNIC_MAGIC = ALL_VALID_RESEARCH.register("intro_runic_magic", () -> new ResearchBase("intro_runic_magic", "runic_magic", 0, 0, 0, 0, new ResourceLocation(MODID, "textures/items/runic_inscriber.png")).setAutoUnlock());
    public static final RegistryObject<ResearchBase> RESEARCH_INTRO_DEMONOLOGY = ALL_VALID_RESEARCH.register("intro_demonology", () -> new ResearchBase("intro_demonology", "demonology", 0, 0, 0, 0, new ResourceLocation(MODID, "textures/research/demonology/pentagram.png")).setAutoUnlock());
    public static final RegistryObject<ResearchBase> RESERACH_INTRO_ALCHEMY = ALL_VALID_RESEARCH.register("intro_alchemy", () -> new ResearchBase("intro_alchemy", "alchemy", 0, 0, 0, 0, new ResourceLocation(MODID, "textures/research/alchemy/alchemy.png")));

    // Register Research Categories
    public static final RegistryObject<ResearchCategory> RESEARCH_CATEGORY_BASE = ALL_VALID_RESEARCH_CATEGORIES.register("sorcery", () -> new ResearchCategory("base", new ResourceLocation(MODID, "textures/items/signomicon.png"), new ResourceLocation(MODID, "textures/research/base.png")));
    public static final RegistryObject<ResearchCategory> RESEARCH_CATEGORY_DEMONOLOGY = ALL_VALID_RESEARCH_CATEGORIES.register("demonology", () -> new ResearchCategory("demonology", new ResourceLocation(MODID, "textures/research/demonology/pentagram.png"), new ResourceLocation(MODID, "textures/research/demonology/demonology.png")));
    public static final RegistryObject<ResearchCategory> RESEARCH_CATEGORY_RUNIC_MAGIC = ALL_VALID_RESEARCH_CATEGORIES.register("runic_magic", () -> new ResearchCategory("runic_magic", new ResourceLocation(MODID, "textures/research/runicmagic/dummyrune.png"), new ResourceLocation(MODID, "textures/research/runicmagic/runicmagic.png")));
    public static final RegistryObject<ResearchCategory> RESEARCH_CATEGORY_ALCHEMY = ALL_VALID_RESEARCH_CATEGORIES.register("alchemy", () -> new ResearchCategory("alchemy", new ResourceLocation(MODID, "textures/research/alchemy/alchemy.png"), new ResourceLocation(MODID, "textures/research/alchemy/alchemy.png")));

    // Create Recipe Types
    public static final IRecipeType<SorcerersTableRecipe> SORCERERS_TABLE_RECIPE = new SorcerersTableRecipeType();
    public static final IRecipeType<ArcaneCircleRecipe> ARCANE_CIRCLE_RECIPE = new ArcaneCircleRecipeType();
    // public static final IRecipeType<CrucibleRecipe> CRUCIBLE_RECIPE = new CrucibleRecipeType();

    // register recipes
    public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
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
    // TODO: Change implementation to allow for exclusions of some blocks (e.g. Arcane Circle Base needs no item)
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
        new RegistryBuilder<ResearchBase>().setType(ResearchBase.class).setName(new ResourceLocation(MODID, "research_registry")).create();
        new RegistryBuilder<ResearchCategory>().setType(ResearchCategory.class).setName(new ResourceLocation(MODID, "research_category_registry")).create();
    }

    // debug output on custom registry creation
    @SubscribeEvent
    public static void onSigilRegistry(final RegistryEvent.Register<Sigil> event) {
        event.getRegistry().getValues().forEach(element -> Sorcery.LOGGER.debug("Sucessfully Registered Sigil: " + element.getName()));
    }
    @SubscribeEvent
    public static void onResearchRegistry(final RegistryEvent.Register<ResearchBase> event) {
        event.getRegistry().getValues().forEach(element -> Sorcery.LOGGER.debug("Successfully Registered Research: " + element.getName()));
    }
    @SubscribeEvent
    public static void onResearchCategoryRegistry(final RegistryEvent.Register<ResearchCategory> event) {
        event.getRegistry().getValues().forEach(e -> Sorcery.LOGGER.debug("Successfullly Registered Research Category" + e.key));
    }
}
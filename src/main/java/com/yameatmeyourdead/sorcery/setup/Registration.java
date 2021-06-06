package com.yameatmeyourdead.sorcery.setup;

import static com.yameatmeyourdead.sorcery.Sorcery.MODID;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.blocks.*;
import com.yameatmeyourdead.sorcery.items.RunicInscriber;
import com.yameatmeyourdead.sorcery.items.Signomicon;
import com.yameatmeyourdead.sorcery.pseudoitem.Sigil;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
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

    @SubscribeEvent
    public static void registerRegistries(final RegistryEvent.NewRegistry event) {
        new RegistryBuilder<Sigil>().setType(Sigil.class).setName(new ResourceLocation(MODID, "sigil_registry")).create();
    }

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


    // Register Sigils (wowee thats a lot)
    public static final RegistryObject<Sigil> AIR_SIGIL = SIGILS.register("test", () -> Sigil.AIR);

    // Create Block Items
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(Sorcery.ITEM_GROUP)).setRegistryName(block.getRegistryName()));
        });
    }

    @SubscribeEvent
    public static void onSigilRegistry(final RegistryEvent.Register<Sigil> event) {
        event.getRegistry().getValues().forEach(element -> Sorcery.LOGGER.debug("Sucessfully Registered Sigil: " + element.getName()));
    }
}

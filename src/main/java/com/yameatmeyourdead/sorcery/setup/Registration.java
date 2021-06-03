package com.yameatmeyourdead.sorcery.setup;

import static com.yameatmeyourdead.sorcery.Sorcery.MODID;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.blocks.*;
import com.yameatmeyourdead.sorcery.items.Signomicon;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid=Sorcery.MODID, bus = Bus.MOD)
public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    // private static final DeferredRegister<Block> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        // DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Register Blocks
    public static final RegistryObject<FirstBlock> FIRSTBLOCK = BLOCKS.register("firstblock", FirstBlock::new);

    // Register Items
    public static final RegistryObject<Signomicon> SIGNOMICON = ITEMS.register("signomicon", Signomicon::new);

    // Register Tiles

    // Register Containers

    // Register Entities

    // Register Dimensions (changed)

    // Create Block Items
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
    	Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(Sorcery.ITEM_GROUP)).setRegistryName(block.getRegistryName()));
        });
    }
}

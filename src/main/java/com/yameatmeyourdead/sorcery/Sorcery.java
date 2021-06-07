package com.yameatmeyourdead.sorcery;

import com.yameatmeyourdead.sorcery.setup.Registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Sorcery.MODID)
@Mod.EventBusSubscriber(modid = Sorcery.MODID, bus = Bus.MOD)
public class Sorcery
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "sorcery";
    
    public Sorcery() 
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addGenericListener(IRecipeSerializer.class, Registration::registerRecipes);
        
        // Register mod objects
        Registration.init(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
        
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
    	
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
    	LOGGER.debug("Hello from load complete");
    }
    
    public static final SorceryItemGroup ITEM_GROUP = new SorceryItemGroup("sorcery");
    public static class SorceryItemGroup extends ItemGroup
    {
    	private SorceryItemGroup(String label)
    	{
    		super(label);
    	}

    	@Override
    	public ItemStack makeIcon()
    	{
            // return new ItemStack(Blocks.DIRT);
    		return new ItemStack(Registration.SIGNOMICON.get());
    	}
    }
}
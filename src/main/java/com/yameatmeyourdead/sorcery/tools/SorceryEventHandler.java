package com.yameatmeyourdead.sorcery.tools;

import java.util.ArrayList;
import java.util.List;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipe;
import com.yameatmeyourdead.sorcery.setup.Registration;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(modid = Sorcery.MODID, bus = Bus.FORGE)
public class SorceryEventHandler {

    @SubscribeEvent
    public static void onPlayerOpenContainer(PlayerContainerEvent.Open event) {
        System.out.println("Opened Chest");
        // get container's content
        Container container = event.getPlayer().containerMenu;
        List<ItemStack> contains = new ArrayList<ItemStack>();
        for (Slot s : container.slots) {
            if(s.getItem().equals(new ItemStack(Items.AIR))) continue;
            contains.add(s.getItem());
        }

        // check if it matches recipe
        for (IRecipe<?> recipe : Registration.getRecipes(Registration.SORCERERS_TABLE_RECIPE, event.getPlayer().level).values()) {
            if(recipe == null)
                continue;
            if(!(recipe instanceof ArcaneCircleRecipe)) continue;
            final ArcaneCircleRecipe STR = (ArcaneCircleRecipe) recipe;
            if(STR.isValid(contains)) {
                ItemStack[] itemsToAdd = STR.getResultItems();
                for(ItemStack item : itemsToAdd) {
                    ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), item.copy());    
                }
                break;
            }
        }
    }
}

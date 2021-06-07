package com.yameatmeyourdead.sorcery.tools;

import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.recipe.SorcerersTableRecipe;
import com.yameatmeyourdead.sorcery.setup.Registration;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(modid = Sorcery.MODID, bus = Bus.FORGE)
public class SorceryEventHandler {
    @SubscribeEvent
    public static void onPlayerToss(ItemTossEvent event) {
        PlayerEntity player = event.getPlayer();
        World world = player.level;
        ItemStack item = event.getEntityItem().getItem();

        for (IRecipe<?> recipe : Registration.getRecipes(Registration.SORCERERS_TABLE_RECIPE, world).values()) {
            final SorcerersTableRecipe STR = (SorcerersTableRecipe) recipe;
            if(STR.isValid(item)) {
                ItemHandlerHelper.giveItemToPlayer(player, recipe.getResultItem().copy());
                event.getEntity().remove();
            }
        }
    }
}

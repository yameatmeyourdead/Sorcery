package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Signomicon extends Item {

    public Signomicon() {
        super(new Item.Properties().stacksTo(1).fireResistant().tab(Sorcery.ITEM_GROUP));
    }
    
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.openItemGui(player.getItemInHand(hand), hand);
        return ActionResult.sidedSuccess(player.getItemInHand(hand), world.isClientSide);
    }
}